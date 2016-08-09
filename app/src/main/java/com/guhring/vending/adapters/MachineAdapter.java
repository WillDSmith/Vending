package com.guhring.vending.adapters;

import android.content.Context;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guhring.vending.R;
import com.guhring.vending.models.Machine;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by SmithW on 8/4/2016.
 */
public class MachineAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Machine> mDataSource;
    private ArrayList<Machine> arrayList;
    private Typeface fontFamily;



    public MachineAdapter(Context context, ArrayList<Machine> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = new ArrayList<Machine>();
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
            convertView = mInflater.inflate(R.layout.list_item_machine_data, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.machine_list_title);
            holder.locationTextView = (TextView) convertView.findViewById(R.id.machine_list_location);
            holder.userTextView = (TextView) convertView.findViewById(R.id.machine_list_user);
            holder.machineTextView = (TextView) convertView.findViewById(R.id.machine_list_machine);
            holder.modelTextView = (TextView) convertView.findViewById(R.id.machine_list_model);
            holder.lastclouduploadTextView = (TextView) convertView.findViewById(R.id.machine_list_lastcloudupload);
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.machine_list_thumbnail);
            holder.dotTextView = (TextView) convertView.findViewById((R.id.machine_list_dot));

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView locationTextView = holder.locationTextView;
        TextView userTextView = holder.userTextView;
        TextView machineTextView = holder.machineTextView;
        TextView modelTextView = holder.modelTextView;
        TextView lastclouduploadTextView = holder.lastclouduploadTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView dotTextView = holder.dotTextView;


        Machine machine = (Machine) getItem(position);

        titleTextView.setText(machine.title);
        locationTextView.setText(machine.location);
        userTextView.setText(machine.user);
        machineTextView.setText(machine.machine);
        modelTextView.setText(machine.model);
        lastclouduploadTextView.setText(machine.lastcloudupload);

        fontFamily = Typeface.createFromAsset(mContext.getAssets(), "fontawesome-webfont.ttf");
        dotTextView.setTypeface(fontFamily);
        dotTextView.setText("\uF111");

        Picasso.with(mContext).load(machine.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
        return convertView;
    }

    // Implement a ViewHolder Class which stores each of the row's subviews,
    // and in turn is stored inside the tag field of the layout
    private static class ViewHolder {
        public TextView  titleTextView;
        public ImageView thumbnailImageView;
        public TextView  locationTextView;
        public TextView  userTextView;
        public TextView  machineTextView;
        public TextView  modelTextView;
        public TextView  lastclouduploadTextView;
        public TextView  dotTextView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());

        mDataSource.clear();
        if (charText.length() == 0) {
            mDataSource.addAll(arrayList);
        }else {
            for (Machine machinedetail : arrayList) {
                if (charText.length() != 0 && machinedetail.user.toLowerCase(Locale.getDefault()).contains(charText)) {
                   mDataSource.add(machinedetail);
                }else if (charText.length() != 0 && machinedetail.machine.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mDataSource.add(machinedetail);
                }
            }
        }
        notifyDataSetChanged();
    }
}
