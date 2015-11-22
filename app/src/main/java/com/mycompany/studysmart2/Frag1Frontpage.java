package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.studysmart2.data.Logik;
import com.mycompany.studysmart2.data.University;

import java.util.Arrays;
import java.util.List;


/**
 * Created by anders on 21-Nov-15.
 *
 * Fragement frontpage
 *
 * This will be the first fragment shown for the user when logged in.
 * He will be able to choose a supported university here.
 *
 */
public class Frag1Frontpage extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private List<University> universities;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frontpage_frag, container, false);
        //TextView textView = (TextView) root.findViewById(R.id.homeworkcalendar_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


        TextView header = (TextView) root.findViewById(R.id.frontpage_header_text);
        header.setText(R.string.welcome_application);

        TextView context = (TextView) root.findViewById(R.id.frontpage_context_text);
        context.setText(R.string.welcome_application_text);

        universities = Arrays.asList(Logik.instance.availableUniversities);

        ListView unilist = (ListView) root.findViewById(R.id.frontpage_uni_list);
        unilist.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, universities));

        unilist.setOnItemClickListener(this);

        root.findViewById(R.id.frontpage_btn).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        setUniversity(0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setUniversity(position);
    }

    private void setUniversity(int position) {

        Logik.instance.student.university = Logik.instance.availableUniversities[position];

        Frag2Homeworkcalendar hwc = new Frag2Homeworkcalendar();

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.main_content, hwc)
                .addToBackStack(null)
                .commit();
    }
}
