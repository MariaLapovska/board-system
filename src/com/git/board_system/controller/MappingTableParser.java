package com.git.board_system.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Parser for Mapping table annotation
 * 
 * @see MappingTable
 */
public class MappingTableParser {

	public static Map<String, String> parse(Class<?> clazz) {
		Map<String, String> mappingTable = new HashMap<String, String>();

		if (clazz.isAnnotationPresent(MappingTable.class)) { // if given class
																// has this
																// annotation
			MappingTable annotation = (MappingTable) clazz
					.getAnnotation(MappingTable.class);

			for (Mapping mapping : annotation.table()) {
				mappingTable.put(mapping.link(), mapping.handle());
			}
		}

		return mappingTable;
	}
}