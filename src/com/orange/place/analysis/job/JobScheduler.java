package com.orange.place.analysis.job;

import org.apache.commons.lang.StringUtils;

import com.orange.common.cassandra.CassandraClient;
import com.orange.common.context.SpringContextUtil;

public class JobScheduler {

	private AnalysisJob analysisJob;

	private CassandraClient cassandraClient;

	private final String RUNNING = "RUNNING";

	public AnalysisJob getAnalysisJob() {
		if (analysisJob == null) {
			analysisJob = SpringContextUtil.getBean("analysisJob");
		}
		return analysisJob;
	}

	public CassandraClient getCassandraClient() {
		if (cassandraClient == null) {
			cassandraClient = SpringContextUtil.getBean("cassandraClient");
		}
		return cassandraClient;
	}

	public void runJob() {
		String status = getStatus();
		if (!StringUtils.isEmpty(status)) {
			// don't run the job if previous job is running.
			return;
		}

		try {
			start();
			AnalysisJob job = getAnalysisJob();
			job.execute();
		} finally {
			end();
		}
	}

	private void end() {
		getCassandraClient().deleteStringColumn("idx_id_long_to_string",
				"analysisJob_status", "status");
	}

	private void start() {
		getCassandraClient().insert("idx_id_long_to_string",
				"analysisJob_status", "status", RUNNING);
	}

	private String getStatus() {
		String status = getCassandraClient().getColumnValue(
				"idx_id_long_to_string", "analysisJob_status", "status");
		return status;
	}
}
