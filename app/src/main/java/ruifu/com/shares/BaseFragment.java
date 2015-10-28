package ruifu.com.shares;


import android.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by dyb on 15/9/27.
 */
public abstract class BaseFragment extends Fragment {

    private int index;
    public RelativeLayout mRloading;
    public int getIndex() {

        return index;
    }

    public void showToast(int resId ){
        Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void hiderLoading(){
        mRloading.setVisibility(View.INVISIBLE);
    }


}
