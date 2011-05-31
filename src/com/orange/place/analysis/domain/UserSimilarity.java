package com.orange.place.analysis.domain;

import java.util.Map;

public class UserSimilarity {

	private long userId;

	private Map<Long, Double> similarity;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Map<Long, Double> getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Map<Long, Double> similarity) {
		this.similarity = similarity;
	}

}
