package net.umc.ModTRSSpout.gui;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import net.umc.ModTRSSpout.ModTRSSpout;

import org.bukkit.ChatColor;


import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;

import org.getspout.spoutapi.gui.TextField;

import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Label;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

import yetanotherx.bukkitplugin.ModTRS.model.ModTRSRequest;

public class MainWindow  extends GenericWindow{
	private SpoutPlayer player;
	
	private Button closeWindowButton , nextButton, backButton;
	
	private Label panelheader;
	private TextField numBox;
	private int page;
	private ArrayList<Integer> nums = new ArrayList<Integer>();
	private Container Box,Title;
	private int pages;
	private ArrayList<doneButton> dones = new ArrayList<doneButton>();
	private ArrayList<claimButton> claims = new ArrayList<claimButton>();
	private ArrayList<Widget> rows = new ArrayList<Widget>();
	

	

	public MainWindow(SpoutPlayer player){
		super("ModReq Interface", player);
		this.player = player;
		this.page = 1;
		this.setPriority(RenderPriority.High);
		initScreen();
	}

	private void initScreen() {
		ModTRSSpout plugin =  ModTRSSpout.getInstance();
		panelheader = new GenericLabel("Moderator Requests");
		panelheader.setX(getMarginLeft()).setY(getMarginTop()+25).setHeight(25).setWidth(190);
		attachWidget(plugin,panelheader);
		closeWindowButton = new GenericButton(ChatColor.RED+"X");
		closeWindowButton.setTooltip("Close Window");
		closeWindowButton.setWidth(20).setHeight(20).setX(getMarginRight()-26).setY(getMarginTop()-2);
		closeWindowButton.setPriority(RenderPriority.Low);
		attachWidget(plugin, closeWindowButton);
		nextButton = new GenericButton(ChatColor.GOLD+">");
		nextButton.setTooltip("Next Page");
		nextButton.setWidth(20).setHeight(20).setY(getMarginBottom()-10).setX(getMarginLeft() +25);
		nextButton.setPriority(RenderPriority.Low);
		attachWidget(plugin,nextButton);
		backButton = new GenericButton(ChatColor.GOLD+"<");
		backButton.setTooltip("Last Page");
		backButton.setWidth(20).setHeight(20).setY(getMarginBottom()-10).setX(getMarginLeft() );
		backButton.setPriority(RenderPriority.Low);
		attachWidget(plugin,backButton);
		Box = new GenericContainer();
		Box.setX(getMarginLeft()).setY(getMarginTop() + 50).setWidth(400).setHeight(110);
		attachWidget(plugin,Box);
		
		refreshView();
	}

	public void open(){
		player.getMainScreen().attachPopupScreen(this);
		
		refreshView();
	}
	

	public void hide(){
		close();
	}
	
	public int getPage(){
		return page;
	}
	
	public void setPage(int p){
		page = p;
		refreshView();
	}
	

	private void refreshView() {
		
		for (Widget row : rows) {
			Box.removeChild(row);
		}
		
dones.clear();
claims.clear();
rows.clear();
Title = new GenericContainer();
Title.setLayout(ContainerType.HORIZONTAL).setMaxHeight(10);;
Title.addChild(new GenericLabel("ID").setMaxWidth(15));
Title.addChild(new GenericLabel("Issued By:").setMaxWidth(60));
Title.addChild(new GenericLabel("Description").setMinWidth(180));
Title.addChild(new GenericLabel("Claimed By").setMaxWidth(100));
Box.addChild(Title);
rows.add(Title);
		ModTRSSpout plugin = ModTRSSpout.getInstance();
		
		List<ModTRSRequest> Reqs = null;
		try {
			Reqs = plugin.api.getRequestTable().getRequests("open");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		int total = Reqs.size();
		
		pages = total  / 10;
		
			pages =+ 1;
		
		
		int x = 1;
		
		int first = (10 * page) - 9;
		int last = (10 * page);
		
		for (ModTRSRequest req : Reqs) {
			
			if (first <= x && x < last) {
				entryRow b = null;
				try {
					b = new entryRow(req.getId(), player);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				b.setMaxHeight(10);
				Box.addChild(b);
				rows.add(b);
				dones.add(b.done);
				claims.add(b.claimb);

				
				

			}
			x++;
		}

		setDirty(true);
		for (Widget widget : getAttachedWidgets()) {
			widget.setVisible(true);
			widget.setDirty(true);
		}
	}

	public void handleClose() {
		
		
	}

	public void handleClick(Button button) {
		if (button.equals(closeWindowButton)) {
			player.closeActiveWindow();
			ModTRSSpout.getInstance().removeScreen(this);
			close();
		}
		if (button.equals(nextButton)) {
			
			setPage(page + 1);
			
		}
		if (button.equals(backButton)) {
			if (!(page == 1)) {
				setPage(page - 1);
			}
			refreshView();
		}
		if (dones.contains(button)){
			
				for(doneButton done:dones){
					if(button.equals(done)){
					done.handlePress();
					}
				}
			
		}
		if (claims.contains(button)){
		
				for(claimButton claim:claims){
					if(button.equals(claim)){
					claim.handlePress();
					}
				}
			
		}
		
		refreshView();
	}

	public void handleTextChange(TextField textField, String newText) {
		if (textField.equals(numBox)){
			
			nums.clear();
		String[] parts = newText.split(",");
		for (String part:parts){
			nums.add(Integer.parseInt(part));
		}
		}
		
	}

	
}
