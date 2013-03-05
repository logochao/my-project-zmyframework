/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-3-1 上午10:08:10
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-3-1   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.valueobject.entity;

import java.util.Date;

import org.wendellup.core.dao.DomainObject;

public class Movie extends DomainObject{
	private Integer id; //
	private String movieName; //电影名
	private String moviePicUrl; //电影海报路径
	private String movieNames; //电影所有名字的集合
	private String movieDirector; //导演名
	private String movieScreenwriter; //编剧
	private String movieStarring; //主演
	private Date movieReleaseTime; //上映日期
	private String movieCountry; //拍摄国家
	private String movieType; //类型
	private Integer movieDuration; //片长(分钟)
	private String movieDescription; //描述
	private Double movieScore; //评分
	private String movieScoreRate; //评分后显示的星星等级
	private Integer movie250Rate; //豆瓣250排名
	private String movieResourceUrl;  //电影下载地址或下载文件地址 
	/**
	 * @return the movieResourceUrl
	 */
	public String getMovieResourceUrl() {
		return movieResourceUrl;
	}
	/**
	 * @param movieResourceUrl the movieResourceUrl to set
	 */
	public void setMovieResourceUrl(String movieResourceUrl) {
		this.movieResourceUrl = movieResourceUrl;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}
	/**
	 * @param movieName the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	/**
	 * @return the moviePicUrl
	 */
	public String getMoviePicUrl() {
		return moviePicUrl;
	}
	/**
	 * @param moviePicUrl the moviePicUrl to set
	 */
	public void setMoviePicUrl(String moviePicUrl) {
		this.moviePicUrl = moviePicUrl;
	}
	/**
	 * @return the movieNames
	 */
	public String getMovieNames() {
		return movieNames;
	}
	/**
	 * @param movieNames the movieNames to set
	 */
	public void setMovieNames(String movieNames) {
		this.movieNames = movieNames;
	}
	/**
	 * @return the movieDirector
	 */
	public String getMovieDirector() {
		return movieDirector;
	}
	/**
	 * @param movieDirector the movieDirector to set
	 */
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	/**
	 * @return the movieScreenwriter
	 */
	public String getMovieScreenwriter() {
		return movieScreenwriter;
	}
	/**
	 * @param movieScreenwriter the movieScreenwriter to set
	 */
	public void setMovieScreenwriter(String movieScreenwriter) {
		this.movieScreenwriter = movieScreenwriter;
	}
	/**
	 * @return the movieStarring
	 */
	public String getMovieStarring() {
		return movieStarring;
	}
	/**
	 * @param movieStarring the movieStarring to set
	 */
	public void setMovieStarring(String movieStarring) {
		this.movieStarring = movieStarring;
	}
	/**
	 * @return the movieReleaseTime
	 */
	public Date getMovieReleaseTime() {
		return movieReleaseTime;
	}
	/**
	 * @param movieReleaseTime the movieReleaseTime to set
	 */
	public void setMovieReleaseTime(Date movieReleaseTime) {
		this.movieReleaseTime = movieReleaseTime;
	}
	/**
	 * @return the movieCountry
	 */
	public String getMovieCountry() {
		return movieCountry;
	}
	/**
	 * @param movieCountry the movieCountry to set
	 */
	public void setMovieCountry(String movieCountry) {
		this.movieCountry = movieCountry;
	}
	/**
	 * @return the movieType
	 */
	public String getMovieType() {
		return movieType;
	}
	/**
	 * @param movieType the movieType to set
	 */
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	/**
	 * @return the movieDuration
	 */
	public Integer getMovieDuration() {
		return movieDuration;
	}
	/**
	 * @param movieDuration the movieDuration to set
	 */
	public void setMovieDuration(Integer movieDuration) {
		this.movieDuration = movieDuration;
	}
	/**
	 * @return the movieDescription
	 */
	public String getMovieDescription() {
		return movieDescription;
	}
	/**
	 * @param movieDescription the movieDescription to set
	 */
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	/**
	 * @return the movieScore
	 */
	public Double getMovieScore() {
		return movieScore;
	}
	/**
	 * @param movieScore the movieScore to set
	 */
	public void setMovieScore(Double movieScore) {
		this.movieScore = movieScore;
	}
	/**
	 * @return the movieScoreRate
	 */
	public String getMovieScoreRate() {
		return movieScoreRate;
	}
	/**
	 * @param movieScoreRate the movieScoreRate to set
	 */
	public void setMovieScoreRate(String movieScoreRate) {
		this.movieScoreRate = movieScoreRate;
	}
	/**
	 * @return the movie250Rate
	 */
	public Integer getMovie250Rate() {
		return movie250Rate;
	}
	/**
	 * @param movie250Rate the movie250Rate to set
	 */
	public void setMovie250Rate(Integer movie250Rate) {
		this.movie250Rate = movie250Rate;
	}
}
