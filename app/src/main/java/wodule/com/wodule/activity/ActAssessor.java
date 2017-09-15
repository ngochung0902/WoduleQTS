package wodule.com.wodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;

public class ActAssessor extends AppCompatActivity implements View.OnClickListener {
    private ImageView iconAccount,iconBag,iconCalendar,iconStart,iconAvatar;
    private TextView lbAccounting,lbAssessmentRecord,lbCalender,lbStartAssessment,lbName,tvIdExam,lbSchool,lbSex,lbAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assessor);
        initUI();
        iconAccount.setOnClickListener(this);
        lbAccounting.setOnClickListener(this);
        iconBag.setOnClickListener(this);
        lbAssessmentRecord.setOnClickListener(this);
        iconCalendar.setOnClickListener(this);
        lbCalender.setOnClickListener(this);
        iconStart.setOnClickListener(this);
        lbStartAssessment.setOnClickListener(this);
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
        if (v.getId()==R.id.iconAccount){
            Intent intent = new Intent(ActAssessor.this,ActAcounting.class);
            QTSHelp.setNum(ActAssessor.this,0);
            startActivity(intent);
        }
        if (v.getId()==R.id.lbAccounting){
            Intent intent = new Intent(ActAssessor.this,ActAcounting.class);
            QTSHelp.setNum(ActAssessor.this,0);
            startActivity(intent);
        }
        if (v.getId()==R.id.iconBag){
            startActivity(new Intent(ActAssessor.this,ActAssessmentHistoryA.class));
        }
        if (v.getId()==R.id.lbAssessmentRecord){
            startActivity(new Intent(ActAssessor.this,ActAssessmentHistoryA.class));
        }
        if (v.getId()==R.id.iconCalendar){
            startActivity(new Intent(ActAssessor.this,ActCalendarE.class));
        }
        if (v.getId()==R.id.lbCalender){
            startActivity(new Intent(ActAssessor.this,ActCalendarE.class));
        }
        if (v.getId()==R.id.iconStart){
            startActivity(new Intent(ActAssessor.this,ActAssessmentStartA.class));
        }
        if (v.getId()==R.id.lbStartAssessment){
            startActivity(new Intent(ActAssessor.this,ActAssessmentStartA.class));
        }
    }
}
