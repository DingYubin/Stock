package ruifu.com.shares;


import android.app.Fragment;

/**
 * Created by dyb on 15/9/27.
 */
public abstract class BaseFragment extends Fragment {

    private int index;

    public int getIndex() {

        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }
}
