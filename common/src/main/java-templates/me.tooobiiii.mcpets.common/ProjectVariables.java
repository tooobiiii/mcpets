package me.tooobiiii.mcpets.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * A class containing project variables to expand by blossom plugin.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectVariables {

	public static final String PLUGIN_NAME = "{{ name }}";
	public static final String PLUGIN_ID = "{{ id }}";
	public static final String PLUGIN_VERSION = "{{ version }}";
	public static final String PLUGIN_DESCRIPTION = "{{ description }}";
	public static final String PLUGIN_AUTHOR = "{{ author }}";
}