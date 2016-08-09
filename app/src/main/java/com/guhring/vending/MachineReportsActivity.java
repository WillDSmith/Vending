package com.guhring.vending;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.guhring.vending.adapters.MachineAdapter;
import com.guhring.vending.adapters.ReportAdapter;
import com.guhring.vending.models.Machine;
import com.guhring.vending.models.Report;

import java.util.ArrayList;

public class MachineReportsActivity extends AppCompatActivity {

    private ReportAdapter adapter;
    private ListView mListView;
    private ArrayList<Report> reportList;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.machinereports);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        mListView = (ListView) findViewById(R.id.machine_report_list_view);
        reportList = Report.getReportsFromFile("reports.json", this);
        adapter = new ReportAdapter(this, reportList);
        if (mListView != null) {
            mListView.setAdapter(adapter);

        }
    }
}
