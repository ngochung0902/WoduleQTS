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
import android.widget.TextView;

import wodule.com.wodule.R;

/**
 * Created by MyPC on 14/09/2017.
 */
public class FrmPart1_e extends Fragment implements View.OnClickListener {
    private ImageView ivNext;
    private TextView tvTime;
    private long count = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_part1_e, container, false);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        ivNext.setVisibility(View.VISIBLE);
        ivNext.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ivNext)
        {
            FrmPart2_e fragment1 = new FrmPart2_e();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
            fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
            fragmentTransaction.commit();
        }
    }


}
