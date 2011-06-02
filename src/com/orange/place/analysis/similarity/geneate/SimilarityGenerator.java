package com.orange.place.analysis.similarity.geneate;

import java.util.Iterator;

import org.apache.mahout.cf.taste.model.DataModel;

import com.orange.place.analysis.domain.Similarity;

public interface SimilarityGenerator {
	Iterator<Similarity> getSimilarity(DataModel dataModel);
}
