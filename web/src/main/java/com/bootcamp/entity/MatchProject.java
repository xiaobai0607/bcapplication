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
 * MatchProject generated by hbm2java
 */
@Entity
@Table(name = "match_project", catalog = "bootcamp")
public class MatchProject implements java.io.Serializable {

	private Integer matchProjectId;
	private int matchId;
	private String PName;
	private String PDirector;
	private Double totalScore;
	private int isDelete;
	private Date createdTime;
	private String createdBy;
	private Date updatedTime;
	private String updatedBy;

	public MatchProject() {
	}

	public MatchProject(int matchId, String PName, String PDirector, Date createdTime, String createdBy,
			Date updatedTime, String updatedBy) {
		this.matchId = matchId;
		this.PName = PName;
		this.PDirector = PDirector;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
	}

	public MatchProject(int matchId, String PName, String PDirector, Double totalScore, Date createdTime,
			String createdBy, Date updatedTime, String updatedBy) {
		this.matchId = matchId;
		this.PName = PName;
		this.PDirector = PDirector;
		this.totalScore = totalScore;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "p_id", unique = true, nullable = false)
	public Integer getMatchProjectId() {
		return this.matchProjectId;
	}

	public void setMatchProjectId(Integer matchProjectId) {
		this.matchProjectId = matchProjectId;
	}

	@Column(name = "p_match_id", nullable = false)
	public int getMatchId() {
		return this.matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	@Column(name = "p_name", nullable = false, length = 300)
	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	@Column(name = "p_director", nullable = false, length = 50)
	public String getPDirector() {
		return this.PDirector;
	}

	public void setPDirector(String PDirector) {
		this.PDirector = PDirector;
	}

	@Column(name = "p_total_score", precision = 5)
	public Double getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
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

	@Column(name = "isdelete", nullable = false, length = 1)
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}