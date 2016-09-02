package com.example.fengxinlin.mytodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fengxinlin.mytodo.models.Todo;

import java.util.List;

/**
 * Created by fengxinlin on 8/31/16.
 */
public class ToDoListAdapter extends BaseAdapter{
    private Context context;
    private List<Todo> data;

    public ToDoListAdapter(@NonNull Context context, List<Todo> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);
            vh = new ViewHolder();
            vh.todoText = (TextView) convertView.findViewById(R.id.main_list_item_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Todo todo = data.get(position);

        vh.todoText.setText(todo.text);
        return convertView;

    }

    private static class ViewHolder {
        TextView todoText;
    }
}
