package com.app.hippotherapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.hippotherapy.model.User;
import com.app.hippotherapy.utils.UsersUtility;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_avatar_erik)
    ImageView imgAvatarErik;
    @BindView(R.id.img_avatar_jane)
    ImageView imgAvatarJane;
    @BindView(R.id.img_avatar_ben)
    ImageView imgAvatarBen;
    @BindView(R.id.img_avatar_elli)
    ImageView imgAvatarElli;
    @BindView(R.id.img_avatar_ken)
    ImageView imgAvatarKen;
    @BindView(R.id.img_avatar_villy)
    ImageView imgAvatarVilly;
    @BindView(R.id.img_avatar_roni)
    ImageView imgAvatarRoni;
    @BindView(R.id.img_avatar_tzim)
    ImageView imgAvatarTzim;

    @BindView(R.id.img_badge_erik)
    ImageView imgBadgeErik;
    @BindView(R.id.img_badge_jane)
    ImageView imgBadgeJane;
    @BindView(R.id.img_badge_ben)
    ImageView imgBadgeBen;
    @BindView(R.id.img_badge_elli)
    ImageView imgBadgeElli;
    @BindView(R.id.img_badge_ken)
    ImageView imgBadgeKen;
    @BindView(R.id.img_badge_villy)
    ImageView imgBadgeVilly;
    @BindView(R.id.img_badge_roni)
    ImageView imgBadgeRoni;
    @BindView(R.id.img_badge_tzim)
    ImageView imgBadgeTzim;

    @BindView(R.id.txt_level_erik)
    TextView txtLevelErik;
    @BindView(R.id.txt_level_jane)
    TextView txtLevelJane;
    @BindView(R.id.txt_level_ben)
    TextView txtLevelBen;
    @BindView(R.id.txt_level_elli)
    TextView txtLevelElli;
    @BindView(R.id.txt_level_ken)
    TextView txtLevelKen;
    @BindView(R.id.txt_level_villy)
    TextView txtLevelVilly;
    @BindView(R.id.txt_level_roni)
    TextView txtLevelRoni;
    @BindView(R.id.txt_level_tzim)
    TextView txtLevelTzim;

    @BindView(R.id.img_bullets)
    ImageView imgBullets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
        //UsersUtility.initializeUsers(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<User> usersList = UsersUtility.readUsers(this);
        if (usersList == null || usersList.size() == 0) {
            UsersUtility.initializeUsers(this);
        } else {
            int n = -1;
            for (User user : usersList) {
                n++;
                Map<String, Integer> map = UsersUtility.getCurrentSession_Task(user);
                int level = map.get("sessionIndex").intValue();
                int taskNo = map.get("taskIndex").intValue();

                int badge_resId = getResources().getIdentifier("badge_level_" + level, "drawable", "com.app.hippotherapy");

                switch (n) {
                    case 0:
                        txtLevelErik.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeErik.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarErik.setImageResource(R.drawable.avatar_erik);
                        } else {
                            imgAvatarErik.setImageResource(R.drawable.avatar_erik_grey);
                        }
                        break;
                    case 1:
                        txtLevelJane.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeJane.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarJane.setImageResource(R.drawable.avatar_jane);
                        } else {
                            imgAvatarJane.setImageResource(R.drawable.avatar_jane_grey);
                        }
                        break;
                    case 2:
                        txtLevelBen.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeBen.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarBen.setImageResource(R.drawable.avatar_ben);
                        } else {
                            imgAvatarBen.setImageResource(R.drawable.avatar_ben_grey);
                        }
                        break;
                    case 3:
                        txtLevelElli.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeElli.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarElli.setImageResource(R.drawable.avatar_elli);
                        } else {
                            imgAvatarElli.setImageResource(R.drawable.avatar_elli_grey);
                        }
                        break;
                    case 4:
                        txtLevelKen.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeKen.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarKen.setImageResource(R.drawable.avatar_ken);
                        } else {
                            imgAvatarKen.setImageResource(R.drawable.avatar_ken_grey);
                        }
                        break;
                    case 5:
                        txtLevelVilly.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeVilly.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarVilly.setImageResource(R.drawable.avatar_villy);
                        } else {
                            imgAvatarVilly.setImageResource(R.drawable.avatar_villy_grey);
                        }
                        break;
                    case 6:
                        txtLevelRoni.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeRoni.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarRoni.setImageResource(R.drawable.avatar_roni);
                        } else {
                            imgAvatarRoni.setImageResource(R.drawable.avatar_roni_grey);
                        }
                        break;
                    case 7:
                        txtLevelTzim.setText(level > 9 ? "" + level : "0" + level);
                        imgBadgeTzim.setImageResource(badge_resId);
                        if (level > 0 || taskNo > 0) {
                            imgAvatarTzim.setImageResource(R.drawable.avatar_tzim);
                        } else {
                            imgAvatarTzim.setImageResource(R.drawable.avatar_tzim_grey);
                        }
                        break;
                }
            }
        }

        imgBullets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resetIntent = new Intent(MainActivity.this, ResetPlayersActivity.class);
                startActivity(resetIntent);
            }
        });
    }

    public void imgErikClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 0);
        startActivity(tasksIntent);
    }

    public void imgJaneClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 1);
        startActivity(tasksIntent);
    }

    public void imgBenClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 2);
        startActivity(tasksIntent);
    }

    public void imgElliClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 3);
        startActivity(tasksIntent);
    }

    public void imgKenClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 4);
        startActivity(tasksIntent);
    }

    public void imgVillyClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 5);
        startActivity(tasksIntent);
    }

    public void imgRoniClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 6);
        startActivity(tasksIntent);
    }

    public void imgTzimClicked(View view) {
        Intent tasksIntent = new Intent(MainActivity.this, TasksActivity.class);
        tasksIntent.putExtra("userId", 7);
        startActivity(tasksIntent);
    }
}