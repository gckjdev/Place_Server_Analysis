package com.orange.place.analysis.statistic;

import com.orange.place.analysis.domain.UserPostStatistic;
import com.orange.place.analysis.domain.UserStatistic;

public interface PostStatisticWriter {

	void saveUserStatistic(UserStatistic statistic);

	void saveUserPostStatistic(UserPostStatistic statistic);
}
