package wodule.com.wodule.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import wodule.com.wodule.R;

public class ActLicense extends AppCompatActivity {
    private TextView lbDisagree,lbAgree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_license);
        initUI();

        lbDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lbAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUI() {
        lbAgree = (TextView) findViewById(R.id.lbAgree);
        lbDisagree = (TextView) findViewById(R.id.lbDisagree);
    }

}
