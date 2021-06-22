package mc322.lab07.model;

public class Player {
	
	private String team;

	private String name;
	
	private int score;
	
	
	public Player(String team, int score, String name){
		this.team = team;
		this.score = score;
		this.name = name;
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
}
