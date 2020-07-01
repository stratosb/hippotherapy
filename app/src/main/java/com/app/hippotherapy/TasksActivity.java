package com.app.hippotherapy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.hippotherapy.model.Session;
import com.app.hippotherapy.model.Task;
import com.app.hippotherapy.model.User;
import com.app.hippotherapy.utils.UsersUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksActivity extends AppCompatActivity {

    @BindView(R.id.img_background)
    ImageView imgBackground;
    @BindView(R.id.img_day)
    ImageView imgDay;
    @BindView(R.id.txt_level)
    TextView txtLevel;
    @BindView(R.id.img_badge)
    ImageView imgBadge;
    @BindView(R.id.img_profile)
    ImageView imgProfile;
    @BindView(R.id.txt_stars_title)
    TextView txtStarsTitle;
    @BindView(R.id.img_profile_rating)
    ImageView imgProfileRating;
    @BindView(R.id.txt_percentage)
    TextView txtPercentage;
    @BindView(R.id.img_status_bar)
    ImageView imgStatusBar;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.img_task_rating)
    ImageView imgTaskRating;
    @BindView(R.id.img_askiseis)
    ImageView imgAskiseis;

    @BindView(R.id.btn_show_video)
    Button btnShowVideo;
    @BindView(R.id.btn_star_1)
    Button btnStar1;
    @BindView(R.id.btn_stars_2)
    Button btnStars2;
    @BindView(R.id.btn_stars_3)
    Button btnStars3;
    @BindView(R.id.btn_exit)
    Button btnExit;

    private User user;
    private int userId;
    private int taskNo;
    private boolean disabledButtons = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            userId = (int) intent.getExtras().get("userId");

            List<User> usersList = UsersUtility.readUsers(this);
            user = usersList.get(userId);
            Map<String, Integer> map = UsersUtility.getCurrentSession_Task(user);

            int level = map.get("sessionIndex").intValue();
            int lvl = level + 1;
            taskNo = map.get("taskIndex").intValue() + 1;

            txtLevel.setText(level > 9 ? "" + level : "0" + level);

            // disable all hidden buttons when all sessions have been completed
            if (level == 10) disabledButtons = true;

            if (level < 10) {
                int bg_resId = getResources().getIdentifier("bg_day_" + (taskNo > 9 ? taskNo : "0" + taskNo), "drawable", "com.app.hippotherapy");
                imgBackground.setImageResource(bg_resId);
            } else {
                imgBackground.setImageResource(R.drawable.lessons_completed_bg);
            }

            if (level < 10) {
                int day_resId = getResources().getIdentifier("day_" + (lvl > 9 ? lvl : "0" + lvl), "drawable", "com.app.hippotherapy");
                imgDay.setImageResource(day_resId);
            } else {
                imgDay.setImageResource(R.drawable.day_completed);
            }

            int badge_resId = getResources().getIdentifier("badge_level_" + level, "drawable", "com.app.hippotherapy");
            imgBadge.setImageResource(badge_resId);

            int rating = UsersUtility.getRating(user);
            int rating_resId = getResources().getIdentifier("profile_star_" + rating, "drawable", "com.app.hippotherapy");
            imgProfileRating.setImageResource(rating_resId);

            int percentage = level * 10;
            txtPercentage.setText(percentage + "%");

            int percent_resId = getResources().getIdentifier("status_bar_" + percentage, "drawable", "com.app.hippotherapy");
            imgStatusBar.setImageResource(percent_resId);

            if (level < 10) {
                imgTaskRating.setImageResource(R.drawable.rate_0);
            }

            if (level < 10) {
                if (user.getName().equals("Τζέιν") || user.getName().equals("Έλλη") || user.getName().equals("Βίλυ")) {
                    txtStarsTitle.setText("Τα αστέρια της " + usersList.get(userId).getName());
                } else {
                    txtStarsTitle.setText("Τα αστέρια του " + usersList.get(userId).getName());
                }
            }

            if (level < 10) {
                int askiseis_resId = getResources().getIdentifier("askiseis_" + taskNo, "drawable", "com.app.hippotherapy");
                imgAskiseis.setImageResource(askiseis_resId);
            }

            switch (userId) {
                case 0:
                    imgProfile.setImageResource(R.drawable.eric_profile);
                    break;
                case 1:
                    imgProfile.setImageResource(R.drawable.jane_profile);
                    break;
                case 2:
                    imgProfile.setImageResource(R.drawable.ben_profile);
                    break;
                case 3:
                    imgProfile.setImageResource(R.drawable.elli_profile);
                    break;
                case 4:
                    imgProfile.setImageResource(R.drawable.ken_profile);
                    break;
                case 5:
                    imgProfile.setImageResource(R.drawable.villy_profile);
                    break;
                case 6:
                    imgProfile.setImageResource(R.drawable.roni_profile);
                    break;
                case 7:
                    imgProfile.setImageResource(R.drawable.tzim_profile);
                    break;
            }
        } else {
            Intent mainIntent = new Intent(TasksActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        btnStar1.setVisibility(View.VISIBLE);
        btnStar1.setBackgroundColor(Color.TRANSPARENT);
        btnStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!disabledButtons) {
                    imgTaskRating.setImageResource(R.drawable.rate_1);
                    completeTask(user, 1);
                }
            }
        });

        btnStars2.setVisibility(View.VISIBLE);
        btnStars2.setBackgroundColor(Color.TRANSPARENT);
        btnStars2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!disabledButtons) {
                    imgTaskRating.setImageResource(R.drawable.rate_2);
                    completeTask(user, 2);
                }
            }
        });

        btnStars3.setVisibility(View.VISIBLE);
        btnStars3.setBackgroundColor(Color.TRANSPARENT);
        btnStars3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!disabledButtons) {
                    imgTaskRating.setImageResource(R.drawable.rate_3);
                    completeTask(user, 3);
                }
            }
        });

        btnShowVideo.setVisibility(View.VISIBLE);
        btnShowVideo.setBackgroundColor(Color.TRANSPARENT);
        btnShowVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!disabledButtons) {
                    Intent videoIntent = new Intent(TasksActivity.this, VideoActivity.class);
                    videoIntent.putExtra("taskNo", taskNo);
                    startActivity(videoIntent);
                }
            }
        });

        btnExit.setVisibility(View.VISIBLE);
        btnExit.setBackgroundColor(Color.TRANSPARENT);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        imgHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyIntent = new Intent(TasksActivity.this, HistoryActivity.class);
                historyIntent.putExtra("userId", userId);
                startActivity(historyIntent);
            }
        });
    }

    private void completeTask(User user, int rating) {
        Map<String, Integer> map = UsersUtility.getCurrentSession_Task(user);
        int sessionIndex = map.get("sessionIndex").intValue();

        if (user.getSessions() == null) {
            List<Session> sessionsList = new ArrayList<>();
            user.setSessions(sessionsList);

            Session session = new Session();
            sessionsList.add(session);

            List<Task> tasksList = new ArrayList<>();
            session.setTasks(tasksList);
        }
        // set values
        if (user.getSessions().get(sessionIndex).getTasks().size() < 9) {
            Task task = new Task();
            task.setRating(rating);
            task.setCompleted(true);
            user.getSessions().get(sessionIndex).getTasks().add(task);
            // persist the data
            UsersUtility.writeUser(user, TasksActivity.this);
            // reload activity
            finish();
            startActivity(getIntent());
        } else {
            Task task = new Task();
            task.setRating(rating);
            task.setCompleted(true);
            user.getSessions().get(sessionIndex).getTasks().add(task);

            user.getSessions().get(sessionIndex).setRating(UsersUtility.getRating(user));
            user.getSessions().get(sessionIndex).setCompleted(true);
            user.setBadgesNo(sessionIndex + 1);

            Session session = new Session();
            user.getSessions().add(session);
            List<Task> tasksList = new ArrayList<>();
            session.setTasks(tasksList);

            // persist the data
            UsersUtility.writeUser(user, TasksActivity.this);

            Intent badgeIntent = new Intent(TasksActivity.this, BadgeActivity.class);
            badgeIntent.putExtra("badgeNo", sessionIndex + 1);
            badgeIntent.putExtra("userId", userId);
            startActivity(badgeIntent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}