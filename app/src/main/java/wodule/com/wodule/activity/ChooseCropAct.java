package wodule.com.wodule.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.fragment.ProfileFragment4;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.UserObject;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;
import wodule.com.wodule.utils.BaseTFragment;
import wodule.com.wodule.utils.CropImageView;

/**
 * Created by TuanQTS on 06/28/2017.
 */
public class ChooseCropAct extends BaseTFragment {
    private ImageView ivBack;
    private TextView btnSubmit, lbTitle;
    private CropImageView mCropView;
    private APIService mAPIService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_crop_layout, container, false);
        mAPIService = APIUtils.getAPIService();
        lbTitle = (TextView) view.findViewById(R.id.lbTitle);
        mCropView = (CropImageView) view.findViewById(R.id.cropImageView);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        btnSubmit = (TextView) view.findViewById(R.id.btnSubmit);
        if (QTSHelp.getIsEdit(getActivity())) {
            lbTitle.setText("EDIT USER");
            if (QTSConstrains.bmAvatar != null){
                mCropView.setImageBitmap(QTSConstrains.bmAvatar);
                mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);
            }else {
                if (QTSConstrains.userObj.getPicture() != null) {
                    Glide.with(getActivity()).load(String.valueOf(QTSConstrains.userObj.getPicture()))
                            .asBitmap()
                            .fitCenter()
                            .signature(new StringSignature(UUID.randomUUID().toString()))
                            .into(mCropView);
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);
                }else QTSConstrains.bmAvatar = null;
            }
        } else {
            if (QTSConstrains.bmAvatar != null){
                mCropView.setImageBitmap(QTSConstrains.bmAvatar);
                mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);

            }
        }
//        FontUtils.setFont(btnSubmit);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QTSHelp.getIsEdit(getActivity())) {
                    pDialog.show();
                    if (mCropView.getCroppedBitmap() != null){
                        QTSConstrains.bmAvatar = mCropView.getCroppedBitmap();
                        QTSConstrains.pictureFile = getFiles(QTSConstrains.bmAvatar, "avaWodule");
                        mCropView.setImageBitmap(QTSConstrains.bmAvatar);
                        mCropView.setCropEnabled(false);
                    }
                    getUpdateApi();
                } else {
                    if (QTSConstrains.bmAvatar != null){
                        QTSConstrains.bmAvatar = mCropView.getCroppedBitmap();
                        QTSConstrains.pictureFile = getFiles(QTSConstrains.bmAvatar, "avaWodule");
                    }
                    startNewScreen(ChooseCropAct.this, new ProfileFragment4());
                }

            }
        });
        return view;
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

    private File getFiles(Bitmap bitmap, String file_name) {
        File file = null;
        try {
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            file = new File(path, file_name + ".jpg");
            fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    private void getUpdateApi() {
        Log.e("newuser",
                newUser.getCity().toString()+"\n"+
                newUser.getCountry().toString()+"\n"+
                newUser.getTelephone().toString()+"\n"+
                newUser.getNationality().toString()+"\n"+
                newUser.getStatus().toString()+"\n"+
                newUser.getGender().toString()+"\n"+
                newUser.getUserName().toString()+"\n"+
                newUser.getEmail().toString()+"\n"+
                newUser.getPassword().toString()+"\n"+
                newUser.getFirstName().toString()+"\n"+
                newUser.getMiddleName().toString()+"\n"+
                newUser.getLastName().toString()+"\n"+
                newUser.getDateOfBirth().toString()+"\n"+
                newUser.getCountryOfBirth().toString()+"\n"+
                newUser.getNativeName().toString()+"\n"+
                newUser.getSuffix().toString()+"\n"+
                newUser.getLnFirst().toString()+"\n"+
                newUser.getAddress().toString()+"\n"+
                newUser.getEthnicity().toString()+"\n"+
                newUser.getReligion().toString()+"\n"+
                newUser.getOrganization().toString()+"\n"+
                newUser.getStudentClass().toString()+"\n"+
                newUser.getAdviser().toString());
        File file = new File(QTSConstrains.pictureFile.getPath());
        RequestBody patch = RequestBody.create(MediaType.parse("text/plain"),"PATCH");
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
        RequestBody fname = RequestBody.create(MediaType.parse("text/plain"),newUser.getFirstName().toString());
        RequestBody mname = RequestBody.create(MediaType.parse("text/plain"),newUser.getMiddleName().toString());
        RequestBody lname = RequestBody.create(MediaType.parse("text/plain"),newUser.getLastName().toString());
        Log.e("error",newUser.getDateOfBirth().toString());
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
        if (QTSConstrains.pictureFile != null && QTSConstrains.checkdate==false) {
            Log.e("getUpdateApi", "QTSConstrains.picture # null");
            Log.e("token",QTSHelp.getAccessToken(getActivity()));
            mAPIService.postUpdateImage("Bearer "+QTSHelp.getAccessToken(getActivity()),patch ,city,country,telephone,nationality,status,gender,fname,mname,lname,countryofbirth,nativename,suffx,ln_first,address,ethnicity,religion,organization,student_class,adviser).enqueue(new Callback<UserObject>() {
                @Override
                public void onResponse(Call<UserObject> call, Response<UserObject> response) {
                    Log.e("update",response.toString());
                    if (response.code()==200){
                        getActivity().finish();
                        QTSHelp.setIsEdit(getActivity(),false);
                        pDialog.dismiss();
                        pDialog.cancel();
                    }else {
                        try {
                            pDialog.dismiss();
                            pDialog.cancel();
                            QTSHelp.ShowpopupMessage(getActivity(),response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserObject> call, Throwable t) {
                    pDialog.cancel();
                    getActivity().finish();
                }
            });
        } else {
            Log.e("getUpdateApi", "QTSConstrains.picture null");
            mAPIService.postUpdatenoImage("Bearer "+QTSHelp.getAccessToken(getActivity()),patch ,city,country,telephone,nationality,status,gender,fname,mname,lname,dateofbirth,countryofbirth,nativename,
                    suffx,ln_first,address,ethnicity,religion,organization,student_class,adviser).enqueue(new Callback<UserObject>() {
                @Override
                public void onResponse(Call<UserObject> call, Response<UserObject> response) {
                    if (response.isSuccessful()){
                        getActivity().finish();
                        QTSConstrains.checkdate = false;
                        QTSHelp.setIsEdit(getActivity(),false);
                        pDialog.dismiss();
                        pDialog.cancel();
                    }else {
                        try {
                            pDialog.dismiss();
                            pDialog.cancel();
                            QTSHelp.ShowpopupMessage(getActivity(),response.errorBody().string());
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
    }
}
