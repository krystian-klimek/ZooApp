package com.krystianklimek.zooapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.krystianklimek.zooapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: Krystian Klimek
 * Date: 24.05.2016.
 */
public class DrawerNavigationListAdapter extends ArrayAdapter<String> {

    public DrawerNavigationListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.navigation_drawer_list_item, parent, false );
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTextView.setText(getItem(position));

        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.titleTextView)
        TextView titleTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}