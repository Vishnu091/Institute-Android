package com.nit.institute.Course;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nit.institute.R;
import com.nit.institute.SQLite.DatabaseHandler;
import com.nit.institute.Student.StudentsAttendance;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    ArrayList<CourseGetterSetter> dbCourseList;
    ListView listCourse;
    Button bAdd;
    DatabaseHandler db;
    //ArrayAdapter<CourseGetterSetter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        listCourse = (ListView) findViewById(R.id.listCourse);
        bAdd = (Button) findViewById(R.id.bAdd);
        db = new DatabaseHandler(this);

        dbCourseList = db.getCourseList();


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        for(int i=0;i<dbCourseList.size();i++){
            Log.d("tag",String.valueOf(dbCourseList.get(i).getCourseName()));
        }



        for (int i = 0; i < dbCourseList.size(); i++) {
                populateCourseList();
        }

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateCourse.class);
                startActivity(i);
            }
        });

        listCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in =new Intent(getApplicationContext(),StudentsAttendance.class);
                in.putExtra("CourseCode",dbCourseList.get(i).getCourseCode());
                in.putExtra("ColumnIndex",dbCourseList.get(i).getClmIndex());
                startActivity(in);
            }
        });


    }

    private void populateCourseList(){
        ArrayAdapter<CourseGetterSetter> adapter=new CourseListAdapter();
        listCourse.setAdapter(adapter);
    }


    public class CourseListAdapter extends ArrayAdapter<CourseGetterSetter> {

        public CourseListAdapter() {
            super(getApplicationContext(), R.layout.activity_course_item_layout,dbCourseList);
        }



        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.activity_course_item_layout,parent,false);
            }
            final CourseGetterSetter currentEvent=dbCourseList.get(position);
            TextView CourseCode=(TextView)convertView.findViewById(R.id.tCode);
            TextView CourseName=(TextView)convertView.findViewById(R.id.tCName);
            CourseCode.setText(currentEvent.getCourseCode());
            CourseName.setText(currentEvent.getCourseName());
            return  convertView;
        }
    }


}