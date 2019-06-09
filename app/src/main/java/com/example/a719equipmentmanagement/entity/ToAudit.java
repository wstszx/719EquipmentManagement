package com.example.a719equipmentmanagement.entity;

import java.io.Serializable;
import java.util.List;

public class ToAudit implements Serializable {

    /**
     * total : 0
     * rows : []
     * code : 0
     */

    private int total;
    private int code;
    private List<?> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
