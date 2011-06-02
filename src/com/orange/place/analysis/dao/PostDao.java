package com.orange.place.analysis.dao;

import java.util.ArrayList;
import java.util.List;

import com.orange.common.cassandra.CassandraClient;

public class PostDao {

	private CassandraClient cassandraClient;

	public void setCassandraClient(CassandraClient cassandraClient) {
		this.cassandraClient = cassandraClient;
	}

	// = new CassandraClient(DBConstants.SERVER, DBConstants.CLUSTERNAME,
	// DBConstants.KEYSPACE);

	public List<String> findRelatedPostByUserId(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId should not be null");
		}

		// String colFamily = "idx_user_posts";
		// Selector selector = pool.createSelector();
		// List<Column> columns = selector.getColumnsFromRow(colFamily,
		// Bytes.fromUuid(UUID.fromString(userId)), false,
		// ConsistencyLevel.ONE);
		//
		List<String> storyIds = new ArrayList<String>();
		// Iterator<Column> it = columns.iterator();
		// while (it.hasNext()) {
		// Column col = it.next();
		// String value = TimeUUIDHelper.toUUID(col.getName()).toString();
		// // String value = new String(col.getName());
		// storyIds.add(value);
		// }
		return storyIds;
	}
}
