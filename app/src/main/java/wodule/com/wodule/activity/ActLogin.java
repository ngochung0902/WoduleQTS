package wodule.com.wodule.activity;

import android.app.Dialog;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.io.IOException;
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
    private boolean checktimeout = true, social = false, notlogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.act_login);
        QTSHelp.setIsLogin(ActLogin.this,false);
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
                            }

                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,birthday");
            request.setParameters(parameters);
            request.executeAsync();
            username = "u01"+"10901034612419";//loginResult.getAccessToken().getUserId();
            password = "facebook";
            social = true;
            Log.e("Id facebook",username +"\n"+ password);
            new GetData().execute();
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
        edPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    QTSHelp.hideKeyboard(ActLogin.this);
                    if (QTSHelp.isNetworkAvailable(ActLogin.this)) {
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
                    }
                    else {
                        QTSHelp.showToast(ActLogin.this,"No Internet");
                    }
                    return true;
                }
                return false;
            }
        });
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
                social = false;
                if (QTSHelp.isNetworkAvailable(ActLogin.this)) {
                    QTSHelp.hideKeyboard(ActLogin.this);
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
                }
                else {
                    QTSHelp.showToast(ActLogin.this,"No Internet");
                }
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
        protected String doInBackground(final String... params) {
            final String status = "";
                mAPIService.savePost(username, password, social).enqueue(new Callback<UserExaminer>() {
                    @Override
                    public void onResponse(Call<UserExaminer> call, Response<UserExaminer> response) {
                        Log.e("Login response", response.toString());
                        if (response.isSuccessful()&&social==false) {
                            final String token = response.body().getToken().toString().trim();
                            QTSHelp.setAccessToken(ActLogin.this,response.body().getToken().toString());
                            mAPIService.getAnswers("Bearer " + response.body().getToken().toString()).enqueue(new Callback<Example>() {
                                @Override
                                public void onResponse(Call<Example> call, Response<Example> response) {
                                    if (response.isSuccessful()) {
                                        Log.e("Type", response.body().getUser().getType().toString());
                                        if (response.body().getUser().getType().equalsIgnoreCase("examinee")) {
                                            Intent intent = new Intent(ActLogin.this, ActExaminer.class);
                                            intent.putExtra("token", token);
                                            intent.putExtra("password",password);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            QTSHelp.setIsLogin(ActLogin.this, true);
                                            QTSHelp.setIsStudent(ActLogin.this, true);

                                        } else {
                                            Intent intent = new Intent(ActLogin.this, ActAssessor.class);
                                            intent.putExtra("token", token);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            QTSHelp.setIsLogin(ActLogin.this, true);
                                            QTSHelp.setIsStudent(ActLogin.this, false);
                                        }
                                        finish();
                                        mProgressDialog.cancel();
                                    } else {

                                    }
                                }

                                @Override
                                public void onFailure(Call<Example> call, Throwable t) {

                                }
                            });
                            Log.e("token", response.body().getToken().toString());
                            mProgressDialog.cancel();
                            finish();
                            checktimeout = false;
                        }else if (response.isSuccessful()&&social==true){
                            QTSHelp.setAccessToken(ActLogin.this,response.body().getToken().toString());
                            if (response.body().getFirst().equalsIgnoreCase("true")){
                                dialogCode();
                            }else {
                                QTSHelp.showToast(ActLogin.this,"first false");
                                mProgressDialog.cancel();
                            }
                        }
                        if (!response.isSuccessful()) {
                            checktimeout = false;
                            mProgressDialog.cancel();
                            try {
                                StringBuilder a = new StringBuilder(response.errorBody().string());
                                a.delete(0, 10);
                                a.delete(a.length() - 2, a.length());
                                QTSHelp.ShowpopupMessage(ActLogin.this, a.toString());
                                }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
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
            username = "u03"+acct.getId();
            password = "google";
            new GetData().execute();
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
                    + user.getData().getWebsite()+"\n"+ user.getData().getId());
            username ="u02"+user.getData().getId();
            password = "instagram";
            new GetData().execute();
        }
//        else
//        {
//            Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
//        }
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

    private void dialogCode() {
        final Dialog dialog_code = new Dialog(ActLogin.this);
        dialog_code.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_code.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_code.setTitle("INPUT CODE");
        dialog_code.setContentView(R.layout.activity_input_code);

        final EditText edCode = (EditText) dialog_code.findViewById(R.id.edEnterCode);
        Button btnOk = (Button) dialog_code.findViewById(R.id.btnOk);
        Button btnCancel = (Button) dialog_code.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QTSHelp.setIsLogin(getApplicationContext(), false);
                dialog_code.dismiss();
                mProgressDialog.cancel();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QTSHelp.hideKeyboard(ActLogin.this);
                if (edCode.getText().toString().equalsIgnoreCase("0m7EQV")){
                    Intent intent = new Intent(ActLogin.this, ActExaminer.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else if (edCode.getText().toString().equalsIgnoreCase("HJ5M9I")){
                    Intent intent = new Intent(ActLogin.this, ActAssessor.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    QTSHelp.showToast(ActLogin.this,"wrong code");
                }
            }
        });
        dialog_code.setCancelable(true);
        dialog_code.show();
    }
}
