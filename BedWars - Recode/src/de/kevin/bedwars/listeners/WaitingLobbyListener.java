package de.kevin.bedwars.listeners;

import org.bukkit.event.Listener;

import de.kevin.bedwars.Main;
import de.kevin.bedwars.gamestate.GameState;

public class WaitingLobbyListener implements Listener {

	private Main plugin;
	private GameState gamestate;
	
	public WaitingLobbyListener(Main plugin, GameState gamestate) {
		this.plugin = plugin;
		this.gamestate = gamestate;
	}
	
	public Main getPlugin() {
		return plugin;
	}
	
	public GameState getGamestate() {
		return gamestate;
	}

}
