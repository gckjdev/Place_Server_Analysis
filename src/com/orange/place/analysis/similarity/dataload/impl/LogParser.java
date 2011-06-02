package com.orange.place.analysis.similarity.dataload.impl;

import org.apache.commons.lang.StringUtils;

public class LogParser {

	/**
	 * logRecord : date;user_id;post_id;place_id;type
	 * 
	 * @param logRecord
	 * @return
	 */
	LogParseResult parse(String logRecord) {
		LogParseResult result = new LogParseResult();

		if (!StringUtils.isEmpty(logRecord)) {
			String[] values = logRecord.split(";");
			result.setLogDate(values[0]);
			result.setUserId(values[1]);
			result.setPostId(values[2]);
			result.setPlaceId(values[3]);
			result.setType(Integer.valueOf(values[4]));
		}
		return result;
	}
}
