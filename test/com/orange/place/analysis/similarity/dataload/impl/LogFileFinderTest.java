package com.orange.place.analysis.similarity.dataload.impl;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogFileFinderTest {

	private static final String EXAMPLE_LOG = "//example.log";

	private static final String TEST_FOLDER = "//LogFileFinderTest";

	private LogFileFinder finder;

	private static String logFolderPath;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String tmpDir = System.getProperty("java.io.tmpdir");
		logFolderPath = tmpDir + TEST_FOLDER;
		File folder = new File(logFolderPath);
		folder.mkdirs();
		// create several log files

		String logFilePath = logFolderPath + EXAMPLE_LOG;
		File logFile = new File(logFilePath);
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File folder = new File(logFolderPath);
		delete(folder);
	}

	private static void delete(File file) {
		if (file.isDirectory()) {
			for (File children : file.listFiles()) {
				delete(children);
			}
		}
		file.delete();
	}

	@Before
	public void before() {
		finder = new LogFileFinder();
		finder.setLogFilePattern(".*\\.log.*");
		finder.setLogFolderPath(logFolderPath);
	}

	@Test
	public void testGetLogFile() {
		List<File> logFiles = finder.getLogFile();
		Assert.assertEquals(1, logFiles.size());
	}

	@Test
	public void testGetLogFile_SampleData() {
		finder = new LogFileFinder();
		finder.setLogFilePattern(".*\\.log.*");
		//finder.setLogFolderPath("C:\\gckj\\Place_Server_Analysis\\data\\");
		finder.setLogFolderPath("C:\\work\\tc\\gckj\\Place_Server_Analysis\\data\\");
		List<File> logFiles = finder.getLogFile();
		Assert.assertEquals(1, logFiles.size());
	}

	@Test
	public void testPattern() {
		String regex = ".*\\.log.*";
		Pattern p = Pattern.compile(regex);
		String value = "example.log";
		Matcher m = p.matcher(value);

		Assert.assertTrue(regex + " should match : " + value, m.matches());
	}

	@Test
	public void testAnalysisLogPattern() {
		String regex = "analysis.log\\..*";
		Pattern p = Pattern.compile(regex);
		String value = "analysis.log";
		Matcher m = p.matcher(value);

		Assert.assertFalse(regex + " should not match : " + value, m.matches());
	}

	@Test
	public void testAnalysisLogPatternMatch() {
		String regex = "analysis.log\\..*";
		Pattern p = Pattern.compile(regex);
		String value = "analysis.log.2011-06-27-22-55";
		Matcher m = p.matcher(value);

		Assert.assertTrue(regex + " should match : " + value, m.matches());
	}
}
