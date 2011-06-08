package com.orange.place.analysis.similarity;

import java.util.Iterator;

import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Required;

import com.orange.place.analysis.dao.StatisticDao;
import com.orange.place.analysis.domain.Similarity;
import com.orange.place.analysis.similarity.calculator.SimilarityCalculator;
import com.orange.place.analysis.similarity.dataload.UserSimilarityDataLoader;

public class UserSimilarityGenerator {

	private UserSimilarityDataLoader userSimilarityDataLoader;

	private SimilarityCalculator similarityCalculator;

	private StatisticDao statisticDao;

	public void generate() {
		DataModel dataModel = userSimilarityDataLoader.getDataModel();

		if (dataModel != null) {
			Iterator<Similarity> it = similarityCalculator
					.getSimilarity(dataModel);

			// TODO: Remove codes if CassandraSimilarityCaluclator ready.
			while (it.hasNext()) {
				Similarity similarity = it.next();
				// write Similarity
				statisticDao.saveUserSimilarity(similarity);
			}
		}
	}

	@Required
	public void setUserSimilarityDataLoader(
			UserSimilarityDataLoader userSimilarityDataLoader) {
		this.userSimilarityDataLoader = userSimilarityDataLoader;
	}

	@Required
	public void setSimilarityCalculator(
			SimilarityCalculator similarityCalculator) {
		this.similarityCalculator = similarityCalculator;
	}

	@Required
	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}
}
