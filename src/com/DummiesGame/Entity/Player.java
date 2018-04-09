package com.DummiesGame.Entity;

import java.awt.Graphics;

import com.DummiesGame.Map.Map;
import com.DummiesGame.Map.Square;
import com.DummiesGame.Tool.ImageTool;

public class Player extends Entity {
	
	private int damage;
	private long lastAction;
	private static final long TARGETTIME = 1000;
	private static final int MAXHP = 300;
	private static final int MAXMP = 300;
	
	public static int getMaxhp() {
		return MAXHP;
	}

	public static int getMaxmp() {
		return MAXMP;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public static long getTargettime() {
		return TARGETTIME;
	}

	public Player(String _name,int _x,int _y,int _HP,int _MP,int _damage,int _ID) {
		super.setName(_name); 
		super.setX(_x);
		super.setY(_y);
		super.setHP(_HP);
		super.setMP(_MP);
		this.damage = _damage;
		super.setID(_ID);
	}
	
	
	public void move(int code,Map map) {
		// TODO Auto-generated method stub
		final int UP = 1;
		final int DOWN = 2;
		final int LEFT = 3;
		final int RIGHT = 4;
		final int WIDTH = 16;
		final int HEIGHT = 16;
	
		if (code == UP)
		{
			int x0 = super.getX();
			int y0 = super.getY() - 1;
			if (y0 < 1)
					y0 = 0;
			Square square = map.getSquare(x0,y0);
			if (square.isCollison() == false) {
				super.setX(x0);
				super.setY(y0);
			}	
		}
		else if (code == DOWN) 
		{
			int x0 = super.getX();
			int y0 = super.getY() + 1;
			if (y0 >= HEIGHT) 
				 y0 = HEIGHT - 1;
			Square square = map.getSquare(x0,y0);
			if (square.isCollison() == false) {
				super.setX(x0);
				super.setY(y0);
			}	
		}
		else if (code == LEFT)
		{
			int x0 = super.getX() - 1;
			int y0 = super.getY();
			if (x0 < 1) 
				x0 = 0;
			Square square = map.getSquare(x0,y0);
			if (square.isCollison() == false) {
				super.setX(x0);
				super.setY(y0);
			}	
		}
		else if (code == RIGHT)
		{
			int x0 = super.getX() + 1;
			int y0 = super.getY();
			if (x0 >= WIDTH) 
				x0 = WIDTH - 1;
			Square square = map.getSquare(x0,y0);
			if (square.isCollison() == false) {
				super.setX(x0);
				super.setY(y0);
			}	
		}
	}
	
	public void attack(Monster monster) {
		if (lastAction == 0)
			lastAction = System.currentTimeMillis();		
		long wait = System.currentTimeMillis() - lastAction;
		if (wait > getTargettime()) {
			lastAction = System.currentTimeMillis();	
			int MP = super.getMP() - 20;
			super.setMP(MP);
			if (MP < 0) 
				setMP(0);
			if (MP > 0)
				monster.getHit(getDamage());
		}		
	}
	
	public void getHit(int hit) {
		int HP = super.getHP() - hit;
		if (HP <= 0)
			setHP(0);
		else if(HP >=getMaxhp()) {
			super.setHP(getMaxhp());
		}
		else super.setHP(HP);
	}
	
	public void heal(Item item) {
		int HP = super.getHP() + item.getHP();
		int MP = super.getMP() + item.getMP();
		if(HP > getMaxhp()) {
			super.setHP(getMaxhp());
		}
		else {
		super.setHP(HP);
		}
		if(MP > getMaxmp()) {
			super.setHP(getMaxmp());
		}
		else {
		super.setMP(MP);
		}
	}
	
	public boolean isAlive() {
		if (super.getHP() > 0)
			return true;
		else 
			return false;
	}
	
	public void draw(Graphics g) {
		final int SCALE = 32;
		g.drawImage(ImageTool.getImage(11), super.getX()*SCALE, super.getY()*SCALE,SCALE,SCALE,null);	
	}

	
}
