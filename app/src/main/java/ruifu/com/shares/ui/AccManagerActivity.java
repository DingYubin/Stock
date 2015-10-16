package ruifu.com.shares.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;

/**
 * Created by Administrator on 2015/10/16.
 */
public class AccManagerActivity extends BaseActivity implements View.OnClickListener{
    private ImageView ret_btn;

    @Override
    public void setUpView() {
        ret_btn = (ImageView) findViewById(R.id.accmanager_close_button);
    }

    @Override
    public void addListener() {
        ret_btn.setOnClickListener(this);
    }

    @Override
    public void fillData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_acc_manager);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.accmanager_close_button:
                goBackFinish();
                break;

            default:
                break;
        }
    }

}
