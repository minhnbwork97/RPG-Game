package com.DummiesGame.Entity;

public class GoodMonster extends Monster{
	private long lastAction;
	private static final long TARGETTIME = 1000;
	public GoodMonster(String _name, int _x, int _y, int _HP, int _MP, int _hitPoint, int _ID) {
		super(_name, _x, _y, _HP, _MP, _hitPoint, _ID);
	}
	
	@Override
	public void chase(Player player) {
		
	}
	
	@Override
	public void attack(Player player) {
		if (lastAction == 0)
			lastAction = System.currentTimeMillis();	
		
		long wait = System.currentTimeMillis() - lastAction;
		if (wait > TARGETTIME) {
			lastAction = System.currentTimeMillis();	
			int MP = super.getMP() - 1;
			super.setMP(MP);
			int hit = (int) getDamage()*MP/100;
			player.getHit(-hit);
		}
}
}
