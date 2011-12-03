package net.umc.ModTRSSpout.gui;

import net.umc.ModTRSSpout.ModTRSSpout;

import org.bukkit.Material;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class claimButton extends GenericButton {
public boolean claimed;
private int idd;
private SpoutPlayer play;

	public claimButton(int i, entryRow row) {
		this.idd = i;
		this.claimed = row.stst;
		this.play = row.player;
		setTitle();
		setMaxWidth(30);
	}
	
	public void handlePress(){
		if (play.hasPermission("modtrs.command.complete")
				|| play.isOp()) {
		ModTRSSpout plugin =ModTRSSpout.getInstance();
		if (claimed != true){
			plugin.api.sendCommandWithUsername(play.getName(), "claim", String.valueOf(idd));
			claimed = true;
			setTitle();
		}else{
			plugin.api.sendCommandWithUsername(play.getName(), "unclaim", String.valueOf(idd));
			claimed = false;
			setTitle();
		}
		}else {
			play.sendNotification("Error", "No Permission", Material.TNT);
		}
	}
	public void setTitle(){
		if (claimed != true){
		setText("Claim");}else{
		 setText("Unclaim");
		}
		setDirty(true);
	}

}
