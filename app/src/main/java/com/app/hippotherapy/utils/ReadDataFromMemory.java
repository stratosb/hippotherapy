package com.app.hippotherapy.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.app.hippotherapy.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

public class ReadDataFromMemory extends AsyncTask<Void, Void, List<User>> {
    private Context context;

    public ReadDataFromMemory(Context context) {
        this.context = context;
    }

    protected List<User> doInBackground(Void... voids) {
        List<User> users = null;

        File file = context.getFileStreamPath("users");
        if (file.exists()) {
            try {
                users = readFromMemory();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return users;
    }

    private List<User> readFromMemory() throws Exception {
        List<User> users = null;
        FileInputStream inputStream = null;
        ObjectInputStream objectStream = null;
        try {
            inputStream = context.openFileInput("users");
            objectStream = new ObjectInputStream(inputStream);
            users = (List<User>) objectStream.readObject();
        } catch (Exception ex) {
            throw new Exception(ex);
            //ex.printStackTrace();
        } finally {
            try {
                if (objectStream != null) objectStream.close();
                if (inputStream != null) inputStream.close();
            } catch (Exception ex) {
                throw new Exception(ex);
                //ex.printStackTrace();
            }
        }
        return users;
    }
}
