package com.DummiesGame.Entity;

public abstract class Entity {
	
	private String name;
	private int HP;
	private int MP;
	private int x, y;
	private int ID;
	
	public int getHP() {
		return HP;
	}

	public int getMP() {
		return MP;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public void setMP(int mP) {
		MP = mP;
	}

	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int _x) {
		x = _x;
	}
	
	public void setY(int _y) {
		y = _y;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int _ID) {
		ID = _ID;
	}
	
}