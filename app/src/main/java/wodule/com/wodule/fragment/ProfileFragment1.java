package wodule.com.wodule.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.UserObject;
import wodule.com.wodule.utils.BaseTFragment;

/**
 * Created by Administrator on 5/9/2017.
 */

public class ProfileFragment1 extends BaseTFragment{
    private ImageView ivNext;
    private ImageView ivChecked;
    private TextView lbTitle, edDate, edCountry;
    private EditText edFirstName, edMiddleName, edLastName, edNativeName, edSuffx;
    boolean isCheck = false;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile, container, false);
        lbTitle = (TextView) view.findViewById(R.id.lbTitle);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        edFirstName = (EditText) view.findViewById(R.id.edFirstName);
        edMiddleName = (EditText) view.findViewById(R.id.edMiddleName);
        edLastName = (EditText) view.findViewById(R.id.edLastName);
        edNativeName = (EditText) view.findViewById(R.id.edNativeName);
        edSuffx = (EditText) view.findViewById(R.id.edSuffx);
        edCountry = (TextView) view.findViewById(R.id.edCountry);
        ivChecked = (ImageView) view.findViewById(R.id.ivChecked);
        edDate = (TextView) view.findViewById(R.id.edDate);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        newUser = new UserObject();

        setDateTimeField();
        if (QTSHelp.getIsEdit(getActivity())){
            lbTitle.setText("EDIT USER");
            getProfile();
        }
        setOnListener();
//        FontUtils.loadFont(getActivity(), LakConst.FONT_HEV_REGULAR);
//        FontUtils.setFont((LinearLayout) view.findViewById(R.id.ll_group_center));
//        FontUtils.setFont((TextView)view.findViewById(R.id.lbCheckbox));
//        FontUtils.loadFont(getActivity(), LakConst.FONT_HEV_MEDIUM);
//        FontUtils.setFont((TextView)view.findViewById(R.id.lbHeader));
//        FontUtils.setFont((TextView)view.findViewById(R.id.lbTitle));
        return view;
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

    private void getProfile(){
        edFirstName.setText(String.valueOf(QTSConstrains.userObj.getFirst_name()));
        edLastName.setText(String.valueOf(QTSConstrains.userObj.getLast_name()));
        edMiddleName.setText(String.valueOf(QTSConstrains.userObj.getMiddle_name()));
        edNativeName.setText(String.valueOf(QTSConstrains.userObj.getNative_name()));
        edCountry.setText(String.valueOf(QTSConstrains.userObj.getCountry_of_birth()));
        edDate.setText(String.valueOf(QTSConstrains.userObj.getDate_of_birth()));
        edSuffx.setText(String.valueOf(QTSConstrains.userObj.getSuffx()));
    }


    @Override
    public String getFragmentTitle() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    private void setOnListener(){
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid().equalsIgnoreCase("isOk")) {
                    setProfiles();
                    startNewScreen(ProfileFragment1.this, new ProfileFragment2());
                }
                else QTSHelp.ShowpopupMessage(getActivity(),checkValid());
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
    }

    private void selectCountry() {
        final CharSequence[] itemCountry=  {"Viet Nam", "B","C"};
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
    private void setProfiles(){
        newUser.setFirst_name(edFirstName.getText().toString());
        newUser.setMiddle_name(edMiddleName.getText().toString());
        newUser.setLast_name(edLastName.getText().toString());
        newUser.setNative_name(edNativeName.getText().toString());
        newUser.setSuffx(edSuffx.getText().toString());
        newUser.setDate_of_birth(QTSHelp.formatDatetime1(edDate.getText().toString()));
        newUser.setCountry_of_birth(edCountry.getText().toString());
        if (isCheck)
            newUser.setIn_first("1");
        else newUser.setIn_first("0");
    }
//    private void dialogCountry(){
//        final Dialog dialog_font = new Dialog(getActivity());
//        dialog_font.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        dialog_font.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
////                WindowManager.LayoutParams.WRAP_CONTENT);
//        dialog_font.setTitle("SELECT COUNTRY");
//        dialog_font.setContentView(R.layout.layout_list_country);
//        ListView lv = (ListView)dialog_font.findViewById(R.id.listCountry);
//        CountryAdapter adapter = new CountryAdapter(getActivity(), getResources().getStringArray(R.array.country_arrs));
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                edCountry.setText(String.valueOf(parent.getItemAtPosition(position)));
//                dialog_font.dismiss();
//            }
//        });
//        dialog_font.setCancelable(true);
//        dialog_font.show();
//    }
}
