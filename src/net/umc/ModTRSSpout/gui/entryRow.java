package net.umc.ModTRSSpout.gui;

import java.sql.SQLException;

import net.umc.ModTRSSpout.ModTRSSpout;

import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.Label;
import org.getspout.spoutapi.player.SpoutPlayer;

import yetanotherx.bukkitplugin.ModTRS.model.ModTRSRequest;
import yetanotherx.bukkitplugin.ModTRS.model.ModTRSUser;

public class entryRow extends GenericContainer{
private Label id,name,desc,claim;
public doneButton done;
public claimButton claimb;
public SpoutPlayer player;
public int idd;
public boolean stst;

	public entryRow(int i, SpoutPlayer player) throws SQLException {
		this.idd =i;
		this.player = player;
	
		setLayout(ContainerType.HORIZONTAL);
		
		ModTRSSpout plugin = ModTRSSpout.getInstance();
		ModTRSRequest req = plugin.api.getRequestTable().getRequestFromId(i);
		id = new GenericLabel();
		id.setText(String.valueOf(i)).setMaxWidth(15);
		addChild(id);
		ModTRSUser user = plugin.api.getUserTable().getUserFromId(
				req.getUserId());
		boolean online = plugin.getServer().getPlayer(user.getName())
				.isOnline();
		name = new GenericLabel();
		if (online) {
			name.setText(ChatColor.GREEN + user.getName()).setMaxWidth(60);
		} else {
			name.setText(ChatColor.RED + user.getName()).setMaxWidth(60);
		}
		addChild(name);
		desc = new GenericLabel();
		String text = req.getText();
		 if (text.length() >= 35) {
			 text = text.substring(0, 35 ) + "...";
         }
		desc.setText(text).setMinWidth(200);
		addChild(desc);
		claim = new GenericLabel();
		if (req.getStatus() == 1) {
			
			claim.setText( plugin.api.getUserTable().getUserFromId(req.getModId())
							.getName());
			
		}
		claim.setMaxWidth(60);
		addChild(claim);
		done = new doneButton(i,this);
		addChild(done);
		stst = req.getStatus() != 0;
		claimb = new claimButton(i, this);
		addChild(claimb);
		

	}
}
