package com.example.fengxinlin.mytodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.fengxinlin.mytodo.models.Todo;
import com.example.fengxinlin.mytodo.utils.DateUtils;
import com.example.fengxinlin.mytodo.utils.UIUtils;

import java.util.Date;

import java.util.Calendar;

/**
 * Created by fengxinlin on 9/2/16.
 */
public class TodoEditActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    public static final String KEY_TODO = "todo";
    public static final String KEY_TODO_ID = "todo_id";

    private EditText todoEdit;
    private TextView dateTv;
    private TextView timeTv;
    private CheckBox completeCb;

    private Todo todo;
    private Date remindDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        todo = getIntent().getParcelableExtra(KEY_TODO);
        remindDate = todo != null
                ? todo.remindDate
                : null;
        setupUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        setTitle(null);
    }
    private void setupUI() {
        setContentView(R.layout.activity_edit_todo);
        setupActionBar();

        todoEdit = (EditText) findViewById(R.id.toto_detail_todo_edit);
        dateTv = (TextView) findViewById(R.id.todo_detail_date);
        timeTv = (TextView) findViewById(R.id.todo_detail_time);
        completeCb = (CheckBox) findViewById(R.id.todo_detail_complete);

        if (todo != null) {
            todoEdit.setText(todo.text);
            UIUtils.setTextViewStrikeThrough(todoEdit, todo.done);
            completeCb.setChecked(todo.done);

            findViewById(R.id.todo_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete();
                }
            });
        } else {
            findViewById(R.id.todo_delete).setVisibility(View.GONE);
        }
        if (remindDate != null) {
            dateTv.setText(DateUtils.dateToStringDate(remindDate));
            timeTv.setText(DateUtils.dateToStringTime(remindDate));
        } else {
            dateTv.setText("Set date");
            timeTv.setText("Set time");
        }
        setupDatePicker();
        setupCheckbox();
        setupSaveButton();
    }

    private void setupDatePicker() {
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = getCalendarFromRemindDate();
                Dialog dialog = new DatePickerDialog(
                        TodoEditActivity.this,
                        TodoEditActivity.this,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = getCalendarFromRemindDate();
                Dialog dialog = new TimePickerDialog(
                        TodoEditActivity.this,
                        TodoEditActivity.this,
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true);
                dialog.show();
            }
        });

    }
    private void setupCheckbox() {
        completeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton ButtonView, boolean isChecked) {
                UIUtils.setTextViewStrikeThrough(todoEdit, isChecked);
                todoEdit.setTextColor(isChecked? Color.GRAY : Color.WHITE);
            }
        });

        View completeWrapper = findViewById(R.id.todo_detail_complete_wrapper);
        completeWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeCb.setChecked(!completeCb.isChecked());
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar c =getCalendarFromRemindDate();
        c.set(year, monthOfYear,dayOfMonth);

        remindDate = c.getTime();
        dateTv.setText(DateUtils.dateToStringDate(remindDate));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar c = getCalendarFromRemindDate();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        remindDate = c.getTime();
        timeTv.setText(DateUtils.dateToStringTime(remindDate));
    }

    private void setupSaveButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.todo_detail_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndExit();
            }
        });



    }

    private void saveAndExit() {
        if (todo == null) {
            todo = new Todo(todoEdit.getText().toString(), remindDate);
        } else {
            todo.text = todoEdit.getText().toString();
            todo.remindDate = remindDate;
        }

        todo.done = completeCb.isChecked();

        Intent result = new Intent();
        result.putExtra(KEY_TODO, todo);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private Calendar getCalendarFromRemindDate() {
        Calendar c = Calendar.getInstance();
        if (remindDate != null) {
            c.setTime(remindDate);
        }
        return c;
    }

    private void delete() {
        Intent result = new Intent();
        result.putExtra(KEY_TODO_ID, todo.id);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
