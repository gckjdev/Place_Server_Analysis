package com.orange.place.analysis.statistic.impl;

import com.orange.place.analysis.dao.StatisticDao;
import com.orange.place.analysis.domain.UserPostStatistic;
import com.orange.place.analysis.domain.UserStatistic;
import com.orange.place.analysis.statistic.PostStatisticWriter;

/**
 * @author echnlee
 * 
 */
// TODO: it's a middle man...any other thing it can do ? validation?
public class CassandraPostStatisticWriter implements PostStatisticWriter {

	private StatisticDao statisticDao;

	public void saveUserStatistic(UserStatistic statistic) {
		statisticDao.saveUserStatistic(statistic);
	}

	public void saveUserPostStatistic(UserPostStatistic statistic) {
		statisticDao.saveUserPostStatistic(statistic);
	}
}
