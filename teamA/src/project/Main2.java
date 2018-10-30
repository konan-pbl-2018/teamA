package project;

import java.awt.Color;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Sprite;
import framework.gameMain.BaseScenarioGameContainer;
import framework.gameMain.SimpleRolePlayingGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
import template.PRG2D.MapStage;
import template.PRG2D.Player;
import template.PRG2D.ScenarioGameContainer;

public class Main2 extends SimpleRolePlayingGame {//���O���@Main2�@�ɂ��Ă܂��B
	int Right = 1,Down = 2,Left = 3,Up = 4,Direction = 0;
	private MapStage map;
	private Player player;
	private Sprite king;
	private Sprite enemy;
	private Enemy enemy2;

	// ���x�ɂ���ĕ��̂������Ă��鎞�Ƀ{�^���������邩�ǂ����𔻒肷��t���O
	private boolean disableControl = false;

	@Override
	public void init(Universe universe) {
		map = new MapStage();
		universe.place(map);
		camera.addTarget(map);

		// �v���C���[�̔z�u
		player = new Player("data\\RPG\\player.png");
		player.setPosition(14.0, 14.0);
		player.setCollisionRadius(0.5);
		universe.place(player);

		// ���l�̔z�u
		king = new Sprite("data\\RPG\\king.png");
		king.setPosition(18.0, 24.0);
		king.setCollisionRadius(0.5);
		universe.place(king);

		// �G�̔z�u
		enemy2 = new Enemy("data\\RPG\\tegakitree.png");
		enemy2.setPosition(18.0, 10.0);
		enemy2.setCollisionRadius(0.5);
		universe.place(enemy2);

		// �v���C���[����ʂ̒�����
		setCenter(player);

		// �V�i���I�̐ݒ�
		setScenario("data\\TemplateRPG\\Scenario\\scenario2.xml");
	}

	@Override
	public void subInit(Universe universe) {
		enemy = new Sprite("data\\RPG\\monster.png", 10.0f);
		enemy.setPosition(15.0, 15.0);
		universe.place(enemy);

		// �G����ʂ̒�����
		setSubCenter(enemy);
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
				Direction = Left;
				System.out.println("��"+ Direction + "�ł��B");
				//disableControl = true;
			}
			// �E
			if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				//player.setVelocity(4.0, 0.0);
				player.moveRight(4.0);
				Direction = Right;
				//disableControl = true;
			}
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				//player.setVelocity(0.0, 4.0);
				player.moveUp(4.0);
				Direction = Up;
			}
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				//player.setVelocity(0.0, -4.0);
				player.moveDown(4.0);
				Direction = Down;
				//disableControl = true;
			}
			//��
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_A)) {
				//�e�𔭎�
			}
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_B)) {
				if(enemy2.checkCollision(player)) {
					System.out.println("knife");
				}
				//�i�C�t�ōU��
			}
			enemy2.enemyMove(player.getPosition());

		player.motion(interval,map);//���R�Ɉړ��ł��Ȃ�
		enemy2.motion(interval,map);

		/*if(enemy2.checlCollision(tama)){
		 	enemy2.HP=enemy2.enemyDamage(power)
		 }*/

		if(enemy2.checkCollision(player)) {
			System.out.println("Direction");
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
