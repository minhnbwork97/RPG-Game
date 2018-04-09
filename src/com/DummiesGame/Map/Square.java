package com.DummiesGame.Map;

import java.awt.Graphics;

import com.DummiesGame.Tool.ImageTool;


public class Square {
	private int x, y;
	private int squareID;
	
	public Square(int _ID, int _x, int _y) {
		setSquareID(_ID);
		setX(_x);
		setY(_y);
	}

	public void setSquareID(int _ID) {
		squareID = _ID;
	}
	
	public int getSquareID() {
		return squareID;
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
	
	public void draw(Graphics g) {
		final int SCALE = 32;
		g.drawImage(ImageTool.getImage(squareID), x*SCALE, y*SCALE,SCALE,SCALE,null);
	}
	
	public boolean isCollison() {
		final int LAND = 1;
		final int BRIDGE = 6;
		final int GATE = 7;
		final int MAP1 = 51;
		final int MAP2 = 52;
		final int MAP3 = 53;
		if (squareID == LAND 	||
			squareID == BRIDGE 	||
			squareID == GATE 	||
			squareID == MAP1 	||
			squareID == MAP2 	||
			squareID == MAP3 	) 
			return false;
		else 
			return true;
	}
	public boolean isWater() {
		final int WATER = 5;
		if(squareID == WATER) 
			return true;
		else 
			return false;
	}
}
