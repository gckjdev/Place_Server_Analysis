package com.orange.place.analysis.domain;

import java.util.Map;

public class Similarity {

	private String userId;

	private Map<String, Double> similarity;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, Double> getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Map<String, Double> similarity) {
		this.similarity = similarity;
	}

}
