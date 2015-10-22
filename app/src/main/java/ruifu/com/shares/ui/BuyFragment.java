package ruifu.com.shares.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;

public class BuyFragment extends BaseFragment implements View.OnClickListener{

    private View layoutView;

    private ListView lv_buy;
    private ListView lv_sale;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_buy, container, false);

        lv_buy = (ListView) layoutView.findViewById(R.id.buy_list);
        lv_sale = (ListView) layoutView.findViewById(R.id.sell_list);

        ArrayList<HashMap<String,Object>> buylistItem =  new ArrayList<HashMap<String,Object>>();
        /*在数组中存放数据*/
        for(int i=0;i<5;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("buy_rate", "买"+ i);
            map.put("updown_rate",6.910+i);
            map.put("numble_rate",100+i);
            buylistItem.add(map);
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(getActivity(),buylistItem,
                R.layout.quote_plate_list_item,
                new String[] {"buy_rate","updown_rate", "numble_rate"},
                new int[] {R.id.buy_rate,R.id.updown_rate,R.id.numble_rate}
         );

        lv_buy.setAdapter(mSimpleAdapter);//为ListView绑定适配器
        lv_sale.setAdapter(mSimpleAdapter);

        return layoutView;
    }


    @Override
    public void onClick(View v) {

    }
}
