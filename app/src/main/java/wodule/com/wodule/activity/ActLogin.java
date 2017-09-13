package wodule.com.wodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;

public class ActLogin extends AppCompatActivity {
    private ImageView img_logosmall;
    private TextView tvRegister,tvForgotPass,tvLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        initUI();

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
    }

    private void initUI() {
        img_logosmall = (ImageView) findViewById(R.id.img_logosmall);
        getWidthHeight();
        tvLicense = (TextView) findViewById(R.id.tvLicense);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
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

}
