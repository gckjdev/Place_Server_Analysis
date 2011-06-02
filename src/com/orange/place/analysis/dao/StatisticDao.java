package com.orange.place.analysis.dao;

import java.util.Map;

import com.orange.place.analysis.domain.Similarity;
import com.orange.place.analysis.domain.UserPostStatistic;
import com.orange.place.analysis.domain.UserStatistic;

//TODO: all this statistic is used in runtime, need high performance storage
public class StatisticDao {

	public void saveUserPostStatistic(UserPostStatistic storyStatistic) {

	}

	public void saveUserSimilarity(Similarity similarity) {
		String colFamily = "place_user";
		String rowKey = similarity.getUserId();
		String colName = "similarity";
		Map<String, Double> columns = similarity.getSimilarity();
	}

	/**
	 * @param similarity
	 */
	// TODO: merge with saveUserSimilarity
	public void saveClusterSimilarity(Similarity similarity) {

	}

	public void saveUserStatistic(UserStatistic statistic) {
		// TODO Auto-generated method stub
	}
}
