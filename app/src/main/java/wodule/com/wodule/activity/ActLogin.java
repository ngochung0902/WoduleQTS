package wodule.com.wodule.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.steelkiwi.instagramhelper.InstagramHelper;
import com.steelkiwi.instagramhelper.InstagramHelperConstants;
import com.steelkiwi.instagramhelper.model.InstagramUser;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.object.UserExaminer;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private ImageView img_logosmall,iconFacebook,iconGPlus,iconInstagram;
    private TextView tvRegister,tvForgotPass,tvLicense;
    private Button btnSubmit;
    private EditText edUserName,edPassword;
    private ProgressDialog mProgressDialog;
    private APIService mAPIService;
    private String username ="",password="";
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private String facebook_access_token = "";
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    private InstagramHelper instagramHelper;
    private CountDownTimer mCountDownTimer1;
    private boolean checktimeout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.act_login);

        mAPIService = APIUtils.getAPIService();

        initUI();
        edUserName.setText("brown");
        edPassword.setText("tatskie");
        //lay key hash fb
        try {
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageInfo(
                        "wodule.com.wodule",
                        PackageManager.GET_SIGNATURES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NoSuchAlgorithmException e) {}
        //con net google va lay thong tin ng dung
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //instagram login
        String scope = "basic";
//scope is for the permissions
        instagramHelper = new InstagramHelper.Builder()
                .withClientId("83517cb5034047f2be190f79e6fc8fda")
                .withRedirectUrl("http://wodule.io/api/redirectIG")
                .withScope(scope)
                .build();


    }

    LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = LoginManager.getInstance();
        }
        return loginManager;
    }

    private void loginFB() {
        loginManager = getLoginManager();
        loginManager.logInWithReadPermissions(this,
                Arrays.asList("public_profile", "email"));
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("Login.this", "Login.initializeFacebook.onSuccess Granted Permissions= " + loginResult.getRecentlyGrantedPermissions().toString());
                Log.e("Login.this", "result.getAccessToken():" + loginResult.getAccessToken().getToken());
                facebook_access_token = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                if (response.getError() != null) {
                                    // handle error
                                } else {
                                    Log.e("loginfacebook",response.getJSONObject().toString());
//                                    QTSHelp.setIsFBLogin(getApplicationContext(), true);
//                                    fbLogin(object, facebook_access_token);
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e("Login Facebook", "Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("Login.this", "Login.initializeFacebook.onError " + error.getMessage());
                if (error instanceof FacebookAuthorizationException) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                }
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
        iconFacebook = (ImageView) findViewById(R.id.iconFacebook);
        iconGPlus = (ImageView) findViewById(R.id.iconGPlus);
        iconInstagram = (ImageView) findViewById(R.id.iconInstagram);
        iconInstagram.setOnClickListener(this);
        tvLicense.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        iconFacebook.setOnClickListener(this);
        iconGPlus.setOnClickListener(this);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("LoginGoogle",""+connectionResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvLicense:
                startActivity(new Intent(ActLogin.this,ActLicense.class));
                break;
            case R.id.tvForgotPass:
                startActivity(new Intent(ActLogin.this,ActForgotPass.class));
                break;
            case R.id.tvRegister:
                startActivity(new Intent(ActLogin.this,ActRegister.class));
                break;
            case R.id.btnSubmit:
                username = edUserName.getText().toString().trim();
                password = edPassword.getText().toString().trim();
                new GetData().execute();
                mCountDownTimer1=new CountDownTimer(20000,1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        if(checktimeout==true)
                        {
                            mProgressDialog.cancel();
                            QTSHelp.showToast(ActLogin.this,"Time out");
                        }
                    }
                };
                mCountDownTimer1.start();
                break;
            case R.id.iconFacebook:
                loginFB();
                break;
            case R.id.iconGPlus:
                signIn();
                break;
            case R.id.iconInstagram:
                instagramHelper.loginFromActivity(ActLogin.this);
        }
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
                        final String token =response.body().getToken().toString().trim();
                        mAPIService.getAnswers("Bearer "+response.body().getToken().toString()).enqueue(new Callback<Example>() {
                            @Override
                            public void onResponse(Call<Example> call, Response<Example> response) {
                                if (response.isSuccessful())
                                {
                                    Log.e("Type",response.body().getUser().getType().toString());
                                    if (response.body().getUser().getType().equalsIgnoreCase("examinee")) {
                                        Intent intent = new Intent(ActLogin.this, ActExaminer.class);
                                        intent.putExtra("token",token);
                                        startActivity(intent);
                                    }
                                    else {
                                        Intent intent = new Intent(ActLogin.this, ActAssessor.class);
                                        intent.putExtra("token",token);
                                        startActivity(intent);
                                    }
                                    finish();
                                    mProgressDialog.cancel();
                                }
                            }

                            @Override
                            public void onFailure(Call<Example> call, Throwable t) {

                            }
                        });
                        Log.e("token",response.body().getToken().toString());
//                        Intent intent = new Intent(ActLogin.this,ActExaminer.class);
//                        startActivity(intent);
                        mProgressDialog.cancel();
                        finish();
                        QTSHelp.setIsLogin(ActLogin.this,true);
                        checktimeout = false;
                    }
                    if (!response.isSuccessful()){
                        checktimeout = false;
                        mProgressDialog.cancel();
                        QTSHelp.ShowpopupMessage(ActLogin.this,response.toString());
//                        startActivity(new Intent(ActLogin.this,ActAssessor.class));
                    }
                }

                @Override
                public void onFailure(Call<UserExaminer> call, Throwable t) {

                }
            });
            return status;
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("Showinfo Googlelogin",acct.getId().toString()+" - "+acct.getEmail().toString()+" - "+acct.getDisplayName().toString()+"-"+acct.getPhotoUrl().toString());
        } else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == InstagramHelperConstants.INSTA_LOGIN && resultCode == RESULT_OK) {
            InstagramUser user = instagramHelper.getInstagramUser(this);
            Log.e("login instagram",user.getData().getUsername() + "\n" + user.getData().getFullName() + "\n"
                    + user.getData().getWebsite()+"");
        }
        else
        {
            Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
        }
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.e("GoogleLogin",mGoogleApiClient.isConnected()+"");
    }
}
