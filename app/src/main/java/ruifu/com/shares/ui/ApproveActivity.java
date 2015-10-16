package ruifu.com.shares.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;

public class ApproveActivity extends BaseActivity implements View.OnClickListener {
    private ImageView btRet;
    private TextView tv;

    @Override
    public void setUpView() {
        btRet = (ImageView) findViewById(R.id.approve_close_button);
        tv = (TextView) findViewById(R.id.approve_title_setting);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("users", Activity.MODE_PRIVATE);
        tv.setText(sp.getString("username",""));
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
        setContentView(R.layout.activity_approve);
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.approve_close_button:
                goBackFinish();
                break;
        }
    }
}
