package com.orange.place.analysis.similarity.dataload.impl;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.AbstractIDMigrator;
import org.apache.mahout.cf.taste.impl.model.file.FileIDMigrator;
import org.apache.mahout.cf.taste.model.UpdatableIDMigrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraIDMigrator extends AbstractIDMigrator implements
		UpdatableIDMigrator {
	public static final String DEFAULT_MAPPING_TABLE = "taste_id_mapping";
	public static final String DEFAULT_LONG_ID_COLUMN = "long_id";
	public static final String DEFAULT_STRING_ID_COLUMN = "string_id";

	private static final Logger log = LoggerFactory
			.getLogger(FileIDMigrator.class);

	/**
	 * @param config
	 */
	public CassandraIDMigrator() {
		this(DEFAULT_MAPPING_TABLE, DEFAULT_LONG_ID_COLUMN,
				DEFAULT_STRING_ID_COLUMN);
	}

	public CassandraIDMigrator(String mappingTable, String longIDColumn,
			String stringIDColumn) {

	}

	public final void storeMapping(long longID, String stringID)
			throws TasteException {
		// TODO: save in canssandra,
		// longID-stringID
		// stringID-longID
	}

	public void initialize(Iterable<String> stringIDs) throws TasteException {
		for (String stringID : stringIDs) {
			storeMapping(toLongID(stringID), stringID);
		}
	}

	public String toStringID(long longID) throws TasteException {
		// TODO: convert in canssandra
		return null;
	}

}
