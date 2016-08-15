package com.git.board_system.controller;

import java.lang.annotation.*;

/**
 * Mapping table annotation, represents array of mapping pairs
 * 
 * @see Mapping
 */
@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface MappingTable {
	public Mapping[] table();
}