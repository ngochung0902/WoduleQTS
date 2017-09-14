package wodule.com.wodule.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import wodule.com.wodule.R;

public class ActAssessmentHistoryE extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ListView list_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assessment_history_e);
        initUI();
        ivBack.setOnClickListener(this);
    }

    private void initUI() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        list_history = (ListView) findViewById(R.id.list_history);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ivBack)
        {
            finish();
        }
    }
}
