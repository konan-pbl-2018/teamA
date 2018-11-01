package project;

import framework.game2D.Sprite;


public class Player extends Sprite{
	//体力
	int HP=3;
	// 弾の座標
	double bulletX, bulletY;
	// 弾幕の最大数
	public final int MAX_DANMAKU = 32;
	// 弾の発射時の自機からの位置
	private final int BULLET_DISTANCE = 1;
	private Bullet Bullet;

	//プレイヤーの向き
	int playerDirection;

	public Player(String imageFile) {
		super(imageFile);
		super.setCollisionRadius(0.5);
	}



	public int playerDamage(int power) {
		this.HP-=power;
		return this.HP;
	}
}
