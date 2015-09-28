package ruifu.com.shares;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

import java.util.Map;

import ruifu.com.shares.commons.Constants;

//import com.umeng.socialize.sso.TencentWBSsoHandler;

public class LoginActivity extends Activity implements OnClickListener {

    // 整个平台的Controller,负责管理整个SDK的配置、操作等处理
    private UMSocialService mController = UMServiceFactory.
            getUMSocialService(Constants.DESCRIPTOR);


    private Button qqLoginButton;
    private Button qqLogoutButton;
    private Button wechatLoginButton;
    private Button wechatLogoutButton;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        qqLoginButton = (Button) this.findViewById(R.id.btn_qq_login);
        qqLogoutButton = (Button) this.findViewById(R.id.btn_qq_logout);
        shareButton = (Button) this.findViewById(R.id.btn_share);
        wechatLoginButton = (Button) this.findViewById(R.id.btn_wechat_login);
        wechatLogoutButton = (Button) this.findViewById(R.id.btn_wechat_logout);


        qqLoginButton.setOnClickListener(this);
        qqLogoutButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        wechatLoginButton.setOnClickListener(this);
        wechatLogoutButton.setOnClickListener(this);

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
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx967daebe835fbeac";
        String appSecret = "5bb696d9ccd75a38c8a0bfe0675559b3";
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
            case R.id.btn_qq_login: // qq登录
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_wechat_login: // 微信登陆
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.btn_qq_logout: // 注销qq账号
                logout(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_wechat_logout:
                logout(SHARE_MEDIA.WEIXIN); // 注销微信账号
                break;
            default:
                break;
        }
    }

    /**
     * 授权。如果授权成功，则获取用户信息
     *
     * @param platform
     */

    private void login(final SHARE_MEDIA platform) {
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
                            // uid不为空，获取用户信息
                            getUserInfo(platform);
                        } else {
                            Toast.makeText(LoginActivity.this, "授权失败...",
                                    Toast.LENGTH_LONG).show();
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
     * 获取用户信息
     *
     * @param platform
     */
    private void getUserInfo(SHARE_MEDIA platform) {
        mController.getPlatformInfo(LoginActivity.this, platform,
                new UMDataListener() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {
                        String showText = "";
                        if (status == StatusCode.ST_CODE_SUCCESSED) {
                            showText = "用户名：" +
                                    info.get("screen_name").toString();
                            Log.d("LoginActivity", "LoginActivity" + info.toString());
                        } else {
                            showText = "获取用户信息失败";
                        }

                        if (info != null) {
                            Toast.makeText(LoginActivity.this, info.toString(),
                                    Toast.LENGTH_SHORT).show();
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
    }
}
