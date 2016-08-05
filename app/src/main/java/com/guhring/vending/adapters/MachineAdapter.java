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
import com.guhring.vending.helpers.FontManager;
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


        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_machine, parent, false);
        // Get title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.machine_list_title);
        // Get thumbnail element
        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.machine_list_thumbnail);
        // Get location element
        TextView locationTextView = (TextView) rowView.findViewById(R.id.machine_list_location);
        // Get user element
        TextView userTextView = (TextView) rowView.findViewById(R.id.machine_list_user);
        // Get machine element
        TextView machineTextView = (TextView) rowView.findViewById(R.id.machine_list_machine);
        // Get model element
        TextView modelTextView = (TextView) rowView.findViewById(R.id.machine_list_model);
        // Get lastcloudupload element
        TextView lastclouduploadTextView = (TextView) rowView.findViewById(R.id.machine_list_lastcloudupload);
        // Get dot element
        fontFamily = Typeface.createFromAsset(mContext.getAssets(), "fontawesome-webfont.ttf");
        TextView dotTextView = (TextView) rowView.findViewById(R.id.machine_list_dot);
        dotTextView.setTypeface(fontFamily);

        Machine machine = (Machine) getItem(position);
        titleTextView.setText(machine.title);
        locationTextView.setText(machine.location);
        userTextView.setText(machine.user);
        machineTextView.setText(machine.machine);
        modelTextView.setText(machine.model);
        lastclouduploadTextView.setText(machine.lastcloudupload);
        dotTextView.setText("\uF111");

        Picasso.with(mContext).load(machine.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
        return rowView;
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
