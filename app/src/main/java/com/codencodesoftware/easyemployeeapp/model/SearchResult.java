package com.codencodesoftware.easyemployeeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Moiz-IHS on 7/28/2018.
 */

public class SearchResult {
    @SerializedName("day")
    @Expose
    private String day;
    /*@SerializedName("multipleCheckInOutList")
    @Expose
    private List multipleCheckInOutList;*/
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("extraHours")
    @Expose
    private String extraHours;
    @SerializedName("workingHours")
    @Expose
    private String workingHours;
    @SerializedName("checkOut")
    @Expose
    private String checkOut;
    @SerializedName("shortHours")
    @Expose
    private String shortHours;
    @SerializedName("checkIn")
    @Expose
    private String checkIn;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

  /*  public List getMultipleCheckInOutList() {
        return multipleCheckInOutList;
    }

    public void setMultipleCheckInOutList(List multipleCheckInOutList) {
        this.multipleCheckInOutList = multipleCheckInOutList;
    }
*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(String extraHours) {
        this.extraHours = extraHours;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getShortHours() {
        return shortHours;
    }

    public void setShortHours(String shortHours) {
        this.shortHours = shortHours;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
}
