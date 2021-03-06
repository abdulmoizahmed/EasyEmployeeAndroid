package com.moiz.easyemployee.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.net.NetworkInterface;
import java.util.*;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CommonUtils {

    @SuppressLint("ResourceType")
    private static Boolean validateRadioGroupEmpty(RadioGroup radioGroup) {
        Boolean isEmpty = false;
        if (radioGroup.getCheckedRadioButtonId() <= 0)
            isEmpty = true;
        return isEmpty;
    }

    public static String getRadioGroupSelectedText(RadioGroup group, View rootView) {
        String radioButtonText = "";
        if (!validateRadioGroupEmpty(group)) {
            RadioButton button = (RadioButton) group.findViewById(group.getCheckedRadioButtonId());
            radioButtonText = button.getText().toString();
        }
        return radioButtonText;
    }

    public static SweetAlertDialog showDismissableFailedDialog(Context context, String messageToShow, String title) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitleText(title.toUpperCase());
        pDialog.setContentText(messageToShow);
        return pDialog;
    }

    public static SweetAlertDialog showDismissableWarningDialog(Context context, String messageToShow, String title) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        pDialog.setTitleText(title.toUpperCase());
        pDialog.setContentText(messageToShow);
        return pDialog;
    }

    public static SweetAlertDialog showDismissableSuccessDialog(Context context, String messageToShow, String title) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText(title.toUpperCase());
        pDialog.setContentText(messageToShow);
        return pDialog;
    }

    public static SweetAlertDialog showYesNoSweetDialog(Context context, String messageToShow, String title, String postiveText, String Negitive, final SweetDialogListener listener) {
        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        pDialog.setTitleText(title.toUpperCase());
        pDialog.setContentText(messageToShow);
        pDialog.setCancelText(Negitive);
        pDialog.setConfirmText(postiveText);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                listener.onPositiveButtonClick();
                pDialog.dismiss();
            }
        });
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                listener.onNegativeButtonClick();
                pDialog.dismiss();
            }
        });
        return pDialog;
    }

    public static String getCurrentMonthName() {
        Calendar mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        return month;
    }

    public static String getPreviousMonthName()
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        return month;
    }

    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}
