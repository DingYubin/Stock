package ruifu.com.shares.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;
import ruifu.com.shares.commons.Constants;

/**
 * Created by Administrator on 2015/10/15.
 */
public class SettingsMainActivity extends BaseActivity implements OnClickListener{

    // 整个平台的Controller,负责管理整个SDK的配置、操作等处理
    private UMSocialService mController = UMServiceFactory
            .getUMSocialService(Constants.DESCRIPTOR);

    private ImageView btRet;

    private RelativeLayout acc_manger;
    private RelativeLayout setting_recommend;
    private RelativeLayout setting_about;

    public void setUpView() {
        btRet = (ImageView) findViewById(R.id.settings_close_button);

        acc_manger = (RelativeLayout) findViewById(R.id.setting_login);
        setting_recommend = (RelativeLayout) findViewById(R.id.setting_recommend);
        setting_about = (RelativeLayout) findViewById(R.id.setting_about);
    }

    @Override
    public void addListener() {
        btRet.setOnClickListener(this);
        acc_manger.setOnClickListener(this);
        setting_recommend.setOnClickListener(this);
        setting_about.setOnClickListener(this);
    }

    @Override
    public void fillData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.settings_main_activity);
        super.onCreate(savedInstanceState);

        //设置分享内容
        setShareContent();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings_close_button:
                goBackFinish();
                break;
            case R.id.setting_login:
                Intent login_intent = new Intent(this,AccManagerActivity.class);
                startActivity(login_intent);
                break;
            case R.id.setting_recommend:
                addCustomPlatforms();
                break;
            case R.id.setting_about:
                Intent about_intent = new Intent(this,SettingAboutActivity.class);
                startActivity(about_intent);
                break;
            default:
                break;
        }
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    private void setShareContent() {

        // 配置SSO
        mController.setShareContent("大家好，欢迎来到股票市场");

        UMImage localImage = new UMImage(SettingsMainActivity.this, R.drawable.device);
        UMImage urlImage = new UMImage(SettingsMainActivity.this,
                "http://www.umeng.com/images/pic/social/integrated_3.png");
        // UMImage resImage = new UMImage(LoginActivity.this, R.drawable.icon);

        // 视频分享
        UMVideo video = new UMVideo(
                "http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
        // vedio.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
        video.setTitle("友盟社会化组件视频");
        video.setThumb(urlImage);

        UMusic uMusic = new UMusic(
                "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
        uMusic.setAuthor("umeng");
        uMusic.setTitle("天籁之音");
        uMusic.setThumb(urlImage);
        // uMusic.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");

        //设置微信分享
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，微信");
        weixinContent.setTitle("友盟社会化分享组件-微信");
        weixinContent.setTargetUrl("http://www.umeng.com");
        weixinContent.setShareMedia(urlImage);
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，朋友圈");
        circleMedia.setTitle("友盟社会化分享组件-朋友圈");
        circleMedia.setShareImage(urlImage);
        // circleMedia.setShareMedia(uMusic);
        // circleMedia.setShareMedia(video);
        circleMedia.setTargetUrl("http://www.umeng.com");
        mController.setShareMedia(circleMedia);

        //设置QQ分享
        UMImage qzoneImage = new UMImage(SettingsMainActivity.this,
                "http://www.umeng.com/images/pic/social/integrated_3.png");
        qzoneImage
                .setTargetUrl("http://www.umeng.com/images/pic/social/integrated_3.png");

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QZone");
        qzone.setTargetUrl("http://www.umeng.com/social");
        qzone.setTitle("QZone title");
        qzone.setShareImage(urlImage);
        mController.setShareMedia(qzone);

        video.setThumb(new UMImage(SettingsMainActivity.this, BitmapFactory
                .decodeResource(getResources(), R.drawable.device)));

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QQ");
        qqShareContent.setTitle("hello, title");
        qqShareContent.setShareImage(urlImage);
        // qqShareContent.setShareMusic(uMusic);
        // qqShareContent.setShareVideo(video);
        qqShareContent.setTargetUrl("http://www.umeng.com/social");
        mController.setShareMedia(qqShareContent);

        // 视频分享
        UMVideo umVideo = new UMVideo(
                "http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
        umVideo.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
        umVideo.setTitle("友盟社会化组件视频");

        TencentWbShareContent tencent = new TencentWbShareContent();
        tencent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，腾讯微博");
        // 设置tencent分享内容
        mController.setShareMedia(tencent);

    }

    /*
    增加所有平台授权
     */
    /**
     * 添加所有的平台</br>
     */
    private void addCustomPlatforms() {
        // 添加新浪sso授权
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        // 添加腾讯微博SSO授权
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 添加微信平台
        addWXPlatform();
        // 添加QQ平台
        addQQQZonePlatform();

        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT);

        mController.openShare(SettingsMainActivity.this, false);
    }

    /**
     * @功能描述 : 添加微信平台分享
     * @return
     */
    private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx2622214496f26933";
        String appSecret = "fc6a7b6348c3eb7e1ec0dd8d1ccefb27";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(SettingsMainActivity.this, appId,
                appSecret);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(SettingsMainActivity.this,
                appId, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
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
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(SettingsMainActivity.this,
                appId, appKey);
        qqSsoHandler.setTargetUrl("http://www.umeng.com");
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
                SettingsMainActivity.this, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
    }

}
