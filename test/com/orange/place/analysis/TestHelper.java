package com.orange.place.analysis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.orange.common.context.SpringContextUtil;
import com.orange.place.analysis.dao.StatisticDao;
import com.orange.place.analysis.domain.Similarity;

public class TestHelper {

	public static void printSimilarityByUser(String userId) {
		StatisticDao statisticDao = SpringContextUtil.getBean("statisticDao");
		Similarity s = statisticDao.findUserSimilarity(userId);
		System.out.print(s);
	}
	
	public static void createContext(String... path) {

		try {
			new ClassPathXmlApplicationContext(path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error while creating context", e);
		}
	}
}
