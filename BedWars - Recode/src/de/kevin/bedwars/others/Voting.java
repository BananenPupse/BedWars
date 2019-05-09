package de.kevin.bedwars.others;

import java.util.ArrayList;
import java.util.Collections;

import de.kevin.bedwars.Main;

public class Voting {

	public static final int MIN_MAPS = 1;
	public static final int MAX_MAPS = Map.getMaps().size();
	
	@SuppressWarnings("unused")
	private Main plugin;
	private ArrayList<Map> maps;
	private Map[] votingMaps;
	
	/**
	 * Create a <b>new</b> Voting Instance.
	 * 
	 * @param plugin Main Class
	 * @param maps List of all Maps that are playable
	 */
	public Voting(Main plugin, ArrayList<Map> maps) {
		this.plugin = plugin;
		this.maps = maps;
		setVotingMaps(new Map[MAX_MAPS]);
		chooseRandomMaps();
	}
	
	/**
	 * Use this method to choose maps to vote for.
	 */
	public void chooseRandomMaps() {
		ArrayList<Map> removedMaps = new ArrayList<>();
		for (int i = 0; i < getVotingMaps().length; i++) {
			Collections.shuffle(getMaps());
			getVotingMaps()[i] = getMaps().remove(0);
			removedMaps.add(getVotingMaps()[i]);
		}
		for (Map map : removedMaps) {
			getMaps().add(map);
		}
	}
	
	/**
	 * Use this method to set the new map.
	 * 
	 * @return <b>Map</b> winner map
	 */
	public Map getWinnerMap() {
		Map winner = getVotingMaps()[0];
		for (int i = 1; i < getVotingMaps().length; i++) {
			if (getVotingMaps()[i].getVotes() > winner.getVotes()) winner = getVotingMaps()[i];
		}
		return winner;
	}
	
	/**
	 * Reset the received votes for this map.
	 */
	public void resetVotes() {
		for (Map map : getMaps()) {
			map.setVotes(0);
		}
	}
	
	/**
	 * @return <b>All Maps</b> that you can vote for
	 */
	public Map[] getVotingMaps() {
		return votingMaps;
	}
	
	public ArrayList<Map> getMaps() {
		return maps;
	}
	
	public void setVotingMaps(Map[] votingMaps) {
		this.votingMaps = votingMaps;
	}
	
}
