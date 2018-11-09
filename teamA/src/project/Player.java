package project;

import framework.game2D.Sprite;


public class Player extends Sprite{
	//�̗�
	int HP=100;
	// �e�̍��W
	double bulletX, bulletY;
	// �e���̍ő吔
	public final int MAX_DANMAKU = 32;
	// �e�̔��ˎ��̎��@����̈ʒu
	private final int BULLET_DISTANCE = 1;
	private Bullet Bullet;

	//�v���C���[�̌���
	public enum way{
		up,
		right,
		down,
		left
	}
	way hou=way.up;

	public Player(String imageFile) {
		super(imageFile);
		super.setCollisionRadius(0.5);
	}



	public int playerDamage(int power) {
		this.HP-=power;
		if(this.HP<0) this.HP=0;
		return this.HP;

	}

	public void muki(way hou) {
		if(hou==way.up) {
			this.setImage("data\\player\\up");
		}
		if(hou==way.right) {
			this.setImage("data\\player\\right");
		}
		if(hou==way.down) {
			this.setImage("data\\player\\down");
		}
		if(hou==way.left) {
			this.setImage("data\\player\\left");
		}

	}



}
