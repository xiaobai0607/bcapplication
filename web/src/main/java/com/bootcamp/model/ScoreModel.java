package com.bootcamp.model;

import com.bootcamp.entity.Judges;
import com.bootcamp.entity.Match;

import java.util.List;

public class ScoreModel {

	private Match match;
	
	private List<ProjectScoreModel> projectScoreModels;
	
	private List<Judges> judges;



	public List<Judges> getJudges() {
		return judges;
	}

	public void setJudges(List<Judges> judges) {
		this.judges = judges;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public List<ProjectScoreModel> getProjectScoreModels() {
		return projectScoreModels;
	}

	public void setProjectScoreModels(List<ProjectScoreModel> projectScoreModels) {
		this.projectScoreModels = projectScoreModels;
	}
}
