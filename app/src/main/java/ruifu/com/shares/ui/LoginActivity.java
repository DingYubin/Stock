package ruifu.com.shares.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.json.JSONObject;

import java.util.Map;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.Global;
import ruifu.com.shares.R;
import ruifu.com.shares.biz.ThirdAuthorizationBiz;
import ruifu.com.shares.commons.Constants;

//import com.umeng.socialize.sso.TencentWBSsoHandler;

public class LoginActivity extends BaseActivity implements OnClickListener {
    private final static String TAG = "LoginActivity";
    private Context context;
    private SharedPreferences sp;
    ThirdAuthorizationBiz thirdAuthorizationBiz;

    private Button bt_ret;
    private Button bt_confiem;
    private EditText et_login_name;
    private EditText et_login_pass;
    private TextView tv_forget_pass;


    public Handler myHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//            hiderLoading();
            int resId = 0;
            switch (msg.what) {

                case Global.LOGIN_EMPTY:

                    resId = R.string.login_pwd_empty;
                    break;
                case Global.LOGIN_PASS_TOSHORT:
                    resId = R.string.login_pass_to_short;
                    break;
                case Global.SUCCESS:
                    resId = R.string.login_success;
                    finish();
                    break;
                case Global.FAIL:
                    resId = R.string.login_fail;
                    break;
                case Global.NET_PROBLEM:
                    resId = R.string.login_net;
                    break;
                default:
                    break;
            }
            if (resId != 0) {
                showToast(resId);
            }
            super.handleMessage(msg);
        }
    };


    // 整个平台的Controller,负责管理整个SDK的配置、操作等处理
    private UMSocialService mController = UMServiceFactory.
            getUMSocialService(Constants.DESCRIPTOR);


    private ImageButton qqLoginButton;
    private Button qqLogoutButton;
    private ImageButton wechatLoginButton;
    private Button wechatLogoutButton;
    private Button shareButton;

    @Override
    public void setUpView() {
        //手机密码登录
        bt_ret = (Button) findViewById(R.id.nav_bt_ret);
        bt_confiem = (Button) findViewById(R.id.nav_bt_confirm);
        et_login_name = (EditText) findViewById(R.id.nav_et_login_name);
        et_login_pass = (EditText) findViewById(R.id.nav_et_login_pass);
        tv_forget_pass = (TextView) findViewById(R.id.nav_tv_forget_pass);

        //第三方授权登录
        qqLoginButton = (ImageButton) this.findViewById(R.id.btn_qq_login);
        qqLogoutButton = (Button) this.findViewById(R.id.btn_qq_logout);
        shareButton = (Button) this.findViewById(R.id.btn_share);
        wechatLoginButton = (ImageButton) this.findViewById(R.id.btn_wechat_login);
        wechatLogoutButton = (Button) this.findViewById(R.id.btn_wechat_logout);

        sp = getSharedPreferences("users", Context.MODE_PRIVATE);
    }

    @Override
    public void fillData() {

    }

    @Override
    public void addListener() {

        bt_ret.setOnClickListener(this);
        bt_confiem.setOnClickListener(this);
        et_login_name.setOnClickListener(this);
        et_login_pass.setOnClickListener(this);
        tv_forget_pass.setOnClickListener(this);

        qqLoginButton.setOnClickListener(this);
        qqLogoutButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        wechatLoginButton.setOnClickListener(this);
        wechatLogoutButton.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login);
        super.onCreate(savedInstanceState);

        // 配置需要分享的相关平台
        configPlatforms();
        // 设置分享的内容
      //  setShareContent();

    }

    /**
     * 配置分享平台参数
     */
    private void configPlatforms() {
        // 添加新浪sso授权
//       mController.getConfig().setSsoHandler(new SinaSsoHandler());
        // 添加腾讯微博SSO授权
//        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 添加微信、微信朋友圈平台
        addWXPlatform();
        // 添加QQ、QZone平台
       addQQQZonePlatform();

    }

    /**
     * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
     *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
     *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
     *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
     * @return
     */
    private void addQQQZonePlatform() {
        String appId = "100424468";
        String appKey = "c7394704798a158208a74ab60104f0ba";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginActivity.this,
                appId, appKey);
        qqSsoHandler.setTargetUrl("http://www.umeng.com");
        qqSsoHandler.addToSocialSDK();
        // 添加QZone平台
//        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
//                LoginActivity.this, appId, appKey);
//        qZoneSsoHandler.addToSocialSDK();
    }


    private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx2622214496f26933是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx2622214496f26933";
        String appSecret = "fc6a7b6348c3eb7e1ec0dd8d1ccefb27";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(LoginActivity.this, appId,
                appSecret);
        wxHandler.setTargetUrl("http://www.umeng.com");
//        wxHandler.setRefreshTokenAvailable(false);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(LoginActivity.this,
                appId, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_bt_ret://返回
                finish();
                break;
            case R.id.nav_bt_confirm://确定登录
                final String login = et_login_name.getText().toString().trim();
                final String pwd = et_login_pass.getText().toString().trim();
                login(login, pwd);
                break;
            case R.id.nav_tv_forget_pass://忘记密码
                String loginname = et_login_name.getText().toString().trim();
//                Intent intent = new Intent(LoginActivity.this,FindPassActivity.class);
//                if(!TextUtils.isEmpty(loginname)){
//                    intent.putExtra("loginname", loginname);
//                }
//                startActivity(intent);
//                finish();
                break;
            case R.id.btn_qq_login: // qq登录
                qqLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_qq_logout: // 注销qq账号
                logout(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_wechat_login: // 微信登陆
                wxLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.btn_wechat_logout:
                logout(SHARE_MEDIA.WEIXIN); // 注销微信账号
                break;
            default:
                break;
        }
    }


    /**
     *账号密码登录
     */
    private void login(final String login, final String pwd){
        if(TextUtils.isEmpty(login)|| TextUtils.isEmpty(pwd)){
            myHandler.sendEmptyMessage(Global.LOGIN_EMPTY);
            return;
        }
        if(pwd.length()<6||pwd.length()>40){
            myHandler.sendEmptyMessage(Global.LOGIN_PASS_TOSHORT);
            return;
        }
        showLoading("正在验证登录...");

    }

    /**
     * 授权。如果授权成功，则获取用户信息
     *
     * @param platform
     * 微信授权登录
     * 微信则是以unionid,为了便于传值，我将这两个值同意转换成useid
     */

    private void wxLogin(final SHARE_MEDIA platform) {
        // TODO Auto-generated method stub
        mController.doOauthVerify(LoginActivity.this, platform,
                new SocializeListeners.UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        // TODO Auto-generated method stub
                        Toast.makeText(LoginActivity.this, "授权开始",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(Bundle value, SHARE_MEDIA platform) {
                        // 获取uid
                        String uid = value.getString("uid");
                        if (!TextUtils.isEmpty(uid)) {
                            getWXUserInfo(platform);
                        }
                    }

                    @Override
                    public void onError(SocializeException e, SHARE_MEDIA platform) {
                        Toast.makeText(LoginActivity.this, "授权失败",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        // TODO Auto-generated method stub
                        Toast.makeText(LoginActivity.this, "授权取消",
                                Toast.LENGTH_SHORT).show();
                    }


                });
    }


    /**
     * 获取wx用户信息
     *
     * @param platform
     */
    private void getWXUserInfo(SHARE_MEDIA platform) {
        mController.getPlatformInfo(LoginActivity.this, platform,
                new UMDataListener() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {

                        if (info != null) {
                            JSONObject json = new JSONObject(info);
                            Log.i("LoginActivity", "userid = " +info.get("unionid").toString());
                            Log.i("LoginActivity", "headimgurl = " +info.get("headimgurl").toString());
                            Log.i("LoginActivity", "info = " + json.toString());

                            sp.edit().putString("userid", info.get("unionid").toString())
                                    .putString("headimgurl", info.get("headimgurl").toString())
                                    .putString("username",info.get("nickname").toString())
                                    .putString("info", json.toString())
                                    .putString("third_author","weixin").commit();
                            thirdAuthorizationBiz = new ThirdAuthorizationBiz(LoginActivity.this);
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    int result = thirdAuthorizationBiz.login();
                                    myHandler.sendEmptyMessage(result);
                                }
                            }.start();
                            Log.i("LoginActivity", "授权有结果了1");


                        } else {
                            Log.d(TAG, "发生错误：" + status);
                        }

                    }

                });
    }


    /**
     * 授权。如果授权成功，则获取用户信息
     *
     * @param platform
     * QQ授权登录
     * qq授权登录返回的值是openid作为标识,为了便于传值，我将这两个值同意转换成useid
     */
    String openid = "";
    private void qqLogin(final SHARE_MEDIA platform) {
        // TODO Auto-generated method stub
        mController.doOauthVerify(LoginActivity.this, platform,
                new SocializeListeners.UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        // TODO Auto-generated method stub
                        Toast.makeText(LoginActivity.this, "授权开始",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(Bundle value, SHARE_MEDIA platform) {
                        // 获取uid
                        String uid = value.getString("uid");
                        openid = value.getString("openid");
                        if (!TextUtils.isEmpty(uid)) {
                            getQQUserInfo(platform);

                        }
                    }

                    @Override
                    public void onError(SocializeException e, SHARE_MEDIA platform) {
                        Toast.makeText(LoginActivity.this, "授权失败",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        // TODO Auto-generated method stub
                        Toast.makeText(LoginActivity.this, "授权取消",
                                Toast.LENGTH_SHORT).show();
                    }


                });
    }

    /**
     * 获取qq用户信息
     *
     * @param platform
     */
    private void getQQUserInfo(SHARE_MEDIA platform) {
        mController.getPlatformInfo(LoginActivity.this, platform,
                new UMDataListener() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {

                        if (info != null) {
                            JSONObject json = new JSONObject(info);

                            Log.i("LoginActivity", "userid = " +openid);
                            Log.i("LoginActivity", "profile_image_url = " + info.get("profile_image_url").toString());
                            Log.i("LoginActivity", "info = " + json.toString());

                            sp.edit().putString("userid", openid).
                                    putString("headimgurl", info.get("profile_image_url").toString())
                                    .putString("username",info.get("screen_name").toString())
                                    .putString("info", json.toString())
                                    .putString("third_author", "qq").commit();
                            thirdAuthorizationBiz = new ThirdAuthorizationBiz(LoginActivity.this);

                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    int result = thirdAuthorizationBiz.login();
                                    myHandler.sendEmptyMessage(result);
                                }
                            }.start();

                            Log.i("LoginActivity", "授权有结果了2");

                        }else{
                            Log.d(TAG,"发生错误："+status);
                        }
                    }

                });
    }

    /**
     * 注销本次登陆
     * @param platform
     */
    private void logout(final SHARE_MEDIA platform) {
        mController.deleteOauth(LoginActivity.this, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除" + platform.toString() + "平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                }
                Toast.makeText(LoginActivity.this, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 如果有使用任一平台的SSO授权, 则必须在对应的activity中实现onActivityResult方法, 并添加如下代码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 根据requestCode获取对应的SsoHandler
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
                resultCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        System.out.println("接受到了信息2");
        Log.i(TAG, "接受到了信息2");
    }

}
