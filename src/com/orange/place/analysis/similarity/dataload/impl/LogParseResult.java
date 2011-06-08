package com.orange.place.analysis.similarity.dataload.impl;

import com.orange.place.analysis.domain.PostType;

public class LogParseResult {

	private String logDate;

	private String userId;

	private String postId;

	private String placeId;

	private int type;

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public PostType getPostType() {
		return PostType.getType(this.type);
	}
}
