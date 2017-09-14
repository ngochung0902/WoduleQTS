package wodule.com.wodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wodule.com.wodule.R;

/**
 * Created by MyPC on 14/09/2017.
 */
public class FrmInstructions extends Fragment {
    private TextView btnStartAssess;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_instructions_e, container, false);
        btnStartAssess = (TextView) view.findViewById(R.id.btnStartAssess);
        btnStartAssess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrmPart1_e fragment1 = new FrmPart1_e();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();

            }
        });
        return view;
    }

}
