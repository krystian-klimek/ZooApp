package com.krystianklimek.zooapp.fragments;

import android.support.v4.app.ListFragment;

/**
 * Created by: Krystian Klimek
 * Date: 24.05.2016.
 */

public class ExhibitsListFragment extends ListFragment {
     public static ExhibitsListFragment getInstance() {
         ExhibitsListFragment fragment = new ExhibitsListFragment();
         return fragment;
     }
}
