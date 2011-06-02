package com.orange.place.analysis.statistic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.place.analysis.dao.PostDao;
import com.orange.place.analysis.statistic.PostStatisticReader;

public class CassandraPostStatisticReader implements PostStatisticReader {

	private PostDao postDao;

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public Map<String, Integer> getPostStatistic(String userId) {
		List<String> posts = postDao.findRelatedPostByUserId(userId);

		Map<String, Integer> statistic = new HashMap<String, Integer>();
		if (!posts.isEmpty()) {
			statistic = getUserPostStatistic(posts);
		}
		return statistic;
	}

	// TODO: consider scalable, 1 user 30 story per day for 30 days = 900 story
	// TODO: move to statistic package later
	private Map<String, Integer> getUserPostStatistic(List<String> story) {
		Map<String, Integer> statistic = new HashMap<String, Integer>();
		for (String storyId : story) {
			// add 1 for story
			if (statistic.get(storyId) == null) {
				statistic.put(storyId, 0);
			}
			statistic.put(storyId, statistic.get(storyId) + 1);
		}
		return statistic;
	}
}
