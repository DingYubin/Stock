package ruifu.com.shares.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ruifu.com.shares.R;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentTabHost mTabHost;
    private RadioGroup m_radioGroup;
    String tabs[] = {"Tab1","Tab2","Tab3","Tab4"};
    Class cls[] = {Fragment1.class,Fragment2.class,Fragment3.class,Fragment4.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setVisibility(View.GONE);
        for (int i = 0; i < tabs.length; i++){
            mTabHost.addTab(mTabHost.newTabSpec(tabs[i]).
                            setIndicator(tabs[i]),cls[i],null);
        }

        m_radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        m_radioGroup.setOnCheckedChangeListener(this);
        ((RadioButton)m_radioGroup.getChildAt(0)).toggle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.portfolio_button:
                mTabHost.setCurrentTabByTag(tabs[0]);
                break;
            case R.id.news_button:
                mTabHost.setCurrentTabByTag(tabs[1]);
                break;
            case R.id.markets_button:
                mTabHost.setCurrentTabByTag(tabs[2]);
                break;
            case R.id.me_button:
                mTabHost.setCurrentTabByTag(tabs[3]);
                break;
        }
    }
}
