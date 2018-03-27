package com.turbulence6th;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TicTacToe {

	private int[][] table;

	private List<Position> spaces;

	public TicTacToe(int[][] table) {
		this.table = table;
		this.spaces = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (table[i][j] == 0) {
					spaces.add(new Position(i, j));
				}
			}
		}
	}

	public Position play() {
		if (isFinished(table, spaces)) {
			return null;
		}
		
		// Heuristic Approach
		if(spaces.size() == 9) {
			return spaces.get(0);
		}
		
		return minimax(1, table, spaces, Integer.MIN_VALUE, Integer.MAX_VALUE).getMove();
	}
	
	public Node minimax(int player, int[][] table, List<Position> spaces, int alpha, int beta) {
		if(isFinished(table, spaces)) {
			return new Node(getWinner(table));
		}
		
		Node score = null, temp = null;
		for(Position position : spaces) {
			
			int[][] playedTable = clone(table);
			playedTable[position.getI()][position.getJ()] = player;
			List<Position> remainingSpaces = clone(spaces);
			remainingSpaces.remove(position);
			
			if(player == 1) {
				temp = minimax(-1, playedTable, remainingSpaces, alpha, beta);
				
				if(temp.getWinner() > alpha) {
					 alpha = temp.getWinner();
					 score = temp;
					 score.setMove(position);
				}
			}
			
			else if(player == -1) {
				temp = minimax(1, playedTable, remainingSpaces, alpha, beta);
				
				if(temp.getWinner() < beta) {
					 beta = temp.getWinner();
					 score = temp;
					 score.setMove(position);
				}
			}
		}
		
		return score != null ? score : temp;
	}

	private static int getRowsWinner(int[][] table) {

		for (int i = 0; i < 3; i++) {
			if (table[i][0] != 0 && table[i][0] == table[i][1] && table[i][1] == table[i][2]) {
				return table[i][0];
			}
		}

		return 0;

	}

	private static int getColumnsWinner(int[][] table) {

		for (int j = 0; j < 3; j++) {
			if (table[0][j] != 0 && table[0][j] == table[1][j] && table[1][j] == table[2][j]) {
				return table[0][j];
			}
		}

		return 0;

	}

	private static int getDiagonalWinner(int[][] table) {

		if (table[0][0] != 0 && table[0][0] == table[1][1] && table[1][1] == table[2][2]) {
			return table[0][0];
		}

		else if (table[0][2] != 0 && table[0][2] == table[1][1] && table[1][1] == table[2][0]) {
			return table[0][2];
		}

		return 0;

	}

	private static int getWinner(int[][] table) {

		int winner = getRowsWinner(table);
		if (winner != 0) {
			return winner;
		}

		winner = getColumnsWinner(table);
		if (winner != 0) {
			return winner;
		}

		return getDiagonalWinner(table);

	}

	private static boolean isFinished(int[][] table, List<Position> spaces) {
		int winner = getWinner(table);
		if (winner != 0) {
			return true;
		}

		return spaces.isEmpty();
	}

	private static int[][] clone(int[][] table) {
		return Arrays.stream(table).map(e -> e.clone()).toArray(int[][]::new);
	}
	
	private static List<Position> clone(List<Position> states) {
		return new LinkedList<>(states);
	}

}
