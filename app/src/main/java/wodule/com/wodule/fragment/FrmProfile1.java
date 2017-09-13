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
import android.util.Log;
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
                setProfile();
                FrmProfile2 fragment1 = new FrmProfile2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();
                QTSHelp.setIsEdit(getActivity(),false);
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
                selectCountry();
            }
        });

        setProfile();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrmProfile fragment1 = new FrmProfile();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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
        Log.e("GsonObject",obj.getFirst_name()+""+obj.getMiddle_name());
        edAddress.setText(String.valueOf(obj.getResidence_address()));
        edAddress2.setText(String.valueOf(obj.getVd1()));
        edAddress3.setText(String.valueOf(obj.getVd2()));
        edCity.setText(String.valueOf(obj.getCity()));
        edPhone.setText(String.valueOf(obj.getTelephone()));
        edEmail.setText(String.valueOf(obj.getEmail()));
        edEthn.setText(String.valueOf(obj.getEthnility()));
        edCountry.setText(String.valueOf(obj.getCountry()));
        edNational.setText(String.valueOf(obj.getNationality()));
    }

    private void setProfile() {
        User newUser = new User();
        newUser.setResidence_address(edAddress.getText().toString());
        newUser.setVd1(edAddress2.getText().toString());
        newUser.setVd2(edAddress3.getText().toString());
        newUser.setCity(edCity.getText().toString());
        newUser.setCountry(edCountry.getText().toString());
        newUser.setTelephone(edPhone.getText().toString());
        newUser.setEmail(edEmail.getText().toString());
        newUser.setNationality(edNational.getText().toString());
        newUser.setEthnility(edEthn.getText().toString());

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(newUser);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
        QTSHelp.setIsEdit(getActivity(),true);
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
}
