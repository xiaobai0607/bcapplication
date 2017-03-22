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
 * Judges generated by hbm2java
 * 评委实体类
 */
@Entity
@Table(name = "judges", catalog = "bootcamp")
public class Judges implements java.io.Serializable {

	private Integer judgesId;
	private String judgesOpenid;
	private int matchId;
	private String judgesName;
	private String judgesLocation;
	private int isDelete;
	private Date createdTime;
	private String createdBy;
	private Date updatedTime;
	private String updatedBy;

	public Judges() {
	}

	public Judges(String judgesOpenid, int matchId, String judgesName, String judgesLocation, Date createdTime,
			String createdBy, Date updatedTime, String updatedBy) {
		this.judgesOpenid = judgesOpenid;
		this.matchId = matchId;
		this.judgesName = judgesName;
		this.judgesLocation = judgesLocation;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "judges_id", unique = true, nullable = false)
	public Integer getJudgesId() {
		return this.judgesId;
	}

	public void setJudgesId(Integer judgesId) {
		this.judgesId = judgesId;
	}

	@Column(name = "judges_openid", nullable = false, length = 100)
	public String getJudgesOpenid() {
		return this.judgesOpenid;
	}

	public void setJudgesOpenid(String judgesOpenid) {
		this.judgesOpenid = judgesOpenid;
	}

	@Column(name = "j_match_id", nullable = false)
	public int getMatchId() {
		return this.matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	@Column(name = "judges_name", nullable = false, length = 50)
	public String getJudgesName() {
		return this.judgesName;
	}

	public void setJudgesName(String judgesName) {
		this.judgesName = judgesName;
	}

	@Column(name = "judges_location", nullable = false, length = 300)
	public String getJudgesLocation() {
		return this.judgesLocation;
	}

	public void setJudgesLocation(String judgesLocation) {
		this.judgesLocation = judgesLocation;
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