package com.app.hippotherapy.utils;

import android.content.Context;
import android.widget.Toast;

import com.app.hippotherapy.model.Session;
import com.app.hippotherapy.model.Task;
import com.app.hippotherapy.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersUtility {

    public static List<User> readUsers(Context context) {
        List<User> users = null;
        try {
            ReadDataFromMemory readDataFromMemoryTask = new ReadDataFromMemory(context);
            readDataFromMemoryTask.execute();
            users = readDataFromMemoryTask.get();
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return users;
    }

    public static void writeUsers(List<User> users, Context context) {
        try {
            WriteDataToMemory writeDataToMemoryTask = new WriteDataToMemory(context);
            writeDataToMemoryTask.execute(users);
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void writeUser(User user, Context context) {
        try {
            List<User> usersList = readUsers(context);
            for (int i = 0; i < usersList.size(); i++) {
                if (user.getName().equals(usersList.get(i).getName())) {
                    usersList.get(i).setSessions(user.getSessions());
                    usersList.get(i).setBadgesNo(user.getBadgesNo());
                    break;
                }
            }
            writeUsers(usersList, context);
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static Map<String, Integer> getCurrentSession_Task(User user) {
        int sessionIndex = 0;
        int taskIndex = 0;

        if (user.getSessions() != null) {
            for (Session session : user.getSessions()) {
                if (!session.isCompleted()) {
                    break;
                }
                sessionIndex++;
            }
            for (Task task : user.getSessions().get(sessionIndex).getTasks()) {
                if (!task.isCompleted()) {
                    break;
                }
                taskIndex++;
            }
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("sessionIndex", sessionIndex);
        map.put("taskIndex", taskIndex);
        return map;
    }

    public static int getRating(User user) {
        int sessionIndex = 0;
        int average = 0;

        if (user.getSessions() != null) {
            for (Session session : user.getSessions()) {
                if (!session.isCompleted()) {
                    break;
                }
                sessionIndex++;
            }
            for (Task task : user.getSessions().get(sessionIndex).getTasks()) {
                if (!task.isCompleted()) {
                    break;
                }
                average += task.getRating();
            }
            if (user.getSessions().get(sessionIndex).getTasks().size() > 0) {
                average = average / user.getSessions().get(sessionIndex).getTasks().size();
            }
        }

        return average;
    }

    public static void resetUser(int userId, Context context) {
        List<User> usersList = UsersUtility.readUsers(context);
        User user = usersList.get(userId);
        user.setSessions(null);
        user.setBadgesNo(0);

        // write to memory
        UsersUtility.writeUsers(usersList, context);
    }

    public static void initializeUsers(Context context) {
        List<User> usersList = new ArrayList<>();
        User user1 = new User("Έρικ");
        usersList.add(user1);
        User user2 = new User("Τζέιν");
        usersList.add(user2);
        User user3 = new User("Μπεν");
        usersList.add(user3);
        User user4 = new User("Έλλη");
        usersList.add(user4);
        User user5 = new User("Κεν");
        usersList.add(user5);
        User user6 = new User("Βίλυ");
        usersList.add(user6);
        User user7 = new User("Ρόνι");
        usersList.add(user7);
        User user8 = new User("Τζιμ");
        usersList.add(user8);
        // write to memory
        UsersUtility.writeUsers(usersList, context);
    }
}