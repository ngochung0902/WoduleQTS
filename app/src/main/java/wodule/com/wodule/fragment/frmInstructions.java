package wodule.com.wodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.ExamCategory;
import wodule.com.wodule.object.ListDataExam;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;
import wodule.com.wodule.utils.BaseTFragment;

/**
 * Created by MyPC on 14/09/2017.
 */
public class FrmInstructions extends BaseTFragment {
    private TextView btnStartAssess;
    private APIService mAPIService;
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
        QTSConstrains.arrayList.clear();
        mAPIService = APIUtils.getAPIService();
        pDialog.show();
        getAPI();

        return view;
    }

    private void getAPI(){
        mAPIService.getExamCategory("api/category/"+ QTSHelp.getNumCategory(getContext())+"/exams").enqueue(new Callback<ListDataExam>() {
            @Override
            public void onResponse(Call<ListDataExam> call, Response<ListDataExam> response) {
                Log.e("category exam reponse",response.body().getData().toString());
                if (response.isSuccessful()){
                    for (int i =0;i<=response.body().getData().size()-1;i++){
                        if (response.body().getData().get(i).getQuestioner()!=null)
                        {
                            QTSConstrains.arrayList.add(new ExamCategory(
                                    response.body().getData().get(i).getIdentifier(),
                                    response.body().getData().get(i).getNumber(),
                                    response.body().getData().get(i).getQuestioner(),
                                    response.body().getData().get(i).getDetail().toString(),
                                    response.body().getData().get(i).getScore(),
                                    response.body().getData().get(i).getSubject(),
                                    response.body().getData().get(i).getAdmin(),
                                    response.body().getData().get(i).getCreationDate().toString(),
                                    response.body().getData().get(i).getLastChange().toString(),
                                    response.body().getData().get(i).getDeletedDate()));
                        }else {
                            QTSConstrains.arrayList.add(new ExamCategory(
                                    response.body().getData().get(i).getIdentifier(),
                                    response.body().getData().get(i).getNumber(),
                                    response.body().getData().get(i).getQuestioner(),
                                    response.body().getData().get(i).getPhoto().toString(),
                                    response.body().getData().get(i).getDetail().toString(),
                                    response.body().getData().get(i).getScore(),
                                    response.body().getData().get(i).getSubject(),
                                    response.body().getData().get(i).getAdmin(),
                                    response.body().getData().get(i).getCreationDate().toString(),
                                    response.body().getData().get(i).getLastChange().toString(),
                                    response.body().getData().get(i).getDeletedDate()));
                            Log.e("error",response.body().getData().get(i).getQuestioner()+"");
                        }
                    }
                }else {
                    Log.e("category exam reponse","no success");
                }
                pDialog.cancel();
            }

            @Override
            public void onFailure(Call<ListDataExam> call, Throwable t) {
                Log.e("category exam onFailure",t.getMessage().toString());
                pDialog.cancel();
            }
        });
    }
    @Override
    public String getFragmentTitle() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }
}
