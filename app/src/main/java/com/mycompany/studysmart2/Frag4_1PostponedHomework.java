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

import com.mycompany.studysmart2.data.Homework;
import com.mycompany.studysmart2.data.Logic;
import com.mycompany.studysmart2.data.StudentChoice;

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
public class Frag4_1PostponedHomework extends Fragment implements AdapterView.OnItemClickListener{

    List<Homework> homework;
    Homework[] homeworks;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag4_1postponedhomework, container, false);

        homeworks = new Homework[0];
        int lengthb = Logic.instance.student.Course.length;
        for (int i = 0; i < lengthb; i++){
            for (int j = 0; j < Logic.instance.student.Course[i].homeworks.length; j++) {
                if (Logic.instance.student.Course[i].homeworks[j].status == 1){
                    Homework[] temp_homeworks = homeworks;
                    homeworks = new Homework[homeworks.length + 1];
                    for(int k = 0; k < temp_homeworks.length; k++) {
                        homeworks[k] = temp_homeworks[k];
                    }

                    homeworks[temp_homeworks.length] = Logic.instance.student.Course[i].homeworks[j];
                }
            }
        }

        homework = Arrays.asList(homeworks);
        Arrays.sort(homeworks);

        ListView hwlist = (ListView) root.findViewById(R.id.postponedhomework_list);
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
        System.out.println("Hoasdfmework Choice: " + StudentChoice.instance.homework.title);

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.main_content, new Frag2_2Homework())
                .addToBackStack(null)
                .commit();
    }
}
