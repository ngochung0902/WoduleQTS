package wodule.com.wodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wodule.com.wodule.R;
import wodule.com.wodule.object.HistoryExam;

/**
 * Created by MyPC on 15/09/2017.
 */
public class AdapterAssessmentHistoryE extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<HistoryExam> arrList;

    public AdapterAssessmentHistoryE(Context mContext) {
        this.context = mContext;
        inflater = LayoutInflater.from(context);
    }

    public AdapterAssessmentHistoryE(Context mContext, List<HistoryExam> arrList) {
        this.context = mContext;
        inflater = LayoutInflater.from(context);
        this.arrList = arrList;
    }

    public class ViewHolder {
        TextView date;
        TextView examId;
        TextView score;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_item_history, null);
            holder.date = (TextView)convertView.findViewById(R.id.row_date);
            holder.examId = (TextView)convertView.findViewById(R.id.row_examId);
            holder.score = (TextView)convertView.findViewById(R.id.row_score);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(String.valueOf(arrList.get(position).getDate()));
        holder.examId.setText(String.valueOf(arrList.get(position).getExam()));
        holder.score.setText(String.valueOf(arrList.get(position).getScore()));
        return convertView;
    }
    public void setData(List<HistoryExam> itemList) {
        this.arrList = itemList;
        this.notifyDataSetChanged();
    }
}
