package com.app.hippotherapy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;
    @BindView(R.id.img_close)
    ImageView imgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final int taskNo = (int) intent.getExtras().get("taskNo");

            //Creating MediaController
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            //specify the location of media file
            int video_resId = getResources().getIdentifier("askisi" + taskNo, "raw", "com.app.hippotherapy");
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + video_resId);

            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
            // play continuously
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
        } else {
            Intent mainIntent = new Intent(VideoActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                finish();
            }
        });
    }
}