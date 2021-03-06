package ruifu.com.shares.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;

public class HKAccountActivity extends BaseActivity implements View.OnClickListener{
    private ImageView btRet;
    @Override
    public void setUpView() {
        btRet = (ImageView) findViewById(R.id.hk_close_button);
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
        setContentView(R.layout.activity_hk_account);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hk_close_button:
                goBackFinish();
                break;
            default:
                break;
        }
    }
}
