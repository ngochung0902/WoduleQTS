package wodule.com.wodule.fragment;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import wodule.com.wodule.R;

/**
 * Created by MyPC on 14/09/2017.
 */
public class FrmPart4_e extends Fragment implements View.OnClickListener {
    private ImageView ivNext;
    private TextView tvTime,btnRecord;
    private ProgressBar record_progress_bar,progressBar1;
    CountDownTimer mCountDownTimer,mCountDownTimer1;
    int i=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_part4_e, container, false);
        ivNext = (ImageView) view.findViewById(R.id.ivNext);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        btnRecord = (TextView) view.findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(this);
        record_progress_bar = (ProgressBar) view.findViewById(R.id.record_progress_bar);
        progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
        ivNext.setOnClickListener(this);
        progressBar();
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ivNext){
            FrmPartEnd_e fragment1 = new FrmPartEnd_e();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
            fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
            fragmentTransaction.commit();
        }
        if (v.getId()==R.id.btnRecord){
            progressBar1.setProgress(i);
            mCountDownTimer1=new CountDownTimer(5000,100) {
                int a=0;
                int q=0;
                @Override
                public void onTick(long millisUntilFinished) {
                    tvTime.setVisibility(View.VISIBLE);
                    tvTime.setText(millisUntilFinished/1000+"");
                    Log.v("Log_tag", "Tick of Progress"+ q+ millisUntilFinished);
                    q++;
                    a++;
                    progressBar1.setProgress((int) q*100/(5000/100));
                    Log.e("progressbar1",a+"");
                    ivNext.setVisibility(View.INVISIBLE);
                    btnRecord.setVisibility(View.INVISIBLE);

//                    tvTime.setText(a);
                }

                @Override
                public void onFinish() {
                    //Do what you want
                    q++;
                    progressBar1.setProgress(100);
                    ivNext.setVisibility(View.VISIBLE);
                }
            };
            mCountDownTimer1.start();
        }
    }

    private void progressBar(){
        record_progress_bar.setProgress(i);
        mCountDownTimer=new CountDownTimer(3000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                record_progress_bar.setProgress((int)i*100/(3000/100));

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                record_progress_bar.setProgress(100);
                btnRecord.setVisibility(View.VISIBLE);
            }
        };
        mCountDownTimer.start();
    }
}
