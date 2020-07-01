package com.app.hippotherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.hippotherapy.model.User;
import com.app.hippotherapy.utils.UsersUtility;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends AppCompatActivity {

    @BindView(R.id.img_day)
    ImageView imgDay;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.editTextTextMultiLine2)
    EditText editTextTextMultiLine;

    private int userId;
    private int dayNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        //editTextTextMultiLine.addTextChangedListener(filterTextWatcher);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            userId = (int) intent.getExtras().get("userId");
            dayNo = (int) intent.getExtras().get("dayNo");

            int day_resId = getResources().getIdentifier("comment_day_" + dayNo, "drawable", "com.app.hippotherapy");
            imgDay.setImageResource(day_resId);
        } else {
            Intent mainIntent = new Intent(CommentActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        btnSubmit.setVisibility(View.VISIBLE);
        btnSubmit.setBackgroundColor(Color.TRANSPARENT);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> usersList = UsersUtility.readUsers(CommentActivity.this);
                User user = usersList.get(userId);
                user.getSessions().get(dayNo-1).setComment(editTextTextMultiLine.getText().toString());
                Calendar c = Calendar.getInstance();
                user.getSessions().get(dayNo-1).setDateCompleted(c.getTimeInMillis());
                // persist the data
                UsersUtility.writeUser(user, CommentActivity.this);
                // go to tasks
                Intent tasksIntent = new Intent(CommentActivity.this, TasksActivity.class);
                tasksIntent.putExtra("userId", userId);
                startActivity(tasksIntent);
                finish();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tasksIntent = new Intent(CommentActivity.this, TasksActivity.class);
                tasksIntent.putExtra("userId", userId);
                startActivity(tasksIntent);
                finish();
            }
        });
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            //Toast.makeText(CommentActivity.this, "count: " + s.toString().split("\\s+").length, Toast.LENGTH_SHORT).show();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Toast.makeText(CommentActivity.this, "count: " + s.toString().split(" ").length, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //editTextTextMultiLine.removeTextChangedListener(filterTextWatcher);
    }
}