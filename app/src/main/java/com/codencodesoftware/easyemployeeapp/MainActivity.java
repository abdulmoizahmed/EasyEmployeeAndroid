package com.codencodesoftware.easyemployeeapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codencodesoftware.easyemployeeapp.databinding.MainActivityBinding;
import com.codencodesoftware.easyemployeeapp.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    MainActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.main_activity);
        setSupportActionBar(binding.toolbar);
        binding.root.markAttendance.setOnClickListener(new CustomClickListener());
        binding.root.viewAttendance.setOnClickListener(new CustomClickListener());





    }

    private class CustomClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == binding.root.markAttendance)
                startActivity(new Intent(MainActivity.this,MarkAttendance.class));
            else if(view == binding.root.viewAttendance)
                startActivity(new Intent(MainActivity.this,ViewAttendanceActivity.class));


        }
    }
}
