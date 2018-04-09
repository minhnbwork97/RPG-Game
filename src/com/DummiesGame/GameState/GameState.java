package com.DummiesGame.GameState;

import com.DummiesGame.Entity.Monster;
import com.DummiesGame.Entity.Player;

public class GameState {
	public static int getAttackState(Monster monster,Player player) {
		int xD = Math.abs(monster.getX() - player.getX());
		int yD = Math.abs(monster.getY() - player.getY());
		if (xD <= 1 && yD <= 1) 
			return 2;
		if (xD <= 3 && yD <= 3)
			return 1;
		return 0;
	}
}
