package wodule.com.wodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActAssessor extends AppCompatActivity implements View.OnClickListener {
    private ImageView iconAccount,iconBag,iconCalendar,iconStart,iconAvatar;
    private TextView lbAccounting,lbAssessmentRecord,lbCalender,lbStartAssessment,lbName,tvIdExam,lbSchool,lbSex,lbAge;
    private APIService mAPIService;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assessor);
        mAPIService = APIUtils.getAPIService();
        token = getIntent().getStringExtra("token");
        getProfile();
        initUI();

    }

    private void initUI() {
        iconAccount = (ImageView) findViewById(R.id.iconAccount);
        lbAccounting = (TextView) findViewById(R.id.lbAccounting);
        iconBag = (ImageView) findViewById(R.id.iconBag);
        lbAssessmentRecord = (TextView) findViewById(R.id.lbAssessmentRecord);
        iconCalendar = (ImageView) findViewById(R.id.iconCalendar);
        lbCalender = (TextView) findViewById(R.id.lbCalender);
        iconStart = (ImageView) findViewById(R.id.iconStart);
        lbStartAssessment = (TextView) findViewById(R.id.lbStartAssessment);
        iconAvatar = (ImageView) findViewById(R.id.iconAvatar);
        lbAge = (TextView) findViewById(R.id.lbAge);
        lbSex = (TextView) findViewById(R.id.lbSex);
        lbSchool = (TextView) findViewById(R.id.lbSchool);
        tvIdExam = (TextView) findViewById(R.id.tvIdExam);
        lbName = (TextView) findViewById(R.id.lbName);

        iconAccount.setOnClickListener(this);
        lbAccounting.setOnClickListener(this);
        iconBag.setOnClickListener(this);
        lbAssessmentRecord.setOnClickListener(this);
        iconCalendar.setOnClickListener(this);
        lbCalender.setOnClickListener(this);
        iconStart.setOnClickListener(this);
        lbStartAssessment.setOnClickListener(this);

        lbName.setText("N/A");
        tvIdExam.setText("1");
        lbSchool.setText("N/A");
        lbSex.setText("Sex: " + "N/A");
        lbAge.setText("Age: " + "N/A");

        QTSHelp.setLayoutView(iconAvatar, QTSHelp.GetWidthDevice(getApplicationContext())*2/5, QTSHelp.GetWidthDevice(getApplicationContext())*2/5);
        QTSHelp.setLayoutView(iconBag, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
        QTSHelp.setLayoutView(iconCalendar, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
        QTSHelp.setLayoutView(iconAccount, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
        QTSHelp.setLayoutView(iconStart, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iconAccount:
                Intent intent = new Intent(ActAssessor.this,ActAcounting.class);
                QTSHelp.setNum(ActAssessor.this,0);
                startActivity(intent);
                break;
            case R.id.lbAccounting:
                Intent intent1 = new Intent(ActAssessor.this,ActAcounting.class);
                QTSHelp.setNum(ActAssessor.this,0);
                startActivity(intent1);
                break;
            case R.id.iconBag:
                startActivity(new Intent(ActAssessor.this,ActAssessmentHistoryA.class));
                break;
            case R.id.lbAssessmentRecord:
                startActivity(new Intent(ActAssessor.this,ActAssessmentHistoryA.class));
                break;
            case R.id.iconCalendar:
                startActivity(new Intent(ActAssessor.this,ActCalendarE.class));
                break;
            case R.id.lbCalender:
                startActivity(new Intent(ActAssessor.this,ActCalendarE.class));
                break;
            case R.id.iconStart:
                startActivity(new Intent(ActAssessor.this,ActAssessmentStartA.class));
                break;
            case R.id.lbStartAssessment:
                startActivity(new Intent(ActAssessor.this,ActAssessmentStartA.class));
                break;
        }
    }

    public void getProfile(){
        mAPIService.getAnswers("Bearer "+token).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    Log.e("getProfile",response.message().toString());
                    lbName.setText(response.body().getUser().getFirstName());
//                    tvIdExam.setText(response.body().getUser().getRoleId());
                    lbSchool.setText(response.body().getUser().getStudentClass());
                    lbSex.setText(response.body().getUser().getGender());
                    String[] strdate =response.body().getUser().getDateOfBirth().split("-");
                    lbAge.setText("Age: " + QTSHelp.getAge(Integer.parseInt(strdate[0]), Integer.parseInt(strdate[1]), Integer.parseInt(strdate[2])));
                    Picasso.with(ActAssessor.this).load(response.body().getUser().getPicture()).into(iconAvatar);
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
