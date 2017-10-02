package wodule.com.wodule.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.utils.BaseTFragment;

/**
 * Created by Administrator on 5/9/2017.
 */

public class ProfileFragment2 extends BaseTFragment {
    private ImageView ivBack;
    private ImageView ivNext;
    private EditText edCity, edAddress, edPhone, edEmail, edEthn;
    private TextView  lbTitle, edCountry, edNational;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile1, container, false);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        lbTitle = (TextView) view.findViewById(R.id.lbTitle);
        edAddress = (EditText) view.findViewById(R.id.edAddress);
        edPhone = (EditText) view.findViewById(R.id.edPhone);
        edCity = (EditText) view.findViewById(R.id.edCity);
        edCountry = (TextView) view.findViewById(R.id.edCountry);
        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edNational = (TextView) view.findViewById(R.id.edNational);
        edEthn = (EditText) view.findViewById(R.id.edEthn);
        if (QTSHelp.getIsEdit(getActivity())){
            lbTitle.setText("EDIT USER");
            getProfile();
        }
//        FontUtils.loadFont(getActivity(), LakConst.FONT_HEV_REGULAR);
//        FontUtils.setFont((LinearLayout) view.findViewById(R.id.ll_group_center));
//        FontUtils.loadFont(getActivity(), LakConst.FONT_HEV_MEDIUM);
//        FontUtils.setFont((TextView)view.findViewById(R.id.lbTitle));

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid().equalsIgnoreCase("isOk")) {
                    setProfiles();
                    startNewScreen(ProfileFragment2.this, new ProfileFragment3());
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
        return view;
    }
    private void getProfile(){
        edAddress.setText(String.valueOf(QTSConstrains.userObj.getAddress()));
        edCity.setText(String.valueOf(QTSConstrains.userObj.getCity()));
        edCountry.setText(String.valueOf(QTSConstrains.userObj.getCountry()));
        edEmail.setText(String.valueOf(QTSConstrains.userObj.getEmail()));
        edPhone.setText(String.valueOf(QTSConstrains.userObj.getTelephone()));
        edNational.setText(String.valueOf(QTSConstrains.userObj.getNationality()));
        edEthn.setText(String.valueOf(QTSConstrains.userObj.getEthnicity()));
    }
    @Override
    public String getFragmentTitle() {
        return null;
    }

    @Override
    public void onBackPressed() {

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
    private void setProfiles(){

        newUser.setAddress(edAddress.getText().toString());
        newUser.setTelephone(edPhone.getText().toString());
        newUser.setEmail(edEmail.getText().toString());
        newUser.setCity(edCity.getText().toString());
        newUser.setCountry(edCountry.getText().toString());
        newUser.setNationality(edNational.getText().toString());
        newUser.setEthnicity(edEthn.getText().toString());
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

    private void selectNational() {
        final CharSequence[] itemCountry=  {"Viet Nam", "B","C"};
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
//    private void dialogCountry(){
//        final Dialog dialog_font = new Dialog(getActivity());
//        dialog_font.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog_font.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);
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
//    private void dialogCity(){
//        final Dialog dialog_font = new Dialog(getActivity());
//        dialog_font.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog_font.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//        dialog_font.setTitle("SELECT CITY");
//        dialog_font.setContentView(R.layout.layout_list_country);
//        ListView lv = (ListView)dialog_font.findViewById(R.id.listCountry);
//        CountryAdapter adapter = new CountryAdapter(getActivity(),getResources().getStringArray(R.array.city_arrs));
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                edCity.setText(String.valueOf(parent.getItemAtPosition(position)));
//                dialog_font.dismiss();
//            }
//        });
//        dialog_font.setCancelable(true);
//        dialog_font.show();
//    }
//    private void dialogNational(){
//        final Dialog dialog_font = new Dialog(getActivity());
//        dialog_font.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog_font.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//        dialog_font.setTitle("SELECT NATIONALITY");
//        dialog_font.setContentView(R.layout.layout_list_country);
//        ListView lv = (ListView)dialog_font.findViewById(R.id.listCountry);
//        CountryAdapter adapter = new CountryAdapter(getActivity(), getResources().getStringArray(R.array.country_arrs));
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                edNational.setText(String.valueOf(parent.getItemAtPosition(position)));
//                dialog_font.dismiss();
//            }
//        });
//        dialog_font.setCancelable(true);
//        dialog_font.show();
//    }
//    private void dialogEthnicity(){
//        final Dialog dialog_font = new Dialog(getActivity());
//        dialog_font.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog_font.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//        dialog_font.setTitle("SELECT ETHNICITY");
//        dialog_font.setContentView(R.layout.layout_list_country);
//        ListView lv = (ListView)dialog_font.findViewById(R.id.listCountry);
//        CountryAdapter adapter = new CountryAdapter(getActivity(),getResources().getStringArray(R.array.ethnicity_arrs));
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                edEthn.setText(String.valueOf(parent.getItemAtPosition(position)));
//                dialog_font.dismiss();
//            }
//        });
//        dialog_font.setCancelable(true);
//        dialog_font.show();
//    }
}