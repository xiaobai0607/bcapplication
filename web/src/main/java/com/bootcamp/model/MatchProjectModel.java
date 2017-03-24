package com.bootcamp.model;

import com.bootcamp.entity.MatchProject;

/**
 * Created by yaobin on 2017/3/24.
 */
public class MatchProjectModel  {
    private MatchProject matchProject;
    private Double totalScorePre;

    public Double getTotalScorePre() {
        return totalScorePre;
    }

    public void setTotalScorePre(Double totalScorePre) {
        this.totalScorePre = totalScorePre;
    }

    public MatchProject getMatchProject() {
        return matchProject;
    }

    public void setMatchProject(MatchProject matchProject) {
        this.matchProject = matchProject;
    }
}
