package wodule.com.wodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import wodule.com.wodule.R;
import wodule.com.wodule.object.LineCategory;

/**
 * Created by MyPC on 06/10/2017.
 */
public class AdapterCategory extends BaseAdapter {
    Context context;
    ArrayList<LineCategory> arrList;

    public AdapterCategory(Context context, ArrayList<LineCategory> arrList) {
        this.context = context;
        this.arrList = arrList;
    }

    @Override
    public int getCount() {
        return arrList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_category,null);
        TextView tv_to = (TextView) convertView.findViewById(R.id.tv_to);
        TextView tv_nho = (TextView) convertView.findViewById(R.id.tv_nho);
        LineCategory category = arrList.get(position);
        tv_to.setText(category.getTv_to());
        tv_nho.setText(category.getTv_nho());
        return convertView;
    }
}
