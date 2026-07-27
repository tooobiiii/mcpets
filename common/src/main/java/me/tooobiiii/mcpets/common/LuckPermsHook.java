package me.tooobiiii.mcpets.common;

import lombok.Getter;
import me.tooobiiii.mcpets.common.utils.Reflection;

/**
 * Class that hooks into LuckPerms for permission management.
 */
@Getter
public class LuckPermsHook {

	@Getter
	private static final LuckPermsHook instance = new LuckPermsHook();

	private final boolean enabled = Reflection.classExists("net.luckperms.api.Luckperms");
}
