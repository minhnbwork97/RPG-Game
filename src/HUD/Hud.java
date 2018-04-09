package HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.DummiesGame.Entity.Player;

public class Hud {
	private static final int HEIGHT = 16;
	private static final int SCALE = 32;
	public static void draw(Player player,Graphics g) {
		Font myFont1 = new Font("Serif", Font.BOLD, 16);
		g.setFont(myFont1);
		g.setColor(Color.RED);
		g.drawString(player.getName(),30, HEIGHT*SCALE + 20);
		g.drawString("HP",0,HEIGHT*SCALE + 42);
		int HP = player.getHP();
		int MP = player.getMP();	
		g.fillRect(30,HEIGHT*SCALE + 34,HP,8);
		g.setColor(Color.BLUE);		
		g.drawString("MP",0,HEIGHT*SCALE + 58);
		g.fillRect(30,HEIGHT*SCALE + 50,MP,8);
		Font myFont2 = new Font("Serif", Font.BOLD, 12);
		g.setFont(myFont2);
		g.setColor(Color.ORANGE);
		g.drawString("Press R to REPLAY", 256, HEIGHT*SCALE + 14);
		g.drawString("Press ESC to QUIT",256 ,HEIGHT*SCALE + 28);
		g.drawString("Press SPACE to attack monster",256,HEIGHT*SCALE + 42);	
	}
}
