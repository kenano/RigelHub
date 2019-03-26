package com.kenanozdamar.android.demo.rigelhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectToGithub();
    }

    // region helpers
    private void connectToGithub() {

    }
    // endregion
}
