package com.DummiesGame.GameState;

import java.awt.Image;

public class VictoryState {
	private static int state = 0;
	
	public static void setState(int _state) {
		state = _state;
	}
	
	public static int getState() {
		return state;
	}
	
	public static Image getImage() {
		return null;
		
	}
}
