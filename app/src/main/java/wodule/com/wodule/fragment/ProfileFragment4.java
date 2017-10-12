package wodule.com.wodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.activity.ActAssessor;
import wodule.com.wodule.activity.ActExaminer;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.object.UserObject;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;
import wodule.com.wodule.utils.BaseTFragment;

/**
 * Created by Administrator on 5/9/2017.
 */

public class ProfileFragment4 extends BaseTFragment {
    private ImageView ivBack, ivAvatar;
    private TextView btnSubmit, btnReset;
    private APIService mAPIService;
    private boolean checktimeout = false;
    private CountDownTimer mCountDownTimer1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile3, container, false);
        mAPIService = APIUtils.getAPIService();
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        btnSubmit = (TextView) view.findViewById(R.id.btnSubmit);
        btnReset = (TextView) view.findViewById(R.id.btnReset);
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checktimeout = true;
                if (newUser != null) {
                    pDialog.show();
                    getRegisterApi();
                }
                mCountDownTimer1=new CountDownTimer(20000,1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        if(checktimeout==true)
                        {
                            pDialog.cancel();
                            QTSHelp.showToast(getActivity(),"Time out");
                        }
                    }
                };
                mCountDownTimer1.start();
            }

        });
        if (QTSConstrains.bmAvatar != null) {
            ivAvatar.setImageBitmap(QTSHelp.getRoundedCornerBitmap(QTSConstrains.bmAvatar, 15));
        } else {
            if (QTSHelp.getIsEdit(getActivity())) {
                setProfile();
            }
        }
        ivAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return view;
    }

    private void setProfile() {
        if (QTSConstrains.userObj.getPicture() != null) {
            Glide.with(getActivity()).load("http://wodule.io/" + String.valueOf(QTSConstrains.userObj.getPicture()))
                    .asBitmap()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivAvatar);
        }

    }

    @Override
    public String getFragmentTitle() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    private void getRegisterApi(){
        Log.e("organization:  ",newUser.getOrganization().toString());
        Log.e("student_class :",newUser.getStudentClass().toString());
        Log.e("adviser:       ",newUser.getAdviser().toString());
        File file = new File(QTSConstrains.pictureFile.getPath());
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("picture", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        RequestBody city = RequestBody.create(MediaType.parse("text/plain"), newUser.getCity().toString());
        RequestBody country = RequestBody.create(MediaType.parse("text/plain"), newUser.getCountry().toString());
        RequestBody telephone = RequestBody.create(MediaType.parse("text/plain"),newUser.getTelephone().toString());
        RequestBody nationality = RequestBody.create(MediaType.parse("text/plain"),newUser.getNationality().toString());
        RequestBody status = RequestBody.create(MediaType.parse("text/plain"),newUser.getStatus().toString());
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"),newUser.getGender().toString());
        RequestBody username = RequestBody.create(MediaType.parse("text/plain"),newUser.getUserName().toString());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"),newUser.getEmail().toString());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"),newUser.getPassword().toString());
        RequestBody code = RequestBody.create(MediaType.parse("text/plain"),newUser.getCode().toString());
        final RequestBody fname = RequestBody.create(MediaType.parse("text/plain"),newUser.getFirstName().toString());
        RequestBody mname = RequestBody.create(MediaType.parse("text/plain"),newUser.getMiddleName().toString());
        RequestBody lname = RequestBody.create(MediaType.parse("text/plain"),newUser.getLastName().toString());
        RequestBody dateofbirth = RequestBody.create(MediaType.parse("text/plain"),newUser.getDateOfBirth().toString());
        RequestBody countryofbirth = RequestBody.create(MediaType.parse("text/plain"),newUser.getCountryOfBirth().toString());
        RequestBody nativename = RequestBody.create(MediaType.parse("text/plain"),newUser.getNativeName().toString());
        RequestBody suffx = RequestBody.create(MediaType.parse("text/plain"),newUser.getSuffix().toString());
        RequestBody ln_first = RequestBody.create(MediaType.parse("text/plain"),newUser.getLnFirst().toString());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"),newUser.getAddress().toString());
        RequestBody ethnicity = RequestBody.create(MediaType.parse("text/plain"),newUser.getEthnicity().toString());
        RequestBody religion = RequestBody.create(MediaType.parse("text/plain"),newUser.getReligion().toString());
        RequestBody organization = RequestBody.create(MediaType.parse("text/plain"),newUser.getOrganization().toString());
        RequestBody student_class = RequestBody.create(MediaType.parse("text/plain"),newUser.getStudentClass().toString());
        RequestBody adviser = RequestBody.create(MediaType.parse("text/plain"),newUser.getAdviser().toString());
        Log.e("result","organization"+organization.toString()+"\n"+
                        "student_class"+student_class.toString()+"\n"+
                        "adviser"+adviser.toString());
        mAPIService.postAPI(
                city,country,telephone,nationality,status,gender,username,email,password,code,fname,mname,lname,dateofbirth,countryofbirth,nativename,
                suffx,ln_first,address,ethnicity,religion,organization,student_class,adviser,filePart).enqueue(new Callback<UserObject>() {
            @Override
            public void onResponse(Call<UserObject> call, Response<UserObject> response) {
                Log.e("User register response",response.toString());
                if (response.isSuccessful()){
                    checktimeout = false;
                    getProfile(response.body().getToken());
                }
                else {
                    try {
                        StringBuilder a = new StringBuilder(response.errorBody().string());
                        a.delete(0,10);
                        a.delete(a.length()-2,a.length());
                        QTSHelp.ShowpopupMessage(getActivity(),a.toString());
                        pDialog.cancel();
                        checktimeout = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserObject> call, Throwable t) {

            }
        });
    }
    private void getProfile(final String token) {
        mAPIService.getAnswers("Bearer "+token).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful())
                {
                    Log.e("Type",response.body().getUser().getType().toString());
                    if (response.body().getUser().getType().equalsIgnoreCase("examinee")) {
                        Intent intent = new Intent(getActivity(), ActExaminer.class);
                        intent.putExtra("token",token);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        QTSHelp.setIsLogin(getActivity(),true);
                        QTSHelp.setIsStudent(getActivity(),true);


                    }
                    else {
                        Intent intent = new Intent(getActivity(), ActAssessor.class);
                        intent.putExtra("token",token);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        QTSHelp.setIsLogin(getActivity(),true);
                        QTSHelp.setIsStudent(getActivity(),false);
                    }
                    getActivity().finish();
                    pDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
