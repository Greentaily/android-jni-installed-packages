package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lib.DeviceInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        TextView androidId_textView = findViewById(R.id.textView_androidId_value);
        androidId_textView.setText(DeviceInfo.getAndroidId(context));

        RecyclerView recyclerView = findViewById(R.id.recyclerView_packages);
        PackageListAdapter package_adapter = new PackageListAdapter(DeviceInfo.getPackages(context));
        recyclerView.setAdapter(package_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}