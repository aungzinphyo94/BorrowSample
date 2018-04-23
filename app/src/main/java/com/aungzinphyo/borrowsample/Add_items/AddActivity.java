package com.aungzinphyo.borrowsample.Add_items;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.aungzinphyo.borrowsample.Db.BorrowModel;
import com.aungzinphyo.borrowsample.R;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private AddBorrowViewModel addBorrowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendar = Calendar.getInstance();
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);

        datePickerDialog = new DatePickerDialog(this,AddActivity.this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        final EditText item_name = (EditText) findViewById(R.id.itemName);
        final EditText person_name = (EditText) findViewById(R.id.personName);
        Button date_btn = (Button) findViewById(R.id.dateButton);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBorrowViewModel.addBorrow(new BorrowModel(0,
                        item_name.getText().toString(),
                        person_name.getText().toString(),
                        date));
                finish();
            }
        });

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        date = calendar.getTime();

    }
}
