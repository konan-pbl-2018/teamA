package project;

import framework.game2D.Sprite;


public class Player extends Sprite{
	//体力
	int HP=100;
	// 弾の座標
	double bulletX, bulletY;
	// 弾幕の最大数
	public final int MAX_DANMAKU = 32;
	// 弾の発射時の自機からの位置
	private final int BULLET_DISTANCE = 1;
	private Bullet Bullet;

	//プレイヤーの向き
	/*
	public enum way{
		up,
		right,
		down,
		left
	}
	*/
	//Main.way hou=Main.way.up;

	public Player(String imageFile) {
		super(imageFile);
		super.setCollisionRadius(0.5);
	}



	public int playerDamage(int power) {
		this.HP-=power;
		if(this.HP<0) this.HP=0;
		return this.HP;

	}

	public void muki(Main.way hou) {
		if(hou==Main.way.up) {
			this.setImage("data\\player\\up.png");
		}
		if(hou==Main.way.right) {
			this.setImage("data\\player\\right.png");
		}
		if(hou==Main.way.down) {
			this.setImage("data\\player\\down.png");
		}
		if(hou==Main.way.left) {
			this.setImage("data\\player\\left.png");
		}

	}



}
