package com.orange.place.analysis.domain;

public class PostStatistic {

	private long userId;

	private long storyId;

	private int interactionCount;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStoryId() {
		return storyId;
	}

	public void setStoryId(long storyId) {
		this.storyId = storyId;
	}

	public int getInteractionCount() {
		return interactionCount;
	}

	public void setInteractionCount(int interactionCount) {
		this.interactionCount = interactionCount;
	}
}
