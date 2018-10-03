package com.example.latha.smoothiesapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class Progress extends AppCompatActivity {
    ProgressBar progress;
    int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_layout);
        progress = findViewById(R.id.progressBar);
        progress.setProgress(0);
        final Thread t = new Thread() {
            @Override
            public void run() {
                for (i = 0; i < 100; i++) {
                    if(i<99)
                    progress.setProgress(i);
                    else {
                        Intent intent = new Intent(getApplicationContext(), MainPage.class);
                        intent.putExtra("frag","Orders");
                        startActivity(intent);
                    }
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }

            }};
        t.start();

    }
}



