package com.moiz.easyemployee;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.moiz.easyemployee.adapter.AttendanceAdapter;
import com.moiz.easyemployee.adapter.OnItemClickListener;
import com.moiz.easyemployee.databinding.ActivityViewAttendanceBinding;
import com.moiz.easyemployee.model.AttendanceResponse;
import com.moiz.easyemployee.model.SearchResult;
import com.moiz.easyemployee.network.ApiServices;
import com.moiz.easyemployee.network.OnResponseReceivedListener;
import com.moiz.easyemployee.utils.CommonUtils;
import com.moiz.easyemployee.utils.DevicePreference;
import com.moiz.easyemployee.utils.NetworkProgressDialog;


public class ViewAttendanceActivity extends AppCompatActivity implements OnItemClickListener {

    public static final String CURRENT = "Current";
    ActivityViewAttendanceBinding binding;
    AttendanceResponse attendanceResponse;
    NetworkProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ViewAttendanceActivity.this, R.layout.activity_view_attendance);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.calendarRecycler.setLayoutManager(mLayoutManager);
        binding.rgMonth.setOnCheckedChangeListener(new CustomCheckChangeListener());
        progress = new NetworkProgressDialog(this);


    }

    private void getAttendance(final String selectedMonth) {
        progress.run();
        ApiServices.getAttendance(DevicePreference.getInstance().getString(DevicePreference.Key.USERKEY), DevicePreference.getInstance().getString(DevicePreference.Key.PASSKEY), selectedMonth.toLowerCase(), new OnResponseReceivedListener() {
            @Override
            public void onSuccessReceived(Object response) {
                progress.dismiss();
                binding.layoutMonth.setVisibility(View.VISIBLE);
                if(selectedMonth.equals(CURRENT))
                    binding.monthName.setText(CommonUtils.getCurrentMonthName().toUpperCase());
                else
                    binding.monthName.setText(CommonUtils.getPreviousMonthName().toUpperCase());
                attendanceResponse = (AttendanceResponse) response;
                binding.calendarRecycler.setHasFixedSize(true);
                binding.calendarRecycler.setItemAnimator(new DefaultItemAnimator());
                binding.calendarRecycler.setAdapter(new AttendanceAdapter(attendanceResponse.getSearchResult(), ViewAttendanceActivity.this));
            }

            @Override
            public void onFailureReceived(String errorMessage) {
                progress.dismiss();
                Toast.makeText(ViewAttendanceActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        binding.cardDetails.setVisibility(View.VISIBLE);
        SearchResult result = attendanceResponse.getSearchResult().get(position);
        binding.employeeName.setText(getString(R.string.name) + attendanceResponse.getEmployeeName());
        binding.employeeId.setText(getString(R.string.employee_code) + attendanceResponse.getEmployeeCode());
        binding.workingHours.setText(getString(R.string.working_hours) + (result.getWorkingHours() == null ? "N/A" : result.getWorkingHours()));
        binding.extraHours.setText(getString(R.string.extra_hours) + (result.getExtraHours() == null ? "N/A" : result.getExtraHours()));
        binding.shortHours.setText(getString(R.string.short_hours) + (result.getShortHours() == null ? "N/A" : result.getShortHours()));
        binding.date.setText(getString(R.string.date) + (result.getDate() == null ? "N/A" : result.getDate()));
        binding.checkIn.setText(getString(R.string.check_in_time) + (result.getCheckIn() == null ? "N/A" : result.getCheckIn()));
        binding.checkOut.setText(getString(R.string.check_out_time) + (result.getCheckOut() == null ? "N/A" : result.getCheckOut()));
        binding.attendanceType.setText(getString(R.string.attendance_type) + (result.getType() == null ? "N/A" : result.getType()));
    }

    private class CustomCheckChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            binding.cardDetails.setVisibility(View.GONE);
            String selectedMonth = CommonUtils.getRadioGroupSelectedText(radioGroup, binding.getRoot());
            getAttendance(selectedMonth);
        }
    }
}
