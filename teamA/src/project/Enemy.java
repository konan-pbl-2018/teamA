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
	//Œü‚¢‚Ä‚¢‚é•ûŒü,1‚ªã‚ÅŒv‰ñ‚è‚É2,3,4
	public int way=1;

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

}
