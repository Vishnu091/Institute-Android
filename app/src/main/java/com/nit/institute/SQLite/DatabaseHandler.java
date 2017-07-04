package com.nit.institute.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nit.institute.Course.CourseGetterSetter;
import com.nit.institute.Student.StudentsGetterSetter;

import java.util.ArrayList;

/**
 * Created by Aakash on 14-Apr-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 9;
    // Database Name
    private static final String DATABASE_NAME = "Institute";

    private static final String TABLE_INDEX="TABLE_INDEX";
    // Contacts table name
    private static final String TABLE_COURSE= "COURSE";
    private static final String TABLE_STUDENTS="STUDENTS";

    private static final String COLUMN_VALUE="columnIndex";
    // Common column names
    private static final String COURSE_CODE = "courseCode";
   //Course column names
   private static final String COURSE_ID="courseId",COURSE_NAME="courseName",COURSE_BATCH="courseBatch",COURSE_BRANCH="courseBranch",COURSE_SEMESTER="courseSemester",COURSE_TIMING="courseTiming",COURSE_DAY="courseDay";
    //Students column names
    private static final String STUDENT_ID="studentsId",STUDENTS_REG="studentReg",STUDENTS_NAME="studentsName",STUDENTS_EMAIL="studentsEmail",STUDENTS_PHONE="studentPhone",STUDENTS_COURSE="studentsCourse";
    private static final String COURSE_FILE ="courseFile";
    private static final String TABLE_LOGIN ="LOGIN" ;
    private static final String NAME="name",USERNAME="userName",PASSWORD="password";
    private static final String LOGIN_ID ="loginId" ;



    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSE_TABLE="CREATE TABLE "+TABLE_COURSE+"(" +
                COURSE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COURSE_CODE+" TEXT NOT NULL ," + COURSE_FILE + " TEXT NOT " +
                "NULL,"+COURSE_NAME+" TEXT NOT NULL," +COLUMN_VALUE +" INTEGER ) ";
        String CREATE_STUDENTS_TABLE="CREATE TABLE "+TABLE_STUDENTS+"(" +
                STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+STUDENTS_NAME+" TEXT,"+STUDENTS_EMAIL+"" +
                " TEXT ,"+STUDENTS_PHONE+" TEXT ,"+STUDENTS_REG+" TEXT ,"+STUDENTS_COURSE+" TEXT )";
        String CREATE_LOGIN_TABLE="CREATE TABLE "+TABLE_LOGIN+"(" +
                LOGIN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ NAME+" TEXT ,"+USERNAME+" TEXT ,"+PASSWORD+" TEXT )" ;
        db.execSQL(CREATE_COURSE_TABLE);
        db.execSQL(CREATE_STUDENTS_TABLE);
        db.execSQL(CREATE_LOGIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCourse(String courseCode,String courseName,String fileName,int columnIndex){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_CODE,courseCode);
        values.put(COURSE_NAME,courseName);
        values.put(COURSE_FILE,fileName);
        values.put(COLUMN_VALUE,columnIndex);
        db.insert(TABLE_COURSE,null,values);
    }


    public ArrayList<CourseGetterSetter> getCourseList(){

        ArrayList<CourseGetterSetter> list = new ArrayList<CourseGetterSetter>();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                CourseGetterSetter contact = new CourseGetterSetter(cursor.getString(1),cursor.getString(3),cursor.getColumnIndex(COLUMN_VALUE));

                Log.d("DB",cursor.getString(2)+ " "+ cursor.getString(1)+" "+cursor.getColumnIndex(COLUMN_VALUE));
                list.add(contact);

            } while (cursor.moveToNext());
        }

        return  list;
    }


    public void addStudent(String name, String reg,String courseCode) {
        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(STUDENTS_NAME, name);
            values.put(STUDENTS_REG, reg);
            values.put(STUDENTS_COURSE,courseCode);
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }


    public void addUser(String name, String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(USERNAME, username);
        values.put(PASSWORD,password);
        // Log.d("TAG","NAME"+name+"USERNAME"+username+"PASSWORD"+password);
        db.insert(TABLE_LOGIN, null, values);
        db.close();
    }

    public boolean checkUser(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;


        Cursor cursor = db.rawQuery(selectQuery, null);

        // Cursor cursor=db.query(TABLE_LOGIN,new String[]{USERNAME,PASSWORD},USERNAME+"=?",new String[]{username},null,null,null);

        if (cursor.moveToFirst()) {
            do {
                if (username.equals(cursor.getString(cursor.getColumnIndex(USERNAME))) && password.equals(cursor.getString(cursor.getColumnIndex(PASSWORD)))) {
                    Log.e("CU","Correct data");
                    return true;

                }
            } while (cursor.moveToNext());
        }

        return false;
    }

    public String getname(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;


        Cursor cursor = db.rawQuery(selectQuery, null);

        // Cursor cursor=db.query(TABLE_LOGIN,new String[]{USERNAME,PASSWORD},USERNAME+"=?",new String[]{username},null,null,null);

        if (cursor.moveToFirst()) {
            do {
                if (username.equals(cursor.getString(cursor.getColumnIndex(USERNAME))) && password.equals(cursor.getString(cursor.getColumnIndex(PASSWORD)))) {
                    {
                        String na=cursor.getString(cursor.getColumnIndex(NAME));
                        return na;
                    }

                }
            } while (cursor.moveToNext());
        }
        return null;
    }



    public ArrayList<StudentsGetterSetter> getStudentsListForAttendance(String courseCode) {
        ArrayList<StudentsGetterSetter> list=new ArrayList<>();

       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor=db.query(TABLE_STUDENTS,new String[]{STUDENTS_REG,STUDENTS_NAME,STUDENTS_COURSE},STUDENTS_COURSE+"=?",new String[]{courseCode},null,null,null);

       if (cursor.moveToFirst()) {
           do {
               StudentsGetterSetter contact = new StudentsGetterSetter(cursor.getString(cursor.getColumnIndex(STUDENTS_REG)),cursor.getString(cursor.getColumnIndex(STUDENTS_COURSE)),cursor.getString(cursor.getColumnIndex(STUDENTS_NAME)));
               list.add(contact);

           } while (cursor.moveToNext());
       }
       return list;
    }



    public String getFileName(String courseCode)
    {
        String file = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_COURSE,new String[]{COURSE_CODE,COURSE_FILE},COURSE_CODE+"=?",new String[]{courseCode},null,null,null);
        if (cursor.moveToFirst()) {
            do {
               // StudentsGetterSetter contact = new StudentsGetterSetter(cursor.getString(cursor.getColumnIndex(COURSE_FILE)));
               // list.add(contact);
                file=cursor.getString(cursor.getColumnIndex(COURSE_FILE));

            } while (cursor.moveToNext());
        }
        return file;
    }

    public void setColumn(String courseCode,int column)
    {
        Log.d("adsaadsadsa", String.valueOf(column));

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_VALUE, column);
        db.update(TABLE_COURSE, cv, COLUMN_VALUE + "= ?", new String[] {String.valueOf((column))});
        db.close();

/*
        String sql = "UPDATE "+TABLE_COURSE +" SET " + COLUMN_VALUE+ " = '"+column+"' WHERE "+COURSE_CODE+ " = "+courseCode;
        SQLiteDatabase db=this.getWritableDatabase();

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);
        try{
            stmt.execute();
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }*/
    }
}
