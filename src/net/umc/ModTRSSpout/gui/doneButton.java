package net.umc.ModTRSSpout.gui;



import net.umc.ModTRSSpout.ModTRSSpout;

import org.bukkit.Material;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class doneButton extends GenericButton{
	
private SpoutPlayer play;
private int idd;

doneButton(int id, entryRow row){
	setText("Done");
	this.idd = id;
	setMaxWidth(25);
	
	this.play = row.player;
}

public void handlePress() {
	if (play.hasPermission("modtrs.command.complete")
			|| play.isOp()) {
	ModTRSSpout plugin =ModTRSSpout.getInstance();
	
		plugin.api.sendCommandWithUsername(play.getName(), "done", String.valueOf(idd));
		
	
	}else {
		play.sendNotification("Error", "No Permission", Material.TNT);
	}
	
}
}
