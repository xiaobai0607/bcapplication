package com.bootcamp.model;

import com.bootcamp.entity.MatchProject;

import java.util.List;

public class ProjectScoreModel {

	private List<String> td;
	private List<MatchProjectModel> matchProjectModels;
	private MatchProject matchProject;
	private MatchProject matchProjectNew;

	public List<String> getTd() {
		return td;
	}

	public void setTd(List<String> td) {
		this.td = td;
	}


	public MatchProject getMatchProject() {
		return matchProject;
	}

	public void setMatchProject(MatchProject matchProject) {
		this.matchProject = matchProject;
	}

	public List<MatchProjectModel> getMatchProjectModels() {
		return matchProjectModels;
	}

	public void setMatchProjectModels(List<MatchProjectModel> matchProjectModels) {
		this.matchProjectModels = matchProjectModels;
	}

	public MatchProject getMatchProjectNew() {
		return matchProjectNew;
	}

	public void setMatchProjectNew(MatchProject matchProjectNew) {
		this.matchProjectNew = matchProjectNew;
	}
}
