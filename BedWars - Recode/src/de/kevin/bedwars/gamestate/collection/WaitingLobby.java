package de.kevin.bedwars.gamestate.collection;

import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import de.kevin.bedwars.Main;
import de.kevin.bedwars.gamestate.GameState;
import de.kevin.bedwars.listeners.WaitingLobbyListener;
import de.kevin.bedwars.others.Team;

public class WaitingLobby extends GameState {
	
	BukkitTask maintask;
	Listener listener;
	int timer = 60;
	
	public WaitingLobby(Main plugin) {
		super(plugin);
		listener = new WaitingLobbyListener(plugin, this);
	}

	@Override
	public int getID() {
		return WAITING_LOBBY;
	}

	@Override
	public void start() {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
		maintask = new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(all -> {
					all.setLevel(timer);
					all.setExp((float) timer / 60F);
				});
				if (Bukkit.getOnlinePlayers().size() >= 3) {
					switch (timer) {
					case 5:
					case 30:
					case 20:
					case 10:
					case 3:
					case 2:
					case 1:
						Bukkit.getOnlinePlayers().forEach(all -> all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1));
						break;
					default:
						break;
					}
					timer--;
				}
				
				if (timer <= 0) {
					if (Bukkit.getOnlinePlayers().size() < 3) {
						Bukkit.broadcastMessage(plugin.PREFIX + "§cEs sind nicht genügend Spieler zum Starten der Runde online!");
						timer = 30;
						return;
					}
					Bukkit.getOnlinePlayers().forEach(all -> all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1));
					Bukkit.getOnlinePlayers().stream().filter(all -> all.getScoreboard().getPlayerTeam(all).getName().contains("_")).collect(Collectors.toList()).forEach(noteam -> {
						for (Team team : Team.getTeams()) {
							if (team.isFull()) continue;
							if (Bukkit.getOnlinePlayers().size() < 5 && team.getMembers().size() > 1) continue;
							team.addTeamMember(noteam);
						}
					});
					this.cancel();
					plugin.getGameStateManager().setGameState(GameState.INGAME);
					return;
				}
				timer--;
			}
		}.runTaskTimer(plugin, 20, 20);
	}

	@Override
	public void stop() {
		HandlerList.unregisterAll(listener);
	}

}
