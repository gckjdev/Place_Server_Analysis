package com.orange.place.analysis.similarity.impl;

import org.apache.mahout.cf.taste.model.DataModel;

import com.orange.place.analysis.similarity.UserSimilarityDataLoader;

public class LogFileUserSimilarityDataLoader implements
		UserSimilarityDataLoader {

	private String logFolderPath;

	private String dataModelFilePath;

	private CassandraIDMigrator idMigrator;

	public void setLogFolderPath(String logFolderPath) {
		this.logFolderPath = logFolderPath;
	}

	public void setDataModelFilePath(String dataModelFilePath) {
		this.dataModelFilePath = dataModelFilePath;
	}

	public void setIdMigrator(CassandraIDMigrator idMigrator) {
		this.idMigrator = idMigrator;
	}

	public DataModel getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
