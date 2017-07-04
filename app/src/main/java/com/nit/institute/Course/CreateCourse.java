package com.nit.institute.Course;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nit.institute.R;
import com.nit.institute.SQLite.DatabaseHandler;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.Iterator;

public class CreateCourse extends Activity  {

    static DatabaseHandler db;
    TextView t1;
    Button bImport;
    private final int PICK_FILE = 1;
    static String path;
    static String cc;
    Button bSubmit;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Spinner sp1,sp2,sp3,sp4;

    Context context;
    private Object List;

    int column=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        bImport = (Button) findViewById(R.id.bImport);
        bSubmit = (Button) findViewById(R.id.bSubmit);
        t1=(TextView)findViewById(R.id.tFile);
        context = getApplicationContext();

        sp1=(Spinner)findViewById(R.id.sp1);
        sp2=(Spinner)findViewById(R.id.sp2);
        sp3=(Spinner)findViewById(R.id.sp3);
        sp4=(Spinner)findViewById(R.id.sp4);


        Spinner dropdown = (Spinner)findViewById(R.id.sp1);
        String[] items = new String[]{"Spring", "Autumn"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        sp1.setAdapter(adapter);



        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                // Depend on first spinner value set adapter to 2nd spinner
                if(position == 0){
                    String[] items = new String[]{"CSE", "ECE"};
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                    sp2.setAdapter(adapter2);
                    sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        public void onItemSelected(AdapterView<?> parentView,
                                                   View selectedItemView, int position, long id) {
                            // Object item = parentView.getItemAtPosition(position);

                            // Depend on first spinner value set adapter to 2nd spinner
                            if(position == 0){
                                String[] items = new String[]{"1", "3","5","7"};
                                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                sp3.setAdapter(adapter4);
                                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    public void onItemSelected(AdapterView<?> parentView,
                                                               View selectedItemView, int position, long id) {
                                        // Object item = parentView.getItemAtPosition(position);

                                        // Depend on first spinner value set adapter to 2nd spinner
                                        if(position == 0){
                                            String[] items = new String[]{"CS101","CS102","CS103","CS104","CS105"};
                                            ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter8);

                                        }else if(position==1){
                                            String[] items = new String[]{"CS301","CS302","CS303","CS304","CS305"};
                                            ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter9);
                                        }
                                        else if(position==2){
                                            String[] items = new String[]{"CS501","CS502","CS503","CS504","CS505"};
                                            ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter10);
                                        }else{
                                            String[] items = new String[]{"CS701","CS702","CS703","CS704","CS705"};
                                            ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter11);
                                        }

                                    }

                                    public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                                    }

                                });

                            }else{
                                String[] items = new String[]{"1","3","5","7"};
                                ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                sp3.setAdapter(adapter5);
                                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    public void onItemSelected(AdapterView<?> parentView,
                                                               View selectedItemView, int position, long id) {
                                        // Object item = parentView.getItemAtPosition(position);

                                        // Depend on first spinner value set adapter to 2nd spinner
                                        if(position == 0){
                                            String[] items = new String[]{"EC101","EC102","EC103","EC104","EC105"};
                                            ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter12);

                                        }else if(position==1){
                                            String[] items = new String[]{"EC301","EC302","EC303","EC304","EC305"};
                                            ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter13);
                                        }
                                        else if(position==2){
                                            String[] items = new String[]{"EC501","EC502","EC503","EC504","EC505"};
                                            ArrayAdapter<String> adapter14 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter14);
                                        }else{
                                            String[] items = new String[]{"EC701","EC702","EC703","EC704","EC705"};
                                            ArrayAdapter<String> adapter15 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter15);
                                        }

                                    }

                                    public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                                    }

                                });
                            }

                        }

                        public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                        }

                    });
                }else{
                    String[] items = new String[]{"CSE", "ECE"};
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                    sp2.setAdapter(adapter3);
                    sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        public void onItemSelected(AdapterView<?> parentView,
                                                   View selectedItemView, int position, long id) {
                            // Object item = parentView.getItemAtPosition(position);

                            // Depend on first spinner value set adapter to 2nd spinner
                            if(position == 0){
                                String[] items = new String[]{"2", "4","6","8"};
                                ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                sp3.setAdapter(adapter6);
                                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    public void onItemSelected(AdapterView<?> parentView,
                                                               View selectedItemView, int position, long id) {
                                        // Object item = parentView.getItemAtPosition(position);

                                        // Depend on first spinner value set adapter to 2nd spinner
                                        if(position == 0){
                                            String[] items = new String[]{"CS201","CS202","CS203","CS204","CS205"};
                                            ArrayAdapter<String> adapter16 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter16);

                                        }else if(position==1){
                                            String[] items = new String[]{"CS401","CS402","CS403","CS404","CS405"};
                                            ArrayAdapter<String> adapter17 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter17);
                                        }
                                        else if(position==2){
                                            String[] items = new String[]{"CS601","CS602","CS603","CS604","CS605"};
                                            ArrayAdapter<String> adapter18 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter18);
                                        }else{
                                            String[] items = new String[]{"CS801","CS802","CS803","CS804","CS805"};
                                            ArrayAdapter<String> adapter19 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter19);
                                        }

                                    }

                                    public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                                    }

                                });
                            }else{
                                String[] items = new String[]{"2","4","6","8"};
                                ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                sp3.setAdapter(adapter7);
                                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    public void onItemSelected(AdapterView<?> parentView,
                                                               View selectedItemView, int position, long id) {
                                        // Object item = parentView.getItemAtPosition(position);

                                        // Depend on first spinner value set adapter to 2nd spinner
                                        if(position == 0){
                                            String[] items = new String[]{"EC201","EC202","EC203","EC204","EC205"};
                                            ArrayAdapter<String> adapter20 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter20);

                                        }else if(position==1){
                                            String[] items = new String[]{"EC401","EC402","EC403","EC404","EC405"};
                                            ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter21);
                                        }
                                        else if(position==2){
                                            String[] items = new String[]{"EC601","EC602","EC603","EC604","EC605"};
                                            ArrayAdapter<String> adapter22 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter22);
                                        }else{
                                            String[] items = new String[]{"EC801","EC802","EC803","EC804","EC805"};
                                            ArrayAdapter<String> adapter23 = new ArrayAdapter<String>(CreateCourse.this, android.R.layout.simple_spinner_dropdown_item, items);
                                            sp4.setAdapter(adapter23);
                                        }

                                    }

                                    public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                                    }

                                });
                            }

                        }

                        public void onNothingSelected(AdapterView<?> arg0) {// do nothing
                        }

                    });
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });









        db = new DatabaseHandler(this);
        bImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cc = sp4.getSelectedItem().toString();

                Intent intent = new Intent();
                intent.setType("application/vnd.ms-excel/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, ""), PICK_FILE);

            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addCourse(sp4.getSelectedItem().toString(),sp1.getSelectedItem().toString(), path,column);
                Intent i = new Intent(getApplicationContext(), CourseList.class);
                i.putExtra("FileName ", path);

                startActivity(i);

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == PICK_FILE) && (resultCode == -1)) {
            Uri currFileURI = data.getData();
            path = currFileURI.getPath();
            readExcelFile(this, path);
            t1.setText(path);
            Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        }
    }

    //Reading Excel File
    private static void readExcelFile(Context context, String filename) {

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e("TAG", "Storage not available or read only");
            return;
        }

        try {


            JSONObject jsonObject = new JSONObject();
            FileInputStream myInput = new FileInputStream(filename);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            HSSFRow mRow = (HSSFRow) rowIter.next();
            HSSFRow mRw = (HSSFRow) rowIter.next();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                int i = 1;
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    if (!myCell.toString().isEmpty() && i % 3 == 1) {
                        jsonObject.put("SERIAL_NUMBER", myCell.toString());
                        i++;
                    } else if (!myCell.toString().isEmpty() && i % 3 == 2) {
                        jsonObject.put("STUDENT_REG", myCell.toString());
                        i++;
                    } else if (!myCell.toString().isEmpty() && i % 3 == 0) {
                        jsonObject.put("STUDENT_NAME", myCell.toString());
                        i++;
                    }

                }
                //Log.d("TAG", "Cell Value: " +  jsonObject.get("STUDENT_NAME").toString() + jsonObject.get("STUDENT_REG").toString());
                db.addStudent(jsonObject.get("STUDENT_NAME").toString(), jsonObject.get("STUDENT_REG").toString(), cc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


}