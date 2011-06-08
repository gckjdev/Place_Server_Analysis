package com.orange.place.analysis.job;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.orange.common.context.SpringContextUtil;
import com.orange.place.analysis.dao.StatisticDao;
import com.orange.place.analysis.domain.Similarity;

public class JobSchedulerTest {

	private static JobScheduler scheduler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			new ClassPathXmlApplicationContext(
					new String[] { "classpath:/com/orange/place/analysis/applicationContext.xml" });
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		scheduler = new JobScheduler();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testRunJob() {
		scheduler.runJob();

		String[] userIds = new String[] { "user_id_1", "user_id_2", "user_id_3" };
		for (String userId : userIds) {
			System.out.print("======== result for " + userId);
			System.out.println();
			printSimilarityByUser(userId);
			System.out.println();
		}
	}

	private void printSimilarityByUser(String userId) {
		StatisticDao statisticDao = SpringContextUtil.getBean("statisticDao");
		Similarity s = statisticDao.findUserSimilarity(userId);
		System.out.print(s);
	}
}
