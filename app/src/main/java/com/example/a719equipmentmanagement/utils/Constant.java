package com.example.a719equipmentmanagement.utils;

import android.graphics.Color;

import com.example.a719equipmentmanagement.R;

public class Constant {


    public static String getEquipType(int type) {
        String typeStr = null;
        switch (type) {
            case 1:
                typeStr = "借用";
                break;
            case 2:
                typeStr = "归还";
                break;
            case 3:
                typeStr = "送检申请";
                break;
            case 4:
                typeStr = "审核送检";
                break;
            case 5:
                typeStr = "送检借出";
                break;
            case 6:
                typeStr = "送检归还";
                break;
            case 7:
                typeStr = "报废申请";
                break;
            case 8:
                typeStr = "审核报废";
                break;
            case 9:
                typeStr = "报废处理";
                break;
            case 10:
                typeStr = "封存";
                break;
            case 11:
                typeStr = "解封申请";
                break;
            case 12:
                typeStr = "审核解封";
                break;
            case 13:
                typeStr = "解封";
                break;
            case 14:
                typeStr = "编辑";
                break;
            case 15:
                typeStr = "删除";
                break;
        }
        return typeStr;
    }

    public static String getDeviceState(int state) {
        String deviceStateStr = null;
        switch (state) {
            case 0:
                deviceStateStr = "可用";
                break;
            case 1:
                deviceStateStr = "借用";
                break;
            case 2:
                deviceStateStr = "送检占用";
                break;
            case 3:
                deviceStateStr = "送检";
                break;
            case 4:
                deviceStateStr = "报废占用";
                break;
            case 5:
                deviceStateStr = "报废";
                break;
            case 6:
                deviceStateStr = "封存";
                break;
            case 7:
                deviceStateStr = "解封占用";
                break;
            case 8:
                deviceStateStr = "过期";
                break;
            case 9:
                deviceStateStr = "外借";
                break;
        }
        return deviceStateStr;
    }
}