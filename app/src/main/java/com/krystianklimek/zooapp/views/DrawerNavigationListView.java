package com.krystianklimek.zooapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.krystianklimek.zooapp.adapters.DrawerNavigationListAdapter;
import com.krystianklimek.zooapp.events.DrawerSectionItemClickedEvent;
import com.krystianklimek.zooapp.utils.EventBus;

/**
 * Created by: Krystian Klimek
 * Date: 24.05.2016.
 */
public class DrawerNavigationListView extends ListView implements AdapterView.OnItemClickListener {
    public DrawerNavigationListView(Context context) {
        this(context, null);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter(getContext(), 0);
        adapter.add("Exhibits");
        adapter.add("Gallery");
        adapter.add("Maps");

        setAdapter(adapter);

        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getInstance().post(new DrawerSectionItemClickedEvent((String) parent.getItemAtPosition(position)));
    }
}
