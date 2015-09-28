package ruifu.com.shares;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

/**
 * Created by dyb on 15/9/27.
 */
public class Fragment4 extends BaseFragment implements OnClickListener {
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;
    private ImageButton mImg;
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment4,null);
        mImg = (ImageButton) layoutView.findViewById(R.id.imageButton);
        mImg.setOnClickListener(this);
        return layoutView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageButton:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
