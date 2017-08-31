package com.example.aelaf.simpletodo.data.db;

import java.util.Date;

/**
 * Created by aelaf on 8/18/17.
 * Model class
 */

public class ToDo {
    private int _id;
    private String name;
    private String detail;
    private String date;
    private String priority;

    public ToDo(int _id,String name, String detail, String date,String priority) {
        this._id = _id;
        this.name = name;
        this.detail = detail;
        this.date = date;
        this.priority = priority;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
