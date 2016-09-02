package com.example.fengxinlin.mytodo;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by fengxinlin on 9/1/16.
 */
public abstract  class ViewHolderAdapter extends BaseAdapter{
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = onCreateViewHolder(parent, position);
            convertView = vh.view;
            vh.view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(vh, position);
        return convertView;
    }

    protected abstract ViewHolder onCreateViewHolder(ViewGroup parent, int position);

    protected abstract void onBindViewHolder(ViewHolder viewHolder, int position);

    public static abstract class ViewHolder {
        protected View view;
        public ViewHolder(@NonNull View view) {
            this.view = view;
        }
    }
}
