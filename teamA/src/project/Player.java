package project;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;


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


	public ArrayList<Bullet> shootDanmaku() {
		ArrayList<Bullet> BulletList = new ArrayList<Bullet>();
		for (int i = 0; i < MAX_DANMAKU; i++) {
			Bullet = new Bullet("data\\images\\弾.png");

			bulletX = BULLET_DISTANCE
					* (Math.cos(i * (2 * Math.PI / MAX_DANMAKU)));
			bulletY = BULLET_DISTANCE
					* (Math.sin(i * (2 * Math.PI / MAX_DANMAKU)));

			// 弾の位置
			Bullet.setPosition(this.getPosition());
			// 弾の移動ベクトル
			Bullet.setVelocity(new Velocity2D(bulletX * 5, bulletY * 5));

			BulletList.add(Bullet);
		}

		return BulletList;
	}
	public int playerDamage(int power) {
		this.HP-=power;
		return this.HP;
	}
}
