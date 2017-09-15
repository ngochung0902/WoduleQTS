package wodule.com.wodule.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import wodule.com.wodule.R;
import wodule.com.wodule.fragment.Frm_accounting;

/**
 * Created by MyPC on 15/09/2017.
 */
public class ActAcounting extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assessment_start_a);
        fragmentManager = getSupportFragmentManager();
        fragment = new Frm_accounting();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment).commit();
    }
}
