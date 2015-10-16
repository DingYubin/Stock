package ruifu.com.shares.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.ChartDataRow;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.DateValueEntity;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.demo.controller.LineChartController;
import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.R;
import ruifu.com.shares.entity.Stock;

/**
 * Created by 王群 on 2015/10/16.
 */
public class StockActivity extends BaseActivity {
    private Stock stock;
    private View returnView;
    private View refreshView;
    private TextView currentPrice;
    private TextView currentChange;
    private TextView currentChangeRate;
    private GridChart timeLineLinearChart;
    private LineChartController timeLineChartController;
    private ChartDataSet timeLineData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("StockActivity", "onCreate");
        setContentView(R.layout.activity_stock_page);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUpView() {
        Log.d("StockActivity", "setUpView");
        returnView = findViewById(R.id.returnTextView);
        refreshView = findViewById(R.id.refreshTextView);
        timeLineLinearChart = (GridChart)findViewById(R.id.stockTimeLineLinearChart);
        currentPrice = (TextView)findViewById(R.id.currentPrice);
        currentChange = (TextView)findViewById(R.id.currentChange);
        currentChangeRate = (TextView)findViewById(R.id.currentChangeRate);
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
                refresh();
            }
        });
    }

    @Override
    public void fillData() {
        stock = (Stock)getIntent().getExtras().get("stock.current");
        timeLineData = new ChartDataSet();
        LineEntity realTimePrice = new LineEntity();
        realTimePrice.setTitle("HIGH");
        realTimePrice.setLineColor(Color.WHITE);
        ArrayList<ChartDataRow> dv = new ArrayList<ChartDataRow>();
        dv.add(new DateValueEntity(947.3056f, 20130424));
        dv.add(new DateValueEntity(952.2242f, 20130425));
        dv.add(new DateValueEntity(963.2635f, 20130426));
        dv.add(new DateValueEntity(961.9385f, 20130502));
        dv.add(new DateValueEntity(962.3391f, 20130503));
        dv.add(new DateValueEntity(961.9631f, 20130506));
        dv.add(new DateValueEntity(961.916f, 20130507));
        dv.add(new DateValueEntity(961.9375f, 20130508));
        dv.add(new DateValueEntity(962.1758f, 20130509));
        dv.add(new DateValueEntity(962.1837f, 20130510));
        dv.add(new DateValueEntity(962.1995f, 20130513));
        dv.add(new DateValueEntity(962.1158f, 20130514));
        dv.add(new DateValueEntity(962.2931f, 20130515));
        dv.add(new DateValueEntity(963.1225f, 20130516));
        dv.add(new DateValueEntity(965.0629f, 20130517));
        dv.add(new DateValueEntity(969.385f, 20130520));
        dv.add(new DateValueEntity(975.5116f, 20130521));
        dv.add(new DateValueEntity(974.0666f, 20130522));
        dv.add(new DateValueEntity(974.2079f, 20130523));
        dv.add(new DateValueEntity(977.2924f, 20130524));
        dv.add(new DateValueEntity(977.4907f, 20130527));
        dv.add(new DateValueEntity(976.429f, 20130528));
        dv.add(new DateValueEntity(977.8235f, 20130529));
        dv.add(new DateValueEntity(981.4609f, 20130530));
        dv.add(new DateValueEntity(983.0612f, 20130531));
        dv.add(new DateValueEntity(978.343f, 20130603));
        dv.add(new DateValueEntity(972.4412f, 20130604));
        dv.add(new DateValueEntity(965.072f, 20130605));
        dv.add(new DateValueEntity(954.1762f, 20130606));
        dv.add(new DateValueEntity(941.5963f, 20130607));
        dv.add(new DateValueEntity(921.8664f, 20130613));
        dv.add(new DateValueEntity(905.6599f, 20130614));
        dv.add(new DateValueEntity(891.2146f, 20130617));
        dv.add(new DateValueEntity(879.2878f, 20130618));
        dv.add(new DateValueEntity(865.2361f, 20130619));
        dv.add(new DateValueEntity(843.2399f, 20130620));
        dv.add(new DateValueEntity(821.4298f, 20130621));
        dv.add(new DateValueEntity(784.0339f, 20130624));
        dv.add(new DateValueEntity(759.5865f, 20130625));
        dv.add(new DateValueEntity(738.5209f, 20130626));
        dv.add(new DateValueEntity(723.5436f, 20130627));
        dv.add(new DateValueEntity(720.2877f, 20130628));
        dv.add(new DateValueEntity(718.5511f, 20130701));
        dv.add(new DateValueEntity(720.9672f, 20130702));
        dv.add(new DateValueEntity(725.9567f, 20130703));
        dv.add(new DateValueEntity(726.3284f, 20130704));
        dv.add(new DateValueEntity(728.0508f, 20130705));
        dv.add(new DateValueEntity(728.961f, 20130708));
        dv.add(new DateValueEntity(730.1062f, 20130709));
        dv.add(new DateValueEntity(734.6287f, 20130710));
        dv.add(new DateValueEntity(736.1662f, 20130711));
        dv.add(new DateValueEntity(739.5985f, 20130712));
        dv.add(new DateValueEntity(743.5045f, 20130715));
        dv.add(new DateValueEntity(749.4669f, 20130716));
        dv.add(new DateValueEntity(753.7623f, 20130717));
        dv.add(new DateValueEntity(753.6917f, 20130718));
        dv.add(new DateValueEntity(752.4678f, 20130719));
        dv.add(new DateValueEntity(760.7568f, 20130722));
        dv.add(new DateValueEntity(765.0131f, 20130723));
        dv.add(new DateValueEntity(768.8569f, 20130724));
        dv.add(new DateValueEntity(770.9514f, 20130725));
        dv.add(new DateValueEntity(768.5318f, 20130726));
        dv.add(new DateValueEntity(762.7225f, 20130729));
        dv.add(new DateValueEntity(759.3295f, 20130730));
        dv.add(new DateValueEntity(757.1793f, 20130731));
        dv.add(new DateValueEntity(756.1526f, 20130801));
        dv.add(new DateValueEntity(755.1125f, 20130802));
        dv.add(new DateValueEntity(756.6308f, 20130805));
        dv.add(new DateValueEntity(757.8153f, 20130806));
        dv.add(new DateValueEntity(757.0371f, 20130807));
        dv.add(new DateValueEntity(763.2288f, 20130808));
        dv.add(new DateValueEntity(764.5119f, 20130809));
        dv.add(new DateValueEntity(767.9202f, 20130812));
        dv.add(new DateValueEntity(770.146f, 20130813));
        dv.add(new DateValueEntity(772.2369f, 20130814));
        dv.add(new DateValueEntity(772.1298f, 20130815));
        dv.add(new DateValueEntity(771.5269f, 20130816));
        dv.add(new DateValueEntity(770.4365f, 20130819));
        dv.add(new DateValueEntity(767.9823f, 20130820));
        dv.add(new DateValueEntity(767.901f, 20130821));
        dv.add(new DateValueEntity(768.2333f, 20130822));
        dv.add(new DateValueEntity(769.7356f, 20130823));
        dv.add(new DateValueEntity(772.7566f, 20130826));
        dv.add(new DateValueEntity(771.9353f, 20130827));
        dv.add(new DateValueEntity(772.5748f, 20130828));
        dv.add(new DateValueEntity(774.17f, 20130829));
        dv.add(new DateValueEntity(776.6239f, 20130830));
        dv.add(new DateValueEntity(779.4005f, 20130902));
        dv.add(new DateValueEntity(782.8205f, 20130903));
        dv.add(new DateValueEntity(787.7852f, 20130904));
        dv.add(new DateValueEntity(795.1398f, 20130905));
        dv.add(new DateValueEntity(798.0329f, 20130906));
        dv.add(new DateValueEntity(777.0803f, 20130909));
        dv.add(new DateValueEntity(745.4303f, 20130910));
        dv.add(new DateValueEntity(733.794f, 20130911));
        dv.add(new DateValueEntity(713.0938f, 20130912));
        dv.add(new DateValueEntity(709.4212f, 20130913));
        dv.add(new DateValueEntity(715.0446f, 20130916));
        dv.add(new DateValueEntity(727.5064f, 20130917));
        dv.add(new DateValueEntity(742.578f, 20130918));
        dv.add(new DateValueEntity(759.8558f, 20130923));
        dv.add(new DateValueEntity(781.4722f, 20130924));
        dv.add(new DateValueEntity(799.6322f, 20130925));
        dv.add(new DateValueEntity(813.7519f, 20130926));
        dv.add(new DateValueEntity(828.4345f, 20130927));
        dv.add(new DateValueEntity(844.6599f, 20130930));
        dv.add(new DateValueEntity(861.8906f, 20131008));
        dv.add(new DateValueEntity(881.4863f, 20131009));
        dv.add(new DateValueEntity(897.0036f, 20131010));
        dv.add(new DateValueEntity(918.4781f, 20131011));
        dv.add(new DateValueEntity(940.6985f, 20131014));
        dv.add(new DateValueEntity(951.0224f, 20131015));
        dv.add(new DateValueEntity(942.7723f, 20131016));
        dv.add(new DateValueEntity(932.7551f, 20131017));
        dv.add(new DateValueEntity(924.7807f, 20131018));
        dv.add(new DateValueEntity(936.6127f, 20131021));
        dv.add(new DateValueEntity(945.5508f, 20131022));
        dv.add(new DateValueEntity(952.1615f, 20131023));
        dv.add(new DateValueEntity(950.4466f, 20131024));
        dv.add(new DateValueEntity(953.2289f, 20131025));
        dv.add(new DateValueEntity(963.9264f, 20131028));
        dv.add(new DateValueEntity(968.6712f, 20131029));
        dv.add(new DateValueEntity(972.3124f, 20131030));
        dv.add(new DateValueEntity(972.3439f, 20131031));
        dv.add(new DateValueEntity(971.8104f, 20131101));
        dv.add(new DateValueEntity(972.5886f, 20131104));
         realTimePrice.setTableData(dv);
        timeLineData.add(realTimePrice);
        timeLineChartController = new LineChartController();
        timeLineChartController.setLineData(timeLineData);
        timeLineChartController.setGridChart(timeLineLinearChart);
        TextView stockNameTitle = (TextView)findViewById(R.id.stockNameTitle);
        stockNameTitle.setText(stock.getName() + "(" + stock.getCode() + ")");
        refresh();
    }

    public void refresh() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TextView tradeStatus = (TextView)findViewById(R.id.tradeStatusTextView);
        if ((hour > 9 && hour < 15) || (hour == 9 && minute >= 30)) {
            tradeStatus.setText("交易中 " + dateFormat.format(calendar.getTime()));
        } else if (hour >= 0 && hour <= 9) {
            tradeStatus.setText("未开盘 " + dateFormat.format(calendar.getTime()));
        } else {
            tradeStatus.setText("已收盘 " + dateFormat.format(calendar.getTime()));
        }
        currentPrice.setText(String.format("%.02f", stock.getPrice() / 100.0));
        if (stock.getChange() > 0) {
            currentChange.setText(String.format("+%.02f", stock.getChange() / 100.0));
            currentChangeRate.setText(String.format("+%.02f%%", stock.getChangeRate()));
            currentPrice.setTextColor(Color.RED);
            currentChange.setTextColor(Color.RED);
            currentChangeRate.setTextColor(Color.RED);
        } else if (stock.getChange() < 0) {
            currentChange.setText(String.format("-%.02f", stock.getChange() / 100.0));
            currentChangeRate.setText(String.format("-%.02f%%", stock.getChangeRate()));
            currentPrice.setTextColor(Color.GREEN);
            currentChange.setTextColor(Color.GREEN);
            currentChangeRate.setTextColor(Color.GREEN);
        } else {
            currentChange.setText("0.00");
            currentChangeRate.setText("0.00%");
            currentPrice.setTextColor(Color.GRAY);
            currentChange.setTextColor(Color.GRAY);
            currentChangeRate.setTextColor(Color.GRAY);
        }
    }
}
