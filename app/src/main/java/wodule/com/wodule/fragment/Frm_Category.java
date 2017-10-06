package wodule.com.wodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.adapter.AdapterCategory;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.LineCategory;
import wodule.com.wodule.object.ListDataCategory;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;
import wodule.com.wodule.utils.BaseTFragment;

/**
 * Created by MyPC on 06/10/2017.
 */
public class Frm_Category extends BaseTFragment {
    private TextView lbNoresult;
    private ListView list_category;
    private AdapterCategory adapterCategory;
    private ArrayList<LineCategory> arr = new ArrayList<LineCategory>();
    private APIService mAPIService;
    private ImageView ivBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_category, container, false);
        lbNoresult = (TextView) view.findViewById(R.id.lbNoresult);
        list_category = (ListView) view.findViewById(R.id.list_category);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mAPIService = APIUtils.getAPIService();
        getData();
        list_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QTSHelp.setNumCategory(getActivity(),arr.get(position).getId());
                Log.e("number",arr.get(position).getId()+"");
                FrmInstructions fragment1 = new FrmInstructions();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();
            }
        });
        pDialog.show();
        return view;
    }
    private void getData(){
        mAPIService.getCategory("Bearer "+QTSHelp.getAccessToken(getActivity())).enqueue(new Callback<ListDataCategory>() {
            @Override
            public void onResponse(Call<ListDataCategory> call, Response<ListDataCategory> response) {
                Log.e("category reponse",response.body().toString());
                if (response.isSuccessful()){
                    pDialog.cancel();
                    for (int i =0;i<=response.body().getData().size()-1;i++){
                        arr.add(new LineCategory(response.body().getData().get(i).getIdentifier(),response.body().getData().get(i).getSubject().toString(),response.body().getData().get(i).getDetails()));
                    }
                    if (arr.size()>0) {
                        lbNoresult.setVisibility(View.GONE);
                        list_category.setVisibility(View.VISIBLE);
                        adapterCategory = new AdapterCategory(getActivity(), arr);
                        list_category.setAdapter(adapterCategory);
                    }
                }else {
                    try {
                        pDialog.cancel();
                        Log.e("no success",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListDataCategory> call, Throwable t) {
                pDialog.cancel();
                Log.e("category onFailure",t.toString());
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
}
