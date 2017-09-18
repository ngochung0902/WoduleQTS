package wodule.com.wodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActExaminer extends AppCompatActivity implements View.OnClickListener {
    private TextView lbLogout,lbName,lbId,tvIdExam,lbSchool,lbSex,lbAge,lbExamhistory,lbCalender,lbStartExam;
    private RatingBar ratingBar2;
    private ImageView iconBag,iconCalendar,iconStart,btnEdit;
    private APIService mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_examiner);
        initUI();
        mAPIService = APIUtils.getAPIService();
        getProfile();
    }

    private void initUI() {
        lbLogout = (TextView) findViewById(R.id.lbLogout);
        lbName = (TextView) findViewById(R.id.lbName);
        lbId = (TextView) findViewById(R.id.lbId);
        tvIdExam = (TextView) findViewById(R.id.tvIdExam);
        lbSchool = (TextView) findViewById(R.id.lbSchool);
        lbSex = (TextView) findViewById(R.id.lbSex);
        lbAge = (TextView) findViewById(R.id.lbAge);
        lbExamhistory = (TextView) findViewById(R.id.lbExamhistory);
        lbCalender = (TextView) findViewById(R.id.lbCalender);
        lbStartExam = (TextView) findViewById(R.id.lbStartExam);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        iconBag = (ImageView) findViewById(R.id.iconBag);
        iconCalendar = (ImageView) findViewById(R.id.iconCalendar);
        iconStart = (ImageView) findViewById(R.id.iconStart);
        btnEdit = (ImageView) findViewById(R.id.btnEdit);

        iconBag.setOnClickListener(this);
        lbExamhistory.setOnClickListener(this);
        iconCalendar.setOnClickListener(this);
        lbCalender.setOnClickListener(this);
        iconStart.setOnClickListener(this);
        lbStartExam.setOnClickListener(this);
        lbLogout.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iconBag)
        {
            Log.e("onlickexminar","onclick");
            startActivity(new Intent(ActExaminer.this,ActAssessmentHistoryE.class));
        }
        if (v.getId()==R.id.lbExamhistory)
        {
            startActivity(new Intent(ActExaminer.this, ActAssessmentHistoryE.class));
        }
        if (v.getId()==R.id.iconCalendar)
        {
            startActivity(new Intent(ActExaminer.this, ActCalendarE.class));
        }
        if (v.getId()==R.id.lbCalender)
        {
            startActivity(new Intent(ActExaminer.this, ActCalendarE.class));
        }
        if (v.getId()==R.id.iconStart)
        {
            startActivity(new Intent(ActExaminer.this, ActAssessmentStartE.class));
        }
        if (v.getId()==R.id.lbStartExam)
        {
            startActivity(new Intent(ActExaminer.this, ActAssessmentStartE.class));
        }
        if (v.getId()==R.id.lbLogout)
        {
            startActivity(new Intent(ActExaminer.this, ActLogin.class));
            finish();
            QTSHelp.setIsLogin(ActExaminer.this,false);
        }
        if (v.getId()==R.id.btnEdit)
        {

        }
    }

    public void getProfile(){
        //mAPIService.getAnswers().request().headers("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI4LCJpc3MiOiJodHRwOi8vd29kdWxlLmlvL2FwaS91c2VyX2xvZ2luIiwiaWF0IjoxNTA1NzMwOTU4LCJleHAiOjE1MDU3MzQ1NTgsIm5iZiI6MTUwNTczMDk1OCwianRpIjoibmJqVHJVc1FXTEdIOGtSUCJ9.2HvlZXasZNcGiCWX5bIoPNaweIS7dtHQM-ZGKYTe07w");
        mAPIService.getAnswers().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    Log.e("getProfile",response.message().toString());
                }
                else
                    Log.e("getProfile",response.message().toString());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
