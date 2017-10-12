package wodule.com.wodule.activity;

import android.app.ProgressDialog;
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
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActAssessor extends AppCompatActivity implements View.OnClickListener {
    private ImageView iconAccount, iconBag, iconCalendar, iconStart, iconAvatar,btnEdit;
    private TextView lbAccounting, lbAssessmentRecord, lbCalender, lbStartAssessment, lbName, tvIdExam, lbSchool, lbSex, lbAge, lbLogout;
    private APIService mAPIService;
    private String token,password;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assessor);
        mAPIService = APIUtils.getAPIService();
        token = getIntent().getStringExtra("token");
        password = getIntent().getStringExtra("password");
        load();
        getProfile();
        initUI();

    }

    private void initUI() {
        btnEdit = (ImageView) findViewById(R.id.btnEdit);
        lbLogout = (TextView) findViewById(R.id.lbLogout);
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
        lbLogout.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        lbName.setText("N/A");
        tvIdExam.setText("1");
        lbSchool.setText("N/A");
        lbSex.setText("Sex: " + "N/A");
        lbAge.setText("Age: " + "N/A");

        QTSHelp.setLayoutView(iconAvatar, QTSHelp.GetWidthDevice(getApplicationContext()) * 2 / 5, QTSHelp.GetWidthDevice(getApplicationContext()) * 2 / 5);
        QTSHelp.setLayoutView(iconBag, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
        QTSHelp.setLayoutView(iconCalendar, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
        QTSHelp.setLayoutView(iconAccount, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
        QTSHelp.setLayoutView(iconStart, QTSHelp.GetWidthDevice(getApplicationContext()) / 8, QTSHelp.GetWidthDevice(getApplicationContext()) / 8);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconAccount:
                Intent intent = new Intent(ActAssessor.this, ActAcounting.class);
                QTSHelp.setNum(ActAssessor.this, 0);
                startActivity(intent);
                break;
            case R.id.lbAccounting:
                Intent intent1 = new Intent(ActAssessor.this, ActAcounting.class);
                QTSHelp.setNum(ActAssessor.this, 0);
                startActivity(intent1);
                break;
            case R.id.iconBag:
                startActivity(new Intent(ActAssessor.this, ActAssessmentHistoryA.class));
                break;
            case R.id.lbAssessmentRecord:
                startActivity(new Intent(ActAssessor.this, ActAssessmentHistoryE.class));
                break;
            case R.id.iconCalendar:
                startActivity(new Intent(ActAssessor.this, ActCalendarE.class));
                break;
            case R.id.lbCalender:
                startActivity(new Intent(ActAssessor.this, ActCalendarE.class));
                break;
            case R.id.iconStart:
                startActivity(new Intent(ActAssessor.this, ActAssessmentStartA.class));
                break;
            case R.id.lbStartAssessment:
                startActivity(new Intent(ActAssessor.this, ActAssessmentStartA.class));
                break;
            case R.id.lbLogout:
                startActivity(new Intent(ActAssessor.this, ActLogin.class));
                finish();
                QTSHelp.setIsLogin(ActAssessor.this, false);
                break;
            case R.id.btnEdit:
                Intent intent2 = new Intent(ActAssessor.this,ActRegister.class);
                QTSHelp.setIsEdit(ActAssessor.this,true);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
    }

    public void getProfile() {
        mAPIService.getAnswers("Bearer " + token).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("Assessor response", response.toString());
                if (response.isSuccessful()) {
                    Log.e("getProfile", response.message().toString());
                    Log.e("reponse result",
                            response.body().getUser().getCity().toString() + "\n" +
                            response.body().getUser().getCountry().toString() + "\n" +
                            response.body().getUser().getTelephone().toString() + "\n" +
                            response.body().getUser().getNationality().toString() + "\n" +
                            response.body().getUser().getStatus().toString() + "\n" +
                            response.body().getUser().getGender().toString() + "\n" +
                            response.body().getUser().getUserName().toString() + "\n" +
                            response.body().getUser().getEmail().toString() + "\n" +
                            response.body().getUser().getFirstName().toString() + "\n" +
                            response.body().getUser().getMiddleName().toString() + "\n" +
                            response.body().getUser().getLastName().toString() + "\n" +
                            response.body().getUser().getDateOfBirth().toString() + "\n" +
                            response.body().getUser().getCountryOfBirth().toString() + "\n" +
                            response.body().getUser().getNativeName().toString() + "\n" +
//                            response.body().getUser().getSuffx().toString()+"\n"+
//                            response.body().getUser().getIn_first().toString()+"\n"+
                            response.body().getUser().getAddress().toString() + "\n" +
                            response.body().getUser().getEthnicity().toString() + "\n" +
                            response.body().getUser().getReligion().toString() + "\n" +
                            response.body().getUser().getOrganization().toString() + "\n" +
                            response.body().getUser().getStudentClass().toString() + "\n" +
                            response.body().getUser().getAdviser().toString()
                    );
                    mProgressDialog.cancel();

                    QTSConstrains.ID = response.body().getUser().getId().toString();
                    if (response.body().getUser().getFirstName()!=null)
                        lbName.setText(response.body().getUser().getFirstName());
                    if (response.body().getUser().getRoleId()!=null)
                        tvIdExam.setText(response.body().getUser().getRoleId()+"");
                    if (response.body().getUser().getOrganization()!=null)
                        lbSchool.setText(response.body().getUser().getOrganization());
                    if (response.body().getUser().getGender()!=null)
                        lbSex.setText(response.body().getUser().getGender());
                    if (response.body().getUser().getDateOfBirth()!=null) {
                        String[] strdate = response.body().getUser().getDateOfBirth().split("-");
                        lbAge.setText("Age: " + QTSHelp.getAge(Integer.parseInt(strdate[0]), Integer.parseInt(strdate[1]), Integer.parseInt(strdate[2])));
                    }
                    if (response.body().getUser().getPicture()!=null)
                        Picasso.with(ActAssessor.this).load(response.body().getUser().getPicture()).into(iconAvatar);

                    if (response.body().getUser().getFirstName()!=null)
                        QTSConstrains.userObj.setFirstName(response.body().getUser().getFirstName().toString());
                    if (response.body().getUser().getMiddleName()!=null)
                        QTSConstrains.userObj.setMiddleName(response.body().getUser().getMiddleName().toString());
                    if (response.body().getUser().getLastName()!=null)
                        QTSConstrains.userObj.setLastName(response.body().getUser().getLastName().toString());
                    if (response.body().getUser().getNativeName()!=null)
                        QTSConstrains.userObj.setNativeName(response.body().getUser().getNativeName().toString());
                    if (response.body().getUser().getSuffix()!=null)
                        QTSConstrains.userObj.setSuffix(response.body().getUser().getSuffix().toString());
                    if (response.body().getUser().getLnFirst()!=null)
                        QTSConstrains.userObj.setLnFirst(response.body().getUser().getLnFirst().toString());
                    if (response.body().getUser().getDateOfBirth()!=null)
                        QTSConstrains.userObj.setDateOfBirth(response.body().getUser().getDateOfBirth().toString());
                    if (response.body().getUser().getCountryOfBirth()!=null)
                        QTSConstrains.userObj.setCountryOfBirth(response.body().getUser().getCountryOfBirth().toString());
                    if (response.body().getUser().getAddress()!=null)
                        QTSConstrains.userObj.setAddress(response.body().getUser().getAddress().toString());
                    if (response.body().getUser().getCity()!=null)
                        QTSConstrains.userObj.setCity(response.body().getUser().getCity().toString());
                    if (response.body().getUser().getCountry()!=null)
                        QTSConstrains.userObj.setCountry(response.body().getUser().getCountry().toString());
                    if (response.body().getUser().getTelephone()!=null)
                        QTSConstrains.userObj.setTelephone(response.body().getUser().getTelephone().toString());
                    if (response.body().getUser().getEmail()!=null)
                        QTSConstrains.userObj.setEmail(response.body().getUser().getEmail().toString());
                    if (response.body().getUser().getNationality()!=null)
                        QTSConstrains.userObj.setNationality(response.body().getUser().getNationality().toString());
                    if (response.body().getUser().getEthnicity()!=null)
                        QTSConstrains.userObj.setEthnicity(response.body().getUser().getEthnicity().toString());
                    if (response.body().getUser().getStatus()!=null)
                        QTSConstrains.userObj.setStatus(response.body().getUser().getStatus().toString());
                    if (response.body().getUser().getReligion()!=null)
                        QTSConstrains.userObj.setReligion(response.body().getUser().getReligion().toString());
                    if (response.body().getUser().getGender()!=null)
                        QTSConstrains.userObj.setGender(response.body().getUser().getGender().toString());
                    if (response.body().getUser().getOrganization()!=null)
                        QTSConstrains.userObj.setOrganization(response.body().getUser().getOrganization().toString());
                    if (response.body().getUser().getStudentClass()!=null)
                        QTSConstrains.userObj.setStudentClass(response.body().getUser().getStudentClass().toString());
                    if (response.body().getUser().getAdviser()!=null)
                        QTSConstrains.userObj.setAdviser(response.body().getUser().getAdviser().toString());
                    if (response.body().getUser().getUserName()!=null)
                        QTSConstrains.userObj.setUserName(response.body().getUser().getUserName().toString());
                    QTSConstrains.userObj.setPassword(password);
                    if (response.body().getUser().getPicture()!=null)
                        QTSConstrains.userObj.setPicture(response.body().getUser().getPicture().toString());

                }else{
                    mProgressDialog.cancel();
                    QTSHelp.showToast(ActAssessor.this,"Token is invalid");
                    Intent intent = new Intent(ActAssessor.this,ActLogin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void load(){
        mProgressDialog = new ProgressDialog(ActAssessor.this);
        mProgressDialog.setMessage("Logging ...");
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);
    }
}
