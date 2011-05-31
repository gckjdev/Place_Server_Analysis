package com.orange.place.analysis.similarity;

import java.util.Iterator;

import org.apache.mahout.cf.taste.model.DataModel;

import com.orange.place.analysis.domain.UserSimilarity;

public interface SimilarityGenerator {

	Iterator<UserSimilarity> getSimilarity(DataModel dataModel);
}
