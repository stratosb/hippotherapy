package com.app.hippotherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadgeActivity extends AppCompatActivity {

    @BindView(R.id.img_badge)
    ImageView imgBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final int badgeNo = (int) intent.getExtras().get("badgeNo");
            final int userId = (int) intent.getExtras().get("userId");

            int badge_resId = getResources().getIdentifier("badge_level_" + badgeNo, "drawable", "com.app.hippotherapy");
            imgBadge.setImageResource(badge_resId);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent commentIntent = new Intent(BadgeActivity.this, CommentActivity.class);
                    commentIntent.putExtra("userId", userId);
                    commentIntent.putExtra("dayNo", badgeNo);
                    startActivity(commentIntent);
                    finish();
                }
            }, 2000);
        } else {
            Intent mainIntent = new Intent(BadgeActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}