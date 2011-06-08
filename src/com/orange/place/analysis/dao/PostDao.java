package com.orange.place.analysis.dao;

import java.util.List;
import java.util.UUID;

import me.prettyprint.hector.api.beans.HColumn;

import com.orange.place.constants.DBConstants;

public class PostDao extends AbstractCassandraDao {

	public List<String> findRelatedPostByUserId(String userId) {
		checkStringParameter(userId, "userId");
		UUID startUUID = null;
		int max = DBConstants.UNLIMITED_COUNT;

		List<HColumn<UUID, String>> resultList = cassandraClient
				.getColumnKeyByRange(DBConstants.INDEX_USER_POST, userId,
						startUUID, max);

		return getColumnNames(resultList);
	}
}
