package com.kenanozdamar.android.demo.rigelhub.search_results.models;

import java.io.Serializable;

public class SearchResult implements Serializable {

    // region ivar(s)
    private String repositoryName;
    private String orgName;
    private String webUrl;
    private String avatarUrl;
    private int starCount = 0;
    // endregion

    // region getters and setters

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

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

    // region Object overrides
    @Override
    public String toString() {
        return "SearchResult{" +
                "repositoryName=" + repositoryName +
                "orgName=" + orgName +
                ", webUrl=" + webUrl +
                ", avatarUrl=" + avatarUrl +
                ", starCount=" + starCount + '\'' +
                '}' + super.toString();
    }
    // endregion
}
