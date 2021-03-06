package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.studysmart2.data.Course;
import com.mycompany.studysmart2.data.Homework;
import com.mycompany.studysmart2.data.Logic;
import com.mycompany.studysmart2.data.StudentChoice;



/**
 * Created by anders on 21-Nov-15.
 *
 * Fragement show homework
 *
 *
 */
public class Frag2_2Homework extends Fragment implements View.OnClickListener{

    Button read, postpone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag2_2homework, container, false);

        Homework homework = Logic.instance.student.Course[StudentChoice.instance.coursePos].homeworks[StudentChoice.instance.homeworkPos];


        TextView header = (TextView) root.findViewById(R.id.homework_header_text);
        header.setText(homework.title);

        TextView context = (TextView) root.findViewById(R.id.homework_context_text);
        context.setText(homework.description);

        read = (Button) root.findViewById(R.id.homework_read_btn);
        postpone = (Button) root.findViewById(R.id.homework_postponed_btn);

        read.setOnClickListener(this);
        postpone.setOnClickListener(this);
        ((MainAct) getActivity()).setTitle(Logic.instance.student.Course[StudentChoice.instance.coursePos].name);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homework_read_btn:
                setStatus(Homework.READ);
                break;
            case R.id.homework_postponed_btn:
                setStatus(Homework.POSTPONED);
                break;
        }
        Fragment fragment;
        switch (StudentChoice.instance.fromView) {
            case StudentChoice.FROM_HOMEWORKCALENDAR:
                fragment = new Frag2_1Homeworkcalendar();
                break;
            case StudentChoice.FROM_POSTPONEDHOMEWORK:
                fragment = new Frag2_3PostponedHomework();
                break;
            default:
                fragment = new Frag2_1Homeworkcalendar();
                break;
        }

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.main_content, fragment)
                .commit();
    }


    private void setStatus(int status) {
        Logic.instance.student.Course[StudentChoice.instance.coursePos].homeworks[StudentChoice.instance.homeworkPos].status = status;
    }
}
