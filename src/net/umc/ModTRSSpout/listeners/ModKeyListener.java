package net.umc.ModTRSSpout.listeners;

import net.umc.ModTRSSpout.ModTRSSpout;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;


public class ModKeyListener implements BindingExecutionDelegate {

	@Override
	public void keyPressed(KeyBindingEvent event) {
		if (event.getScreenType() != ScreenType.CHAT_SCREEN){
		ModTRSSpout plugin = ModTRSSpout.getInstance();
		event.getPlayer().closeActiveWindow();
		plugin.openOverlay(event.getPlayer());
		}
		
	}

	@Override
	public void keyReleased(KeyBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
