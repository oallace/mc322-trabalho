package chess.player;

import chess.player.nation.ISkill;
import view.Window;

public class Player {
	
	private String team;

	private String name;
	
	private int score;

	private ISkill nation;
	
	
	public Player(String team, int score, String name, ISkill nation){
		this.team = team;
		this.score = score;
		this.name = name;
		this.nation = nation;
	}
	
	public String getTeam() {
		return this.team;
	}
	
	public int getScore() {
		return this.score;
	}

	public String getName()
	{
		return name;
	}
	
	public void scoreChange(int change) {
		this.score += change;
	}

	public void basicSkill(int iTarget, int jTarget)
	{
		nation.basicSkill(iTarget, jTarget);
		scoreChange(-3);
		Window.instance.actualizePlayerScore(name, score);
	}

	public void mainSkill(int iTarget, int jTarget)
	{
		nation.mainSkill(iTarget, jTarget);
		scoreChange(-7);
		Window.instance.actualizePlayerScore(name, score);
	}
}
