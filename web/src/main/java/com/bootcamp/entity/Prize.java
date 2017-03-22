package com.bootcamp.entity;
// Generated 2017-3-14 11:04:35 by Hibernate Tools 3.5.0.Final

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
 * Prize generated by hbm2java
 */
@Entity
@Table(name = "prize", catalog = "bootcamp")
public class Prize implements java.io.Serializable {

	private Integer prizeId;
	private String prizeName;//奖项名称
	private String prizeNum;//中奖人数
	private String prizeIntro;//奖项介绍
	private String prizeNumFont;//号码字体
	private String prizeNumColor;//号码颜色
	private int prizeNumSize;//号码字体大小
	private int prizeNumUpLowSpace;//号码上下间距
	private int prizeNumLeftRightSpace;//号码左右间距
	private int prizeNumColumn;//号码排列列数
	private int prizeIsEnd;//是否已抽过奖
	private int prizeOrderBy;//排序序号
	private int luckDrawId;//抽奖活动id
	private Date createdTime;
	private String createdBy;
	private Date updatedTime;
	private String updatedBy;
	private int isDelete;

	public Prize() {
	}

	public Prize(String prizeName, String prizeNum, String prizeIntro, int luckDrawId) {
		this.prizeName = prizeName;
		this.prizeNum = prizeNum;
		this.prizeIntro = prizeIntro;
		this.luckDrawId = luckDrawId;
	}


	public Prize(String prizeName, String prizeNum, int luckDrawId, Date createdTime, String createdBy,
				 Date updatedTime, String updatedBy) {
		this.prizeName = prizeName;
		this.prizeNum = prizeNum;
		this.luckDrawId = luckDrawId;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
	}

	public Prize(String prizeName, String prizeNum, String prizeNumFont, String prizeNumColor, int prizeNumSize,
			int prizeNumUpLowSpace, int prizeNumLeftRightSpace, int prizeNumColumn, int prizeIsEnd, int prizeOrderBy,
			int luckDrawId, Date createdTime, String createdBy, Date updatedTime, String updatedBy, int isDelete) {
		this.prizeName = prizeName;
		this.prizeNum = prizeNum;
		this.prizeNumFont = prizeNumFont;
		this.prizeNumColor = prizeNumColor;
		this.prizeNumSize = prizeNumSize;
		this.prizeNumUpLowSpace = prizeNumUpLowSpace;
		this.prizeNumLeftRightSpace = prizeNumLeftRightSpace;
		this.prizeNumColumn = prizeNumColumn;
		this.prizeIsEnd = prizeIsEnd;
		this.prizeOrderBy = prizeOrderBy;
		this.luckDrawId = luckDrawId;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
		this.isDelete = isDelete;
	}

	public Prize(String prizeName, String prizeNum, String prizeIntro, String prizeNumFont, String prizeNumColor,
			int prizeNumSize, int prizeNumUpLowSpace, int prizeNumLeftRightSpace, int prizeNumColumn, int prizeIsEnd,
			int prizeOrderBy, int luckDrawId, Date createdTime, String createdBy, Date updatedTime, String updatedBy,
			int isDelete) {
		this.prizeName = prizeName;
		this.prizeNum = prizeNum;
		this.prizeIntro = prizeIntro;
		this.prizeNumFont = prizeNumFont;
		this.prizeNumColor = prizeNumColor;
		this.prizeNumSize = prizeNumSize;
		this.prizeNumUpLowSpace = prizeNumUpLowSpace;
		this.prizeNumLeftRightSpace = prizeNumLeftRightSpace;
		this.prizeNumColumn = prizeNumColumn;
		this.prizeIsEnd = prizeIsEnd;
		this.prizeOrderBy = prizeOrderBy;
		this.luckDrawId = luckDrawId;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
		this.isDelete = isDelete;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "prize_id", unique = true, nullable = false)
	public Integer getPrizeId() {
		return this.prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	@Column(name = "prize_name", nullable = false, length = 100)
	public String getPrizeName() {
		return this.prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	@Column(name = "prize_num", nullable = false, length = 10)
	public String getPrizeNum() {
		return this.prizeNum;
	}

	public void setPrizeNum(String prizeNum) {
		this.prizeNum = prizeNum;
	}

	@Column(name = "prize_intro", length = 100)
	public String getPrizeIntro() {
		return this.prizeIntro;
	}

	public void setPrizeIntro(String prizeIntro) {
		this.prizeIntro = prizeIntro;
	}

	@Column(name = "prize_num_font" , length = 200)
	public String getPrizeNumFont() {
		return this.prizeNumFont;
	}

	public void setPrizeNumFont(String prizeNumFont) {
		this.prizeNumFont = prizeNumFont;
	}

	@Column(name = "prize_num_color", nullable = false, length = 50)
	public String getPrizeNumColor() {
		return this.prizeNumColor;
	}

	public void setPrizeNumColor(String prizeNumColor) {
		this.prizeNumColor = prizeNumColor;
	}

	@Column(name = "prize_num_size", nullable = false)
	public int getPrizeNumSize() {
		return this.prizeNumSize;
	}

	public void setPrizeNumSize(int prizeNumSize) {
		this.prizeNumSize = prizeNumSize;
	}

	@Column(name = "prize_num_up_low_space", nullable = false)
	public int getPrizeNumUpLowSpace() {
		return this.prizeNumUpLowSpace;
	}

	public void setPrizeNumUpLowSpace(int prizeNumUpLowSpace) {
		this.prizeNumUpLowSpace = prizeNumUpLowSpace;
	}

	@Column(name = "prize_num_left_right_space", nullable = false)
	public int getPrizeNumLeftRightSpace() {
		return this.prizeNumLeftRightSpace;
	}

	public void setPrizeNumLeftRightSpace(int prizeNumLeftRightSpace) {
		this.prizeNumLeftRightSpace = prizeNumLeftRightSpace;
	}

	@Column(name = "prize_num_column", nullable = false)
	public int getPrizeNumColumn() {
		return this.prizeNumColumn;
	}

	public void setPrizeNumColumn(int prizeNumColumn) {
		this.prizeNumColumn = prizeNumColumn;
	}

	@Column(name = "prize_is_end", nullable = false)
	public int getPrizeIsEnd() {
		return this.prizeIsEnd;
	}

	public void setPrizeIsEnd(int prizeIsEnd) {
		this.prizeIsEnd = prizeIsEnd;
	}

	@Column(name = "prize_order_by", nullable = false)
	public int getPrizeOrderBy() {
		return this.prizeOrderBy;
	}

	public void setPrizeOrderBy(int prizeOrderBy) {
		this.prizeOrderBy = prizeOrderBy;
	}

	@Column(name = "luck_draw_id", nullable = false)
	public int getLuckDrawId() {
		return this.luckDrawId;
	}

	public void setLuckDrawId(int luckDrawId) {
		this.luckDrawId = luckDrawId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false, length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "created_by", nullable = false, length = 15)
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

	@Column(name = "updated_by", nullable = false, length = 15)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "isdelete", nullable = false)
	public int getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

}
