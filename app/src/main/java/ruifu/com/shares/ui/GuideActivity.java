package ruifu.com.shares.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ruifu.com.shares.R;
import ruifu.com.shares.adapter.ViewPagerAdapter;

public class GuideActivity extends Activity implements OnPageChangeListener{

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };
    private ImageView start_image;
  //  private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initViews();
        initDots();
    }



    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three, null));

        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        start_image = (ImageView) views.get(2).findViewById(R.id.start_imge);
        start_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        vp.setOnPageChangeListener(this);
      //  start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
    }


    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.pageindicator_selected);
            } else {
                dots[i].setImageResource(R.drawable.pageindicator_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
