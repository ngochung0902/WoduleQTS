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
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;

/**
 * Created by MyPC on 13/09/2017.
 */
public class FrmProfile3 extends Fragment {
    private ImageView ivBack,ivAvatar;
    private TextView btnSubmit,btnReset;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile3, container, false);
        btnSubmit = (TextView) view.findViewById(R.id.btnSubmit);
        btnReset = (TextView) view.findViewById(R.id.btnReset);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrmProfile2 fragment1 = new FrmProfile2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter1, R.anim.anim_exit1);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();
                QTSHelp.setIsEdit(getActivity(),true);

            }
        });

        if (QTSConstrains.bmAvatar != null) {
            ivAvatar.setImageBitmap(QTSHelp.getRoundedCornerBitmap(QTSConstrains.bmAvatar, 15));
        } else {
            if (QTSHelp.getIsEdit(getActivity())) {
                setProfile();
            }
        }
        ivAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        return view;

    }

    private void setProfile() {
        if (FrmProfile.newUser.getPicture() != null) {
//            Glide.with(getActivity()).load("http://wodule.io/" + String.valueOf(HomeActivity.userObj.getPicture()))
//                    .asBitmap()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(ivAvatar);
        }

    }
}
