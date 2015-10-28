package ruifu.com.shares.biz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.protobuf.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

import ruifu.com.shares.Global;
import ruifu.com.shares.entity.CMsgHead;
import ruifusoft.protocol.trade.Trade;

/**
 * Created by Administrator on 2015/10/27.
 */
public class TransactionBiz {

    private final static String TAG = "TransactionBiz";
    private Context context;
    private SharedPreferences sp;
    private String msg;
    private String user_id;
    private String use_trade_passwd;
    private String new_user_login;
    private String acc_num;
    private String cucy_code;
    private String acc_status;
    private String session_id;

    CMsgHead head;

    int cmd_code = Global.TRADE_USERLOGON_REQ;

    public TransactionBiz(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("transaction_login", Activity.MODE_PRIVATE);

    }

    private Message buildReq(int cmd_code,String loginname, String password) {
        com.google.protobuf.Message pb_req = null;
        if (Global.TRADE_USERLOGON_REQ == cmd_code) {
            pb_req = buildUserLoginReq(loginname,password);
        }
        return pb_req;
    }

    /*
    请求登录
     */
    private Message buildUserLoginReq(String loginname, String password) {
        Trade.UserLoginReq.User login_user = Trade.UserLoginReq.User
                .newBuilder().setUserId(loginname).setPassword(password)
                .setPasswordEx("F~2~P || F~5~W || L~3~D")
                .setPasswordExtended("ABCD12345678").build();
        Trade.UserLoginReq.Channel login_channel = Trade.UserLoginReq.Channel
                .newBuilder().setChannelType("V").build();
        Trade.UserLoginReq login_req = Trade.UserLoginReq.newBuilder()
                .setUser(login_user).setChannel(login_channel).build();
        return login_req;
    }

    /*
    登录响应
     */
//    private void parseResponse(int cmd_code, byte[] pb_buf) {
//        com.google.protobuf.Message pb_response = null;
//        int a = 0;
//        try {
//            if (Global.TRADE_USERLOGON_RESP == cmd_code) {
//                pb_response = Trade.UserLoginResp.parseFrom(pb_buf);
//                a = Trade.UserLoginResp.parseFrom(pb_buf).getStatusInfo().getStatus().getNumber();
//            }
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
//
//        if (pb_response != null) {
//            System.out.println("返回的值： ----》  " + pb_response.toString()+ "!!!!!!!!!!!!!-----s = "+a);
//        }
//    }

    public int login(String loginname, String password) {
        int result = Global.FAILED;
        com.google.protobuf.Message req = buildReq(cmd_code,loginname,password);

        int length = 24 + req.getSerializedSize();
        head = new CMsgHead(length,1,cmd_code,0,0);
        byte[] sendBytes = head.toBytes();
        for (byte b : sendBytes) {
            System.out.printf("%02x", b);
        }
        System.out.println();

        try {
            Socket socket = new Socket("139.196.34.10", 30002);
            // 获取输入输出流
            OutputStream os = socket.getOutputStream();
            os.write(sendBytes);
            req.writeTo(socket.getOutputStream());
            os.flush();

            // 获取输入流
            InputStream is = socket.getInputStream();

            byte[] receiveBytes = new byte[24];

            is.read(receiveBytes);

            for (byte b : receiveBytes) {
                System.out.printf("%02x", b);
            }
            System.out.println();

            head.fromBytes(receiveBytes);


            if (head.validate()&& head.getEvent() == Global.TRADE_USERLOGON_RESP) {

                receiveBytes = new byte[head.getLength() - 24];
                // 获取服务器值
                is.read(receiveBytes);

                //获取状态码
                result = Trade.UserLoginResp.parseFrom(receiveBytes).getStatusInfo().getStatus().getNumber();

                                /*
                    如果状态码
                 */
                if (result == Global.SUCCESS) {
                    System.out.println("登录成功------------------" + result);
                    msg = Trade.UserLoginResp.parseFrom(receiveBytes).
                            getStatusInfo().getMsg();
                    user_id = Trade.UserLoginResp.parseFrom(receiveBytes).
                            getUser().getUserId();
                    use_trade_passwd = Trade.UserLoginResp.parseFrom(receiveBytes).
                            getUser().getUseTradePasswd();
                    new_user_login = Trade.UserLoginResp.parseFrom(receiveBytes).
                            getUser().getNewUserLogin();
                    List<Trade.UserLoginResp.User.Account> accouts =
                            Trade.UserLoginResp.parseFrom(receiveBytes).
                            getUser().getAccProfileList();
                     for(Trade.UserLoginResp.User.Account account : accouts){
                        acc_num = account.getAccNum();
                        cucy_code = account.getCucyCode();
                        acc_status = account.getAccStatus();
                  }
                    session_id = Trade.UserLoginResp.parseFrom(receiveBytes).
                            getUser().getSessionId();

                    sp.edit().putInt("status", result)
                            .putString("msg",msg)
                            .putString("user_id",user_id)
                            .putString("use_trade_passwd",use_trade_passwd)
                            .putString("new_user_login",new_user_login)
                            .putString("acc_num",acc_num)
                            .putString("cucy_code",cucy_code)
                            .putString("acc_status",acc_status)
                            .putString("acc_status",acc_status)
                            .putString("session_id",session_id).commit();

//                    sp.edit().putInt("status", result).putBoolean("new_user", new_user).putInt("user_id", user_id)
//                            .putString("session", session).commit();
                    //结果码
                }else if (result == Global.INVALID_REQUEST){
                    msg = Trade.UserLoginResp.parseFrom(receiveBytes).
                            getStatusInfo().getMsg();
                }
                Log.i(TAG, "status = " + result + ", msg = " + msg + "," +
                        " user_id = " + user_id + ", use_trade_passwd = " + use_trade_passwd +
                        ", new_user_login = " + new_user_login + ", acc_num = " + acc_num +
                        ", cucy_code = " + cucy_code + ", acc_status = " + acc_status +
                        ", session_id = " + session_id);

//                parseResponse(head.getEvent(), receiveBytes);

            } else {
                result = Global.FAILED;
                System.out.println(head.getEvent() + " "
                        + new Date(System.currentTimeMillis()));
            }
            System.out.println(head.toString());
            System.out.println("event = " + head.getEvent());
            System.out.println(head.getFlag() + "0xEF");

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
