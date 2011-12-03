package net.umc.ModTRSSpout.listeners;

import net.umc.ModTRSSpout.ModTRSSpout;

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ModPlayerListener extends PlayerListener {
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		ModTRSSpout.getInstance().removeScreen(event.getPlayer());
	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) {
		ModTRSSpout.getInstance().removeScreen(event.getPlayer());
	}
}
