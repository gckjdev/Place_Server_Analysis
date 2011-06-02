package com.orange.place.analysis.statistic;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.place.analysis.dao.UserDao;
import com.orange.place.analysis.domain.UserPostStatistic;
import com.orange.place.analysis.domain.UserStatistic;

/**
 * Post statistic generator, like this : {userId, postId} = 2
 * 
 * @author echnlee
 * 
 */
public class PostStatisticGenerator {

	public static final Logger log = LoggerFactory
			.getLogger(PostStatisticGenerator.class);

	private PostStatisticReader postStatisticReader;

	private PostStatisticWriter postStatisticWriter;

	private UserDao userDao;

	public void generate() {

		Iterator<String> it = userDao.findAllUserId();
		while (it.hasNext()) {
			String userId = it.next();
			Map<String, Integer> postStatisticByUser = postStatisticReader
					.getPostStatistic(userId);
			int total = saveUserPostStatistic(userId, postStatisticByUser);
			saveUserStatistic(userId, total);
		}

	}

	private int saveUserPostStatistic(String userId,
			Map<String, Integer> postStatisticByUser) {
		int total = 0;
		Iterator<String> postIdIter = postStatisticByUser.keySet().iterator();
		while (postIdIter.hasNext()) {
			String postId = postIdIter.next();
			Integer interactionCount = postStatisticByUser.get(postId);
			total += interactionCount;
			UserPostStatistic postStatistic = new UserPostStatistic(userId,
					postId, interactionCount);
			postStatisticWriter.saveUserPostStatistic(postStatistic);
			log.debug("save userPostStatistic, userId={}, postId={}, count={}",
					new Object[] { userId, postId, });
		}
		return total;
	}

	private void saveUserStatistic(String userId, int total) {
		UserStatistic statistic = new UserStatistic(userId, total);
		postStatisticWriter.saveUserStatistic(statistic);
		log.debug("save userStatistic, userId={},count={}", new Object[] {
				userId, total });
	}

	public void setPostStatisticReader(PostStatisticReader postStatisticReader) {
		this.postStatisticReader = postStatisticReader;
	}

	public void setPostStatisticWriter(PostStatisticWriter postStatisticWriter) {
		this.postStatisticWriter = postStatisticWriter;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
