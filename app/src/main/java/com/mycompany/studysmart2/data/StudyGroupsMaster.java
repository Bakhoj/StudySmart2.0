package com.mycompany.studysmart2.data;

import java.util.Date;
import java.util.Vector;

/**
 * Created by MortenDam on 05-12-2015.
 */
public class StudyGroupsMaster {

    Vector<StudyGroup> groups;
    Vector<String> courseNames;
    int gLength;
    int cnLength;

    public class StudyGroup{
        String ID;
        String name;
        String shortDescription;
        String longDescription;
        String location;
        Course course;
        Date date;

        public StudyGroup(String id, String n, String sd, String ld, String l, Course c, Date d){
            ID = id;
            name = n;
            shortDescription = sd;
            longDescription = ld;
            location = l;
            course = c;
            date = d;
        }

        public void changeInfo(String id, String n, String sd, String ld, String l, Course c, Date d){
            if (id != null){
                ID = id;
            }
            if (n != null){
                name = n;
            }
            if (sd!= null){
                shortDescription = sd;
            }
            if (ld != null){
                longDescription = ld;
            }
            if (l != null){
                location = l;
            }
            if (c != null){
                course = c;
            }
            if (d != null){
                date = d;
            }
        }
    }

    public StudyGroupsMaster(){
        groups = new Vector<>();
        gLength = groups.size();
        courseNames = new Vector<>();
        cnLength = courseNames.size();
    }

    public void addGroup(String id, String n, String sd, String ld, String l, Course c, Date d){
        groups.add(new StudyGroup(id, n, sd, ld, l, c, d));
        gLength++;
        if(!courseNames.contains(c.name)){
            courseNames.add(c.name);
            cnLength++;
        }
    }

    public String getCourseName(int position){
        return courseNames.elementAt(position);
    }

    public void removeGroup(String id){
        for (int i = 0; i < groups.size(); ++i){
            if (groups.elementAt(i).ID.equals(id)){
                checkGroupNames(groups.elementAt(i).course.name);
                groups.remove(i);
                gLength--;
                break;
            }
        }
    }

    public int getGroupCount(){
        return gLength;
    }

    public int getCourseCount(){
        return cnLength;
    }

    public void checkGroupNames(String cn){
        int[] names = new int[cnLength];

        for(int i = 0; i < gLength;++i){
            names[courseNames.indexOf(groups.elementAt(i).course.name)]++;
        }

        if(names[courseNames.indexOf(cn)] == 1){
            courseNames.remove(cn);
            cnLength--;
        }
    }

    public void sortByDate(){
        Vector<StudyGroup> res = new Vector<>();
        Date tempDate;
        int tempPos = 0;
        while(!groups.isEmpty()) {
            tempDate = new Date(2147483647);
            for (int i = 0; i < groups.size(); ++i) {
                if (!tempDate.before(groups.elementAt(i).date)) {
                    tempDate = groups.elementAt(i).date;
                    tempPos = i;
                }
            }
            res.add(groups.elementAt(tempPos));
            groups.remove(tempPos);
        }

        groups = res;
    }

    public void updateElement(String oldid, String newid, String n, String sd, String ld, String l, Course c, Date d){
        for (int i = 0; i < groups.size(); ++i){
            if (groups.elementAt(i).ID.equals(oldid)){
                groups.elementAt(i).changeInfo(newid, n, sd, ld, l, c, d);
                break;
            }
        }
        if(!courseNames.contains(c.name)){
            courseNames.add(c.name);
            cnLength++;
        }
    }
}
