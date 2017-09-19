package wodule.com.wodule.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.User;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

/**
 * Created by MyPC on 13/09/2017.
 */
public class FrmProfile3 extends Fragment implements View.OnClickListener {
    private ImageView ivBack, ivAvatar;
    private TextView btnSubmit, btnReset;
    private APIService mAPIService;
    private ProgressDialog mProgressDialog;
    private CountDownTimer mCountDownTimer1;
    private boolean checktimeout = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile3, container, false);
        btnSubmit = (TextView) view.findViewById(R.id.btnSubmit);
        btnReset = (TextView) view.findViewById(R.id.btnReset);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        btnSubmit.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        mAPIService = APIUtils.getAPIService();
        if (QTSConstrains.bmAvatar != null) {
            ivAvatar.setImageBitmap(QTSHelp.getRoundedCornerBitmap(QTSConstrains.bmAvatar, 15));
        }
        ivAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Log.e("Object",
                        FrmProfile.newUser.getLnFirst() +
                                FrmProfile.newUser.getAddress() + FrmProfile.newUser.getAddress1() + FrmProfile.newUser.getAddress2() +
                                FrmProfile.newUser.getEthnicity() +
                                FrmProfile.newUser.getReligion() +
                                FrmProfile.newUser.getPicture() +
                                FrmProfile.newUser.getEmail() +
                                FrmProfile.newUser.getLastName() +
                                FrmProfile.newUser.getCity() +
                                FrmProfile.newUser.getNationality() +
                                FrmProfile.newUser.getCode() +
                                FrmProfile.newUser.getPassword() +
                                FrmProfile.newUser.getNativeName() +
                                FrmProfile.newUser.getSuffix() +
                                FrmProfile.newUser.getFirstName() +
                                FrmProfile.newUser.getDateOfBirth() +
                                FrmProfile.newUser.getCountry() +
                                FrmProfile.newUser.getStatus() +
                                FrmProfile.newUser.getUserName() +
                                FrmProfile.newUser.getMiddleName() +
                                FrmProfile.newUser.getCountryOfBirth() +
                                FrmProfile.newUser.getTelephone() +
                                FrmProfile.newUser.getGender());
                new GetData().execute();
                mCountDownTimer1 = new CountDownTimer(30000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if (checktimeout == true) {
                            mProgressDialog.cancel();
                            QTSHelp.showToast(getActivity(), "Time out");
                        }
                    }
                };
                mCountDownTimer1.start();
                break;
            case R.id.ivBack:
                FrmProfile2 fragment1 = new FrmProfile2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter1, R.anim.anim_exit1);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();
                QTSHelp.setIsEdit(getActivity(), true);
                break;
        }
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("User Register...");
            mProgressDialog.show();
            mProgressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            final String status = "";
            mAPIService.postRegister(FrmProfile.newUser.getLnFirst().toString(),
                    FrmProfile.newUser.getAddress().toString() + FrmProfile.newUser.getAddress1().toString() + FrmProfile.newUser.getAddress2().toString(),
                    FrmProfile.newUser.getEthnicity().toString(),
                    FrmProfile.newUser.getReligion().toString(),
                    FrmProfile.newUser.getPicture().toString(),
                    FrmProfile.newUser.getEmail().toString(),
                    FrmProfile.newUser.getLastName().toString(),
                    FrmProfile.newUser.getCity().toString(),
                    FrmProfile.newUser.getNationality().toString(),
                    FrmProfile.newUser.getCode().toString(),
                    FrmProfile.newUser.getPassword().toString(),
                    FrmProfile.newUser.getNativeName().toString(),
                    FrmProfile.newUser.getSuffix().toString(),
                    FrmProfile.newUser.getFirstName().toString(),
                    FrmProfile.newUser.getDateOfBirth().toString(),
                    FrmProfile.newUser.getCountry().toString(),
                    FrmProfile.newUser.getStatus().toString(),
                    FrmProfile.newUser.getUserName().toString(),
                    FrmProfile.newUser.getMiddleName().toString(),
                    FrmProfile.newUser.getCountryOfBirth().toString(),
                    FrmProfile.newUser.getTelephone().toString(),
                    FrmProfile.newUser.getGender().toString()
            ).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("response", response.toString());
                    Log.e("reponse1", response.message().toString());
                    if (response.isSuccessful()) {
                        Log.e("Register", response.body().toString());
                        Log.e("response", response.body().getToken().toString());
                        Log.e("response", response.message().toString());
                        mProgressDialog.cancel();
                        checktimeout = false;
                    } else if (!response.isSuccessful()) {
                        Log.e("response", response.message().toString());
                        checktimeout = false;
                        mProgressDialog.cancel();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            return status;
        }
    }
}
