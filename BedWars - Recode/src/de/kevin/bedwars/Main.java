package de.kevin.bedwars;

import org.bukkit.plugin.java.JavaPlugin;

import de.kevin.bedwars.gamestate.GameStateManager;
import de.kevin.bedwars.others.ConfigLocationUtil;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	private GameStateManager gameStateManager;
	private int maxTeams;
	private int maxTeamSize;
	
	@Override
	public void onEnable() {
		plugin = this;
		gameStateManager = new GameStateManager(plugin);
		maxTeams = new ConfigLocationUtil(this, "MaxTeams").getMaxTeams();
		maxTeamSize = new ConfigLocationUtil(this, "MaxTeamSize").getMaxTeamSize();
	}
	
	@Override
	public void onDisable() {
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public int getMaxTeams() {
		return maxTeams;
	}
	
	public int getMaxTeamSize() {
		return maxTeamSize;
	}
	
	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

}
