package wodule.com.wodule.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.adapter.AdapterAssessmentHistoryE;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.HistoryExam;
import wodule.com.wodule.object.ListData;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActAssessmentHistoryE extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ListView list_history;
    private ArrayList<HistoryExam> arr = new ArrayList<HistoryExam>();
    private AdapterAssessmentHistoryE adapter;
    private APIService mAPIService;
    private TextView lbNoresult;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assessment_history_e);
        initUI();
        ivBack.setOnClickListener(this);
        mAPIService = APIUtils.getAPIService();
        getAhistory();
        pDialog = new ProgressDialog(ActAssessmentHistoryE.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
    }


    private void initUI() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        list_history = (ListView) findViewById(R.id.list_history);
        lbNoresult = (TextView) findViewById(R.id.lbNoresult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ivBack)
        {
            finish();
        }
    }

    private void getAhistory(){
        mAPIService.getAhistory("Bearer " + QTSHelp.getAccessToken(getApplicationContext()),"api/users/"+QTSConstrains.ID+"/records").enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                Log.e("reponse Ahistory",response.toString());
                if (response.isSuccessful()){
                    for (int i=0;i<=response.body().getData().size()-1;i++){
                        StringBuilder a = new StringBuilder(response.body().getData().get(i).getCreationDate());
                        a.delete(10, a.length());
                        arr.add(new HistoryExam(a.toString(),String.valueOf(response.body().getData().get(i).getExam()),String.valueOf(response.body().getData().get(i).getScore())));
                        Log.e("arratList",String.valueOf(response.body().getData().get(i).getCreationDate()+"\n"+response.body().getData().get(i).getExam()+"\n"+response.body().getData().get(i).getScore()));
                    }
                    Log.e("arr",arr.size()+"");
                    list_history.setVisibility(View.VISIBLE);
                    lbNoresult.setVisibility(View.GONE);
                    adapter = new AdapterAssessmentHistoryE(getApplicationContext(),arr);
                    list_history.setAdapter(adapter);
                    pDialog.cancel();
                }else {
                    pDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {
                pDialog.cancel();
            }
        });
    }
}
