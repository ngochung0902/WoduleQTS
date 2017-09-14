package wodule.com.wodule.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.UserExaminer;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActLogin extends AppCompatActivity {
    private ImageView img_logosmall;
    private TextView tvRegister,tvForgotPass,tvLicense;
    private Button btnSubmit;
    private EditText edUserName,edPassword;
    private ProgressDialog mProgressDialog;
    private APIService mAPIService;
    private String username ="",password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        mAPIService = APIUtils.getAPIService();

        initUI();
        edUserName.setText("brown");
        edPassword.setText("tatskie");
        tvLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActLogin.this,ActLicense.class));
            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActLogin.this,ActForgotPass.class));
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActLogin.this,ActRegister.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edUserName.getText().toString().trim();
                password = edPassword.getText().toString().trim();
                new GetData().execute();
            }
        });
    }

    private void initUI() {
        img_logosmall = (ImageView) findViewById(R.id.img_logosmall);
        getWidthHeight();
        tvLicense = (TextView) findViewById(R.id.tvLicense);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edUserName = (EditText) findViewById(R.id.edUserName);
        //QTSHelp.setLayoutView(img_logosmall,QTSHelp.GetWidthDevice(ActLogin.this)/3,QTSHelp.GetHeightDevice(ActLogin.this)/3*54/119);
    }

    private void getWidthHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int wwidth = displaymetrics.widthPixels;
        QTSHelp.SetHeightDevice(this,height);
        QTSHelp.SetWidthDevice(this,wwidth);
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(ActLogin.this);
            mProgressDialog.setMessage("Logging you in...");
            mProgressDialog.show();
            mProgressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            final String status = "";
            mAPIService.savePost(username,password,false).enqueue(new Callback<UserExaminer>() {
                @Override
                public void onResponse(Call<UserExaminer> call, Response<UserExaminer> response) {
                    Log.e("response",response.toString());
                    if (response.isSuccessful()){
                        Log.e("token",response.body().getToken().toString());
                        Intent intent = new Intent(ActLogin.this,ActExaminer.class);
                        startActivity(intent);
                        mProgressDialog.cancel();
                        finish();
                    }
                    if (!response.isSuccessful()){
                        QTSHelp.ShowpopupMessage(ActLogin.this,response.toString());
                        mProgressDialog.cancel();
                    }
                }

                @Override
                public void onFailure(Call<UserExaminer> call, Throwable t) {

                }
            });
            return status;
        }
    }

}
