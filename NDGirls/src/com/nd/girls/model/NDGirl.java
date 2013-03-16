package com.nd.girls.model;

public class NDGirl {
	private String ndNo ;
	private String name ;
	private boolean isGirl ;
	private String department ;
	private String dimen ;
	private String year ;
	private String comment ;
	private int stars ;
	private String picPath ;
	private String voteList ;
	
	public String getVoteList() {
		return voteList;
	}
	public void setVoteList(String voteList) {
		this.voteList = voteList;
	}
	public String getNdNo() {
		return ndNo;
	}
	public void setNdNo(String ndNo) {
		this.ndNo = ndNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isGirl() {
		return isGirl;
	}
	public void setGirl(boolean isGirl) {
		this.isGirl = isGirl;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDimen() {
		return dimen;
	}
	public void setDimen(String dimen) {
		this.dimen = dimen;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	/**
	 * 生成插入sql
	 * @return
	 */
	public boolean insert() {
		return true ;
	}
	
	public NDGirl(String no,String name,String dep) {
		this.ndNo = no ;
		this.name = name ;
		this.department = dep ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NDGirl))
			return false ;
		NDGirl other = (NDGirl) obj ;
		return this.ndNo.equals(other.getNdNo());
	}
	
	public static void delete(String no) {
		
	}
	
	public boolean delete() {
		return false ;
	}
	
	public boolean update() {
		return false ;
	}
	
}
