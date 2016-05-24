package com.krystianklimek.zooapp.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.krystianklimek.zooapp.R;
import com.krystianklimek.zooapp.events.DrawerSectionItemClickedEvent;
import com.krystianklimek.zooapp.fragments.ExhibitsListFragment;
import com.krystianklimek.zooapp.fragments.GalleryFragment;
import com.krystianklimek.zooapp.fragments.ZooMapFragment;
import com.krystianklimek.zooapp.utils.Constants;
import com.krystianklimek.zooapp.utils.EventBus;
import com.krystianklimek.zooapp.utils.Utils;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mCurrentFragmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.drawer_opened);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.drawer_closed);
                }
            }
        };

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);

        displayInitialFragment();
    }

    private void displayInitialFragment() {
        Utils.replaceFragment(getSupportFragmentManager(), ExhibitsListFragment.getInstance());
        mCurrentFragmentTitle = "Exhibits";
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getInstance().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onDrawerSectionItemClickEvent (DrawerSectionItemClickedEvent event) {
        drawerLayout.closeDrawers();

        if (event == null || TextUtils.isEmpty(event.section) || event.section.equalsIgnoreCase(mCurrentFragmentTitle)) {
            return;
        }

        Utils.showToast(this, "MainActivity: Section Clicked: " + event.section);

        if (event.section.equalsIgnoreCase("maps")) {
            Utils.replaceFragment(getSupportFragmentManager(), ZooMapFragment.getInstance());
        } else if (event.section.equalsIgnoreCase("gallery")) {
            Utils.replaceFragment(getSupportFragmentManager(), GalleryFragment.getInstance());
        } else if (event.section.equalsIgnoreCase("exhibits")) {
            Utils.replaceFragment(getSupportFragmentManager(), ExhibitsListFragment.getInstance());
        } else {
            return;
        }

        mCurrentFragmentTitle = event.section;
    }
}
