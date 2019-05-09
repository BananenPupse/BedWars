package de.kevin.bedwars.others;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import de.kevin.bedwars.Main;

public class Map implements Comparable<Map> {

	private Main plugin;
	private String name;
	private String builder;
	private Location[] spawnLocations = new Location[8];
	private Location spectatorLocation;
	static List<Map> maps = new ArrayList<Map>();
	
	private int votes;
	
	public Map(Main plugin, String name) {
		this.plugin = plugin;
		this.name = name.toUpperCase();
	}
	
	public void create(String builder) {
		setBuilder(builder);
		plugin.getConfig().set("Arenas." + getName() + ".Builder", builder);
		plugin.saveConfig();
	}
	
	public boolean exists() {
		return (plugin.getConfig().getString("Arenas." + getName() + ".Builder") != null);
	}
	
	public boolean playable() {
		ConfigurationSection cfgSection = plugin.getConfig().getConfigurationSection("Arenas." + getName());
		if (!cfgSection.contains("Spectator")) return false;
		if (!cfgSection.contains("Builder")) return false;
		for (int i = 1; i < getSpawnLocations().length; i++) {
			if(!cfgSection.contains(Integer.toString(i))) return false;
		}
		return true;
	}
	
	public void setSpawnLocation(int spawnNumber, Location location) {
		getSpawnLocations()[spawnNumber - 1] = location;
		new ConfigLocationUtil(plugin, location, "Arenas." + getName() + "." + spawnNumber).saveLocation();
	}
	
	public void setSpectatorLocation(Location location) {
		spectatorLocation = location;
		new ConfigLocationUtil(plugin, location, "Arenas." + getName() + ".Spectator").saveLocation();
	}
	
	public String getName() {
		return name;
	}
	
	public String getBuilder() {
		return builder;
	}
	
	public Location[] getSpawnLocations() {
		return spawnLocations;
	}
	
	public Location getSpectatorLocation() {
		return spectatorLocation;
	}
	
	public int getVotes() {
		return votes;
	}
	
	public static List<Map> getMaps() {
		return maps;
	}
	
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public void addVote() {
		setVotes(getVotes()+1);
	}
	
	public void setBuilder(String builder) {
		this.builder = builder;
	}
	
	public void setSpawnLocations(Location[] spawnLocations) {
		this.spawnLocations = spawnLocations;
	}
	
	@Override
	public String toString() {
		return String.format("Map[name=%s;builder=%s;location=%s,%s,%s,%s,%s]", getName(), getBuilder(), getSpawnLocations()[0].getX(), getSpawnLocations()[0].getY(), getSpawnLocations()[0].getZ(), getSpawnLocations()[0].getYaw(), getSpawnLocations()[0].getPitch());
	}

	@Override
	public int compareTo(Map map) {
		return getName().compareTo(map.getName());
	}

	public Map getMapByName(String name) {
		return getMaps().stream().filter(maps -> maps.getName().equals(name.toUpperCase())).collect(Collectors.toList()).get(0);
	}
	
}
