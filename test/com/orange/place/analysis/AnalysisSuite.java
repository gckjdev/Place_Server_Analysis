package com.orange.place.analysis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.orange.place.analysis.dao.DaoSuite;
import com.orange.place.analysis.similarity.SimilaritySuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ SimilaritySuite.class, DaoSuite.class })
public class AnalysisSuite {

}
