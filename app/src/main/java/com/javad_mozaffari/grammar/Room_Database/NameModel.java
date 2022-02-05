package com.javad_mozaffari.grammar.Room_Database;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NameTable")
public class NameModel implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String family;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.family);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.name = source.readString();
        this.family = source.readString();
    }

    public NameModel() {
    }

    protected NameModel(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.family = in.readString();
    }

    public static final Creator<NameModel> CREATOR = new Creator<NameModel>() {
        @Override
        public NameModel createFromParcel(Parcel source) {
            return new NameModel(source);
        }

        @Override
        public NameModel[] newArray(int size) {
            return new NameModel[size];
        }
    };
}
