package project;

import framework.game2D.Position2D;
import framework.game2D.Sprite;

public class Enemy extends Sprite {
	public Enemy(String imageFile) {
		super(imageFile);
	}
	//‘Ì—Í
	public int HP=100;
	//UŒ‚—Í
	public int power=1;

	public enum way{//“G‚ÌŒü‚«
		up,
		right,
		down,
		left
	}
	way hou=way.up;

	public void enemyMove(Position2D player) {
		float x = 0,y=0;
		if( this.getPosition().getX() < player.getX() ) {
			x = 2;
		}
		if( this.getPosition().getX() > player.getX() ) {
			x = -2;
		}

		if( this.getPosition().getY() < player.getY() ) {
			y = 2;
		}
		if( this.getPosition().getY() > player.getY() ) {
			y = -2;
		}

		this.setVelocity(x, y);
	}
	public int enemyDamage(int power) {
		this.HP-=power;
		return this.HP;
	}
	public int getPower() {
		return this.power;
	}

	public void muki(way hou) {
		if(hou==way.up) {
			this.setImage("data\\images\\ue");
		}
		if(hou==way.right) {
			this.setImage("data\\images\\migi");
		}
		if(hou==way.down) {
			this.setImage("data\\images\\sita");
		}
		if(hou==way.left) {
			this.setImage("data\\images\\hidari");
		}

	}


}
