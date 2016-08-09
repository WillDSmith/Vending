package com.guhring.vending.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guhring.vending.R;
import com.guhring.vending.models.Report;

import java.util.ArrayList;

/**
 * Created by SmithW on 8/9/2016.
 */
public class ReportAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Report> mDataSource;
    private ArrayList<Report> arrayList;

    public ReportAdapter(Context context, ArrayList<Report> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = new ArrayList<Report>();
        arrayList.addAll(mDataSource);

    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_machine_reports, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.reports_list_title);
            holder.subTitleTextView = (TextView) convertView.findViewById(R.id.reports_list_subtitle);


            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView subTitleTextView = holder.subTitleTextView;

        Report report = (Report) getItem(position);

        titleTextView.setText(report.title);
        subTitleTextView.setText(report.subtitle);

        return convertView;
    }

    // Implement a ViewHolder Class which stores each of the row's subviews,
    // and in turn is stored inside the tag field of the layout
    private static class ViewHolder {
        public TextView  titleTextView;
        public TextView  subTitleTextView;

    }
}
