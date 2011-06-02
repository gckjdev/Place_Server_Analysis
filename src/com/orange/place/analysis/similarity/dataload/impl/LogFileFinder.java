package com.orange.place.analysis.similarity.dataload.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileFinder {

	private String logFolderPath;

	private Pattern logFilePattern;

	public List<File> getLogFile() {
		List<File> result = new ArrayList<File>();
		File targetFile = new File(logFolderPath);
		if (targetFile.exists()) {
			result = getSuitableLogFile(targetFile);
		}
		return result;
	}

	private List<File> getSuitableLogFile(File target) {
		List<File> result = new ArrayList<File>();
		if (target.isDirectory()) {
			File[] children = target.listFiles();
			for (File child : children) {
				result.addAll(getSuitableLogFile(child));
			}
		} else if (target.isFile()) {
			Matcher m = logFilePattern.matcher(target.getName());
			if (m.matches()) {
				result.add(target);
			}
		}
		return result;
	}

	public void setLogFolderPath(String logFolderPath) {
		this.logFolderPath = logFolderPath;
	}

	public void setLogFilePattern(String logFilePatternValue) {
		logFilePattern = Pattern.compile(logFilePatternValue);
	}
}
