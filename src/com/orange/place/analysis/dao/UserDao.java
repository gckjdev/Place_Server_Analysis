package com.orange.place.analysis.dao;

import java.util.ArrayList;
import java.util.Iterator;

import com.orange.common.cassandra.CassandraClient;

public class UserDao {
	private CassandraClient cassandraClient;

	public void setCassandraClient(CassandraClient cassandraClient) {
		this.cassandraClient = cassandraClient;
	}

	public Iterator<String> findAllUserId() {
		return new ArrayList<String>().iterator();
	}
}
