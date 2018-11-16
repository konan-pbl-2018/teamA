package project;

import framework.game2D.Position2D;
import framework.game2D.Sprite;

public class Enemy extends Sprite {
	public Enemy(String imageFile) {
		super(imageFile);
	}
	//体力
	public int HP=100;
	//攻撃力
	public int power=10;
	//スピード
	public int spd=2;

	public enum way{//敵の向き
		up,
		right,
		down,
		left
	}
	way hou=way.up;

	public void enemyMove(Position2D player) {
		float x = 0,y=0;
		if( this.getPosition().getX() < player.getX() ) {
			x = spd;
		}
		if( this.getPosition().getX() > player.getX() ) {
			x = -spd;
		}
		if( this.getPosition().getY() < player.getY() ) {
			y = spd;
		}
		if( this.getPosition().getY() > player.getY() ) {
			y = -spd;
		}

		if(Math.abs(this.getPosition().getX()-player.getX()) > Math.abs(this.getPosition().getY()-player.getY())) {
			y=0;
		}else {
			x=0;
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
