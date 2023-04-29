package com.ritvik.reminder2;

public class ReminderModel {
    private String name;
    private int hour;
    private int minute;
    private int date;
    private int month;
    private int year;
    private String desc;

    public ReminderModel(String name, int hour, int minute, int date, int month, int year, String desc) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.date = date;
        this.month = month;
        this.year = year;
        this.desc = desc;
    }


    public String toString() {
        return "ReminderModel{" +
                "name='" + name + '\'' +
                ", hour=" + hour +
                ", minute=" + minute +
                ", date=" + date +
                ", month=" + month +
                ", year=" + year +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
