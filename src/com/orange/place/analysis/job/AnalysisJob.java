package com.orange.place.analysis.job;

import com.orange.place.analysis.similarity.UserSimilarityGenerator;
import com.orange.place.analysis.statistic.PostStatisticGenerator;

public class AnalysisJob {

	private PostStatisticGenerator postStatisticGenerator;

	private UserSimilarityGenerator userSimilarityGenerator;

	public void execute() {
		postStatisticGenerator.generate();
		userSimilarityGenerator.generate();
	}

	public void setPostStatisticGenerator(
			PostStatisticGenerator postStatisticGenerator) {
		this.postStatisticGenerator = postStatisticGenerator;
	}

	public void setUserSimilarityGenerator(
			UserSimilarityGenerator userSimilarityGenerator) {
		this.userSimilarityGenerator = userSimilarityGenerator;
	}
}
