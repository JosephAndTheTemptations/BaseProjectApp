package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by josephcriseno on 5/7/18.
 */

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @NonNull
    public String getmWord() {
        return mWord;
    }

    public void setmWord(@NonNull String mWord) {
        this.mWord = mWord;
    }

    public void setMphonenumber(@NonNull String mphonenumber) {
        this.mphonenumber = mphonenumber;
    }

    public int getId() {
        return id;
    }

    public String getMphonenumber() {
        return mphonenumber;
    }

    @NonNull
    @ColumnInfo(name = "phonenumber")
    private String mphonenumber;

    public Word(int id, @NonNull String mWord, String mphonenumber) {
        this.id = id;
        this.mWord = mWord;
        this.mphonenumber = mphonenumber;
    }


}
