package com.orange.place.analysis.similarity.dataload.impl;

import java.util.Arrays;

import junit.framework.Assert;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orange.common.cassandra.CassandraClient;
import com.orange.place.analysis.dao.IdGenerator;
import com.orange.place.constants.DBConstants;

public class CassandraIDMigratorTest {

	private static CassandraClient cassandraClient;
	private static CassandraIDMigrator migrator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		migrator = new CassandraIDMigrator();
		cassandraClient = new CassandraClient(DBConstants.SERVER,
				DBConstants.CLUSTERNAME, DBConstants.KEYSPACE);
		migrator.setCassandraClient(cassandraClient);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testStoreMapping() throws TasteException {
		String userId = IdGenerator.generateId();
		long userIdLong = migrator.toLongID(userId);
		migrator.storeMapping(userIdLong, userId);
		String userIdResult = migrator.toStringID(userIdLong);
		Assert.assertEquals("userId should be the same after convert.", userId,
				userIdResult);
	}

	@Test
	public void testInitialize() throws TasteException {
		String userId = IdGenerator.generateId();
		String postId = IdGenerator.generateId();

		migrator.initialize(Arrays.asList(userId, postId));

		long userIdLong = migrator.toLongID(userId);
		long postIdLong = migrator.toLongID(postId);

		String userIdResult = migrator.toStringID(userIdLong);
		String postIdResult = migrator.toStringID(postIdLong);
		Assert.assertEquals("userId should be the same after convert.", userId,
				userIdResult);
		Assert.assertEquals("postId should be the same after convert.", postId,
				postIdResult);
	}

	@Test
	public void testToStringID() throws TasteException {
		String userId = IdGenerator.generateId();
		long userIdLong = migrator.toLongID(userId);
		migrator.storeMapping(userIdLong, userId);
		String userIdResult = migrator.toStringID(userIdLong);
		Assert.assertEquals("userId should be the same after convert.", userId,
				userIdResult);
	}

	@Test
	public void testToPostID1() throws TasteException {
		String userId = "cea5a560-aa76-11e0-8458-002481b373b7";
		long userIdLong = migrator.toLongID(userId);
		System.out.println(userIdLong);
		migrator.storeMapping(userIdLong, userId);
		String userIdResult = migrator.toStringID(userIdLong);
		Assert.assertEquals("userId should be the same after convert.", userId,
				userIdResult);
	}

	@Test
	public void testToPostID2() throws TasteException {
		String userId = "ceb62020-aa76-11e0-8458-002481b373b7";
		long userIdLong = migrator.toLongID(userId);
		System.out.println(userIdLong);
		migrator.storeMapping(userIdLong, userId);
		String userIdResult = migrator.toStringID(userIdLong);
		Assert.assertEquals("userId should be the same after convert.", userId,
				userIdResult);
	}

	@Test
	public void testTo() throws TasteException {
		String userId = "ce9a0ca0-aa76-11e0-8458-002481b373b7";
		long userIdLong = migrator.toLongID(userId);
		System.out.println(userIdLong);
		migrator.storeMapping(userIdLong, userId);
		String userIdResult = migrator.toStringID(userIdLong);
		Assert.assertEquals("userId should be the same after convert.", userId,
				userIdResult);
	}
}
