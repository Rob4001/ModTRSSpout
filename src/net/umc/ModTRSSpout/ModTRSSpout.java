package net.umc.ModTRSSpout;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.umc.ModTRSSpout.gui.MainWindow;
import net.umc.ModTRSSpout.listeners.ModInputListener;
import net.umc.ModTRSSpout.listeners.ModKeyListener;
import net.umc.ModTRSSpout.listeners.ModPlayerListener;
import net.umc.ModTRSSpout.listeners.ModScreenListener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

import yetanotherx.bukkitplugin.ModTRS.ModTRS;
import yetanotherx.bukkitplugin.ModTRS.api.ModTRSAPI;

public class ModTRSSpout extends JavaPlugin {

	public Plugin mod;
	public Plugin mod2;
	public ModTRS modtrs;
	public ModTRSAPI api;
	private Logger log;
	private ModScreenListener screenListener;
	private ModPlayerListener playerListener = new ModPlayerListener();
	private Map<String, MainWindow> screens = new HashMap<String, MainWindow>();
	private static ModTRSSpout instance;

	@Override
	public void onDisable() {
		
		
	}

	@Override
	public void onEnable() {
		instance = this;
		log = Logger.getLogger("minecraft");
		mod = this.getServer().getPluginManager().getPlugin("ModTRS");
		if (mod == null){
			log.info("[ModTRSSpout] Cannot find ModTRS! Shutting Down");
		}else{
			mod2 = this.getServer().getPluginManager().getPlugin("Spout");
			if (mod2 == null){
				log.info("[ModTRSSpout] Cannot find Spout! Shutting Down");
			}else{
			modtrs = (ModTRS)mod;
			api = modtrs.getAPI();
			
			screenListener = new ModScreenListener(this);
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvent(Type.CUSTOM_EVENT, screenListener, Priority.Normal, this);
			pm.registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
			pm.registerEvent(Type.PLAYER_KICK, playerListener, Priority.Normal, this);
			pm.registerEvent(Type.CUSTOM_EVENT, new ModInputListener(), Priority.Normal, this);
			SpoutManager.getKeyBindingManager().registerBinding("ModTRS GUI", Keyboard.KEY_P, "Open ModTRS GUI", new ModKeyListener(), this);
			
			}
			
		}
		
		
	}

	public static ModTRSSpout getInstance() {
		return instance;
	}

	public void openOverlay(SpoutPlayer player){
		if(player.hasPermission("modtrs.command.complete") ||player.hasPermission("modtrs.command.check" )|| player.isOp()){
			MainWindow screen = null;
			if(!screens.containsKey(player.getName())){
				screens.put(player.getName(), new MainWindow(player));
			}
			screen = screens.get(player.getName());
			screen.open();
		} else {
			player.sendNotification("Error", "No Permission", Material.TNT);
		}
	}
	public void removeScreen(MainWindow screen)
	{
		if(screen != null){
			screens.remove(screen.getPlayer().getName());
		}
	}
	public void removeScreen(Player player) {
		removeScreen(screens.get(player.getName()));
	}


	

}
