package com.kenanozdamar.android.demo.services.githubclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    // region ivar(s)
    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("login")
    private String orgName;
    // endregion

    // region getters/setters
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    // endregion
}
