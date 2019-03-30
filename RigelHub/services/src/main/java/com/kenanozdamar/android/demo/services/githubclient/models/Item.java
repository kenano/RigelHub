package com.kenanozdamar.android.demo.services.githubclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    // region JSON Properties.
    @JsonProperty("name")
    private String name;

    @JsonProperty("html_url")
    private String stringUrl;

    @JsonProperty("stargazers_count")
    private String stargazersCount;
    // endregion


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringUrl() {
        return stringUrl;
    }

    public void setStringUrl(String stringUrl) {
        this.stringUrl = stringUrl;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(String stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
}
