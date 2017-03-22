package com.bootcamp.entity;
// Generated 2016-9-22 15:09:05 by Hibernate Tools 3.5.0.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ScoreItem generated by hbm2java
 */
@Entity
@Table(name = "score_item", catalog = "bootcamp")
public class ScoreItem implements java.io.Serializable {

	private Integer scoreItemId;
	private String scoreItemName;
	private String scoreItemIntro;
	private int scoreItemMaxScore;
	private int isEnabled;
	private Date createdTime;
	private String createdBy;
	private Date updatedTime;
	private String updatedBy;

	public ScoreItem() {
	}

	public ScoreItem(String scoreItemName, String scoreItemIntro, int scoreItemMaxScore, int isEnabled, Date createdTime, String createdBy,
			Date updatedTime, String updatedBy) {
		this.scoreItemName = scoreItemName;
		this.scoreItemIntro = scoreItemIntro;
		this.scoreItemMaxScore = scoreItemMaxScore;
		this.isEnabled = isEnabled;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "s_id", unique = true, nullable = false)
	public Integer getScoreItemId() {
		return this.scoreItemId;
	}

	public void setScoreItemId(Integer scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	@Column(name = "s_name", nullable = false, length = 100)
	public String getScoreItemName() {
		return this.scoreItemName;
	}

	public void setScoreItemName(String scoreItemName) {
		this.scoreItemName = scoreItemName;
	}

	@Column(name = "s_intro", nullable = false, length = 300)
	public String getScoreItemIntro() {
		return this.scoreItemIntro;
	}

	public void setScoreItemIntro(String scoreItemIntro) {
		this.scoreItemIntro = scoreItemIntro;
	}

	@Column(name = "s_max_score", nullable = false)
	public int getScoreItemMaxScore() {
		return this.scoreItemMaxScore;
	}

	public void setScoreItemMaxScore(int scoreItemMaxScore) {
		this.scoreItemMaxScore = scoreItemMaxScore;
	}

	@Column(name = "is_enabled", nullable = false)
	public int getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false, length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "created_by", nullable = false, length = 20)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time", nullable = false, length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "updated_by", nullable = false, length = 20)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
