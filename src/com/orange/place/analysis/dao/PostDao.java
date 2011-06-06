package com.orange.place.analysis.dao;

import java.util.List;
import java.util.UUID;

import me.prettyprint.hector.api.beans.HColumn;

import com.orange.place.analysis.constants.DBConstants;

public class PostDao extends AbstractCassandraDao {

	// = new CassandraClient(DBConstants.SERVER, DBConstants.CLUSTERNAME,
	// DBConstants.KEYSPACE);

	public List<String> getRelatedPostByUserId(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId should not be null");
		}

		// TODO: no limitation for related post
		UUID startUUID = null;
		int max = DBConstants.UNLIMITED_COUNT;

		List<HColumn<UUID, String>> resultList = cassandraClient
				.getColumnKeyByRange(DBConstants.INDEX_USER_POST, userId,
						startUUID, max);

		return getColumnNames(resultList);
	}
}
