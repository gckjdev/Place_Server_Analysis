package com.orange.place.analysis.job;

import com.orange.common.context.SpringContextUtil;

public class JobScheduler {

	private AnalysisJob analysisJob;

	public AnalysisJob getAnalysisJob() {
		if (analysisJob == null) {
			analysisJob = SpringContextUtil.getBean("analysisJob");
		}
		return analysisJob;
	}

	public void runJob() {
		// TODO: start control
		AnalysisJob job = getAnalysisJob();
		job.execute();
	}

}
