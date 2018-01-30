package com.example.fengxinlin.mytodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.fengxinlin.mytodo.models.Todo;
import com.example.fengxinlin.mytodo.utils.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengxinlin on 1/28/18.
 */

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_TODO_EDIT = 100;

    private static final String TODOS = "todos";

    private ToDoListAdapter adapter;
    private List<Todo> todos;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodoEditActivity.class);
                startActivityForResult(intent, REQ_CODE_TODO_EDIT);
            }
        });

        loadData();

       adapter = new ToDoListAdapter(this, todos );
       ((ListView) findViewById(R.id.main_list_item_view)).setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_TODO_EDIT && resultCode == Activity.RESULT_OK) {
            String todoId = data.getStringExtra(TodoEditActivity.KEY_TODO_ID);
            if (todoId != null) {
                deleteTodo(todoId);
            } else {
                Todo todo = data.getParcelableExtra(TodoEditActivity.KEY_TODO);
                updateTodo(todo);
            }
        }
    }

    private void updateTodo(Todo todo) {
        boolean found = false;
        for (int i = 0; i < todos.size(); ++i) {
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todo.id)) {
                found = true;
                todos.set(i, todo);
                break;
            }
        }

        if (!found) {
            todos.add(todo);
        }

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
    }

    public void updateTodo(int index, boolean done) {
        todos.get(index).done = done;

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
    }

    private void deleteTodo(@NonNull String todoId) {
        for (int i = 0; i < todos.size(); ++i) {
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todoId)) {
                todos.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
    }

    private void loadData() {
        todos = ModelUtils.read(this, TODOS, new TypeToken<List<Todo>>(){});
        if (todos == null) {
            todos = new ArrayList<>();
        }
    }
}
