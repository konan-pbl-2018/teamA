package project;

import framework.game2D.Sprite;


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



	public int playerDamage(int power) {
		this.HP-=power;
		return this.HP;
	}
}
