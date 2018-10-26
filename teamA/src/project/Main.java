package project;

import java.awt.Color;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
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
	private Bullet bullet;

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

		//�e
		bullet=new Bullet("data\\images\\�e.png");
		bullet.setPosition(18, 10);
		bullet.setCollisionRadius(0.5);
		universe.place(bullet);//�Ƃ肠�����z�u���Ă܂�


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
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				//player.setVelocity(-4.0, 0.0);
				player.moveLeft(4.0);
				//disableControl = true;
			}
			// �E
			if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				//player.setVelocity(4.0, 0.0);
				player.moveRight(4.0);
				//disableControl = true;

			}
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				//player.setVelocity(0.0, 4.0);
				player.moveUp(4.0);
			}
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				//player.setVelocity(0.0, -4.0);
				player.moveDown(4.0);
				//disableControl = true;
			}
			//��
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_A)) {

				//�e�𔭎�
			}
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_B)) {
				if(enemy.checkCollision(player)) {//�����_�ł̓v���C���[�ƂԂ���������Ă��ƂɂȂ��Ă�
					System.out.println("knife");
				}
				//�i�C�t�ōU��
			}
			enemy.enemyMove(player.getPosition());

		player.motion(interval,map);//���R�Ɉړ��ł��Ȃ���肠��
		enemy.motion(interval,map);

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
