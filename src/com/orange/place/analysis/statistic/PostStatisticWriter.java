package com.orange.place.analysis.statistic;

import com.orange.place.analysis.domain.PostStatistic;

public interface PostStatisticWriter {

	void saveStatistic(PostStatistic statistic);
}
