package com.orange.place.analysis.systemtest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.orange.place.analysis.job.JobScheduler;

public class AnalysisFlowTestHelper {
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

		// String[] userIds = new String[] { "user_id_1", "user_id_2",
		// "user_id_3" };
		// for (String userId : userIds) {
		// System.out.print("======== result for " + userId);
		// System.out.println();
		// TestHelper.printSimilarityByUser(userId);
		// System.out.println();
		// }
	}
}
