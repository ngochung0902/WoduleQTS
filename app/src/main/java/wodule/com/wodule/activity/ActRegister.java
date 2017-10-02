package wodule.com.wodule.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import wodule.com.wodule.R;
import wodule.com.wodule.fragment.ProfileFragment1;
import wodule.com.wodule.helper.QTSHelp;

public class ActRegister extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_new_user);
        fragmentManager = getSupportFragmentManager();
        fragment = new ProfileFragment1();
        QTSHelp.setIsEdit(ActRegister.this,false);
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        QTSHelp.setIsEdit(ActRegister.this,false);
    }
}
