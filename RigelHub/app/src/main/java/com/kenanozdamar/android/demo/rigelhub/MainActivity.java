package com.kenanozdamar.android.demo.rigelhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kenanozdamar.android.demo.services.ServicesFacade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectToGithub();
    }

    // region helpers
    private void connectToGithub() {
        ServicesFacade servicesFacade = ServicesFacade.getServiceFacade();

        if (servicesFacade != null) servicesFacade.getGithubClientFacade().request();
    }
    // endregion
}
