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
