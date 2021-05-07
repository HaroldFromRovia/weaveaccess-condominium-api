package com.waveaccess.condominiumapi.utils;

import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.models.Resource;

import java.util.Comparator;

public class TimeComparator implements Comparator<Reservation> {

    @Override
    public int compare(Reservation reservation, Reservation t1) {
        return reservation.getStartTime().compareTo(t1.getStartTime());
    }
}
