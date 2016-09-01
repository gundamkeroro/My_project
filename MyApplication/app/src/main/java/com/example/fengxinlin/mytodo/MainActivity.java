package com.example.fengxinlin.mytodo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fengxinlin.mytodo.models.Todo;
import com.example.fengxinlin.mytodo.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
            }
        });

        setupUI(fakeData());
    }

    private void setupUI(@NonNull List<Todo> todos) {
        ListView listView = (ListView) findViewById(R.id.main_list_item_view);
        listView.setAdapter(new ToDoListAdapter(this, todos));
    }

    @NonNull
    private List<Todo> fakeData() {
        List<Todo> list = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            list.add(new Todo("todo " + i, DateUtils.stringToDate("2016 8 31 19:20")));
        }
        return list;
    }


}
