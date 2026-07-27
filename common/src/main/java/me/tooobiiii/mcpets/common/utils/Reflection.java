package me.tooobiiii.mcpets.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class holding reflection methods
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reflection {

	/**
	 * Returns
	 *
	 * @param path
	 * @return
	 */
	public static boolean classExists(@NotNull String path) {
		try {
			Class.forName(path);
			return true;
		} catch (Throwable t) {
			return false;
		}
	}
}
