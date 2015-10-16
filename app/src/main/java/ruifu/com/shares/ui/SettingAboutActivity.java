package ruifu.com.shares.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;

/**
 * Created by Administrator on 2015/10/16.
 */
public class SettingAboutActivity extends BaseActivity implements View.OnClickListener{
    private ImageView ret_btn;
    private RelativeLayout setting_disclaimer;
    @Override
    public void setUpView() {
        ret_btn = (ImageView) findViewById(R.id.version_close_button);
        setting_disclaimer = (RelativeLayout) findViewById(R.id.setting_disclaimer);
    }

    @Override
    public void addListener() {
        ret_btn.setOnClickListener(this);
        setting_disclaimer.setOnClickListener(this);
    }

    @Override
    public void fillData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings_about);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.version_close_button:
                goBackFinish();
                break;
            case R.id.setting_disclaimer:
                Intent disclaimerIntetn = new Intent(this,DisclaimerActivity.class);
                startActivity(disclaimerIntetn);
                break;
            default:
                break;
        }
    }
}
