package com.kenanozdamar.android.demo.rigelhub.search_results.models;

import java.io.Serializable;

public class SearchResult implements Serializable {

    // region ivar(s)
    private String repositoryName;
    private String webUrl;
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
    // endregion

    // region Object overrides
    @Override
    public String toString() {
        return "SearchResult{" +
                "repositoryName=" + repositoryName +
                ", webUrl=" + webUrl +
                ", starCount=" + starCount + '\'' +
                '}' + super.toString();
    }
    // endregion
}
