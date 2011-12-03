package net.umc.ModTRSSpout.listeners;

import java.util.ArrayList;


import net.umc.ModTRSSpout.ModTRSSpout;

import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ModInputListener extends InputListener{
	public ArrayList<SpoutPlayer> control = new ArrayList<SpoutPlayer>();
	@Override
    public void onKeyPressedEvent(KeyPressedEvent event) {
		if (event.getKey()  ==  Keyboard.KEY_LCONTROL){
			control.add(event.getPlayer());
		}
		if (event.getKey() == Keyboard.KEY_Q && control.contains(event.getPlayer())){
			ModTRSSpout plugin = ModTRSSpout.getInstance();
			event.getPlayer().closeActiveWindow();
			plugin.openOverlay(event.getPlayer());
			
		}
    }
	@Override
    public void onKeyReleasedEvent(KeyReleasedEvent event) {
		if (event.getKey()  ==  Keyboard.KEY_LCONTROL){
			control.remove(event.getPlayer());
		}
    }
}
