package com.app.hippotherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hippotherapy.adapter.HistoryAdapter;
import com.app.hippotherapy.model.Session;
import com.app.hippotherapy.model.User;
import com.app.hippotherapy.utils.UsersUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.txt_level)
    TextView txtLevel;
    @BindView(R.id.img_badge)
    ImageView imgBadge;
    @BindView(R.id.img_profile)
    ImageView imgProfile;
    @BindView(R.id.img_profile_rating)
    ImageView imgProfileRating;
    @BindView(R.id.txt_percentage)
    TextView txtPercentage;
    @BindView(R.id.img_status_bar)
    ImageView imgStatusBar;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_no_badges)
    TextView txtNoBadges;
    @BindView(R.id.img_badge_1)
    ImageView imgBadge1;
    @BindView(R.id.img_badge_2)
    ImageView imgBadge2;
    @BindView(R.id.img_badge_3)
    ImageView imgBadge3;
    @BindView(R.id.img_badge_4)
    ImageView imgBadge4;
    @BindView(R.id.img_badge_5)
    ImageView imgBadge5;
    @BindView(R.id.img_badge_6)
    ImageView imgBadge6;
    @BindView(R.id.img_badge_7)
    ImageView imgBadge7;
    @BindView(R.id.img_badge_8)
    ImageView imgBadge8;
    @BindView(R.id.img_badge_9)
    ImageView imgBadge9;
    @BindView(R.id.img_badge_10)
    ImageView imgBadge10;
    @BindView(R.id.history_recycler_view)
    RecyclerView historyRecyclerView;
    @BindView(R.id.btn_exit)
    Button btnExit;

    private static final int gridColumns = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final int userId = (int) intent.getExtras().get("userId");

            List<User> usersList = UsersUtility.readUsers(this);
            User user = usersList.get(userId);
            Map<String, Integer> map = UsersUtility.getCurrentSession_Task(user);

            int level = map.get("sessionIndex").intValue();

            txtLevel.setText("0" + level);

            int badge_resId = getResources().getIdentifier("badge_level_" + level, "drawable", "com.app.hippotherapy");
            imgBadge.setImageResource(badge_resId);

            int rating = UsersUtility.getRating(user);
            int rating_resId = getResources().getIdentifier("profile_star_" + rating, "drawable", "com.app.hippotherapy");
            imgProfileRating.setImageResource(rating_resId);
            
            int percentage = level * 10;
            txtPercentage.setText(percentage + "%");

            int percent_resId = getResources().getIdentifier("status_bar_" + percentage, "drawable", "com.app.hippotherapy");
            imgStatusBar.setImageResource(percent_resId);

            txtNoBadges.setText(level + "/10");
            for (int n=1; n<=level; n++) {
                if (n == 1) imgBadge1.setImageResource(R.drawable.my_badge_01);
                if (n == 2) imgBadge2.setImageResource(R.drawable.my_badge_02);
                if (n == 3) imgBadge3.setImageResource(R.drawable.my_badge_03);
                if (n == 4) imgBadge4.setImageResource(R.drawable.my_badge_04);
                if (n == 5) imgBadge5.setImageResource(R.drawable.my_badge_05);
                if (n == 6) imgBadge6.setImageResource(R.drawable.my_badge_06);
                if (n == 7) imgBadge7.setImageResource(R.drawable.my_badge_07);
                if (n == 8) imgBadge8.setImageResource(R.drawable.my_badge_08);
                if (n == 9) imgBadge9.setImageResource(R.drawable.my_badge_09);
                if (n == 10) imgBadge10.setImageResource(R.drawable.my_badge_10);
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

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, gridColumns);
            historyRecyclerView.setLayoutManager(gridLayoutManager);
            historyRecyclerView.setHasFixedSize(true);

            if (user.getSessions() != null && user.getSessions().size() > 0) {
                List<Session> sessions = new ArrayList<>();
                for (Session session : user.getSessions()) {
                    if (session.isCompleted()) {
                        sessions.add(session);
                    }
                }
                HistoryAdapter historyAdapter = new HistoryAdapter(sessions, user.getName(), this);
                historyRecyclerView.setAdapter(historyAdapter);

                historyAdapter.notifyDataSetChanged();
            }
        } else {
            Intent mainIntent = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
    }
}
