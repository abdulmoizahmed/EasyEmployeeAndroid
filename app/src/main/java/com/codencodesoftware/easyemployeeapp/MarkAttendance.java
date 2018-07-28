package com.codencodesoftware.easyemployeeapp;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codencodesoftware.easyemployeeapp.databinding.ActivityMarkAttendanceBinding;
import com.codencodesoftware.easyemployeeapp.model.RestResponse;
import com.codencodesoftware.easyemployeeapp.network.ApiServices;
import com.codencodesoftware.easyemployeeapp.network.OnResponseReceivedListener;
import com.codencodesoftware.easyemployeeapp.utils.CommonUtils;
import com.codencodesoftware.easyemployeeapp.utils.DevicePreference;
import com.codencodesoftware.easyemployeeapp.utils.GoogleFusedLocation;
import com.codencodesoftware.easyemployeeapp.utils.LocationListener;
import com.codencodesoftware.easyemployeeapp.utils.NetworkProgressDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MarkAttendance extends AppCompatActivity implements OnMapReadyCallback {

    public static final int ZOOM_LEVEL = 15;
    private GoogleMap mMap;
    private GoogleFusedLocation fusedLocation;
    private ActivityMarkAttendanceBinding binding;
    private NetworkProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mark_attendance);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocation = new GoogleFusedLocation();
        progress = new NetworkProgressDialog(this);
        fusedLocation.init(this, new CustomLocationListener());
        binding.btnAttendance.setOnClickListener(new CustomClickListener());
        binding.btnCheckout.setOnClickListener(new CustomClickListener());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fusedLocation.connect();

    }


    @Override
    protected void onPause() {
        super.onPause();
        fusedLocation.disconnect();

    }

    private class CustomClickListener implements View.OnClickListener {

        public static final String IN = "in";
        public static final String OUT = "out";

        @Override
        public void onClick(View view) {
            if(view == binding.btnAttendance) {
                markAttendance(IN);
            }
            else if( view == binding.btnCheckout)
            {
                markAttendance(OUT);
            }
        }

        private void markAttendance(String inOut) {
            if (validateAttendance((inOut.equals(IN))?true:false)) {
                progress.show();
                ApiServices.postAttendance(DevicePreference.getInstance().getString(DevicePreference.Key.USERKEY), DevicePreference.getInstance().getString(DevicePreference.Key.PASSKEY), getAttendanceType(), String.valueOf(fusedLocation.getCurrentLocation().getLatitude()), String.valueOf(fusedLocation.getCurrentLocation().getLongitude()), inOut, new OnResponseReceivedListener() {
                    @Override
                    public void onSuccessReceived(Object response) {
                        progress.dismiss();
                        RestResponse restResponse = (RestResponse) response;
                        if (restResponse.getSuccess())
                            CommonUtils.showDismissableSuccessDialog(MarkAttendance.this, restResponse.getMessage(), "Great!").show();
                        else
                            CommonUtils.showDismissableFailedDialog(MarkAttendance.this, restResponse.getMessage(), "Sorry!").show();
                    }

                    @Override
                    public void onFailureReceived(String errorMessage) {
                        progress.dismiss();
                        CommonUtils.showDismissableFailedDialog(MarkAttendance.this, errorMessage, "Sorry!").show();

                    }
                });
            }
        }
    }




    private String getAttendanceType() {
        if (CommonUtils.getRadioGroupSelectedText(binding.attendanceStatus, binding.getRoot()).contains("Home"))
            return "W";
        else if (CommonUtils.getRadioGroupSelectedText(binding.attendanceStatus, binding.getRoot()).contains("Client"))
            return "O";
        else
            return "P";
    }

    private boolean validateAttendance(boolean isCheckIn) {

        boolean allValid = true;
        String errorMessage = "";
        if(isCheckIn) {
            if (CommonUtils.getRadioGroupSelectedText(binding.attendanceStatus, binding.getRoot()).equals("")) {
                allValid = false;
                errorMessage = "Select Attendance Type";

            }
        }
        if (fusedLocation.getCurrentLocation() == null) {
            allValid = false;
            errorMessage = "Your location is not available right now please turn on your location.";
        }

        if (!allValid)
            CommonUtils.showDismissableWarningDialog(this, errorMessage, "Error").show();

        return allValid;
    }

    private class CustomLocationListener implements LocationListener {
        @SuppressLint("MissingPermission")
        @Override
        public void onLocationReceived(Location location) {
            if (mMap != null) {
                LatLng currentLocation = new LatLng(fusedLocation.getCurrentLocation().getLatitude(), fusedLocation.getCurrentLocation().getLongitude());
                mMap.setMyLocationEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, ZOOM_LEVEL));

            }
        }
    }
}
