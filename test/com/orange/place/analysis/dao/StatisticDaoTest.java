package com.orange.place.analysis.dao;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.orange.common.cassandra.CassandraClient;
import com.orange.place.analysis.domain.Similarity;
import com.orange.place.analysis.domain.UserPostStatistic;
import com.orange.place.analysis.domain.UserStatistic;
import com.orange.place.constants.DBConstants;

public class StatisticDaoTest {

	private static CassandraClient cassandraClient;
	private static StatisticDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new StatisticDao();
		cassandraClient = new CassandraClient(DBConstants.SERVER,
				DBConstants.CLUSTERNAME, DBConstants.KEYSPACE);
		dao.setCassandraClient(cassandraClient);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSaveUserPostStatistic() {
		String userId = IdGenerator.generateId();
		String postId = IdGenerator.generateId();
		int interactionCount = 3;

		UserPostStatistic stat = new UserPostStatistic(userId, postId,
				interactionCount);

		dao.saveUserPostStatistic(stat);

		UserPostStatistic result = dao.findUserPostStatistic(userId, postId);
		Assert.assertEquals("interactionCount should be save and read",
				interactionCount, result.getInteractionCount());
	}

	@Test
	public void testSaveUserStatistic() {
		String userId = IdGenerator.generateId();
		int interactionCount = 10;

		UserStatistic stat = new UserStatistic(userId, interactionCount);
		dao.saveUserStatistic(stat);

		UserStatistic result = dao.findUserStatistic(userId);
		Assert.assertEquals("interactionCount should be save and read",
				interactionCount, result.getInteractionCount());
	}

	@Test
	public void testSaveStatistic_UserStatisticAndUserPostStatistic() {
		String userId = IdGenerator.generateId();
		String postId = IdGenerator.generateId();
		int interactionCount = 3;

		String postId2 = IdGenerator.generateId();
		int interactionCount2 = 2;

		int total = 5;

		// save post1 statistic
		UserPostStatistic stat1 = new UserPostStatistic(userId, postId,
				interactionCount);
		dao.saveUserPostStatistic(stat1);

		// save post2 statistic
		UserPostStatistic stat2 = new UserPostStatistic(userId, postId2,
				interactionCount2);
		dao.saveUserPostStatistic(stat2);

		// save total statistic
		UserStatistic stat = new UserStatistic(userId, total);
		dao.saveUserStatistic(stat);

		// verify post1
		UserPostStatistic result1 = dao.findUserPostStatistic(userId, postId);
		Assert.assertEquals("postId interactionCount should be save and read",
				interactionCount, result1.getInteractionCount());

		UserPostStatistic result2 = dao.findUserPostStatistic(userId, postId2);
		Assert.assertEquals("postId2 interactionCount should be save and read",
				interactionCount2, result2.getInteractionCount());

		UserStatistic totalResult = dao.findUserStatistic(userId);
		Assert.assertEquals("total interaction should be save and read", total,
				totalResult.getInteractionCount());
	}

	@Test
	public void testSaveUserSimilarity() {
		String userId = IdGenerator.generateId();

		String userId2 = IdGenerator.generateId();
		Double simiBetweenUser1And2 = 0.3d;

		Map<String, Double> similarityMap = new HashMap<String, Double>();
		similarityMap.put(userId2, simiBetweenUser1And2);

		Similarity similarity = new Similarity();
		similarity.setUserId(userId);
		similarity.setSimilarity(similarityMap);
		dao.saveUserSimilarity(similarity);

		Similarity result = dao.findUserSimilarity(userId);

		Assert.assertEquals(userId, result.getUserId());

		Assert.assertNotNull(
				"similarity should save for user : " + userId2.toString(),
				result.getSimilarity().get(userId2.toString()));

		Assert.assertEquals(
				"similarity should be equal for user : " + userId2.toString(),
				simiBetweenUser1And2,
				result.getSimilarity().get(userId2.toString()), 0.0001);
	}

	@Ignore
	@Test
	public void testSaveClusterSimilarity() {
		fail("Not yet implemented");
	}

}
