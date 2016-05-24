package com.krystianklimek.zooapp.fragments;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by: Krystian Klimek
 * Date: 24.05.2016.
 */

public class ZooMapFragment extends SupportMapFragment {
    public static ZooMapFragment getInstance() {
        ZooMapFragment fragment = new ZooMapFragment();
        return fragment;
    }
}
