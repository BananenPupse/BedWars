package de.kevin.bedwars.others;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import de.kevin.bedwars.Main;

public class Team implements Comparable<Team> {
	
	private Main plugin = Main.getPlugin();
	private String name;
	private int id;
	public static List<Team> teams = new ArrayList<Team>();
	private List<Player> teamMembers;
	private List<Player> members;

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
		teamMembers = new ArrayList<Player>();
		members = new ArrayList<Player>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getScoreboardName() {
		return getId() + "_" + getName();
	}
	
	public String getColor() {
		return getName().substring(0, 1);
	}
	
	public List<Player> getTeamMembers() {
		return teamMembers;
	}
	
	public Team setTeamMembers(List<Player> teamMembers) {
		for (Player player : teamMembers) {
			addTeamMember(player);
		}
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public Team addTeamMember(Player player) {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		if (scoreboard == null)
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		org.bukkit.scoreboard.Team t = scoreboard.getTeam(getScoreboardName());
		if (t == null)
			t = scoreboard.registerNewTeam(getScoreboardName());
		t.setCanSeeFriendlyInvisibles(true);
		t.setAllowFriendlyFire(false);
		t.setPrefix(getName() + " §8|" + getColor() + " ");
		t.setSuffix("§r");
		t.addPlayer(player);
		player.setDisplayName(t.getPrefix() + player.getName());
		getTeamMembers().add(player);
		return this;
	}
	
	public List<Player> getMembers() {
		return members;
	}
	
	public void setMembers() {
		members.clear();
		members.addAll(teamMembers);
	}
	
	@SuppressWarnings("deprecation")
	public void setOldTeamMembers() {
		for (Player player : members) {
			if (!player.isOnline()) return;
			Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
			if (scoreboard == null)
				scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
			org.bukkit.scoreboard.Team t = scoreboard.getTeam(getScoreboardName());
			if (t == null)
				t = scoreboard.registerNewTeam(getScoreboardName());
			t.setCanSeeFriendlyInvisibles(true);
			t.setAllowFriendlyFire(false);
			t.setPrefix(getName() + " §8|" + getColor() + " ");
			t.setSuffix("§r");
			t.addPlayer(player);
			player.setDisplayName(t.getPrefix() + player.getName());
		}
	}
	
	public Team removeTeamMember(Player p) {
		if (getTeamMembers().contains(p))
			getTeamMembers().remove(p);
		return this;
	}
	
	public boolean isFull() {
		return getTeamMembers().size() >= new ConfigLocationUtil(plugin, "TeamSize").getMaxTeamSize();
	}
	
	public boolean isEmpty() {
		return getTeamMembers().size() == 0;
	}

	@Override
	public int compareTo(Team team) {
		return getName().compareTo(team.getName());
	}
	
	public static List<Team> getTeams() {
		return teams;
	}
	
	public static Team getTeamByColorName(String colorName) {
		return teams.stream().filter(team -> team.getName().contains(colorName)).collect(Collectors.toList()).get(0);
	}
	
}
