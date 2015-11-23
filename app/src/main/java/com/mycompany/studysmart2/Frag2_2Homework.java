package com.mycompany.studysmart2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.mycompany.studysmart2.data.Homework1;
import com.mycompany.studysmart2.data.StudentChoice;



/**
 * Created by anders on 21-Nov-15.
 *
 * Fragement frontpage
 *
 * This will be the first fragment shown for the user when logged in.
 * He will be able to choose a supported university here.
 *
 */
public class Frag2_2Homework extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag2_2homework, container, false);

        Homework1 homework = StudentChoice.instance.homework;

        TextView header = (TextView) root.findViewById(R.id.homework_header_text);
        header.setText(homework.title);

        TextView context = (TextView) root.findViewById(R.id.homework_context_text);
        context.setText(homework.description);

        return root;
    }
}
