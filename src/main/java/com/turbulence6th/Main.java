package com.turbulence6th;

public class Main {

	public static void main(String[] args) {

		int[][] table = { 
				{ 1, -1, 0 }, 
				{ 0, 0, 0 }, 
				{ 0, 0, 0 } 
		};

		System.out.println(new TicTacToe(table).play());

	}

}
