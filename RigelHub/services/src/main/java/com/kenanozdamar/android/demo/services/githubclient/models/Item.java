package com.kenanozdamar.android.demo.services.githubclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    // region JSON Properties.
    @JsonProperty("name")
    private String name;

    @JsonProperty("html_url")
    private String url;

    @JsonProperty("stargazers_count")
    private String stargazersCount;
    // endregion


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String stringUrl) {
        this.url = stringUrl;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(String stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
}
