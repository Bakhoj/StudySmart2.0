package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mycompany.studysmart2.data.Logik;

public class MainAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logik.instance = new Logik();
        Logik.instance.makeTestData();

        setContentView(R.layout.main_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            Logik.instance = new Logik();
            Logik.instance.makeTestData();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_content, new Frag1Frontpage())
                    .commit();
        }

//        app:headerLayout="@layout/leftmenu_head"
//        app:menu="@menu/activity_home_work_drawer"

        View headerLayout = navigationView.inflateHeaderView(R.layout.leftmenu_head);


/*      TODO: check how to get this working later on! adding the students name and email to leftmenu header
        TextView left_user = (TextView) findViewById(R.id.leftmenu_head_username);
        TextView left_mail = (TextView) findViewById(R.id.leftmenu_head_usermail);

        left_user.setText(Logik.instance.student.lastname +", " + Logik.instance.student.name);
        left_mail.setText(Logik.instance.student.email);
  */  }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.leftmenu_homeworkcalendar) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.main_content, new Frag2Homeworkcalendar())
                    .commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.leftmenu_studygroup_groups) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.main_content, new Frag3Studygroupmanager())
                    .commit();
        } else if (id == R.id.leftmenu_studygroup_meetings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
