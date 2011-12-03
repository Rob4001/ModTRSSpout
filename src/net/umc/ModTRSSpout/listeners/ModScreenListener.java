package net.umc.ModTRSSpout.listeners;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.gui.Screen;

import net.umc.ModTRSSpout.ModTRSSpout;
import net.umc.ModTRSSpout.gui.MainWindow;

public class ModScreenListener extends ScreenListener {
	

	public ModScreenListener(ModTRSSpout tmbMain) {
		
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		if(!event.getButton().isVisible())
			return;
		Screen screen = event.getScreen();
		if(screen instanceof MainWindow){
			MainWindow tmi = ((MainWindow)screen);
			tmi.handleClick(event.getButton());
		}
	}

	@Override
    public void onTextFieldChange(TextFieldChangeEvent event) {
		Screen screen = event.getScreen();
		if(screen instanceof MainWindow){
			MainWindow tmi = ((MainWindow)screen);
			tmi.handleTextChange(event.getTextField(),event.getNewText());
		}     
    }

	@Override
	public void onScreenClose(ScreenCloseEvent event) {
		Screen screen = event.getScreen();
		if(screen instanceof MainWindow){
			MainWindow tmi = ((MainWindow)screen);
			tmi.handleClose();
		}
	}

}
