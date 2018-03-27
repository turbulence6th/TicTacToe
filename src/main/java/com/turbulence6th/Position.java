package com.turbulence6th;

public class Position {

	private int i;
	
	private int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Position)) {
			return false;
		}
		
		Position p = (Position) o;
		return i == p.i && j == p.j;
	}
	
	@Override
	public String toString() {
		return String.format("[i: %d, j: %d]", i, j);
	}
	
}
