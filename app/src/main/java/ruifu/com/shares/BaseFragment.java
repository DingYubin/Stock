package ruifu.com.shares;


import android.support.v4.app.Fragment;

/**
 * Created by dyb on 15/9/27.
 */
public abstract class BaseFragment extends Fragment {

    private int indext;

    public int getIndext(){

        return indext;
    }


    public void setIndex(int index) {
        this.indext = indext;
    }
}
