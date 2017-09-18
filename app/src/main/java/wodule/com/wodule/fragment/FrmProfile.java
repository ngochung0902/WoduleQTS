package wodule.com.wodule.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.User;

/**
 * Created by MyPC on 13/09/2017.
 */
public class FrmProfile extends Fragment {
    private ImageView ivNext,ivChecked;
    boolean isCheck = false;
    private EditText edFirstName,edMiddleName,edLastName,edNativeName,edSuffx;
    private TextView edDate,edCountry;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    public static User newUser = new User();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile, container, false);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        ivChecked = (ImageView) view.findViewById(R.id.ivChecked);
        edFirstName = (EditText) view.findViewById(R.id.edFirstName);
        edMiddleName = (EditText) view.findViewById(R.id.edMiddleName);
        edLastName = (EditText) view.findViewById(R.id.edLastName);
        edNativeName = (EditText) view.findViewById(R.id.edNativeName);
        edSuffx = (EditText) view.findViewById(R.id.edSuffx);
        edDate = (TextView) view.findViewById(R.id.edDate);
        edCountry = (TextView) view.findViewById(R.id.edCountry);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        if (QTSHelp.getIsEdit(getActivity()))
            getProfile();



        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid().equalsIgnoreCase("isOk")) {
                    setProfiles();
                    FrmProfile1 fragment1 = new FrmProfile1();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
                    fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                    fragmentTransaction.commit();
                    QTSHelp.setIsEdit(getActivity(),false);
                }
                else{
                    QTSHelp.ShowpopupMessage(getActivity(),checkValid());
                }

            }
        });

        ivChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck){
                    isCheck = !isCheck;
                    ivChecked.setImageResource(R.mipmap.ic_tick);
                }else {
                    isCheck = !isCheck;
                    ivChecked.setImageResource(R.mipmap.ic_ticked);
                }
            }
        });

        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });

        edCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCountry();
            }
        });

        return view;

    }

    private void getProfile(){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        User obj = gson.fromJson(json, User.class);
        Log.e("GsonObject",obj.getFirstName()+""+obj.getMiddleName());
        edFirstName.setText(String.valueOf(obj.getFirstName()));
        edLastName.setText(String.valueOf(obj.getLastName()));
        edMiddleName.setText(String.valueOf(obj.getMiddleName()));
        edNativeName.setText(String.valueOf(obj.getNativeName()));
        edCountry.setText(String.valueOf(obj.getCountryOfBirth()));
        edDate.setText(String.valueOf(obj.getDateOfBirth()));
        edSuffx.setText(String.valueOf(obj.getSuffix()));
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

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    private void setProfiles(){
        newUser.setFirstName(edFirstName.getText().toString());
        newUser.setMiddleName(edMiddleName.getText().toString());
        newUser.setLastName(edLastName.getText().toString());
        newUser.setNativeName(edNativeName.getText().toString());
        newUser.setSuffix(edSuffx.getText().toString());
        newUser.setDateOfBirth(edDate.getText().toString());
        newUser.setCountryOfBirth(edCountry.getText().toString());
//        if (isCheck)
//            newUser.setDisplay(1);
//        else newUser.setDisplay(0);
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(newUser);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    private String checkValid(){
        if (edFirstName.getText().toString().trim().length() == 0){
            return getString(R.string.check_fname);
        }
        if (edMiddleName.getText().toString().trim().length() == 0){
            return getString(R.string.check_mname);
        }
        if (edLastName.getText().toString().trim().length() == 0){
            return getString(R.string.check_lname);
        }
        if (edNativeName.getText().toString().trim().length() == 0){
            return getString(R.string.check_nativename);
        }
        if (edSuffx.getText().toString().length() == 0){
            return getString(R.string.check_suffx);
        }
        if (edDate.getText().toString().trim().length() == 0){
            return getString(R.string.check_dateofbirth);
        }
        if (edCountry.getText().toString().trim().length() == 0){
            return getString(R.string.check_country_of_birth);
        }
        return "isOk";
    }
}
