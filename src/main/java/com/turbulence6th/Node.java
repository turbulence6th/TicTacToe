package com.turbulence6th;

public class Node implements Comparable<Node> {

	private int winner;
	
	private Position move;
	
	public Node(int winner) {
		this.winner = winner;
	}
	
	@Override
	public int compareTo(Node node) {
		return winner - node.winner;
	}
	
	@Override
	public String toString() {
		return String.format("[winner: %d, move: %s]\n", winner, move);
	}
	
	public int getWinner() {
		return winner;
	}
	
	public void setMove(Position move) {
		this.move = move;
	}
	
	public Position getMove() {
		return move;
	}
	
}
