package wodule.com.wodule.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import wodule.com.wodule.R;
import wodule.com.wodule.fragment.FrmProfile;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.User;

public class ActRegister extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    public static User userObj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_new_user);
        fragmentManager = getSupportFragmentManager();
        fragment = new FrmProfile();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment).commit();
        if (QTSHelp.getIsEdit(getApplicationContext()))
            loadDataStorage();
    }
    private void loadDataStorage() {
        Gson gson = new Gson();
        userObj = new User();
        userObj = gson.fromJson(QTSHelp.getUserObject(getApplicationContext()), User.class);
    }
}
