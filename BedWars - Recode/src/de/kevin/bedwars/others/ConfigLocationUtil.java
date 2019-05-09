package de.kevin.bedwars.others;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import de.kevin.bedwars.Main;

public class ConfigLocationUtil {

	private Main plugin;
	private Location location;
	private String root;

	public ConfigLocationUtil(Main plugin, Location location, String root) {
		this.plugin = plugin;
		this.location = location;
		this.root = root;
	}

	public ConfigLocationUtil(Main plugin, String root) {
		this(plugin, null, root);
	}
	
	public void saveLocation() {
		FileConfiguration cfg = plugin.getConfig();
		cfg.set(getRoot() + ".World", getLocation().getWorld().getName());
		cfg.set(getRoot() + ".X", getLocation().getX());
		cfg.set(getRoot() + ".Y", getLocation().getY());
		cfg.set(getRoot() + ".Z", getLocation().getZ());
		cfg.set(getRoot() + ".Yaw", getLocation().getYaw());
		cfg.set(getRoot() + ".Pitch", getLocation().getPitch());
		plugin.saveConfig();
	}

	public Location loadLocation() {
		FileConfiguration cfg = plugin.getConfig();
		if (cfg.contains(getRoot())) {
			World world = Bukkit.getWorld(cfg.getString(getRoot() + ".World"));
			double x = cfg.getDouble(getRoot() + ".X"), y = cfg.getDouble(getRoot() + ".Y"), z = cfg.getDouble(getRoot() + ".Z");
			float yaw = (float) cfg.getDouble(getRoot() + ".Yaw"), pitch = (float) cfg.getDouble(getRoot() + ".Pitch");
			return new Location(world, x, y, z, yaw, pitch);
		} else
			return null;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getRoot() {
		return root;
	}
	
	public void setRoot(String root) {
		this.root = root;
	}

	public int getMaxTeams() {
		FileConfiguration cfg = plugin.getConfig();
		if (cfg.contains(getRoot())) {
			return cfg.getInt(getRoot());
		} else
			return 1;
	}
	
	public int getMaxTeamSize() {
		FileConfiguration cfg = plugin.getConfig();
		if (cfg.contains(getRoot())) {
			return cfg.getInt(getRoot());
		} else
			return 1;
	}
	
	public void setMaxTeams(int i) {
		FileConfiguration cfg = plugin.getConfig();
		cfg.set(getRoot(), i);
		plugin.saveConfig();
	}
	
	public void setMaxTeamSize(int i) {
		FileConfiguration cfg = plugin.getConfig();
		cfg.set(getRoot(), i);
		plugin.saveConfig();
	}

}
