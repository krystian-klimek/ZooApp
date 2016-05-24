package com.krystianklimek.zooapp.utils;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.krystianklimek.zooapp.R;
import com.krystianklimek.zooapp.fragments.ExhibitsListFragment;

/**
 * Created by: Krystian Klimek
 * Date: 24.05.2016.
 */

public class Utils {
    public static void showToast (Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void replaceFragment (FragmentManager manager, Fragment fragment) {
        manager.beginTransaction().replace(R.id.container, fragment).commit();
    }
}
