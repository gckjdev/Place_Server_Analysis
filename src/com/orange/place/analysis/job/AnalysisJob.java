package com.orange.place.analysis.job;

import org.springframework.beans.factory.annotation.Required;

import com.orange.place.analysis.similarity.UserSimilarityGenerator;
import com.orange.place.analysis.statistic.PostStatisticGenerator;

public class AnalysisJob {

	private PostStatisticGenerator postStatisticGenerator;

	private UserSimilarityGenerator userSimilarityGenerator;

	public void execute() {
		postStatisticGenerator.generate();
		userSimilarityGenerator.generate();
	}

	@Required
	public void setPostStatisticGenerator(
			PostStatisticGenerator postStatisticGenerator) {
		this.postStatisticGenerator = postStatisticGenerator;
	}

	@Required
	public void setUserSimilarityGenerator(
			UserSimilarityGenerator userSimilarityGenerator) {
		this.userSimilarityGenerator = userSimilarityGenerator;
	}
}
