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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
                                "\n"+FrmProfile.newUser.getAddress()  +
                                "\n"+FrmProfile.newUser.getEthnicity() +
                                "\n"+FrmProfile.newUser.getReligion() +
                                "\n"+QTSConstrains.pictureFile.toString() +
                                "\n"+FrmProfile.newUser.getEmail() +
                                "\n"+FrmProfile.newUser.getLastName() +
                                "\n"+FrmProfile.newUser.getCity() +
                                "\n"+FrmProfile.newUser.getNationality() +
                                "\n"+FrmProfile.newUser.getCode() +
                                "\n"+FrmProfile.newUser.getPassword() +
                                "\n"+FrmProfile.newUser.getNativeName() +
                                "\n"+FrmProfile.newUser.getSuffix() +
                                "\n"+FrmProfile.newUser.getFirstName() +
                                "\n"+FrmProfile.newUser.getDateOfBirth() +
                                "\n"+FrmProfile.newUser.getCountry() +
                                "\n"+FrmProfile.newUser.getStatus() +
                                "\n"+FrmProfile.newUser.getUserName() +
                                "\n"+FrmProfile.newUser.getMiddleName() +
                                "\n"+FrmProfile.newUser.getCountryOfBirth() +
                                "\n"+FrmProfile.newUser.getTelephone() +
                                "\n"+FrmProfile.newUser.getGender());
                new GetData().execute();
                mCountDownTimer1 = new CountDownTimer(20000, 1000) {

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

//            File file = new File("/storage/emulated/0/avaWodule.jpg");
//            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), QTSConstrains.pictureFile);
            final MultipartBody.Part file = MultipartBody.Part.createFormData("picture",QTSConstrains.pictureFile.getName(),requestFile);
            Log.e("error",file.toString());
            mAPIService.postRegister(
//                    FrmProfile.newUser.getLnFirst().toString(),
//                    FrmProfile.newUser.getAddress().toString(),// + FrmProfile.newUser.getAddress1().toString() + FrmProfile.newUser.getAddress2().toString(),
//                    FrmProfile.newUser.getEthnicity().toString(),
//                    FrmProfile.newUser.getReligion().toString(),
////                    file,
                    FrmProfile.newUser.getLastName().toString(),
                    FrmProfile.newUser.getEmail().toString(),
                    FrmProfile.newUser.getCity().toString(),
                    FrmProfile.newUser.getNationality(),
//                    FrmProfile.newUser.getCode().toString(),
                    FrmProfile.newUser.getPassword().toString(),
//                    FrmProfile.newUser.getNativeName().toString(),
//                    FrmProfile.newUser.getSuffix().toString(),
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
                    Log.e("error",FrmProfile.newUser.getLnFirst().toString()+"\n"+
                            FrmProfile.newUser.getAddress().toString()+"\n"+
                            FrmProfile.newUser.getEthnicity().toString()+"\n"+
                            FrmProfile.newUser.getReligion().toString()+"\n"+
                            file.toString()+"\n"+
                            FrmProfile.newUser.getLastName().toString()+"\n"+
                            FrmProfile.newUser.getEmail().toString()+"\n"+
                            FrmProfile.newUser.getCity().toString()+"\n"+
                            FrmProfile.newUser.getNationality()+"\n"+
                            FrmProfile.newUser.getCode().toString()+"\n"+
                            FrmProfile.newUser.getPassword().toString()+"\n"+
                            FrmProfile.newUser.getNativeName().toString()+"\n"+
                            FrmProfile.newUser.getSuffix().toString()+"\n"+
                            FrmProfile.newUser.getFirstName().toString()+"\n"+
                            FrmProfile.newUser.getDateOfBirth().toString()+"\n"+
                            FrmProfile.newUser.getCountry().toString()+"\n"+
                            FrmProfile.newUser.getStatus().toString()+"\n"+
                            FrmProfile.newUser.getUserName().toString()+"\n"+
                            FrmProfile.newUser.getMiddleName().toString()+"\n"+
                            FrmProfile.newUser.getCountryOfBirth().toString()+"\n"+
                            FrmProfile.newUser.getTelephone().toString()+"\n"+
                            FrmProfile.newUser.getGender().toString());
                    Log.e("response", response.toString());
                    Log.e("reponse1", response.message().toString());
                    Log.e("error",response.errorBody().contentType().toString());

                    if (response.isSuccessful()) {
                        Log.e("error",response.body().getError().toString());
                        Log.e("Register", response.body().toString());
//                        Log.e("response", response.body().getToken().toString());
                        Log.e("response", response.message().toString());
                        mProgressDialog.cancel();
                        checktimeout = false;
                    } else if (!response.isSuccessful()) {
                        Log.e("response", response.message().toString());
                        checktimeout = false;
                        mProgressDialog.cancel();
                    }
                    Log.e("error",response.body().getError().toString());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            return status;
        }
    }
}
