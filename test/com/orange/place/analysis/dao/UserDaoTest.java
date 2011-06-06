package com.orange.place.analysis.dao;

import java.util.HashMap;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orange.common.cassandra.CassandraClient;
import com.orange.place.constants.DBConstants;

public class UserDaoTest {

	private static CassandraClient cassandraClient;
	private static UserDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new UserDao();
		cassandraClient = new CassandraClient(DBConstants.SERVER,
				DBConstants.CLUSTERNAME, DBConstants.KEYSPACE);
		dao.setCassandraClient(cassandraClient);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testFindAllUserId() {
		String userId = IdGenerator.generateId();
		// save user
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(DBConstants.F_USERID, userId);
		cassandraClient.insert(DBConstants.USER, userId, map);

		Iterator<String> it = dao.findAllUserId();
		Assert.assertTrue("there's at least one user.here", it.hasNext());

		boolean contain = false;
		while (it.hasNext()) {
			String userIdDB = it.next();
			if (userId.equals(userIdDB)) {
				contain = true;
				break;
			}
		}

		Assert.assertTrue("user id should be included in database: " + userId,
				contain);
	}

}
