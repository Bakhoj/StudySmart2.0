package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;
import com.mycompany.studysmart2.data.Homework;
import com.mycompany.studysmart2.data.Logic;
import com.mycompany.studysmart2.data.StudentChoice;

import java.util.Arrays;
import java.util.List;


/**
 * Created by anders on 21-Nov-15.
 * Frag2 1Homeworkcalendar
 * this class setup the
 */
public class Frag2_1Homeworkcalendar extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag2_1vp_homeworkcalendar, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) root.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);

        mViewPager.setVerticalScrollbarPosition(StudentChoice.instance.sgmPos);

        return root;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        //Change FragmentPagerAdapter to FragmentStatePagerAdapter
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getItemPosition(Object object){
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "7days";
                case 1:
                    return "Next week";
                case 2:
                    return "All";
            }
            return null;
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int position) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        private List<Homework> homework;
        private Homework[] homeworks;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.frag2_1homeworkcalendar, container, false);
            homeworks = new Homework[0];

            for (int i = 0; i < Logic.instance.student.Course.length; i++){
                for (int j = 0; j < Logic.instance.student.Course[i].homeworks.length; j++) {
                    Homework[] temp_homeworks = homeworks;
                    homeworks = new Homework[homeworks.length + 1];
                    for(int k = 0; k < temp_homeworks.length; k++) {
                        homeworks[k] = temp_homeworks[k];
                    }
                    homeworks[temp_homeworks.length] = Logic.instance.student.Course[i].homeworks[j];
                }
            }

            homework = Arrays.asList(homeworks);
            Arrays.sort(homeworks);

            ListView hwlist = (ListView) root.findViewById(R.id.homeworkcalendar_list);
            hwlist.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, homework));
            hwlist.setOnItemClickListener(this);

            return root;
    }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setHomework(position);
        }

        private void setHomework(int position){
            StudentChoice.instance.homework = homeworks[position];
            StudentChoice.instance.setCourse();
            StudentChoice.instance.fromView = StudentChoice.FROM_HOMEWORKCALENDAR;
            Log.d("Homework Choice", StudentChoice.instance.homework.title);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.main_content, new Frag2_2Homework())
//                    .addToBackStack(null)
                    .commit();
        }
    }
}
