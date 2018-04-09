package com.DummiesGame.Entity;

import java.awt.Graphics;

import com.DummiesGame.Tool.ImageTool;

public class Item extends Entity {
	
	public Item(String name,int x, int y, int HP, int MP,int ID) {
		super.setName(name); 
		super.setX(x);
		super.setY(y);
		super.setHP(HP);
		super.setMP(MP);
		super.setID(ID);
	}
	public boolean isAlive() {
		if (super.getHP() > 0)
			return true;
		else 
			return false;
	}
	
	public void draw(Graphics g) {
		final int SCALE = 32;
		if (isAlive() == true)
		g.drawImage(ImageTool.getImage(super.getID()), super.getX()*SCALE, super.getY()*SCALE,SCALE,SCALE,null);
	}
}
