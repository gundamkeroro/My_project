package com.example.fengxinlin.mytodo.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by fengxinlin on 8/31/16.
 */
public class Todo implements Parcelable {

    public String id;

    public String text;

    public boolean done;

    public Date remindDate;

    public Todo(String text, Date remindDate) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.remindDate = remindDate;
        this.done = false;
    }

    protected Todo(Parcel in) {
        id = in.readString();
        text = in.readString();
        done = in.readByte() != 0;
        long date = in.readLong();
        remindDate = date == 0? null : new Date(date);
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeLong(remindDate != null ? remindDate.getTime() : 0);
        dest.writeString(id);
        dest.writeByte((byte) (done ? 1 : 0));
    }
}

