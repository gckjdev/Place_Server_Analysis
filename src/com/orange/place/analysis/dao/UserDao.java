package com.orange.place.analysis.dao;

import java.util.Iterator;
import java.util.List;

import me.prettyprint.hector.api.beans.Rows;

import com.orange.place.constants.DBConstants;

public class UserDao extends AbstractCassandraDao {

	public Iterator<String> findAllUserId() {

		int max = DBConstants.UNLIMITED_COUNT;
		Rows<String, String, String> rows = cassandraClient.getMultiRow(
				DBConstants.USER, max);

		List<String> userList = getKeys(rows);
		return userList.iterator();
	}
}
