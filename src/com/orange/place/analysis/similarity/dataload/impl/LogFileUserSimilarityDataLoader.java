package com.orange.place.analysis.similarity.dataload.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.orange.place.analysis.similarity.dataload.UserSimilarityDataLoader;

public class LogFileUserSimilarityDataLoader implements
		UserSimilarityDataLoader {

	public static final Logger log = LoggerFactory
			.getLogger(LogFileUserSimilarityDataLoader.class);

	private static final char TAB_SEPARATOR = '\t';

	private String dataModelFilePath;

	private CassandraIDMigrator iDMigrator;

	private LogParser logParser;

	private LogFileFinder logFileFinder;

	public DataModel getDataModel() {
		log.debug("start to LogFileUserSimilarityDataLoader#getDataModel");

		// init the folder
		prepareDataModelFile();

		List<File> logFiles = logFileFinder.getLogFile();

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(dataModelFilePath));
			for (File logFile : logFiles) {
				BufferedReader reader = null;

				try {
					reader = new BufferedReader(new FileReader(logFile));

					String logRecord = reader.readLine();
					while (logRecord != null) {
						if (!StringUtils.isEmpty(logRecord)) {
							LogParseResult logParseResult = logParser
									.parse(logRecord);
							writer.write(getDataModelFormat(logParseResult));
							writer.newLine();
							logRecord = reader.readLine();
						}
					}
				} catch (FileNotFoundException e) {
					log.error(
							"LogFileUserSimilarityDataLoader#getDataModel FileNotFoundException",
							e);
				} catch (IOException e) {
					log.error(
							"LogFileUserSimilarityDataLoader#getDataModel IOException",
							e);
				} catch (TasteException e) {
					log.error(
							"LogFileUserSimilarityDataLoader#getDataModel TasteException",
							e);
				} finally {
					close(reader);
				}
			}
		} catch (IOException e1) {
			throw new RuntimeException(
					"failed in creating similarity writer.", e1);
		}finally {
			close(writer);
		}

		return createDataModel();
	}

	/**
	 * Create an empty file for dataModelFilePath to avoid no file created when
	 * there's no data exist.
	 */
	private void prepareDataModelFile() {
		File dataModelFile = new File(dataModelFilePath);
		File dataModelFolder = new File(dataModelFile.getParent());
		if (!dataModelFolder.exists()) {
			dataModelFolder.mkdirs();
		}

		if (dataModelFile.exists()) {
			// dataModelFile.createNewFile();
			dataModelFile.delete();
		}
	}

	private DataModel createDataModel() {
		DataModel model = null;
		try {
			File dataModelFile = new File(dataModelFilePath);
			if (dataModelFile.exists()) {
				model = new FileDataModel(dataModelFile);
			}
		} catch (IOException e) {
			log.error(
					"LogFileUserSimilarityDataLoader#getDataModel#FileDataModel TasteException",
					e);
		}
		return model;
	}

	private void close(BufferedReader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				log.error(
						"LogFileUserSimilarityDataLoader#getDataModel#reader.close IOException",
						e);
			}
		}
	}

	private void close(BufferedWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				log.error(
						"LogFileUserSimilarityDataLoader#getDataModel#writer.close IOException",
						e);
			}
		}
	}

	private String getDataModelFormat(LogParseResult logParseResult)
			throws TasteException {
		// TODO: iDMigrator here is not very good, move to else?
		iDMigrator.initialize(Arrays.asList(logParseResult.getUserId(),
				logParseResult.getPostId()));
		StringBuffer sb = new StringBuffer();
		sb.append(iDMigrator.toLongID(logParseResult.getUserId())).append(
				TAB_SEPARATOR);
		sb.append(iDMigrator.toLongID(logParseResult.getPostId())).append(
				TAB_SEPARATOR);
		sb.append("1");
		String content = sb.toString();
		return content;
	}

	@Required
	public void setDataModelFilePath(String dataModelFilePath) {
		this.dataModelFilePath = dataModelFilePath;
	}

	@Required
	public void setiDMigrator(CassandraIDMigrator iDMigrator) {
		this.iDMigrator = iDMigrator;
	}

	@Required
	public void setLogParser(LogParser logParser) {
		this.logParser = logParser;
	}

	@Required
	public void setLogFileFinder(LogFileFinder logFileFinder) {
		this.logFileFinder = logFileFinder;
	}
}
