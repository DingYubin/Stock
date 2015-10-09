package ruifu.com.shares;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/9.
 */
public class MyApplication extends Application{
    private List<Activity> activityList;

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivitys(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        activityList = new ArrayList<Activity>();
    }
}
