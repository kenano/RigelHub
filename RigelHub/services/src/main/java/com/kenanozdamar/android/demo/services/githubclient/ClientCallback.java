package com.kenanozdamar.android.demo.services.githubclient;


import com.kenanozdamar.android.demo.services.githubclient.models.ClientResults;

public interface ClientCallback {
    void onUpdate(ClientResults results);
    void onError(Throwable exc);
}
