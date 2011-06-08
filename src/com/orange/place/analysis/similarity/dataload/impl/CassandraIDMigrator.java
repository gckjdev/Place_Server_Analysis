package com.orange.place.analysis.similarity.dataload.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.AbstractIDMigrator;
import org.apache.mahout.cf.taste.impl.model.file.FileIDMigrator;
import org.apache.mahout.cf.taste.model.UpdatableIDMigrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.orange.common.cassandra.CassandraClient;

public class CassandraIDMigrator extends AbstractIDMigrator implements
		UpdatableIDMigrator {
	public static final String DEFAULT_LONG_STRING_COLUMN_FAMILY = "idx_id_long_to_string";

	public static final String DEFAULT_COLUMN_NAME = "convertId";

	private static final Logger log = LoggerFactory
			.getLogger(FileIDMigrator.class);

	private final String long2StringColumnFamily;

	private final String columnName;

	private CassandraClient cassandraClient;

	/**
	 * @param config
	 */
	public CassandraIDMigrator() {
		this(DEFAULT_LONG_STRING_COLUMN_FAMILY, DEFAULT_COLUMN_NAME);
	}

	public CassandraIDMigrator(String long2StringColumnFamily, String columnName) {
		this.long2StringColumnFamily = long2StringColumnFamily;
		// this.string2LongColumnFamily = string2LongColumnFamily;
		this.columnName = columnName;
	}

	public final void storeMapping(long longID, String stringID)
			throws TasteException {
		// save in canssandra,
		// longID-stringID
		cassandraClient.insert(long2StringColumnFamily, String.valueOf(longID),
				columnName, stringID);
	}

	public void initialize(Iterable<String> stringIDs) throws TasteException {
		for (String stringID : stringIDs) {
			storeMapping(toLongID(stringID), stringID);
		}
	}

	public String toStringID(long longID) throws TasteException {
		// convert in canssandra
		String value = cassandraClient.getColumnValue(long2StringColumnFamily,
				String.valueOf(longID), columnName);
		if (StringUtils.isEmpty(value)) {
			log.error("can't convert the id for long value : " + longID);
		}
		return value;
	}

	@Required
	public void setCassandraClient(CassandraClient cassandraClient) {
		this.cassandraClient = cassandraClient;
	}
}
