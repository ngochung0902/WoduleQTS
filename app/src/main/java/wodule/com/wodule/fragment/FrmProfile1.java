package wodule.com.wodule.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.User;

/**
 * Created by MyPC on 13/09/2017.
 */
public class FrmProfile1 extends Fragment {
    private ImageView ivNext,ivBack;
    private EditText edAddress,edAddress2,edAddress3,edCity,edPhone,edEmail,edEthn;
    private TextView edCountry,edNational;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile1, container, false);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        edAddress = (EditText) view.findViewById(R.id.edAddress);
        edAddress2 = (EditText) view.findViewById(R.id.edAddress2);
        edAddress3 = (EditText) view.findViewById(R.id.edAddress3);
        edCity = (EditText) view.findViewById(R.id.edCity);
        edPhone = (EditText) view.findViewById(R.id.edPhone);
        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edEthn = (EditText) view.findViewById(R.id.edEthn);
        edCountry = (TextView) view.findViewById(R.id.edCountry);
        edNational = (TextView) view.findViewById(R.id.edNational);
        if (QTSHelp.getIsEdit(getActivity()))
            getProfile();

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid().equalsIgnoreCase("isOk"))
                {
                    setProfile();
                    FrmProfile2 fragment1 = new FrmProfile2();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
                    fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                    fragmentTransaction.commit();
                    QTSHelp.setIsEdit(getActivity(),false);
                }
                else QTSHelp.ShowpopupMessage(getActivity(),checkValid());

            }
        });

        edCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCountry();
            }
        });

        edNational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNational();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QTSHelp.setIsEdit(getActivity(),true);
                FrmProfile fragment1 = new FrmProfile();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter1, R.anim.anim_exit1);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();

            }
        });
        return view;
    }

    private void getProfile() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        User obj = gson.fromJson(json, User.class);
        edAddress.setText(String.valueOf(obj.getAddress()));
//        edAddress2.setText(String.valueOf(obj.getVd1()));
//        edAddress3.setText(String.valueOf(obj.getVd2()));
        edCity.setText(String.valueOf(obj.getCity()));
        edPhone.setText(String.valueOf(obj.getTelephone()));
        edEmail.setText(String.valueOf(obj.getEmail()));
        edEthn.setText(String.valueOf(obj.getEthnicity()));
        edCountry.setText(String.valueOf(obj.getCountry()));
        edNational.setText(String.valueOf(obj.getNationality()));
    }

    private void setProfile() {
        FrmProfile.newUser.setAddress(edAddress.getText().toString());
//        FrmProfile.newUser.setVd1(edAddress2.getText().toString());
//        FrmProfile.newUser.setVd2(edAddress3.getText().toString());
        FrmProfile.newUser.setCity(edCity.getText().toString());
        FrmProfile.newUser.setCountry(edCountry.getText().toString());
        FrmProfile.newUser.setTelephone(edPhone.getText().toString());
        FrmProfile.newUser.setEmail(edEmail.getText().toString());
        FrmProfile.newUser.setNationality(edNational.getText().toString());
        FrmProfile.newUser.setEthnicity(edEthn.getText().toString());

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(FrmProfile.newUser);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
//        QTSHelp.setIsEdit(getActivity(),true);
    }

    private void selectCountry() {
        final CharSequence[] itemCountry=  {"A", "B","C"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("SELECT COUNTRY");
        builder.setItems(itemCountry, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                edCountry.setText(itemCountry[item]);
            }
        });
        builder.show();
    }

    private void selectNational() {
        final CharSequence[] itemCountry=  {"A", "B","C"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("SELECT NATIONAL");
        builder.setItems(itemCountry, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                edNational.setText(itemCountry[item]);
            }
        });
        builder.show();
    }

    private String checkValid(){
        if (edAddress.getText().toString().trim().length() == 0){
            return getString(R.string.check_address);
        }
        if (edPhone.getText().toString().trim().length() == 0){
            return getString(R.string.check_telephone);
        }
        if (edEmail.getText().toString().trim().length() == 0){
            return getString(R.string.check_email);
        }
        if (edCity.getText().toString().trim().length() == 0){
            return getString(R.string.check_city);
        }
        if (edCountry.getText().toString().trim().length() == 0){
            return getString(R.string.check_country);
        }
        if (edNational.getText().toString().trim().length() == 0){
            return getString(R.string.check_nationality);
        }
        if (edEthn.getText().toString().trim().length() == 0){
            return getString(R.string.check_ethnicity);
        }
        return "isOk";
    }
}
