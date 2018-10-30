package project;

import java.util.ArrayList;

import framework.game2D.Sprite;
import framework.game2D.Velocity2D;


public class Player extends Sprite{
	//�̗�
	int HP=3;
	// �e�̍��W
	double bulletX, bulletY;
	// �e���̍ő吔
	public final int MAX_DANMAKU = 32;
	// �e�̔��ˎ��̎��@����̈ʒu
	private final int BULLET_DISTANCE = 1;
	private Bullet Bullet;

	//�v���C���[�̌���
	int playerDirection;

	public Player(String imageFile) {
		super(imageFile);
		super.setCollisionRadius(0.5);
	}


	public ArrayList<Bullet> shootDanmaku() {
		ArrayList<Bullet> BulletList = new ArrayList<Bullet>();
		for (int i = 0; i < MAX_DANMAKU; i++) {
			Bullet = new Bullet("data\\images\\�e.png");

			bulletX = BULLET_DISTANCE
					* (Math.cos(i * (2 * Math.PI / MAX_DANMAKU)));
			bulletY = BULLET_DISTANCE
					* (Math.sin(i * (2 * Math.PI / MAX_DANMAKU)));

			// �e�̈ʒu
			Bullet.setPosition(this.getPosition());
			// �e�̈ړ��x�N�g��
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
