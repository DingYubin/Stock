package ruifu.com.shares.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;

public class SaleFragment extends BaseFragment implements View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale, container, false);
    }


    @Override
    public void onClick(View v) {
        
    }
}
