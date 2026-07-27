package fr.nocsy.mcpets.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.tooobiiii.mcpets.common.ProjectVariables;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(
		id          = ProjectVariables.PLUGIN_ID,
		name        = ProjectVariables.PLUGIN_NAME,
		version     = ProjectVariables.PLUGIN_VERSION,
		description = ProjectVariables.PLUGIN_DESCRIPTION,
		authors     = ProjectVariables.PLUGIN_AUTHOR
)
@Getter
@RequiredArgsConstructor(onConstructor_ =  @Inject)
public class McPetsVelocity {

	private static final MinecraftChannelIdentifier CHANNEL =
			MinecraftChannelIdentifier.from("mcpets:sync");

	private final ProxyServer proxy;
	private final Logger logger;
	@DataDirectory
	private final Path dataFolder;

	@Subscribe
	public void onProxyInitialize(ProxyInitializeEvent event) {
		// Register the plugin messaging channel so the proxy can send/receive on it
		proxy.getChannelRegistrar().register(CHANNEL);

		// Load config
		VelocityPluginConfig config = new VelocityPluginConfig(dataFolder, logger);
		config.load();

		// Register the event listener
		proxy.getEventManager().register(this, new SyncListener(proxy, config, logger, CHANNEL));

		logger.info("[MCPets-Velocity] Loaded. Syncing pets on cross-server switches.");
	}
}