package wodule.com.wodule.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import wodule.com.wodule.R;
import wodule.com.wodule.adapter.ScoreAdapter;

/**
 * Created by MyPC on 15/09/2017.
 */
public class Frm_Part2_a extends Fragment implements View.OnClickListener {
    private TextView btnScore, lbComment,tvTime;
    private ImageView ivDropdown, ivDropUp,ivNext,ivPlay;
    private EditText edComment;
    private ProgressBar progressBar1;
    CountDownTimer mCountDownTimer1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frmpart2_a, container, false);
        btnScore = (TextView) view.findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);
        ivDropdown = (ImageView) view.findViewById(R.id.ivDropdown);
        ivDropdown.setOnClickListener(this);
        ivDropUp = (ImageView) view.findViewById(R.id.ivDropUp);
        ivDropUp.setOnClickListener(this);
        lbComment = (TextView) view.findViewById(R.id.lbComment);
        edComment = (EditText) view.findViewById(R.id.edComment);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        ivPlay = (ImageView) view.findViewById(R.id.ivPlay);
        progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        return view;
    }

    private void dialogScore() {
        final Dialog dialog_font = new Dialog(getActivity());
        dialog_font.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog_font.setTitle("SELECT SCORE");
        dialog_font.setContentView(R.layout.layout_list_scoredialog);
        GridView lv = (GridView) dialog_font.findViewById(R.id.list_font);
        ScoreAdapter adapter = new ScoreAdapter(getActivity());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog_font.dismiss();
//                String score = (String) parent.getItemAtPosition(position);
//                ActAssessmentStartA.storeresult.setPart_1_score(score);
            }
        });
        dialog_font.setCancelable(true);
        dialog_font.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnScore) {
            dialogScore();
        }
        if (v.getId() == R.id.ivDropdown) {
            lbComment.setVisibility(View.GONE);
            edComment.setVisibility(View.GONE);
            ivDropdown.setVisibility(View.GONE);
            ivDropUp.setVisibility(View.VISIBLE);

        }
        if (v.getId() == R.id.ivDropUp) {
            lbComment.setVisibility(View.VISIBLE);
            edComment.setVisibility(View.VISIBLE);
            ivDropUp.setVisibility(View.GONE);
            ivDropdown.setVisibility(View.VISIBLE);
        }
        if (v.getId()==R.id.ivNext){
            Frm_Part3_a fragment1 = new Frm_Part3_a();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
            fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
            fragmentTransaction.commit();
        }
        if (v.getId()==R.id.ivPlay){
            int i=0;
            ivPlay.setVisibility(View.GONE);
            progressBar1.setVisibility(View.VISIBLE);
            tvTime.setVisibility(View.VISIBLE);
            progressBar1.setProgress(i);
            mCountDownTimer1=new CountDownTimer(5000,100) {
                int q=0;
                @Override
                public void onTick(long millisUntilFinished) {

                    Log.v("Log_tag", "Tick of Progress"+ q+ millisUntilFinished);
                    tvTime.setText(millisUntilFinished/1000+"");
                    q++;
                    progressBar1.setProgress((int) q*100/(5000/100));
                    ivNext.setVisibility(View.INVISIBLE);
                }
                @Override
                public void onFinish() {
                    //Do what you want
                    q++;
                    progressBar1.setProgress(100);
                    tvTime.setVisibility(View.GONE);
                    progressBar1.setVisibility(View.GONE);
                    ivNext.setVisibility(View.VISIBLE);
                    ivPlay.setVisibility(View.VISIBLE);
                }
            };
            mCountDownTimer1.start();
        }
    }
}
