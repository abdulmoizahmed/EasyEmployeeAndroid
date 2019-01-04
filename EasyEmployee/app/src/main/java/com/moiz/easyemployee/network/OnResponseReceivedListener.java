package com.moiz.easyemployee.network;

/**
 * Created by Moiz-IHS on 12/21/2017.
 */

public interface OnResponseReceivedListener {
    void onSuccessReceived(Object response);
    void onFailureReceived(String errorMessage);
}
