package com.example.staticdata.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.staticdata.Adapter.Modal;
import com.example.staticdata.Adapter.MyAdapter;
import com.example.staticdata.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addbtn;
    RecyclerView recyclerView;

    ArrayList<Modal> list;
    MyAdapter adapter;

    TextInputEditText nameED, emailED, contactED;
    TextView dob;
    Button okbtn, cancelbtn;
    RadioGroup gender;
    RadioButton selectradiobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbtn = findViewById(R.id.addbtn_id);
        recyclerView = findViewById(R.id.recyclerView_id);

        list = new ArrayList<>();


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialoge_layout);
                dialog.setCancelable(false);

                okbtn = dialog.findViewById(R.id.okbtn_add_id);
                cancelbtn = dialog.findViewById(R.id.cancelbtn_add_id);
                nameED = dialog.findViewById(R.id.nameED_id);
                emailED = dialog.findViewById(R.id.emailED_id);
                contactED = dialog.findViewById(R.id.contactED_id);
                dob = dialog.findViewById(R.id.date_id);
                gender = dialog.findViewById(R.id.radiogroup_id);
                adapter = new MyAdapter(list, MainActivity.this);
                dob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.getWeekYear();

                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                dob.setText(i + "/" + i1 + "/" + i2);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Name = nameED.getText().toString();
                        String Email = emailED.getText().toString();
                        String Contact = contactED.getText().toString();
                        String DOB = dob.getText().toString();
                        selectradiobtn = gender.findViewById(gender.getCheckedRadioButtonId());

                        if (Name.isEmpty() || Email.isEmpty() || Contact.isEmpty() || DOB.isEmpty() || selectradiobtn == null) {
                            nameED.setError("");
                            emailED.setError("");
                            contactED.setError("");
                            dob.setError("");
                            Toast.makeText(MainActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, selectradiobtn.getText(), Toast.LENGTH_SHORT).show();

                            Modal modal = new Modal(Name, Email, Contact, DOB);
                            list.add(modal);
                            recyclerView.setAdapter(adapter);

                            adapter.notifyItemInserted(list.size() - 1);
                            dialog.dismiss();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}