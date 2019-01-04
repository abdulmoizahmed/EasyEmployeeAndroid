package com.moiz.easyemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Moiz-IHS on 7/28/2018.
 */

public class AttendanceResponse {
    @SerializedName("searchResult")
    @Expose
    private List<SearchResult> searchResult = null;
    @SerializedName("actualTime")
    @Expose
    private String actualTime;
    @SerializedName("employeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<SearchResult> searchResult) {
        this.searchResult = searchResult;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
