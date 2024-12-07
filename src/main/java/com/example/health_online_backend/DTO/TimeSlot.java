package com.example.health_online_backend.DTO;

import java.util.List;

public class TimeSlot {
    int daySpacing;
    List<Integer> listSlot;

    public TimeSlot() {
    }

    public TimeSlot(int daySpacing, List<Integer> listSlot) {
        this.daySpacing = daySpacing;
        this.listSlot = listSlot;
    }

    public int getDaySpacing() {
        return daySpacing;
    }

    public void setDaySpacing(int daySpacing) {
        this.daySpacing = daySpacing;
    }

    public List<Integer> getListSlot() {
        return listSlot;
    }

    public void setListSlot(List<Integer> listSlot) {
        this.listSlot = listSlot;
    }
}
