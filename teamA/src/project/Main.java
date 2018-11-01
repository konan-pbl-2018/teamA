package project;

import java.awt.Color;
import java.util.ArrayList;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Velocity2D;
import framework.gameMain.BaseScenarioGameContainer;
import framework.gameMain.SimpleRolePlayingGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
import template.PRG2D.Player;
import template.PRG2D.ScenarioGameContainer;

public class Main extends SimpleRolePlayingGame {
	private Map map;
	private Player player;
	private Enemy enemy;
	private Bullet Bullet;
	private long lastMyShipBulletShootTime = 0;
	private ArrayList<Bullet> BulletList = new ArrayList<Bullet>();
	private int Direction=0;
	// ���x�ɂ���ĕ��̂������Ă��鎞�Ƀ{�^���������邩�ǂ����𔻒肷��t���O
	private boolean disableControl = false;

	@Override
	public void init(Universe universe) {
		map = new Map();
		universe.place(map);
		camera.addTarget(map);

		// �v���C���[�̔z�u
		player = new Player("data\\RPG\\player.png");
		player.setPosition(14.0, 14.0);
		player.setCollisionRadius(0.5);
		universe.place(player);
;

		// �G�̔z�u
		enemy = new Enemy("data\\images\\Enemy.gif");
		enemy.setPosition(18.0, 10.0);
		enemy.setCollisionRadius(0.5);
		universe.place(enemy);


		// �v���C���[����ʂ̒�����
		setCenter(player);

		// �V�i���I�̐ݒ�
		setScenario("data\\TemplateRPG\\Scenario\\scenario2.xml");
	}

	@Override
	public void subInit(Universe universe) {
	}

	@Override
	public RWTFrame3D createFrame3D() {
		frame = new RWTFrame3D();
		frame.setSize(1000, 800);
		frame.setTitle("TeamA Project");
		frame.setBackground(Color.BLACK);
		return frame;
	}

	@Override
	protected RWTContainer createRWTContainer() {
		container = new ScenarioGameContainer();
		return container;
	}


	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		// ���H�Q�[���X�e�[�W���\������I�u�W�F�N�g�̈ʒu�ƃv���C���[�̈ʒu�����Ƃɑ��x��0�ɂ��邩�ǂ����𒲂ׂ�B
		//Position2D gridPoint = map.getNeighborGridPoint(player);

		// ���x��0�ɂ���t���O�������Ă���΁A���x��0�ɂ���
		/*if (gridPoint != null) {
			player.setPosition(gridPoint);
			player.setVelocity(0.0, 0.0);
			disableControl = false;
		}*/

		// �L�������ړ����Ă��Ȃ���΁A�L�[����̏������s����B
			// �L�[����̏���
			float playerMoveX=0,playerMoveY=0;
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				playerMoveX-=4.0;
				//disableControl = true;
				Direction=4;
			}
			// �E
			if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				playerMoveX+=4.0;
				//disableControl = true;
				Direction=2;

			}
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				playerMoveY+=4.0;
				Direction=1;
			}
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				playerMoveY-=4.0;
				Direction=3;
			}

			// �e�̔���
			if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_A)) {
				if (System.currentTimeMillis() - lastMyShipBulletShootTime > 1000) {
					if(Direction!=0) {
						Bullet = new Bullet("data\\images\\�e.png");
					}
					Bullet.setPosition(player.getPosition());
					if(Direction==1) {
						Bullet.setVelocity(new Velocity2D(0.0, 5.0));
					}
					if(Direction==2) {
						Bullet.setVelocity(new Velocity2D(5.0, 0.0));
					}
					if(Direction==3) {
						Bullet.setVelocity(new Velocity2D(0.0, -5.0));
					}
					if(Direction==4) {
						Bullet.setVelocity(new Velocity2D(-5.0, 0.0));
					}
					universe.place(Bullet);
					BulletList.add(Bullet);
					lastMyShipBulletShootTime = System.currentTimeMillis();
				}
			}

			// �v���C���[�̒e�𓮂���
			for (int i = 0; i < BulletList.size(); i++) {
				Bullet Bullet = BulletList.get(i);
				Bullet.motion(interval);		// �v���C���[�̒e�̈ړ�
				if (Bullet.isInScreen(viewRangeWidth, viewRangeHeight) == false) {
					// �v���C���[�̒e������
					universe.displace(Bullet);
					BulletList.remove(i);
				}
			}
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_B)) {
				if(enemy.checkCollision(player)) {//�����_�ł̓v���C���[�ƂԂ���������Ă��ƂɂȂ��Ă�
					System.out.println("knife");
				}
				//�i�C�t�ōU��
			}
			enemy.enemyMove(player.getPosition());

		//�v���C���[���ړ�������
		player.setVelocity(playerMoveX, playerMoveY);
		player.motion(interval);
		//�}�b�v�Ƃ̓����蔻��𒲍����A�ǂɂԂ����Ă�����v���C���[�̈ʒu��߂�
		if(map.checkCollision(player).isCheckColision()) {
			player.setVelocity(-playerMoveX, -playerMoveY);
			player.motion(interval);
		}
		enemy.motion(interval);

		/*if(enemy2.checlCollision(tama)){
		 	enemy2.HP=enemy2.enemyDamage(power)
		 }*/

		if(enemy.checkCollision(player)) {
			System.out.println("damage");

		}

		//enemy2.motion(interval,map,player);

		// �Փ˔���
		/*if (player.checkCollision(king)) {
			// �v���C���[�Ɖ��l���Ԃ������ꍇ
			scenario.fire("���l�ƂԂ���");	// �u���l�ƂԂ���v�Ƃ����C�x���g�𔭐�����i�V�i���I���i�ށj
		}*/
	}

	@Override
	public void action(String action, Event event, ScenarioState nextState) {
		// �V�i���I�i�s�ɂ�鐢�E�ւ̍�p�������ɏ���
		if (action.equals("startFight")) {
			changeToSubContainer();
		} else if (action.equals("endFight")) {
			changeToMainContainer();
		}
	}

	/**
	 * �Q�[���̃��C��
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Main game = new Main();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

	@Override
	public BaseScenarioGameContainer createSubRWTContainer() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

}
