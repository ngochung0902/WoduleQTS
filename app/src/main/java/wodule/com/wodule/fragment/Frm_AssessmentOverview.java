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
import wodule.com.wodule.helper.QTSHelp;

/**
 * Created by MyPC on 15/09/2017.
 */
public class Frm_AssessmentOverview extends Fragment implements View.OnClickListener {
    private TextView btnSubmit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_assessmentoverview, container, false);
        btnSubmit = (TextView) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnSubmit){
            QTSHelp.setNum(getActivity(),1);
            Frm_accounting fragment1 = new Frm_accounting();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
            fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
            fragmentTransaction.commit();
        }
    }
}
