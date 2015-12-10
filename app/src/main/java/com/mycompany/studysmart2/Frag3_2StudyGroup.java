package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mycompany.studysmart2.data.Homework;
import com.mycompany.studysmart2.data.Logic;
import com.mycompany.studysmart2.data.StudentChoice;
import com.mycompany.studysmart2.data.StudyGroupsMaster;

/**
 * Created by MortenDam on 06-12-2015.
 */
public class Frag3_2StudyGroup  extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag3_2studygroup, container, false);

        StudyGroupsMaster SGM = Logic.instance.studyGroupsMaster;
        int pos = StudentChoice.instance.coursePos;
        System.out.println(pos);
        TextView header = (TextView) root.findViewById(R.id.studygroup_header_text);
        header.setText(SGM.getGroupName(pos));

        TextView context = (TextView) root.findViewById(R.id.studygroup_context_text);
        context.setText(SGM.groups.elementAt(pos).longToString());

        ((MainAct) getActivity()).setTitle(Logic.instance.studyGroupsMaster.getGroupName(StudentChoice.instance.coursePos));

        return root;
    }

}
