package teamtreehouse.com.stormy.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.DayAdapter;
import teamtreehouse.com.stormy.weather.Day;

public class DailyForecastActivity extends Activity {

    private Day[] mDays;

    @InjectView(android.R.id.list) ListView mListView;
    @InjectView(android.R.id.empty) TextView mEmptyTextView;
    @InjectView(android.R.id.button1) Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.inject(DailyForecastActivity.this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mEmptyTextView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeek = mDays[position].getDayOfTheWeek();
                String conditions = mDays[position].getSummary();
                String highTemp = mDays[position].getTemperatureMax() + "";
                String message = String.format("On %s the high will be %s and it will be %s",
                        dayOfTheWeek,
                        highTemp,
                        conditions);
                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
//
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        String dayOfTheWeek = mDays[position].getDayOfTheWeek();
//        String conditions = mDays[position].getSummary();
//        String highTemp = mDays[position].getTemperatureMax() + "";
//        String message = String.format("On %s the high will be %s and it will be %s",
//                dayOfTheWeek,
//                highTemp,
//                conditions);
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }

// OnListItemClick.  When an item is clicked a toast message appears and displays a string constructed of values from mDays array.
}



/*
// If you wanted to use a regular ListView here are the steps
1.  Use butterknife and @InjectView(android.R.id.empty) (TextView) mEmptyTextView
Basically @InjectView(THE SPECIFIC RESOURCE ID OF THE WHATEVER LABEL) (CAST TO WHATEVER) NAME_IT_NOW;

2.  When you use butterknife you have to then specify you used it as Butterknife.inject(this).
Butterknife.inject(THIS IS THE ACTIVITY THAT YOU ARE USING IT IN.  THIS refers to the present activity which is DailyForecastActivity.this or just this)

3.  You then use mListView or the ListView and then pass the adapter information into it.  mListView.setListAdapter(adapter);
4.  You then set mListView.setEmptyView(mEmptyTextView).  This sets what happens when the mListView is empty.

5.  You then have to create an inner class event... action by doing the following:
Type setonitem and let it auto complete... then inside you pass in (new AdapterView.OnItemClick...) and press enter for it to auto complete.

6.  Inside the inner class you construct the string then create a Toast message that appears when a List Item is clicked on.  Once it's clicked on
        the message/string you created gets shown as a toast notification.*/




