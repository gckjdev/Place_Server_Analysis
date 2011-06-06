package com.orange.place.analysis.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.orange.place.analysis.constants.DBConstants;
import com.orange.place.analysis.domain.Similarity;
import com.orange.place.analysis.domain.UserPostStatistic;
import com.orange.place.analysis.domain.UserStatistic;

public class StatisticDao extends AbstractCassandraDao {

	public void saveUserPostStatistic(UserPostStatistic storyStatistic) {
		String colFamily = DBConstants.USER_POST_STATISTIC;
		String rowKey = storyStatistic.getUserId();
		String columnName = storyStatistic.getPostId();
		String columnValue = String.valueOf(storyStatistic
				.getInteractionCount());
		cassandraClient.insert(colFamily, rowKey, columnName, columnValue);
	}

	public void saveUserStatistic(UserStatistic statistic) {
		String colFamily = DBConstants.USER_POST_STATISTIC;
		String rowKey = statistic.getUserId();
		String columnName = DBConstants.F_USER_POST_TOTOAL;
		String columnValue = String.valueOf(statistic.getInteractionCount());
		cassandraClient.insert(colFamily, rowKey, columnName, columnValue);
	}

	public void saveUserSimilarity(Similarity similarity) {
		String colFamily = DBConstants.USER_SIMILARITY;
		String rowKey = similarity.getUserId();
		Map<String, Double> similarityByUser = similarity.getSimilarity();

		if (StringUtils.isEmpty(rowKey)) {
			throw new IllegalArgumentException("rowKey should not be empty");
		}
		if (similarityByUser == null) {
			throw new IllegalArgumentException(
					"similarityByUser should not be null");
		}

		HashMap<String, String> columns = new HashMap<String, String>();
		Iterator<String> it = similarityByUser.keySet().iterator();
		while (it.hasNext()) {
			String userId = it.next();
			String similarityValue = getSimilarityValue(similarityByUser,
					userId);
			columns.put(userId, similarityValue);
		}
		cassandraClient.insert(colFamily, rowKey, columns);

	}

	private String getSimilarityValue(Map<String, Double> similarityByUser,
			String userId) {
		Double simiValue = similarityByUser.get(userId);
		if (simiValue == null) {
			simiValue = 0d;
		}

		String similarityValue = String.valueOf(simiValue);
		return similarityValue;
	}

	/**
	 * @param similarity
	 */
	// TODO: merge with saveUserSimilarity
	public void saveClusterSimilarity(Similarity similarity) {

	}
}
