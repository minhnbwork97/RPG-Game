package com.DummiesGame.Map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import com.DummiesGame.Entity.GoodMonster;
import com.DummiesGame.Entity.Item;
import com.DummiesGame.Entity.Monster;


public class Map {
	
	private String name;
	private int ID;
	
	private ArrayList<Square> squares= new ArrayList<Square>();
	private LinkedList<Monster> monsters = new LinkedList<Monster>();
	private LinkedList<Item> items = new LinkedList<Item>();
	
	
	public ArrayList<Square> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}

	public LinkedList<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(LinkedList<Monster> monsters) {
		this.monsters = monsters;
	}

	public LinkedList<Item> getItems() {
		return items;
	}

	public void setItems(LinkedList<Item> items) {
		this.items = items;
	}

	public Map(String _name,int _ID) {
		setName(_name);
		setID(_ID);
		loadMap();
	}
	
	public void loadMap() {
		final int WIDTH = 16;
		System.out.println("Load Map");
		try {
			 FileInputStream fstream = new FileInputStream("Maps/"+ name + ".txt");
			 DataInputStream in = new DataInputStream(fstream); 
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));
			 String line;
			 // doc vao monster
			 while ((line = br.readLine()) != null) {
				 if (line.equalsIgnoreCase("[MAPCODE]"))
					 break;
				 String[] value = line.split(" ");
				 String _name = value[0];
				 int x = Integer.parseInt(value[1]);
				 int y = Integer.parseInt(value[2]);
				 int HP = Integer.parseInt(value[3]);
				 int MP = Integer.parseInt(value[4]);
				 int hitPoint = Integer.parseInt(value[5]);
				 int ID = Integer.parseInt(value[6]);
				 Monster m = new Monster(_name,x,y,HP,MP,hitPoint,ID);
				 monsters.add(m);
				 System.out.println("Monster : " + monsters.size());
			 }
			 while ((line = br.readLine()) != null) {
				 if (line.equalsIgnoreCase("[GOODMONSTER]"))
					 break;
				 String[] value = line.split(" ");
				 String _name = value[0];
				 int x = Integer.parseInt(value[1]);
				 int y = Integer.parseInt(value[2]);
				 int HP = Integer.parseInt(value[3]);
				 int MP = Integer.parseInt(value[4]);
				 int hitPoint = Integer.parseInt(value[5]);
				 int ID = Integer.parseInt(value[6]);
				 Monster sw =  new GoodMonster(_name,x,y,HP,MP,hitPoint,ID);
				 monsters.add(sw);
				 System.out.println("Monster : " + monsters.size());
			 }
			 int y0 = 0;
			 // doc vao map code
			 while ((line = br.readLine())!= null) {
				 if (line.equalsIgnoreCase("[ITEM]")) 
					 break;
				 String[] value = line.split(" ");
				 for (int x0 = 0; x0 < WIDTH; x0++) {
					 int squareID = Integer.parseInt(value[x0]);
					 Square square = new Square(squareID,x0,y0);
					 squares.add(square);
				 }
				 y0++;
			 }
			 
			 // doc vao item
			 while ((line = br.readLine()) != null) {
				String[] value = line.split(" ");
				String _name = value[0];
				int x = Integer.parseInt(value[1]);
				int y = Integer.parseInt(value[2]);
				int HP = Integer.parseInt(value[3]);
				int MP = Integer.parseInt(value[4]);
				int ID = Integer.parseInt(value[5]);
				Item item = new Item(_name,x,y,HP,MP,ID);
				items.add(item);
			 }
			 
			 in.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Read the file: "+ name + ".txt failed");
		}
	}
	public void drawMap(Graphics g) {
		for (Square s: squares) 
			s.draw(g);
	}
	
	public Map nextMap(int squareID) {
		if (squareID == 51) {
			Map map1 = new Map("map1",51);
			return map1;
		}
		if (squareID == 52) {
			Map map1 = new Map("map2",52);
			return map1; 
		}
		if (squareID == 53) {
			Map map1 = new Map("map3",53);
			return map1;
		}
		return null;
	}
	// lay ra tat ca quai vat trong map
	public LinkedList<Monster> getAllMonster() {
		return monsters;
	}
	public LinkedList<Item> getAllItem() {
		return items;
	}
	
	public Square getSquare(int _x, int _y) {
		final int WIDTH = 16;	
		int index = _y*WIDTH + _x;
		return squares.get(index);
	}
	public String getName() {
		return name;
	}

	public void setName(String _name) {
		name = _name;
	}
	public int getID() {
		return ID;
	}
	
	public void setID(int _ID) {
		ID = _ID;
	}
}
