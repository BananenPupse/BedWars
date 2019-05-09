package de.kevin.bedwars.gamestate;

import de.kevin.bedwars.Main;
import de.kevin.bedwars.gamestate.collection.WaitingLobby;

public class GameStateManager {
	
	@SuppressWarnings("unused")
	private Main plugin;
	private GameState[] gameStates;
	private GameState currentGameState;
	
	public GameStateManager(Main plugin) {
		this.plugin = plugin;
		currentGameState = null;
		gameStates = new GameState[4];
		
		gameStates[GameState.WAITING_LOBBY] = new WaitingLobby(plugin);
//		gameStates[GameState.INGAME] = new Ingame(plugin);
//		gameStates[GameState.ENDING] = new Ending(plugin);
//		gameStates[GameState.SETUP] = new SettingUp(plugin);
	}
	
	public void setGameState(int gameStateID) {
		stopCurrentGameState();
		currentGameState = gameStates[gameStateID];
		currentGameState.start();
	}
	
	public void stopCurrentGameState() {
		if (currentGameState != null)
			currentGameState.stop();
		currentGameState = null;
	}
	
	public GameState getCurrentGameState() {
		return currentGameState;
	}
	
	public boolean isGameState(int gameState) {
		return getCurrentGameState().getID() == gameState;
	}

}
