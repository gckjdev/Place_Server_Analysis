package com.orange.place.analysis.statistic;

import java.util.Map;

public interface PostStatisticReader {

	Map<String, Integer> getPostStatistic(String userId);
}
