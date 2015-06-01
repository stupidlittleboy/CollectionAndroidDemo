package com.stupidman.admin.collectionandroiddemo.share.thirldapplogindemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.stupidman.admin.collectionandroiddemo.R;

import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

public class ThirldMainActivity extends Activity {

    Handler hanlder = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = new Intent();
            intent.setClass(ThirldMainActivity.this, LoginActivity.class);
            HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
            JSONObject json = new JSONObject(map);
            intent.putExtra("userId", json.toString());
            startActivity(intent);

        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.share).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                share();
            }
        });

        findViewById(R.id.weibologin).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                goWeiboLogin();
            }
        });

        findViewById(R.id.qqlogin).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                goQzoneLogin();
            }
        });


        //微信登录
        findViewById(R.id.wechatlogin).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                goWeixinLogin();
            }
        });
    }

    /**
     * 实现多平台分享功能
     */
    // 添加分享调用代码
    private void share() {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();

        oks.setDialogMode();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.addHiddenPlatform(SinaWeibo.NAME);

        // 分享时Notification的图标和文字
        oks.setNotification(R.mipmap.ic_launcher, getString(R.string.app_name));

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        // oks.setTitle(getString(R.string.share));
        oks.setTitle("微蚁");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是微博的文本");
        //oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //					oks.setImagePath("/sdcard/12-1.jpg");//使用这张图片必须保证您的SDcard下面存在这样的一张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://money.bmob.cn");

        //					oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("分享应用");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.baidu.com");

        //		OneKeyShareCallback callback=new OneKeyShareCallback();
        //oks.setCallback(callback);

        oks.setSilent(false);

        //					 通过OneKeyShareCallback来修改不同平台分享的内容
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                System.out.println("不是短信的话就进入");
                if (!platform.getName().equals("ShortMessage")) {
                    paramsToShare.setText("我的");
                    paramsToShare.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
                }
            }
        });
        //					 启动分享GUI
        oks.show(this);

    }

    /**
     * Sina微博登录
     */
    private void goWeiboLogin() {
        ShareSDK.initSDK(this);
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        authorize(platform);
    }

    /**
     * QQ登录
     */
    private void goQzoneLogin() {
        ShareSDK.initSDK(this);
        Platform platform = ShareSDK.getPlatform(QZone.NAME);
        authorize(platform);
    }

    /**
     * 微信登录
     */
    private void goWeixinLogin() {
        ShareSDK.initSDK(this);
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        authorize(platform);
    }

    /**
     * 平台登录实现
     *
     * @param platform Sina、QQ、微信等平台
     */
    private void authorize(Platform platform) {
        if (platform == null) {
            return;
        }
        if (platform.isValid()) {
            String userId = platform.getDb().getUserId();
            //Toast.makeText(this, userId, 1).show();
            platform.removeAccount();
        }
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub

            }

            //这个是登录完成后进行的操作
            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                Message msg = Message.obtain();
                msg.obj = arg2;
                hanlder.sendMessage(msg);
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        // true不使用SSO授权，false使用SSO授权
        platform.SSOSetting(true);
        //获取用户资料
        platform.showUser(null);
    }

    protected void onStop() {
        super.onStop();
        ShareSDK.stopSDK();
    }
}
