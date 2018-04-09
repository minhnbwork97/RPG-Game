package com.DummiesGame.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.DummiesGame.Entity.*;
import com.DummiesGame.Map.Map;
import com.DummiesGame.Map.Square;
import com.DummiesGame.Tool.ImageTool;

import HUD.Hud;

import com.DummiesGame.GameState.*;



public class GamePanel extends JPanel implements Runnable, KeyListener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread thread;
	
	// loop 
	
	private Player player;
	private Map map;
	
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	private static final int SCALE = 32;
	private static final int HUD = 60;
	
	private static  final int UP = 1;
	private static  final int DOWN = 2;
	private static  final int LEFT = 3;
	private static  final int RIGHT = 4;
	
	private static final int TARGETMONSTER = 12;

	private LinkedList<Monster> monsters = new LinkedList<Monster>();
	private LinkedList<Item> items = new LinkedList<Item>();
	
	private int attackState;
	private boolean running;
	private boolean replay = false;
	private boolean playerAttack;
	private int deadMonster;
	private boolean hasAttack;
	public GamePanel(Player _player, Map _map) {
		player = _player;
		map = _map;
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE + HUD));
		setFocusable(true);
		requestFocus();
		deadMonster = 0;
	}
	
	// initializes fields
	private void init() {
		replay = false;
		running = true;
		playerAttack = false;
		ImageTool.loadImages();
		monsters = map.getAllMonster();
		items = map.getAllItem();
		hasAttack = false;
	}
	
	// ready to display
	public void addNotify() {
			super.addNotify();
			if(thread == null) {
				addKeyListener(this);
				thread = new Thread(this);
				thread.start();
			}
	}
	@Override
	public void run() {
		init();
		deadMonster = 0;
		System.out.println(monsters.size());
		System.out.println(items.size());
		while(running) {
			//next map
			int xP = player.getX();
			int yP = player.getY();
			Square square = map.getSquare(xP, yP);
			if (map.nextMap(square.getSquareID())!= null) {
				map = map.nextMap(square.getSquareID());
				if (xP == 0)
					xP = WIDTH - 2;
				else if (xP == WIDTH - 1)
					xP = 1;
				else if (yP == 0) 
					yP = HEIGHT - 2;
				else if (yP == HEIGHT - 1)
					yP = 1;
				player.setX(xP);
				player.setY(yP);
			}
			
			// attack monster
			monsters = map.getAllMonster();
			items = map.getAllItem();
			for (Monster monster: monsters) {
				if (monster.isAlive() == true) {
					attackState = GameState.getAttackState(monster,player);
					if (attackState == 1) 
						monster.chase(player);					
					if (attackState == 2) {
						monster.attack(player);
						hasAttack = true;
					}
					if (attackState == 2 && playerAttack) {
						player.attack(monster);
						if (monster.isAlive() == false) 
							deadMonster ++;
					}
				}
			}
			
			// pick item
			for (Item item: items) {
				if (item.isAlive() == true) {
					int xT = item.getX() - player.getX();
					int yT = item.getY() - player.getY();
					if (xT == 0 && yT == 0) {
						player.heal(item);
						item.setHP(0);
						item.setMP(0);
					}
				}
			}
			playerAttack = false;
			
			// replay
			if (replay == true) {
				replay = false;
				map = new Map("map1",51);
				player = new Player("Hero",1,1,100,100,50,11);
				deadMonster = 0;
				init();  
				
			}
			
			if (player.getHP() == 0) {
				VictoryState.setState(-1);
			}
			else if (deadMonster >= TARGETMONSTER)
				VictoryState.setState(1);
			else 
				VictoryState.setState(0);

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
			hasAttack = false;
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Hud.draw(player,g);
		if (VictoryState.getState() != 0) {
			g.drawImage(VictoryState.getImage(),0,0,WIDTH,HEIGHT,null);
			Font myFont = new Font("Serif", Font.BOLD, 32);
			g.setFont(myFont);
			g.setColor(Color.RED);
			if (VictoryState.getState() == -1) {
				g.drawString("Ban da that bai",200,200);
			}
			else 
				g.drawString("Ba da Chien Thang", 200,200);
			myFont = new Font("Serif", Font.BOLD, 22);
			g.setFont(myFont);
			g.drawString("Press R to REPLAY", 200,300);
			g.drawString("Press ESC to QUIT",200,330);
		}
		else {
			map.drawMap(g);
			for (Monster monster: monsters) 
					monster.draw(g);

			for (Item item: items)
					item.draw(g);
			player.draw(g);
			if (hasAttack)  {
				Font myFont = new Font("Serif", Font.BOLD, 12);
				g.setFont(myFont);
				g.setColor(Color.RED);
				g.drawString("ZZZZZ", player.getX()*SCALE, player.getY()*SCALE - 20);
			}
		}
		repaint();
	}
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		int i = key.getKeyCode();
		if(i == KeyEvent.VK_UP) {
			player.move(UP,map);
			System.out.println(player.getX() + " " + player.getY());
		}
		else if(i == KeyEvent.VK_LEFT) {
			player.move(LEFT,map);
			System.out.println(player.getX() + " " + player.getY());
		}
		else if(i == KeyEvent.VK_DOWN) {
			player.move(DOWN,map);
			System.out.println(player.getX() + " " + player.getY());
		}
		else if(i == KeyEvent.VK_RIGHT) { 
			player.move(RIGHT,map);
			System.out.println(player.getX() + " " + player.getY());
		}
		else if(i == KeyEvent.VK_SPACE) 
			playerAttack = true;
		else if(i == KeyEvent.VK_ESCAPE) 
			System.exit(0);
		else if(i == KeyEvent.VK_R) 
			replay = true;
	}
	public void keyReleased(KeyEvent key) {
		System.out.println("keyReleased");
	}
}
