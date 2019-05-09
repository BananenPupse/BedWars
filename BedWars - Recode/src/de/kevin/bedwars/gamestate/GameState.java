package de.kevin.bedwars.gamestate;

import de.kevin.bedwars.Main;

public abstract class GameState {
	
	public static final int WAITING_LOBBY = 0, INGAME = 1, ENDING = 2, SETUP = 5;
	
	public Main plugin;
	
	public GameState(Main plugin) {
		this.plugin = plugin;
	}
	
	public abstract int getID();
	public abstract void start();
	public abstract void stop();

}
