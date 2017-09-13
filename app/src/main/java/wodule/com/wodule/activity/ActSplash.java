package wodule.com.wodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;

public class ActSplash extends AppCompatActivity {
    private ImageView img_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        initUI();
//        QTSHelp.setLayoutView(img_logo,QTSHelp.GetWidthDevice(this),QTSHelp.GetHeightDevice(this)*103/445);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
//                        if(QTSHelp.getIsLogin(ActSplash.this)) {
                            wait(QTSConstrains.Splash_Time);
                            Intent intent = new Intent(ActSplash.this, ActLogin.class);
                            startActivity(intent);
                            finish();
//                        }
//                        else {
//                            wait(QTSConstrains.Splash_Time);
//                            Intent intent = new Intent(ActSplash.this, ActLogin.class);
//                            startActivity(intent);
//                            finish();
//                        }
                    }
                } catch (InterruptedException ex) {

                }

            }

        };
        splashTread.start();
    }

    private void initUI() {
        img_logo = (ImageView) findViewById(R.id.img_logo);
        getWidthHeight();
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
