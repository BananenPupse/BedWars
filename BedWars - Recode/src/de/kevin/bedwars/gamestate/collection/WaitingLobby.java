package de.kevin.bedwars.gamestate.collection;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import de.kevin.bedwars.Main;
import de.kevin.bedwars.gamestate.GameState;
import de.kevin.bedwars.listeners.WaitingLobbyListener;

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
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(all -> {
					all.setLevel(timer);
					all.setExp((float) timer / 60F);
				});
				if (Bukkit.getOnlinePlayers().size() >= 3)
				
				if (timer <= 0) {
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
