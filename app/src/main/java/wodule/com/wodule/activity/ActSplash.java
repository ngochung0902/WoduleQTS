package wodule.com.wodule.activity;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;

public class ActSplash extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {
        			/* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.color_background); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        // configSplash.setPathSplash(SyncStateContract.Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.color_background); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(1000);
        configSplash.setPathSplashFillColor(R.color.color_background); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("DemoNuts"); //change your app name here
        configSplash.setTitleTextColor(R.color.color_background);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(0);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);

    }

    @Override
    public void animationsFinished() {
        if(QTSHelp.getIsLogin(ActSplash.this)) {
            if (QTSHelp.getIsStudent(ActSplash.this)){
                Intent intent = new Intent(ActSplash.this, ActExaminer.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(ActSplash.this,ActAssessor.class);
                startActivity(intent);
                finish();
            }
        }
        else {
            Intent intent = new Intent(ActSplash.this, ActLogin.class);
            startActivity(intent);
            finish();
        }
    }
//    private ImageView img_logo;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_splash);
//        initUI();
////        QTSHelp.setLayoutView(img_logo,QTSHelp.GetWidthDevice(this),QTSHelp.GetHeightDevice(this)*103/445);
//        Thread splashTread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    synchronized (this) {
//                        // Wait given period of time or exit on touch

//                    }
//                } catch (InterruptedException ex) {
//
//                }
//
//            }
//
//        };
//        splashTread.start();
//    }
//
//    private void initUI() {
//        img_logo = (ImageView) findViewById(R.id.img_logo);
//        getWidthHeight();
//    }
//
//    private void getWidthHeight() {
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        int wwidth = displaymetrics.widthPixels;
//        QTSHelp.SetHeightDevice(this,height);
//        QTSHelp.SetWidthDevice(this,wwidth);
//    }
}
