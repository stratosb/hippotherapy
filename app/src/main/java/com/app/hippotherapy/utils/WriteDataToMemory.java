package com.app.hippotherapy.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.app.hippotherapy.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteDataToMemory extends AsyncTask<List<User>, Void, Void> {
    private Context context;

    public WriteDataToMemory(Context context) {
        this.context = context;
    }

    protected Void doInBackground(List<User>... users) {
        File file = context.getFileStreamPath("users");
        if (!file.exists()) {
            /*
             *  Create new file.
             *  When doing this, we need to pass the file path to the file directory.
             *  Otherwise we are trying to write the file to root, which is READ-ONLY!
             */
            String filePath = context.getFilesDir().getPath().toString() + "/users";
            try {
                file = new File(filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // finally write to memory
        try {
            writeToMemory(users[0]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private void writeToMemory(List<User> favourites) throws Exception {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectStream = null;
        try {
            outputStream = context.openFileOutput("users", Context.MODE_PRIVATE);
            objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(favourites);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            try {
                if (objectStream != null) objectStream.close();
                if (outputStream != null) outputStream.close();
            } catch (Exception ex) {
                throw new Exception(ex);
                //ex.printStackTrace();
            }
        }
    }
}
