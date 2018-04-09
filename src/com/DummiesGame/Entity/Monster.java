package com.DummiesGame.Entity;

import java.awt.Graphics;

import com.DummiesGame.Tool.ImageTool;

public class Monster extends Entity implements Chasable {
	private long lastAction;
	private long lastMove;
	private static final long TARGETTIME = 1000;
	private int damage;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Monster(String _name,int _x,int _y,int _HP,int _MP,int _damage,int _ID) {
		super.setName(_name); 
		super.setX(_x);
		super.setY(_y);
		super.setHP(_HP);
		super.setMP(_MP);
		damage = _damage;
		super.setID(_ID);
	}
	
	public void chase(Player player) {
		if (lastMove == 0)
			lastMove = System.currentTimeMillis();
		int targetX = player.getX();
		int targetY = player.getY();
		int x_monster = super.getX();
		int y_monster = super.getY();
		long wait = System.currentTimeMillis() - lastMove;
		if (wait > TARGETTIME) {
			lastMove = System.currentTimeMillis();
			if (x_monster < targetX) 
				x_monster++;
			else if (x_monster > targetX)
				x_monster--;
			else if (y_monster < targetY)
				y_monster++;
			else if (y_monster > targetY)
				y_monster--;
		}
		super.setX(x_monster);
		super.setY(y_monster);
		
	}
	
	public void attack(Player player) {
		if (lastAction == 0)
			lastAction = System.currentTimeMillis();	
		
		long wait = System.currentTimeMillis() - lastAction;
		if (wait > TARGETTIME) {
			lastAction = System.currentTimeMillis();	
			int MP = super.getMP() - 1;
			super.setMP(MP);
			int hit = (int) getDamage()*MP/100;
			player.getHit(hit);
		}
	}
	
	public void getHit(int hit) {
		int HP = super.getHP() - hit;
		if (HP <= 0 )
			 setHP(0);
		super.setHP(HP);
	}
	public void draw(Graphics g) {
		final int SCALE = 32;
		if (isAlive() == true)
		g.drawImage(ImageTool.getImage(super.getID()), super.getX()*SCALE, super.getY()*SCALE,SCALE,SCALE,null);	
	}
	
	public boolean isAlive() {
		if (super.getHP() > 0)
			return true;
		else 
			return false;
	}
}
