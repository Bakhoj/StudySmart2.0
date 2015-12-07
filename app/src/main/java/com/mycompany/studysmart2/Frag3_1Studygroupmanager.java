package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.mycompany.studysmart2.data.Logic;
import com.mycompany.studysmart2.data.StudentChoice;

/**
 * Created by anders on 21-Nov-15.
 */
public class Frag3_1Studygroupmanager extends Fragment {

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
        mViewPager.setCurrentItem(StudentChoice.instance.sgmPos);

        return root;
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        ((MainAct) activity).setTitle("Studygroup Manager");
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

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
        public int getCount() {
            // shows the amount of courses

            return Logic.instance.studyGroupsMaster.getCourseCount();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return Logic.instance.studyGroupsMaster.getCourseName(position);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {
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

        //private StudyGroupsMaster studygroups;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.frag3_1studygroupmanager, container, false);
            TextView textView = (TextView) root.findViewById(R.id.studygroupmanager_label);
            //studygroups = Logic.instance.studyGroupsMaster;

            textView.setText("Studygroup manager");
            ListView sglist = (ListView) root.findViewById(R.id.studygroupmanager_list);
            Bundle args = this.getArguments();
            sglist.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, Logic.instance.studyGroupsMaster.getSubGroups(args.getInt(ARG_SECTION_NUMBER)-1)));

            sglist.setOnItemClickListener(this);

            return root;
        }


        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setStudyGroup(position);
        }

        private void setStudyGroup(int position){
            StudentChoice.instance.coursePos = position;
            System.out.println("Chosen Studygroup: " + Logic.instance.studyGroupsMaster.getGroupName(position));

            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.main_content, new Frag3_2StudyGroup())
                    //.addToBackStack(null)
                    .commit();
        }
    }

}
