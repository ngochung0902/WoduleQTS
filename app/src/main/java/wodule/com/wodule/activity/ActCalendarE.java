package wodule.com.wodule.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import wodule.com.wodule.R;
import wodule.com.wodule.adapter.AdapterCalendar;


public class ActCalendarE extends AppCompatActivity {
    private static final String TAG = ActCalendarE.class.getSimpleName();
    public Calendar month;
    public AdapterCalendar adapter;
    public Handler handler;
    private TextView tvtoday, lbSun, lbMon, lbThu, lbWed, lbTue, lbFri, lbSat;
    private TextView lbMonthyear;
    GridView gridview;
    String strSearch = "";
    int pos = -1;
    private String mTitle;
    private TextView lbTitle;
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calendar_e);
        lbTitle = (TextView) findViewById(R.id.lbTitle);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        mTitle = getIntent().getStringExtra("title");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lbTitle.setText(mTitle);
        month = Calendar.getInstance();
        adapter = new AdapterCalendar(this, month, -1);

        gridview = (GridView) findViewById(R.id.gridview);
//		gridview.setLayoutParams(new GridView.AUTO_FIT, LakRun.GetWidthDevice(getApplicationContext()) / 7);
        gridview.setAdapter(adapter);
        handler = new Handler();
        handler.post(calendarUpdater);

        lbMonthyear = (TextView) findViewById(R.id.lbMonthyear);
        lbMonthyear.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

        TextView previous = (TextView) findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH)) {
                    month.set((month.get(Calendar.YEAR) - 1),
                            month.getActualMaximum(Calendar.MONTH), 1);
                } else {
                    month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
                }
                refreshCalendar();
            }
        });

        TextView next = (TextView) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (month.get(Calendar.MONTH) == month
                        .getActualMaximum(Calendar.MONTH)) {
                    month.set((month.get(Calendar.YEAR) + 1),
                            month.getActualMinimum(Calendar.MONTH), 1);
                } else {
                    month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
                }
                refreshCalendar();

            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                view.setTag(position);
                pos = (Integer) view.getTag();
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                        InputMethodManager.HIDE_NOT_ALWAYS);
//				Log.e("position", position + "");
                TextView date = (TextView) view.findViewById(R.id.date);
                String days = date.getText().toString();
                if (days.length() < 2) {
                    days = "0" + days;
                }
                int sMonth = month.get(Calendar.MONTH) + 1;
                String strMonth;
                if (sMonth < 10) {
                    strMonth = "0" + sMonth;
                } else {
                    strMonth = String.valueOf(sMonth);
                }
//				LakRun.showToast(getApplicationContext(),"date:"+month.get(Calendar.YEAR)+"-"+strMonth+"-"+days);
                strSearch = month.get(Calendar.YEAR) + "-" + strMonth + "-" + days;
                adapter = new AdapterCalendar(ActCalendarE.this, month, pos);
                gridview.setAdapter(adapter);
                refreshCalendar();

            }
        });
    }

    public void refreshCalendar() {
        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some random calendar items

        lbMonthyear.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }


    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            adapter.notifyDataSetChanged();
        }
    };

}
