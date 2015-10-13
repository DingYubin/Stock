package ruifu.com.shares.biz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;

import ruifu.com.shares.Global;
import ruifu.com.shares.entity.CMsgHead;
import ruifusoft.protocol.User.ThirdPartyLoginRequest;
import ruifusoft.protocol.User.ThirdPartyLoginResponse;

/**
 * Created by Administrator on 2015/10/12.
 */
public class ThirdAuthorizationBiz{

    private final static String TAG = "ThirdAuthorizationBiz";
    CMsgHead head;
    Socket socket = null;
    private Context context;
    private SharedPreferences sp;

    private String userid;
    private String info;
    private String third_author;
    private long loginTime;

    private boolean new_user;
    private int user_id;
    private String session;

    public ThirdAuthorizationBiz(Context context) {
        super();
        this.context = context;
        sp = context.getSharedPreferences("users", Activity.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        info = sp.getString("info","");
        third_author = sp.getString("third_author","");

        Log.i(TAG,"userid : " + userid + ", info　："+info);
    }

    /**
     * 通过第三方端执行远程登录
     *
     * @return -1 网络连接失败请重试
     * 			0 登录成功
     * 			1 用户名或密码错误
     * @throws Exception
     */
    public int login() {
        //body
        int result = Global.FAIL;
        loginTime = System.currentTimeMillis();
//        ThirdPartyLoginRequest body = ThirdPartyLoginRequest.newBuilder().
//                setPlatformType("weixin").setPlatformUid("ot4TWwYsXmDRStGfsYAJBtrCl1B8").
//                setExtraInfo("").setLoginTime(loginTime).build();

        Log.i(TAG, "unionid :--------------------------------------------------> " + userid);
        ThirdPartyLoginRequest body = ThirdPartyLoginRequest.newBuilder().
                setPlatformType(third_author).setPlatformUid(userid).
                setExtraInfo(info).setLoginTime(loginTime).build();

        int length = 24 + body.getSerializedSize();

        head = new CMsgHead(length, 1, 100013, 0, 0);
        //向服务器发送信息
        byte[] sendBytes = head.toBytes();
//        for (byte b: sendBytes)
//        {
//            System.out.printf("%02x", b);
//        }
//        System.out.println();
        try {
            Log.i(TAG, "2------------------11111------------------" + new Date(System.currentTimeMillis()));

            // 链接服务器 并设置连接超时为5秒
//				socket = new Socket();
//				socket.connect(new InetSocketAddress("10.0.0.221", 30001), 5000);
            socket = new Socket("10.0.0.221", 30001);
            //获取输出流
            OutputStream os = socket.getOutputStream();
            os.write(sendBytes);
            body.writeTo(socket.getOutputStream());
            os.flush();

            //获取输入流
            InputStream is = socket.getInputStream();

            byte[] receiveBytes = new byte[24];
            is.read(receiveBytes);
            head.fromBytes(receiveBytes);
//            for (byte b: receiveBytes)
//            {
//                System.out.printf("%02x", b);
//
//            }
//            System.out.println();

            if (head.validate() && head.getEvent() == 100014) {
                Log.i(TAG, "验证成功 ----------------->>>>" + new Date(System.currentTimeMillis()));
                receiveBytes = new byte[head.getLength() - 24];
                //获取服务器值
                is.read(receiveBytes);

                //获取状态码
                result = ThirdPartyLoginResponse.parseFrom(receiveBytes).getStatus();

                /*
                    如果状态码
                 */
                if (result == Global.SUCCESS) {
                    new_user = ThirdPartyLoginResponse.parseFrom(receiveBytes).getNewUser();
                    user_id = ThirdPartyLoginResponse.parseFrom(receiveBytes).getUserId();
                    session = ThirdPartyLoginResponse.parseFrom(receiveBytes).getSession();

                    sp.edit().putInt("status", result).putBoolean("new_user", new_user).putInt("user_id", user_id)
                            .putString("session", session).commit();
                    //结果码
                }
                Log.i(TAG, "status = " + result + ", new_user = " + new_user + ", user_id = " + user_id + ", session = " + session);

            } else {
                result = Global.FAIL;
                Log.i(TAG, "登录出错" + new Date(System.currentTimeMillis()));
            }

            Log.i(TAG, head.toString());
            System.out.println(head.getFlag() + "0xEF");

            Log.i(TAG, "发送成功head------------------发送成功head------------------");
            Log.i(TAG, "发送成功head------------------发送成功head------------------");
            //发送消息  修改UI现场中的组件
            is.close();
            os.close();
            socket.close();
        } catch (SocketTimeoutException aa) {
            //连接超时 在UI界面显示消息
            result = Global.NET_PROBLEM;
        } catch (IOException e) {
            e.printStackTrace();
            result = Global.NET_PROBLEM;
        }
        return result;
    }
}
