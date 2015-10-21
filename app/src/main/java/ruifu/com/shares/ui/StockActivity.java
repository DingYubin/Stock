package ruifu.com.shares.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;
import ruifu.com.shares.entity.StockEntity;
import ruifu.com.shares.entity.TimesEntity;
import ruifu.com.shares.view.TimesView;

/**
 * Created by 王群 on 2015/10/16.
 */
public class StockActivity extends BaseActivity {
    static private final int MSG_SUCCESS = 0;
    static private final int MSG_FAILURE = 1;

    private StockEntity stockEntity;
    private View returnView;
    private View refreshView;
    private TextView tradeStatus;
    private View stockPriceInfo;    // 股票价格信息
    private View stockBasicInfo;    // 股票基本信息
    private View stockTradeLine;    // 股票分时图信息

    private int stockButtonId;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case StockEntity.MSG_SUCCESS:
                    refresh();
                    break;
                case StockEntity.MSG_FAILURE:
                    // TODO
                    break;
            }
        };
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("StockActivity", "onCreate");
        setContentView(R.layout.activity_stock_page);
        super.onCreate(savedInstanceState);
        stockButtonId = R.id.timeLineButton;
    }

    @Override
    public void setUpView() {
        Log.d("StockActivity", "setUpView");
        returnView = findViewById(R.id.returnTextView);
        refreshView = findViewById(R.id.refreshTextView);
        tradeStatus = (TextView)findViewById(R.id.tradeStatusTextView);
        stockPriceInfo = findViewById(R.id.stockPriceInfo);
        stockBasicInfo = findViewById(R.id.stockBasicInfo);
        stockTradeLine = findViewById(R.id.stockTrendLine);
    }

    @Override
    public void addListener() {
        returnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackFinish();
            }
        });
        refreshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockEntity.syncData(handler);
            }
        });
        stockTradeLine.findViewById(R.id.timeLineButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stockButtonId != R.id.timeLineButton) {
                    stockButtonId = R.id.timeLineButton;
                    // TODO
                }
            }
        });
        stockTradeLine.findViewById(R.id.fiveDaysButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stockButtonId != R.id.fiveDaysButton) {
                    stockButtonId = R.id.fiveDaysButton;
                    // TODO
                }
            }
        });
        stockTradeLine.findViewById(R.id.dailyKButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stockButtonId != R.id.dailyKButton) {
                    stockButtonId = R.id.dailyKButton;
                    // TODO
                }
            }
        });
        stockTradeLine.findViewById(R.id.weeklyKButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stockButtonId != R.id.weeklyKButton) {
                    stockButtonId = R.id.weeklyKButton;
                    // TODO
                }
            }
        });
        stockTradeLine.findViewById(R.id.monthlyKButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stockButtonId != R.id.monthlyKButton) {
                    stockButtonId = R.id.monthlyKButton;
                    // TODO
                }
            }
        });
    }

    @Override
    public void fillData() {
        stockEntity = (StockEntity)getIntent().getExtras().get("stock.current");
        List<TimesEntity> timesEntityList = new ArrayList<>();
        JSONObject data = null;
        JSONArray jsonArrayData = null;
        try {
            jsonArrayData = new JSONObject(
                    "{\"error\":\"成功\",\"status\":0,\"data\":[{\"changeAmount\":5,\"weightedIndex\":6855,\"time\":\"09:31\",\"sell\":970,\"buy\":970,\"volume\":30,\"changeRate\":0.073,\"nonWeightedIndex\":6855},{\"changeAmount\":6,\"weightedIndex\":6855,\"time\":\"09:32\",\"sell\":590,\"buy\":590,\"volume\":40,\"changeRate\":0.088,\"nonWeightedIndex\":6855},{\"changeAmount\":6,\"weightedIndex\":6855,\"time\":\"09:33\",\"sell\":580,\"buy\":580,\"volume\":40,\"changeRate\":0.088,\"nonWeightedIndex\":6856},{\"changeAmount\":7,\"weightedIndex\":6856,\"time\":\"09:34\",\"sell\":626,\"buy\":626,\"volume\":54,\"changeRate\":0.102,\"nonWeightedIndex\":6856},{\"changeAmount\":7,\"weightedIndex\":6856,\"time\":\"09:35\",\"sell\":756,\"buy\":756,\"volume\":54,\"changeRate\":0.102,\"nonWeightedIndex\":6857},{\"changeAmount\":8,\"weightedIndex\":6856,\"time\":\"09:36\",\"sell\":778,\"buy\":778,\"volume\":66,\"changeRate\":0.117,\"nonWeightedIndex\":6857},{\"changeAmount\":8,\"weightedIndex\":6856,\"time\":\"09:37\",\"sell\":708,\"buy\":708,\"volume\":66,\"changeRate\":0.117,\"nonWeightedIndex\":6858},{\"changeAmount\":8,\"weightedIndex\":6856,\"time\":\"09:38\",\"sell\":708,\"buy\":708,\"volume\":66,\"changeRate\":0.117,\"nonWeightedIndex\":6858},{\"changeAmount\":9,\"weightedIndex\":6857,\"time\":\"09:39\",\"sell\":684,\"buy\":684,\"volume\":78,\"changeRate\":0.131,\"nonWeightedIndex\":6858},{\"changeAmount\":9,\"weightedIndex\":6857,\"time\":\"09:40\",\"sell\":696,\"buy\":696,\"volume\":88,\"changeRate\":0.131,\"nonWeightedIndex\":6859},{\"changeAmount\":9,\"weightedIndex\":6857,\"time\":\"09:41\",\"sell\":580,\"buy\":580,\"volume\":88,\"changeRate\":0.131,\"nonWeightedIndex\":6859},{\"changeAmount\":10,\"weightedIndex\":6857,\"time\":\"09:42\",\"sell\":540,\"buy\":540,\"volume\":98,\"changeRate\":0.146,\"nonWeightedIndex\":6859},{\"changeAmount\":10,\"weightedIndex\":6857,\"time\":\"09:43\",\"sell\":570,\"buy\":570,\"volume\":98,\"changeRate\":0.146,\"nonWeightedIndex\":6860},{\"changeAmount\":12,\"weightedIndex\":6858,\"time\":\"09:44\",\"sell\":1040,\"buy\":1040,\"volume\":118,\"changeRate\":0.175,\"nonWeightedIndex\":6861},{\"changeAmount\":12,\"weightedIndex\":6858,\"time\":\"09:45\",\"sell\":1180,\"buy\":1180,\"volume\":118,\"changeRate\":0.175,\"nonWeightedIndex\":6862},{\"changeAmount\":10,\"weightedIndex\":6858,\"time\":\"09:46\",\"sell\":928,\"buy\":928,\"volume\":130,\"changeRate\":0.146,\"nonWeightedIndex\":6861},{\"changeAmount\":10,\"weightedIndex\":6858,\"time\":\"09:47\",\"sell\":708,\"buy\":708,\"volume\":130,\"changeRate\":0.146,\"nonWeightedIndex\":6860},{\"changeAmount\":9,\"weightedIndex\":6858,\"time\":\"09:48\",\"sell\":596,\"buy\":596,\"volume\":140,\"changeRate\":0.131,\"nonWeightedIndex\":6859},{\"changeAmount\":9,\"weightedIndex\":6858,\"time\":\"09:49\",\"sell\":590,\"buy\":590,\"volume\":140,\"changeRate\":0.131,\"nonWeightedIndex\":6859},{\"changeAmount\":10,\"weightedIndex\":6858,\"time\":\"09:50\",\"sell\":560,\"buy\":560,\"volume\":150,\"changeRate\":0.146,\"nonWeightedIndex\":6859},{\"changeAmount\":8,\"weightedIndex\":6858,\"time\":\"09:51\",\"sell\":608,\"buy\":608,\"volume\":162,\"changeRate\":0.117,\"nonWeightedIndex\":6859},{\"changeAmount\":8,\"weightedIndex\":6858,\"time\":\"09:52\",\"sell\":708,\"buy\":708,\"volume\":162,\"changeRate\":0.117,\"nonWeightedIndex\":6858},{\"changeAmount\":8,\"weightedIndex\":6858,\"time\":\"09:53\",\"sell\":708,\"buy\":708,\"volume\":174,\"changeRate\":0.117,\"nonWeightedIndex\":6858},{\"changeAmount\":9,\"weightedIndex\":6858,\"time\":\"09:54\",\"sell\":636,\"buy\":636,\"volume\":182,\"changeRate\":0.131,\"nonWeightedIndex\":6858},{\"changeAmount\":9,\"weightedIndex\":6858,\"time\":\"09:55\",\"sell\":488,\"buy\":488,\"volume\":182,\"changeRate\":0.131,\"nonWeightedIndex\":6859},{\"changeAmount\":7,\"weightedIndex\":6858,\"time\":\"09:56\",\"sell\":496,\"buy\":496,\"volume\":196,\"changeRate\":0.102,\"nonWeightedIndex\":6858},{\"changeAmount\":7,\"weightedIndex\":6858,\"time\":\"09:57\",\"sell\":756,\"buy\":756,\"volume\":196,\"changeRate\":0.102,\"nonWeightedIndex\":6857},{\"changeAmount\":8,\"weightedIndex\":6858,\"time\":\"09:58\",\"sell\":796,\"buy\":796,\"volume\":206,\"changeRate\":0.117,\"nonWeightedIndex\":6857},{\"changeAmount\":8,\"weightedIndex\":6858,\"time\":\"09:59\",\"sell\":610,\"buy\":610,\"volume\":206,\"changeRate\":0.117,\"nonWeightedIndex\":6858},{\"changeAmount\":6,\"weightedIndex\":6858,\"time\":\"10:00\",\"sell\":640,\"buy\":640,\"volume\":226,\"changeRate\":0.088,\"nonWeightedIndex\":6857},{\"changeAmount\":6,\"weightedIndex\":6858,\"time\":\"10:01\",\"sell\":1120,\"buy\":1120,\"volume\":226,\"changeRate\":0.088,\"nonWeightedIndex\":6856},{\"changeAmount\":5,\"weightedIndex\":6858,\"time\":\"10:02\",\"sell\":664,\"buy\":664,\"volume\":234,\"changeRate\":0.073,\"nonWeightedIndex\":6855},{\"changeAmount\":4,\"weightedIndex\":6858,\"time\":\"10:03\",\"sell\":664,\"buy\":664,\"volume\":254,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":4,\"weightedIndex\":6858,\"time\":\"10:04\",\"sell\":1180,\"buy\":1180,\"volume\":254,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":5,\"weightedIndex\":6858,\"time\":\"10:05\",\"sell\":850,\"buy\":850,\"volume\":264,\"changeRate\":0.073,\"nonWeightedIndex\":6854},{\"changeAmount\":5,\"weightedIndex\":6858,\"time\":\"10:06\",\"sell\":590,\"buy\":590,\"volume\":264,\"changeRate\":0.073,\"nonWeightedIndex\":6855},{\"changeAmount\":5,\"weightedIndex\":6858,\"time\":\"10:07\",\"sell\":590,\"buy\":590,\"volume\":264,\"changeRate\":0.073,\"nonWeightedIndex\":6855},{\"changeAmount\":6,\"weightedIndex\":6857,\"time\":\"10:08\",\"sell\":492,\"buy\":492,\"volume\":272,\"changeRate\":0.088,\"nonWeightedIndex\":6855},{\"changeAmount\":6,\"weightedIndex\":6857,\"time\":\"10:09\",\"sell\":448,\"buy\":448,\"volume\":272,\"changeRate\":0.088,\"nonWeightedIndex\":6856},{\"changeAmount\":7,\"weightedIndex\":6857,\"time\":\"10:10\",\"sell\":480,\"buy\":480,\"volume\":284,\"changeRate\":0.102,\"nonWeightedIndex\":6856},{\"changeAmount\":7,\"weightedIndex\":6857,\"time\":\"10:11\",\"sell\":708,\"buy\":708,\"volume\":284,\"changeRate\":0.102,\"nonWeightedIndex\":6857},{\"changeAmount\":7,\"weightedIndex\":6857,\"time\":\"10:12\",\"sell\":660,\"buy\":660,\"volume\":284,\"changeRate\":0.102,\"nonWeightedIndex\":6857},{\"changeAmount\":5,\"weightedIndex\":6857,\"time\":\"10:13\",\"sell\":784,\"buy\":784,\"volume\":298,\"changeRate\":0.073,\"nonWeightedIndex\":6855},{\"changeAmount\":5,\"weightedIndex\":6857,\"time\":\"10:14\",\"sell\":826,\"buy\":826,\"volume\":298,\"changeRate\":0.073,\"nonWeightedIndex\":6855},{\"changeAmount\":4,\"weightedIndex\":6857,\"time\":\"10:15\",\"sell\":800,\"buy\":800,\"volume\":308,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":4,\"weightedIndex\":6857,\"time\":\"10:16\",\"sell\":560,\"buy\":560,\"volume\":308,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":4,\"weightedIndex\":6857,\"time\":\"10:17\",\"sell\":590,\"buy\":590,\"volume\":308,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":3,\"weightedIndex\":6857,\"time\":\"10:18\",\"sell\":788,\"buy\":788,\"volume\":324,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":3,\"weightedIndex\":6857,\"time\":\"10:19\",\"sell\":944,\"buy\":944,\"volume\":324,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":3,\"weightedIndex\":6857,\"time\":\"10:20\",\"sell\":764,\"buy\":764,\"volume\":334,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":3,\"weightedIndex\":6857,\"time\":\"10:21\",\"sell\":590,\"buy\":590,\"volume\":334,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":1,\"weightedIndex\":6857,\"time\":\"10:22\",\"sell\":746,\"buy\":746,\"volume\":356,\"changeRate\":0.015,\"nonWeightedIndex\":6852},{\"changeAmount\":1,\"weightedIndex\":6857,\"time\":\"10:23\",\"sell\":1298,\"buy\":1298,\"volume\":356,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6856,\"time\":\"10:24\",\"sell\":754,\"buy\":754,\"volume\":366,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6856,\"time\":\"10:25\",\"sell\":590,\"buy\":590,\"volume\":366,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":0,\"weightedIndex\":6856,\"time\":\"10:26\",\"sell\":590,\"buy\":590,\"volume\":376,\"changeRate\":0,\"nonWeightedIndex\":6850},{\"changeAmount\":0,\"weightedIndex\":6856,\"time\":\"10:27\",\"sell\":514,\"buy\":514,\"volume\":384,\"changeRate\":0,\"nonWeightedIndex\":6850},{\"changeAmount\":0,\"weightedIndex\":6856,\"time\":\"10:28\",\"sell\":472,\"buy\":472,\"volume\":384,\"changeRate\":0,\"nonWeightedIndex\":6850},{\"changeAmount\":0,\"weightedIndex\":6856,\"time\":\"10:29\",\"sell\":472,\"buy\":472,\"volume\":384,\"changeRate\":0,\"nonWeightedIndex\":6850},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:30\",\"sell\":464,\"buy\":464,\"volume\":392,\"changeRate\":0.029,\"nonWeightedIndex\":6851},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:31\",\"sell\":432,\"buy\":432,\"volume\":392,\"changeRate\":0.029,\"nonWeightedIndex\":6852},{\"changeAmount\":3,\"weightedIndex\":6856,\"time\":\"10:32\",\"sell\":660,\"buy\":660,\"volume\":404,\"changeRate\":0.044,\"nonWeightedIndex\":6852},{\"changeAmount\":3,\"weightedIndex\":6856,\"time\":\"10:33\",\"sell\":708,\"buy\":708,\"volume\":404,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":4,\"weightedIndex\":6856,\"time\":\"10:34\",\"sell\":604,\"buy\":604,\"volume\":412,\"changeRate\":0.058,\"nonWeightedIndex\":6853},{\"changeAmount\":4,\"weightedIndex\":6856,\"time\":\"10:35\",\"sell\":448,\"buy\":448,\"volume\":412,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:36\",\"sell\":540,\"buy\":540,\"volume\":422,\"changeRate\":0.029,\"nonWeightedIndex\":6852},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:37\",\"sell\":590,\"buy\":590,\"volume\":422,\"changeRate\":0.029,\"nonWeightedIndex\":6852},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:38\",\"sell\":560,\"buy\":560,\"volume\":422,\"changeRate\":0.029,\"nonWeightedIndex\":6852},{\"changeAmount\":3,\"weightedIndex\":6856,\"time\":\"10:39\",\"sell\":590,\"buy\":590,\"volume\":432,\"changeRate\":0.044,\"nonWeightedIndex\":6852},{\"changeAmount\":4,\"weightedIndex\":6856,\"time\":\"10:40\",\"sell\":588,\"buy\":588,\"volume\":440,\"changeRate\":0.058,\"nonWeightedIndex\":6853},{\"changeAmount\":4,\"weightedIndex\":6856,\"time\":\"10:41\",\"sell\":472,\"buy\":472,\"volume\":440,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":4,\"weightedIndex\":6856,\"time\":\"10:42\",\"sell\":440,\"buy\":440,\"volume\":440,\"changeRate\":0.058,\"nonWeightedIndex\":6854},{\"changeAmount\":3,\"weightedIndex\":6856,\"time\":\"10:43\",\"sell\":472,\"buy\":472,\"volume\":448,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":3,\"weightedIndex\":6856,\"time\":\"10:44\",\"sell\":472,\"buy\":472,\"volume\":448,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":3,\"weightedIndex\":6856,\"time\":\"10:45\",\"sell\":472,\"buy\":472,\"volume\":448,\"changeRate\":0.044,\"nonWeightedIndex\":6853},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:46\",\"sell\":532,\"buy\":532,\"volume\":458,\"changeRate\":0.029,\"nonWeightedIndex\":6852},{\"changeAmount\":2,\"weightedIndex\":6856,\"time\":\"10:47\",\"sell\":590,\"buy\":590,\"volume\":458,\"changeRate\":0.029,\"nonWeightedIndex\":6852},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:48\",\"sell\":490,\"buy\":490,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:49\",\"sell\":448,\"buy\":448,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:50\",\"sell\":472,\"buy\":472,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:51\",\"sell\":472,\"buy\":472,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:52\",\"sell\":472,\"buy\":472,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:53\",\"sell\":448,\"buy\":448,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:54\",\"sell\":472,\"buy\":472,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":1,\"weightedIndex\":6855,\"time\":\"10:55\",\"sell\":472,\"buy\":472,\"volume\":466,\"changeRate\":0.015,\"nonWeightedIndex\":6851},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"10:56\",\"sell\":536,\"buy\":536,\"volume\":486,\"changeRate\":-0.015,\"nonWeightedIndex\":6850},{\"changeAmount\":-2,\"weightedIndex\":6855,\"time\":\"10:57\",\"sell\":1168,\"buy\":1168,\"volume\":500,\"changeRate\":-0.029,\"nonWeightedIndex\":6848},{\"changeAmount\":-2,\"weightedIndex\":6855,\"time\":\"10:58\",\"sell\":826,\"buy\":826,\"volume\":500,\"changeRate\":-0.029,\"nonWeightedIndex\":6848},{\"changeAmount\":-2,\"weightedIndex\":6855,\"time\":\"10:59\",\"sell\":826,\"buy\":826,\"volume\":500,\"changeRate\":-0.029,\"nonWeightedIndex\":6848},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:00\",\"sell\":666,\"buy\":666,\"volume\":510,\"changeRate\":-0.015,\"nonWeightedIndex\":6848},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:01\",\"sell\":560,\"buy\":560,\"volume\":510,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:02\",\"sell\":590,\"buy\":590,\"volume\":510,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:03\",\"sell\":468,\"buy\":468,\"volume\":518,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":0,\"weightedIndex\":6855,\"time\":\"11:04\",\"sell\":348,\"buy\":348,\"volume\":522,\"changeRate\":0,\"nonWeightedIndex\":6849},{\"changeAmount\":0,\"weightedIndex\":6855,\"time\":\"11:05\",\"sell\":228,\"buy\":228,\"volume\":522,\"changeRate\":0,\"nonWeightedIndex\":6850},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:06\",\"sell\":434,\"buy\":434,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:07\",\"sell\":590,\"buy\":590,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:08\",\"sell\":560,\"buy\":560,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:09\",\"sell\":590,\"buy\":590,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:10\",\"sell\":590,\"buy\":590,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:11\",\"sell\":540,\"buy\":540,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-1,\"weightedIndex\":6855,\"time\":\"11:12\",\"sell\":590,\"buy\":590,\"volume\":532,\"changeRate\":-0.015,\"nonWeightedIndex\":6849},{\"changeAmount\":-2,\"weightedIndex\":6855,\"time\":\"11:13\",\"sell\":590,\"buy\":590,\"volume\":542,\"changeRate\":-0.029,\"nonWeightedIndex\":6848},{\"changeAmount\":-3,\"weightedIndex\":6854,\"time\":\"11:14\",\"sell\":610,\"buy\":610,\"volume\":554,\"changeRate\":-0.044,\"nonWeightedIndex\":6847},{\"changeAmount\":-4,\"weightedIndex\":6854,\"time\":\"11:15\",\"sell\":672,\"buy\":672,\"volume\":564,\"changeRate\":-0.058,\"nonWeightedIndex\":6847},{\"changeAmount\":-4,\"weightedIndex\":6854,\"time\":\"11:16\",\"sell\":590,\"buy\":590,\"volume\":564,\"changeRate\":-0.058,\"nonWeightedIndex\":6846},{\"changeAmount\":-4,\"weightedIndex\":6854,\"time\":\"11:17\",\"sell\":580,\"buy\":580,\"volume\":564,\"changeRate\":-0.058,\"nonWeightedIndex\":6846},{\"changeAmount\":-3,\"weightedIndex\":6854,\"time\":\"11:18\",\"sell\":590,\"buy\":590,\"volume\":574,\"changeRate\":-0.044,\"nonWeightedIndex\":6846},{\"changeAmount\":-3,\"weightedIndex\":6854,\"time\":\"11:19\",\"sell\":560,\"buy\":560,\"volume\":574,\"changeRate\":-0.044,\"nonWeightedIndex\":6847},{\"changeAmount\":-3,\"weightedIndex\":6854,\"time\":\"11:20\",\"sell\":590,\"buy\":590,\"volume\":574,\"changeRate\":-0.044,\"nonWeightedIndex\":6847},{\"changeAmount\":-5,\"weightedIndex\":6854,\"time\":\"11:21\",\"sell\":870,\"buy\":870,\"volume\":594,\"changeRate\":-0.073,\"nonWeightedIndex\":6845},{\"changeAmount\":-5,\"weightedIndex\":6854,\"time\":\"11:22\",\"sell\":1180,\"buy\":1180,\"volume\":594,\"changeRate\":-0.073,\"nonWeightedIndex\":6845},{\"changeAmount\":-5,\"weightedIndex\":6854,\"time\":\"11:23\",\"sell\":568,\"buy\":568,\"volume\":602,\"changeRate\":-0.073,\"nonWeightedIndex\":6845},{\"changeAmount\":-5,\"weightedIndex\":6854,\"time\":\"11:24\",\"sell\":472,\"buy\":472,\"volume\":602,\"changeRate\":-0.073,\"nonWeightedIndex\":6845},{\"changeAmount\":-4,\"weightedIndex\":6854,\"time\":\"11:25\",\"sell\":666,\"buy\":666,\"volume\":616,\"changeRate\":-0.058,\"nonWeightedIndex\":6845},{\"changeAmount\":-4,\"weightedIndex\":6854,\"time\":\"11:26\",\"sell\":756,\"buy\":756,\"volume\":616,\"changeRate\":-0.058,\"nonWeightedIndex\":6846},{\"changeAmount\":-3,\"weightedIndex\":6853,\"time\":\"11:27\",\"sell\":602,\"buy\":602,\"volume\":626,\"changeRate\":-0.044,\"nonWeightedIndex\":6846},{\"changeAmount\":-3,\"weightedIndex\":6853,\"time\":\"11:28\",\"sell\":590,\"buy\":590,\"volume\":626,\"changeRate\":-0.044,\"nonWeightedIndex\":6847},{\"changeAmount\":-5,\"weightedIndex\":6853,\"time\":\"11:29\",\"sell\":1070,\"buy\":1070,\"volume\":646,\"changeRate\":-0.073,\"nonWeightedIndex\":6845},{\"changeAmount\":-6,\"weightedIndex\":6853,\"time\":\"11:30\",\"sell\":1960,\"buy\":1960,\"volume\":686,\"changeRate\":-0.088,\"nonWeightedIndex\":6844},{\"changeAmount\":-6,\"weightedIndex\":6853,\"time\":\"13:31\",\"sell\":2360,\"buy\":2360,\"volume\":686,\"changeRate\":-0.088,\"nonWeightedIndex\":6844},{\"changeAmount\":-6,\"weightedIndex\":6852,\"time\":\"13:32\",\"sell\":1700,\"buy\":1700,\"volume\":696,\"changeRate\":-0.088,\"nonWeightedIndex\":6844},{\"changeAmount\":-6,\"weightedIndex\":6852,\"time\":\"13:33\",\"sell\":560,\"buy\":560,\"volume\":696,\"changeRate\":-0.088,\"nonWeightedIndex\":6844},{\"changeAmount\":-5,\"weightedIndex\":6852,\"time\":\"13:34\",\"sell\":632,\"buy\":632,\"volume\":708,\"changeRate\":-0.073,\"nonWeightedIndex\":6844},{\"changeAmount\":-5,\"weightedIndex\":6852,\"time\":\"13:35\",\"sell\":660,\"buy\":660,\"volume\":708,\"changeRate\":-0.073,\"nonWeightedIndex\":6845},{\"changeAmount\":-6,\"weightedIndex\":6852,\"time\":\"13:36\",\"sell\":756,\"buy\":756,\"volume\":728,\"changeRate\":-0.088,\"nonWeightedIndex\":6844},{\"changeAmount\":-6,\"weightedIndex\":6852,\"time\":\"13:37\",\"sell\":1180,\"buy\":1180,\"volume\":728,\"changeRate\":-0.088,\"nonWeightedIndex\":6844},{\"changeAmount\":-7,\"weightedIndex\":6852,\"time\":\"13:38\",\"sell\":1120,\"buy\":1120,\"volume\":750,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-7,\"weightedIndex\":6852,\"time\":\"13:39\",\"sell\":1298,\"buy\":1298,\"volume\":750,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-8,\"weightedIndex\":6852,\"time\":\"13:40\",\"sell\":1176,\"buy\":1176,\"volume\":764,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-8,\"weightedIndex\":6852,\"time\":\"13:41\",\"sell\":826,\"buy\":826,\"volume\":764,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-8,\"weightedIndex\":6852,\"time\":\"13:42\",\"sell\":826,\"buy\":826,\"volume\":764,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-10,\"weightedIndex\":6851,\"time\":\"13:43\",\"sell\":1264,\"buy\":1264,\"volume\":798,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-10,\"weightedIndex\":6851,\"time\":\"13:44\",\"sell\":2006,\"buy\":2006,\"volume\":798,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-10,\"weightedIndex\":6851,\"time\":\"13:45\",\"sell\":1424,\"buy\":1424,\"volume\":812,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-10,\"weightedIndex\":6851,\"time\":\"13:46\",\"sell\":826,\"buy\":826,\"volume\":812,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-9,\"weightedIndex\":6851,\"time\":\"13:47\",\"sell\":762,\"buy\":762,\"volume\":822,\"changeRate\":-0.131,\"nonWeightedIndex\":6840},{\"changeAmount\":-9,\"weightedIndex\":6851,\"time\":\"13:48\",\"sell\":590,\"buy\":590,\"volume\":822,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-8,\"weightedIndex\":6851,\"time\":\"13:49\",\"sell\":618,\"buy\":618,\"volume\":834,\"changeRate\":-0.117,\"nonWeightedIndex\":6841},{\"changeAmount\":-8,\"weightedIndex\":6851,\"time\":\"13:50\",\"sell\":672,\"buy\":672,\"volume\":834,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-7,\"weightedIndex\":6851,\"time\":\"13:51\",\"sell\":680,\"buy\":680,\"volume\":844,\"changeRate\":-0.102,\"nonWeightedIndex\":6842},{\"changeAmount\":-7,\"weightedIndex\":6851,\"time\":\"13:52\",\"sell\":550,\"buy\":550,\"volume\":844,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-7,\"weightedIndex\":6851,\"time\":\"13:53\",\"sell\":686,\"buy\":686,\"volume\":856,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-7,\"weightedIndex\":6851,\"time\":\"13:54\",\"sell\":708,\"buy\":708,\"volume\":856,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-7,\"weightedIndex\":6851,\"time\":\"13:55\",\"sell\":672,\"buy\":672,\"volume\":856,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-8,\"weightedIndex\":6850,\"time\":\"13:56\",\"sell\":668,\"buy\":668,\"volume\":866,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-8,\"weightedIndex\":6850,\"time\":\"13:57\",\"sell\":550,\"buy\":550,\"volume\":866,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-8,\"weightedIndex\":6850,\"time\":\"13:58\",\"sell\":706,\"buy\":706,\"volume\":878,\"changeRate\":-0.117,\"nonWeightedIndex\":6842},{\"changeAmount\":-9,\"weightedIndex\":6850,\"time\":\"13:59\",\"sell\":620,\"buy\":620,\"volume\":888,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-9,\"weightedIndex\":6850,\"time\":\"14:00\",\"sell\":590,\"buy\":590,\"volume\":888,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-9,\"weightedIndex\":6850,\"time\":\"14:01\",\"sell\":590,\"buy\":590,\"volume\":898,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-7,\"weightedIndex\":6850,\"time\":\"14:02\",\"sell\":616,\"buy\":616,\"volume\":910,\"changeRate\":-0.102,\"nonWeightedIndex\":6842},{\"changeAmount\":-7,\"weightedIndex\":6850,\"time\":\"14:03\",\"sell\":576,\"buy\":576,\"volume\":918,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-7,\"weightedIndex\":6850,\"time\":\"14:04\",\"sell\":448,\"buy\":448,\"volume\":918,\"changeRate\":-0.102,\"nonWeightedIndex\":6843},{\"changeAmount\":-9,\"weightedIndex\":6850,\"time\":\"14:05\",\"sell\":940,\"buy\":940,\"volume\":938,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-9,\"weightedIndex\":6850,\"time\":\"14:06\",\"sell\":1052,\"buy\":1052,\"volume\":946,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-9,\"weightedIndex\":6850,\"time\":\"14:07\",\"sell\":472,\"buy\":472,\"volume\":946,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-10,\"weightedIndex\":6850,\"time\":\"14:08\",\"sell\":472,\"buy\":472,\"volume\":954,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-12,\"weightedIndex\":6849,\"time\":\"14:09\",\"sell\":592,\"buy\":592,\"volume\":978,\"changeRate\":-0.175,\"nonWeightedIndex\":6839},{\"changeAmount\":-12,\"weightedIndex\":6849,\"time\":\"14:10\",\"sell\":1416,\"buy\":1416,\"volume\":978,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-13,\"weightedIndex\":6849,\"time\":\"14:11\",\"sell\":996,\"buy\":996,\"volume\":990,\"changeRate\":-0.19,\"nonWeightedIndex\":6837},{\"changeAmount\":-13,\"weightedIndex\":6849,\"time\":\"14:12\",\"sell\":708,\"buy\":708,\"volume\":990,\"changeRate\":-0.19,\"nonWeightedIndex\":6837},{\"changeAmount\":-12,\"weightedIndex\":6849,\"time\":\"14:13\",\"sell\":662,\"buy\":662,\"volume\":1000,\"changeRate\":-0.175,\"nonWeightedIndex\":6837},{\"changeAmount\":-12,\"weightedIndex\":6849,\"time\":\"14:14\",\"sell\":570,\"buy\":570,\"volume\":1000,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-11,\"weightedIndex\":6849,\"time\":\"14:15\",\"sell\":588,\"buy\":588,\"volume\":1012,\"changeRate\":-0.16,\"nonWeightedIndex\":6838},{\"changeAmount\":-11,\"weightedIndex\":6849,\"time\":\"14:16\",\"sell\":672,\"buy\":672,\"volume\":1012,\"changeRate\":-0.16,\"nonWeightedIndex\":6839},{\"changeAmount\":-11,\"weightedIndex\":6849,\"time\":\"14:17\",\"sell\":684,\"buy\":684,\"volume\":1024,\"changeRate\":-0.16,\"nonWeightedIndex\":6839},{\"changeAmount\":-11,\"weightedIndex\":6849,\"time\":\"14:18\",\"sell\":672,\"buy\":672,\"volume\":1024,\"changeRate\":-0.16,\"nonWeightedIndex\":6839},{\"changeAmount\":-11,\"weightedIndex\":6849,\"time\":\"14:19\",\"sell\":708,\"buy\":708,\"volume\":1024,\"changeRate\":-0.16,\"nonWeightedIndex\":6839},{\"changeAmount\":-10,\"weightedIndex\":6849,\"time\":\"14:20\",\"sell\":568,\"buy\":568,\"volume\":1034,\"changeRate\":-0.146,\"nonWeightedIndex\":6839},{\"changeAmount\":-10,\"weightedIndex\":6849,\"time\":\"14:21\",\"sell\":520,\"buy\":520,\"volume\":1034,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-10,\"weightedIndex\":6849,\"time\":\"14:22\",\"sell\":672,\"buy\":672,\"volume\":1046,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-9,\"weightedIndex\":6849,\"time\":\"14:23\",\"sell\":682,\"buy\":682,\"volume\":1056,\"changeRate\":-0.131,\"nonWeightedIndex\":6840},{\"changeAmount\":-9,\"weightedIndex\":6849,\"time\":\"14:24\",\"sell\":590,\"buy\":590,\"volume\":1056,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-9,\"weightedIndex\":6848,\"time\":\"14:25\",\"sell\":624,\"buy\":624,\"volume\":1068,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-9,\"weightedIndex\":6848,\"time\":\"14:26\",\"sell\":708,\"buy\":708,\"volume\":1068,\"changeRate\":-0.131,\"nonWeightedIndex\":6841},{\"changeAmount\":-10,\"weightedIndex\":6848,\"time\":\"14:27\",\"sell\":622,\"buy\":622,\"volume\":1078,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-10,\"weightedIndex\":6848,\"time\":\"14:28\",\"sell\":590,\"buy\":590,\"volume\":1078,\"changeRate\":-0.146,\"nonWeightedIndex\":6840},{\"changeAmount\":-12,\"weightedIndex\":6848,\"time\":\"14:29\",\"sell\":700,\"buy\":700,\"volume\":1098,\"changeRate\":-0.175,\"nonWeightedIndex\":6839},{\"changeAmount\":-12,\"weightedIndex\":6848,\"time\":\"14:30\",\"sell\":1180,\"buy\":1180,\"volume\":1098,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-12,\"weightedIndex\":6848,\"time\":\"14:31\",\"sell\":1120,\"buy\":1120,\"volume\":1098,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-11,\"weightedIndex\":6848,\"time\":\"14:32\",\"sell\":650,\"buy\":650,\"volume\":1108,\"changeRate\":-0.16,\"nonWeightedIndex\":6838},{\"changeAmount\":-11,\"weightedIndex\":6848,\"time\":\"14:33\",\"sell\":590,\"buy\":590,\"volume\":1108,\"changeRate\":-0.16,\"nonWeightedIndex\":6839},{\"changeAmount\":-11,\"weightedIndex\":6848,\"time\":\"14:34\",\"sell\":434,\"buy\":434,\"volume\":1116,\"changeRate\":-0.16,\"nonWeightedIndex\":6839},{\"changeAmount\":-12,\"weightedIndex\":6848,\"time\":\"14:35\",\"sell\":546,\"buy\":546,\"volume\":1126,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-12,\"weightedIndex\":6848,\"time\":\"14:36\",\"sell\":560,\"buy\":560,\"volume\":1126,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-12,\"weightedIndex\":6848,\"time\":\"14:37\",\"sell\":590,\"buy\":590,\"volume\":1126,\"changeRate\":-0.175,\"nonWeightedIndex\":6838},{\"changeAmount\":-14,\"weightedIndex\":6848,\"time\":\"14:38\",\"sell\":560,\"buy\":560,\"volume\":1136,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6848,\"time\":\"14:39\",\"sell\":590,\"buy\":590,\"volume\":1136,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-15,\"weightedIndex\":6848,\"time\":\"14:40\",\"sell\":884,\"buy\":884,\"volume\":1152,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6848,\"time\":\"14:41\",\"sell\":912,\"buy\":912,\"volume\":1152,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6848,\"time\":\"14:42\",\"sell\":820,\"buy\":820,\"volume\":1162,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6848,\"time\":\"14:43\",\"sell\":560,\"buy\":560,\"volume\":1162,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6848,\"time\":\"14:44\",\"sell\":590,\"buy\":590,\"volume\":1162,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-17,\"weightedIndex\":6847,\"time\":\"14:45\",\"sell\":550,\"buy\":550,\"volume\":1172,\"changeRate\":-0.248,\"nonWeightedIndex\":6833},{\"changeAmount\":-17,\"weightedIndex\":6847,\"time\":\"14:46\",\"sell\":590,\"buy\":590,\"volume\":1172,\"changeRate\":-0.248,\"nonWeightedIndex\":6833},{\"changeAmount\":-17,\"weightedIndex\":6847,\"time\":\"14:47\",\"sell\":608,\"buy\":608,\"volume\":1184,\"changeRate\":-0.248,\"nonWeightedIndex\":6833},{\"changeAmount\":-17,\"weightedIndex\":6847,\"time\":\"14:48\",\"sell\":708,\"buy\":708,\"volume\":1184,\"changeRate\":-0.248,\"nonWeightedIndex\":6833},{\"changeAmount\":-16,\"weightedIndex\":6847,\"time\":\"14:49\",\"sell\":650,\"buy\":650,\"volume\":1194,\"changeRate\":-0.233,\"nonWeightedIndex\":6833},{\"changeAmount\":-16,\"weightedIndex\":6847,\"time\":\"14:50\",\"sell\":590,\"buy\":590,\"volume\":1194,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-15,\"weightedIndex\":6847,\"time\":\"14:51\",\"sell\":498,\"buy\":498,\"volume\":1202,\"changeRate\":-0.219,\"nonWeightedIndex\":6834},{\"changeAmount\":-15,\"weightedIndex\":6847,\"time\":\"14:52\",\"sell\":472,\"buy\":472,\"volume\":1202,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-14,\"weightedIndex\":6847,\"time\":\"14:53\",\"sell\":530,\"buy\":530,\"volume\":1212,\"changeRate\":-0.204,\"nonWeightedIndex\":6835},{\"changeAmount\":-14,\"weightedIndex\":6847,\"time\":\"14:54\",\"sell\":560,\"buy\":560,\"volume\":1212,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6847,\"time\":\"14:55\",\"sell\":590,\"buy\":590,\"volume\":1222,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6847,\"time\":\"14:56\",\"sell\":550,\"buy\":550,\"volume\":1222,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6847,\"time\":\"14:57\",\"sell\":590,\"buy\":590,\"volume\":1222,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-15,\"weightedIndex\":6847,\"time\":\"14:58\",\"sell\":920,\"buy\":920,\"volume\":1242,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6847,\"time\":\"14:59\",\"sell\":1180,\"buy\":1180,\"volume\":1242,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6847,\"time\":\"15:00\",\"sell\":828,\"buy\":828,\"volume\":1254,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6847,\"time\":\"15:01\",\"sell\":708,\"buy\":708,\"volume\":1254,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:02\",\"sell\":904,\"buy\":904,\"volume\":1274,\"changeRate\":-0.204,\"nonWeightedIndex\":6835},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:03\",\"sell\":1160,\"buy\":1160,\"volume\":1274,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-13,\"weightedIndex\":6846,\"time\":\"15:04\",\"sell\":804,\"buy\":804,\"volume\":1286,\"changeRate\":-0.19,\"nonWeightedIndex\":6836},{\"changeAmount\":-13,\"weightedIndex\":6846,\"time\":\"15:05\",\"sell\":660,\"buy\":660,\"volume\":1286,\"changeRate\":-0.19,\"nonWeightedIndex\":6837},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:06\",\"sell\":800,\"buy\":800,\"volume\":1306,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:07\",\"sell\":1100,\"buy\":1100,\"volume\":1306,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:08\",\"sell\":1180,\"buy\":1180,\"volume\":1306,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-15,\"weightedIndex\":6846,\"time\":\"15:09\",\"sell\":1080,\"buy\":1080,\"volume\":1326,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-15,\"weightedIndex\":6846,\"time\":\"15:10\",\"sell\":1100,\"buy\":1100,\"volume\":1326,\"changeRate\":-0.219,\"nonWeightedIndex\":6835},{\"changeAmount\":-16,\"weightedIndex\":6846,\"time\":\"15:11\",\"sell\":1100,\"buy\":1100,\"volume\":1346,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-16,\"weightedIndex\":6846,\"time\":\"15:12\",\"sell\":1180,\"buy\":1180,\"volume\":1346,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-16,\"weightedIndex\":6846,\"time\":\"15:13\",\"sell\":844,\"buy\":844,\"volume\":1360,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-16,\"weightedIndex\":6846,\"time\":\"15:14\",\"sell\":826,\"buy\":826,\"volume\":1360,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-15,\"weightedIndex\":6846,\"time\":\"15:15\",\"sell\":634,\"buy\":634,\"volume\":1370,\"changeRate\":-0.219,\"nonWeightedIndex\":6834},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:16\",\"sell\":650,\"buy\":650,\"volume\":1390,\"changeRate\":-0.204,\"nonWeightedIndex\":6835},{\"changeAmount\":-14,\"weightedIndex\":6846,\"time\":\"15:17\",\"sell\":1180,\"buy\":1180,\"volume\":1390,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-16,\"weightedIndex\":6845,\"time\":\"15:18\",\"sell\":1160,\"buy\":1160,\"volume\":1430,\"changeRate\":-0.233,\"nonWeightedIndex\":6835},{\"changeAmount\":-16,\"weightedIndex\":6845,\"time\":\"15:19\",\"sell\":2360,\"buy\":2360,\"volume\":1430,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-16,\"weightedIndex\":6845,\"time\":\"15:20\",\"sell\":2200,\"buy\":2200,\"volume\":1430,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-17,\"weightedIndex\":6845,\"time\":\"15:21\",\"sell\":1112,\"buy\":1112,\"volume\":1444,\"changeRate\":-0.248,\"nonWeightedIndex\":6833},{\"changeAmount\":-16,\"weightedIndex\":6845,\"time\":\"15:22\",\"sell\":706,\"buy\":706,\"volume\":1454,\"changeRate\":-0.233,\"nonWeightedIndex\":6833},{\"changeAmount\":-16,\"weightedIndex\":6845,\"time\":\"15:23\",\"sell\":590,\"buy\":590,\"volume\":1454,\"changeRate\":-0.233,\"nonWeightedIndex\":6834},{\"changeAmount\":-15,\"weightedIndex\":6845,\"time\":\"15:24\",\"sell\":592,\"buy\":592,\"volume\":1468,\"changeRate\":-0.219,\"nonWeightedIndex\":6834},{\"changeAmount\":-14,\"weightedIndex\":6845,\"time\":\"15:25\",\"sell\":838,\"buy\":838,\"volume\":1484,\"changeRate\":-0.204,\"nonWeightedIndex\":6835},{\"changeAmount\":-14,\"weightedIndex\":6845,\"time\":\"15:26\",\"sell\":880,\"buy\":880,\"volume\":1484,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6845,\"time\":\"15:27\",\"sell\":854,\"buy\":854,\"volume\":1494,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-14,\"weightedIndex\":6845,\"time\":\"15:28\",\"sell\":550,\"buy\":550,\"volume\":1494,\"changeRate\":-0.204,\"nonWeightedIndex\":6836},{\"changeAmount\":-13,\"weightedIndex\":6845,\"time\":\"15:29\",\"sell\":590,\"buy\":590,\"volume\":1504,\"changeRate\":-0.19,\"nonWeightedIndex\":6836},{\"changeAmount\":-12,\"weightedIndex\":6845,\"time\":\"15:30\",\"sell\":900,\"buy\":900,\"volume\":1524,\"changeRate\":-0.175,\"nonWeightedIndex\":6837}]}")
                    .getJSONArray("data");
            for (int i = 0; i < jsonArrayData.length(); i++) {
                data = jsonArrayData.getJSONObject(i);
                timesEntityList.add(new TimesEntity(data.getString("time"), data
                        .getDouble("weightedIndex"), data.getDouble("nonWeightedIndex"), data
                        .getInt("buy"), data.getInt("sell"), data.getInt("volume")));
            }
            TimesView timesView = (TimesView)stockTradeLine.findViewById(R.id.stockTimeLineLinearChart);
            timesView.setTimeAxis(Arrays.asList("9:30", "11:30/13:00", "15:00"));
            timesView.setTimesList(timesEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Calendar beforeOpeningCalendar = Calendar.getInstance();
        beforeOpeningCalendar.set(Calendar.HOUR_OF_DAY, 9);
        beforeOpeningCalendar.set(Calendar.MINUTE, 30);
        beforeOpeningCalendar.set(Calendar.SECOND, 0);
        Calendar afterClosingCalendar = Calendar.getInstance();
        afterClosingCalendar.set(Calendar.HOUR_OF_DAY, 9);
        afterClosingCalendar.set(Calendar.MINUTE, 30);
        afterClosingCalendar.set(Calendar.SECOND, 0);
        if (calendar.after(beforeOpeningCalendar) && calendar.before(afterClosingCalendar)) {
            tradeStatus.setText("交易中 " + dateFormat.format(stockEntity.getCurrentDateTime()));
            tradeStatus.refreshDrawableState();
            setStockPriceInfo(true);
        } else if (calendar.before(beforeOpeningCalendar)) {
            TextView currentPrice = (TextView)stockPriceInfo.findViewById(R.id.currentPrice);
            TextView currentChange = (TextView)stockPriceInfo.findViewById(R.id.currentChange);
            TextView currentChangeRate = (TextView)stockPriceInfo.findViewById(R.id.currentChangeRate);
            tradeStatus.setText("未开盘 " + dateFormat.format(stockEntity.getCurrentDateTime()));
            tradeStatus.refreshDrawableState();
            setStockPriceInfo(false);
        } else {
            String tradeStatusText = "已收盘 " + dateFormat.format(stockEntity.getCurrentDateTime());
            if (tradeStatus.getText() != tradeStatusText) {
                tradeStatus.setText(tradeStatusText);
                setStockPriceInfo(true);
            }
        }
    }

    public void setStockPriceInfo(boolean isValid) {
        TextView currentPrice = (TextView) stockPriceInfo.findViewById(R.id.currentPrice);
        TextView currentChange = (TextView) stockPriceInfo.findViewById(R.id.currentChange);
        TextView currentChangeRate = (TextView) stockPriceInfo.findViewById(R.id.currentChangeRate);
        if (isValid) {
            tradeStatus.refreshDrawableState();
            currentPrice.setText(String.format("%.02f", stockEntity.getCurrentPrice()));
            String prefix;
            int textColor;
            if (Float.compare(stockEntity.getChange(), 0.0f) > 0) {
                prefix = "+";
                textColor = Color.RED;
            } else if (Float.compare(stockEntity.getChange(), 0.0f) < 0) {
                prefix = "-";
                textColor = Color.GREEN;
            } else {
                prefix = "";
                textColor = Color.GRAY;
            }
            currentChange.setText(prefix + String.format("%.02f", stockEntity.getChange()));
            currentChangeRate.setText(prefix + String.format("%.02f%%", stockEntity.getChangeRate() * 100));
            currentPrice.setTextColor(textColor);
            currentChange.setTextColor(textColor);
            currentChangeRate.setTextColor(textColor);
        } else {

            currentPrice.setText("--");
            currentChange.setText("--");
            currentChangeRate.setText("--");
            currentPrice.setTextColor(Color.GRAY);
            currentChange.setTextColor(Color.GRAY);
            currentChangeRate.setTextColor(Color.GRAY);
        }
    }
}
