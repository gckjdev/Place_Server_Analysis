package com.orange.place.analysis.dao;

import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orange.common.cassandra.CassandraClient;
import com.orange.place.constants.DBConstants;

public class PostDaoTest {

	private static CassandraClient cassandraClient;
	private static PostDao postDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		postDao = new PostDao();
		cassandraClient = new CassandraClient(DBConstants.SERVER,
				DBConstants.CLUSTERNAME, DBConstants.KEYSPACE);
		postDao.setCassandraClient(cassandraClient);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void createPostDao() throws Exception {
	}

	@Test
	public void testFindRelatedPostByUserId() {
		UUID userId = IdGenerator.generateUUId();
		UUID postId = IdGenerator.generateUUId();

		cassandraClient.insert(DBConstants.INDEX_USER_POST, userId.toString(),
				postId, "");
		List<String> postIdList = postDao.findRelatedPostByUserId(userId
				.toString());

		Assert.assertEquals(1, postIdList.size());
	}
}
