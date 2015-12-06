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

        Homework homework = StudentChoice.instance.homework;

        TextView header = (TextView) root.findViewById(R.id.homework_header_text);
        header.setText(homework.title);

        TextView context = (TextView) root.findViewById(R.id.homework_context_text);
        context.setText(homework.description);

        read = (Button) root.findViewById(R.id.homework_read_btn);
        postpone = (Button) root.findViewById(R.id.homework_postponed_btn);

        read.setOnClickListener(this);
        postpone.setOnClickListener(this);

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
    }

    private void setStatus(int status) {
        for(int i = 0; i < Logic.instance.student.Course.length; i++) {
            for(int j = 0; j < Logic.instance.student.Course[i].homeworks.length; j++) {
                if(Logic.instance.student.Course[i].homeworks[j] == StudentChoice.instance.homework) {
                    Logic.instance.student.Course[i].homeworks[j].status = status;
                    StudentChoice.instance.homework = Logic.instance.student.Course[i].homeworks[j];
                    Toast.makeText(getContext(), "Status stored", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }
}
