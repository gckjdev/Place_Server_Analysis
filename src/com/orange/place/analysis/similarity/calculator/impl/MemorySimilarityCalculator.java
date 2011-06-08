package com.orange.place.analysis.similarity.calculator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.UpdatableIDMigrator;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.orange.place.analysis.domain.Similarity;
import com.orange.place.analysis.similarity.calculator.SimilarityCalculator;

public class MemorySimilarityCalculator implements SimilarityCalculator {

	private static final ArrayList<Similarity> EMPTY_LIST = new ArrayList<Similarity>();

	private final Logger log = LoggerFactory
			.getLogger(MemorySimilarityCalculator.class);

	private UpdatableIDMigrator iDMigrator;

	public Iterator<Similarity> getSimilarity(DataModel dataModel) {
		Assert.notNull(dataModel, "dataModel should be init");
		List<Similarity> result = EMPTY_LIST;
		try {
			// TODO: not needed codes
			// DataModel model = new GenericDataModel(
			// GenericDataModel.toDataMap(dataModel));
			DataModel model = dataModel;
			UserSimilarity userSimilarity = new TanimotoCoefficientSimilarity(
					model);

			LongPrimitiveIterator it1 = dataModel.getUserIDs();
			while (it1.hasNext()) {
				long userID1 = it1.next();
				LongPrimitiveIterator it2 = dataModel.getUserIDs();

				Similarity similarity = new Similarity();
				result.add(similarity);

				similarity.setUserId(iDMigrator.toStringID(userID1));
				// setSimilarity
				Map<String, Double> similarityMap = new HashMap<String, Double>();
				while (it2.hasNext()) {
					long userID2 = it2.next();
					double similarityValue = userSimilarity.userSimilarity(
							userID1, userID2);

					similarityMap.put(iDMigrator.toStringID(userID2),
							similarityValue);
				}
				similarity.setSimilarity(similarityMap);
			}
		} catch (TasteException e) {
			log.error("getSimilarity failed.", e);
		}

		return result.iterator();
	}

	@Required
	public void setiDMigrator(UpdatableIDMigrator iDMigrator) {
		this.iDMigrator = iDMigrator;
	}
}
