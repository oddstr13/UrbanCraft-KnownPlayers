package no.urbancraft.mod.website.knownplayers;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

/*
 * 07:04:20 <cazzar> so you need to add another class that implements IPlayerTracker and you @Oveeride public void onPlayerLogin(EntityPlayer player)
 * 07:04:52 <cazzar> but you must register the playertracker using GameRegistry.registerPlayerTracker(tracker)
 */
public class PlayerTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if (KnownPlayers.instance.event_login) {
			new PosterThread(KnownPlayers.instance.post_url, KnownPlayers.instance.identifier, "login", player.username, KnownPlayers.instance.debug).run();
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		if (KnownPlayers.instance.event_logout) {
			new PosterThread(KnownPlayers.instance.post_url, KnownPlayers.instance.identifier, "logout", player.username, KnownPlayers.instance.debug).run();
		}
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		if (KnownPlayers.instance.event_changedimension) {
			new PosterThread(KnownPlayers.instance.post_url, KnownPlayers.instance.identifier, "changedimension", player.username, KnownPlayers.instance.debug).run();
		}
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		if (KnownPlayers.instance.event_respawn) {
			new PosterThread(KnownPlayers.instance.post_url, KnownPlayers.instance.identifier, "respawn", player.username, KnownPlayers.instance.debug).run();
		}
	}
}
