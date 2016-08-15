package com.git.board_system.controller;

/**
 * Mapping annotation, represents pair of link and its handler
 */
public @interface Mapping {
	public String link();
	public String handle();
}