package template.PRG2D;

import java.awt.Color;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Position2D;
import framework.game2D.Sprite;
import framework.gameMain.BaseScenarioGameContainer;
import framework.gameMain.SimpleRolePlayingGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;

public class TemplateRPG2D extends SimpleRolePlayingGame {
	Sprite snake;
	Sprite Heart;
	private MapStage map;
	private Player player;
	private Sprite king;
	private Sprite enemy;

	// ���x�ɂ���ĕ��̂������Ă��鎞�Ƀ{�^���������邩�ǂ����𔻒肷��t���O
	private boolean disableControl = false;

	@Override
	public void init(Universe universe) {
		map = new MapStage();
		universe.place(map);
		camera.addTarget(map);


		Heart = new Sprite("data\\Life\\Heart.png");
		Heart.setPosition(18.0, 24.0);
		Heart.setCollisionRadius(0.5);
		universe.place(Heart);

		snake = new Sprite("data\\enemy\\snake.gif");
		snake.setPosition(18.0, 10.0);
		snake.setCollisionRadius(0.5);
		universe.place(snake);

		// �v���C���[�̔z�u
		player = new Player("data\\RPG\\player.png");
		player.setPosition(14.0, 14.0);
		player.setCollisionRadius(0.5);
		universe.place(player);

//<<<<<<< HEAD
		 //���l�̔z�u
//=======

		// ���l�̔z�u
//>>>>>>> branch 'master' of https://github.com/konan-pbl-2018/teamA.git
		king = new Sprite("data\\RPG\\king.png");
		king.setPosition(18.0, 24.0);
		king.setCollisionRadius(0.5);
		universe.place(king);

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
		frame.setTitle("Template for 2D Role Playing Game");
		frame.setBackground(Color.BLACK);
		return frame;
	}

	@Override
	protected RWTContainer createRWTContainer() {
		container = new ScenarioGameContainer();
		return container;
	}

	// �퓬�p��ʂ̍쐬
	public BaseScenarioGameContainer createSubRWTContainer() {
		subContainer = new FightContainer();
		return subContainer;
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		// ���H�Q�[���X�e�[�W���\������I�u�W�F�N�g�̈ʒu�ƃv���C���[�̈ʒu�����Ƃɑ��x��0�ɂ��邩�ǂ����𒲂ׂ�B
		Position2D gridPoint = map.getNeighborGridPoint(player);

		// ���x��0�ɂ���t���O�������Ă���΁A���x��0�ɂ���
		if (gridPoint != null) {
			player.setPosition(gridPoint);
			player.setVelocity(0.0, 0.0);
			disableControl = false;
		}

		// �L�������ړ����Ă��Ȃ���΁A�L�[����̏������s����B
		if(!disableControl){
			// �L�[����̏���
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				player.setVelocity(-5.0, 0.0);
				disableControl = true;
				player.setImage("data\\RPG\\player.png");
			}
			// �E
			else if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				player.setVelocity(5.0, 0.0);
				disableControl = true;
//<<<<<<< HEAD

//=======
				player.setImage("data\\RPG\\player.png");
//>>>>>>> branch 'master' of https://github.com/konan-pbl-2018/teamA.git
			}
			// ��
			else if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				player.setVelocity(0.0, 5.0);
				disableControl = true;
				player.setImage("data\\RPG\\king.png");
			}
			// ��
			else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				player.setVelocity(0.0, -5.0);
				disableControl = true;
				player.setImage("data\\RPG\\king.png");
			}
		}
		player.motion(interval, map);

//<<<<<<< HEAD


//=======
//>>>>>>> branch 'master' of https://github.com/konan-pbl-2018/teamA.git
		// �Փ˔���
		if (player.checkCollision(king)) {
			// �v���C���[�Ɖ��l���Ԃ������ꍇ
			scenario.fire("���l�ƂԂ���");	// �u���l�ƂԂ���v�Ƃ����C�x���g�𔭐�����i�V�i���I���i�ށj
		}
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
		SimpleRolePlayingGame game = new TemplateRPG2D();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

}
