package no.urbancraft.mod.website.knownplayers;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = KnownPlayers.ID, name = KnownPlayers.ID, version = KnownPlayers.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class KnownPlayers {
	public static final String ID = "UrbanCraft KnownPlayers";
	public static final String VERSION = "0.0.1";

	public String post_url;
	public String identifier;
	public boolean debug;

	public boolean event_login;
	public boolean event_logout;
	public boolean event_respawn;
	public boolean event_changedimension;

	// The instance of your mod that Forge uses.
	@Instance(ID)
	public static KnownPlayers instance;

	public static Logger logger;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		post_url = config.get(config.CATEGORY_GENERAL, "post_url", "http://localhost/post/", "This is the url of which the mod posts updates to.").value;
		identifier = config.get(config.CATEGORY_GENERAL, "identifier", "knownplayers", "This string determines the value of the id field in the post request.").value;
		debug = config.get(Configuration.CATEGORY_GENERAL, "debug", false, "Enable debuging?").getBoolean(true);

		event_login = config.get("event", "login", true, "Send POST on player login?").getBoolean(true);
		event_logout = config.get("event", "logout", true, "Send POST on player logout?").getBoolean(true);
		event_respawn = config.get("event", "respawn", true, "Send POST on player respawn?").getBoolean(true);
		event_changedimension = config.get("event", "changedimension", true, "Send POST when player changes world?").getBoolean(true);

		config.save();

		logger.info("debug: " + debug);
		logger.info("identifier: " + identifier);
		logger.info("post_url: " + post_url);
		logger.info("event_login: " + event_login);
		logger.info("event_logout: " + event_logout);
		logger.info("event_respawn: " + event_respawn);
		logger.info("event_changedimension: " + event_changedimension);
	}

	@Init
	public void load(FMLInitializationEvent event) {
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		logger.info("Loaded.");
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
