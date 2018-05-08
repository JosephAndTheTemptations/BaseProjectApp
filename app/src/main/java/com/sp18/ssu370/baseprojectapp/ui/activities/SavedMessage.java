package com.sp18.ssu370.baseprojectapp.ui.activities;

/**
 * Created by josephcriseno on 5/7/18.
 */

public class SavedMessage {

    private int _id;
    private String _phonenumber;
    private String _message;

    public SavedMessage(){

    }
    

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_phonenumber(String _phonenumber) {
        this._phonenumber = _phonenumber;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public int get_id() {
        return _id;
    }

    public String get_phonenumber() {
        return _phonenumber;
    }

    public String get_message() {
        return _message;
    }

    public SavedMessage(String phonenumber, String message) {
        this._phonenumber = phonenumber;
        this._message = message;
    }
}
