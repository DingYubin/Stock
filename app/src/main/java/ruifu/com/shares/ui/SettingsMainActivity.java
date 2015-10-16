package ruifu.com.shares.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;

/**
 * Created by Administrator on 2015/10/15.
 */
public class SettingsMainActivity extends BaseActivity implements OnClickListener{

    private ImageView btRet;

    public void setUpView() {
        btRet = (ImageView) findViewById(R.id.settings_close_button);
    }

    @Override
    public void addListener() {
        btRet.setOnClickListener(this);
    }

    @Override
    public void fillData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.settings_main_activity);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings_close_button:
                goBackFinish();
                break;
            default:
                break;
        }
    }

}
