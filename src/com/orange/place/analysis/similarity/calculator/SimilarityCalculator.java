package com.orange.place.analysis.similarity.calculator;

import java.util.Iterator;

import org.apache.mahout.cf.taste.model.DataModel;

import com.orange.place.analysis.domain.Similarity;

public interface SimilarityCalculator {
	Iterator<Similarity> getSimilarity(DataModel dataModel);
}
