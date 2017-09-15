package wodule.com.wodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import wodule.com.wodule.R;

/**
 * Created by MyPC on 14/09/2017.
 */
public class FrmPartEnd_e extends Fragment {
    private TextView btnHome;
    private ImageView ivAvatar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_partend_e, container, false);
        btnHome = (TextView) view.findViewById(R.id.btnHome);
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
