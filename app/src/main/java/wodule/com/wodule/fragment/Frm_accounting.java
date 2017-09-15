package wodule.com.wodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;

/**
 * Created by MyPC on 15/09/2017.
 */
public class Frm_accounting extends Fragment implements View.OnClickListener {
    private ImageView ivBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_accounting, container, false);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ivBack){
            if (QTSHelp.getNum(getActivity())==1)
            {
                Frm_AssessmentOverview fragment1 = new Frm_AssessmentOverview();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter1, R.anim.anim_exit1);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();

            }else {
                getActivity().finish();
            }
        }
    }
}
