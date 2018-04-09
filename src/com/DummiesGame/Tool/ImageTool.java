package com.DummiesGame.Tool;

import java.awt.Image;

public class ImageTool {
	private static final int IMAGE_MAX = 100;
	
	private static final int LAND = 1;
	private static final int TREE = 2;
	private static final int BIGTREE = 3;
	private static final int SMALLTREE = 4;
	private static final int WATER = 5;
	private static final int BRIDGE = 6;
	private static final int GATE = 7;
	private static final int WOOD = 8;
	private static final int STONE = 9;
	private static final int LAVA = 10;
	
	private static final int ITEM1 = 61;
	private static final int ITEM2 = 62;
	private static final int ITEM3 = 63;
	
	private static final int MAP1 = 51;
	private static final int MAP2 = 52;
	private static final int MAP3 = 53;
	
	private static final int PLAYER1 = 11;
	
	private static final int MONSTER1 = 41;
	private static final int MONSTER2 = 42;
	private static final int MONSTER3 = 43;
	private static final int MONSTER4 = 44;
	private static final int MONSTER5 = 45;
	private static final int MONSTER6 = 46;
	private static final int MONSTER7 = 47;
	private static final int DRAKE = 48;
	private static final int PRINCESS = 49;
	//
	private static Image[] image = new Image[IMAGE_MAX];
	public static void loadImages() {
		image[LAND] 	= Handler.getImage("Images/Land.png");
		image[TREE] 	= Handler.getImage("Images/Tree.png");
		image[BIGTREE] 	= Handler.getImage("Images/Bigtree.png");
		image[SMALLTREE] 	= Handler.getImage("Images/Smalltree.png");
		image[WATER] 	= Handler.getImage("Images/Water.png");
		image[BRIDGE] 	= Handler.getImage("Images/Bridge.png");
		image[GATE] 	= Handler.getImage("Images/Gate.png");
		image[WOOD] 	= Handler.getImage("Images/Wood.png");
		image[STONE] 	= Handler.getImage("Images/Stone.png");
		image[LAVA] 	= Handler.getImage("Images/Lava.png");
		image[PLAYER1] 	= Handler.getImage("Images/Player1.png");
		image[MONSTER1] = Handler.getImage("Images/Monster1.jpg");
		image[MONSTER2] = Handler.getImage("Images/Monster2.png");
		image[MONSTER3] = Handler.getImage("Images/Monster2.png");
		image[MONSTER4] = Handler.getImage("Images/Monster4.png");
		image[MONSTER5] = Handler.getImage("Images/Monster5.png");
		image[MONSTER6] = Handler.getImage("Images/Monster6.png");
		image[MONSTER7] = Handler.getImage("Images/Monster8.jpg");
		image[DRAKE] = Handler.getImage("Images/Drag.png");
		image[PRINCESS] = Handler.getImage("Images/Princess.png");
		image[ITEM1] 	= Handler.getImage("Images/Item1.gif");
		image[ITEM2]	= Handler.getImage("Images/Item2.png");
		image[ITEM3]	= Handler.getImage("Images/Item2.png");
		image[MAP1] =  Handler.getImage("Images/Map.png");
		image[MAP2] =  Handler.getImage("Images/Map.png");
		image[MAP3] =  Handler.getImage("Images/Map.png");
	}
	
	public static Image getImage(int _ID) {
		loadImages();
		return image[_ID];
	}
}
