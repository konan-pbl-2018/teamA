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

	// 速度によって物体が動いている時にボタンを押せるかどうかを判定するフラグ
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

		// プレイヤーの配置
		player = new Player("data\\RPG\\player.png");
		player.setPosition(14.0, 14.0);
		player.setCollisionRadius(0.5);
		universe.place(player);

		 //王様の配置
		king = new Sprite("data\\RPG\\king.png");
		king.setPosition(18.0, 24.0);
		king.setCollisionRadius(0.5);
		universe.place(king);

		// プレイヤーを画面の中央に
		setCenter(player);

		// シナリオの設定
		setScenario("data\\TemplateRPG\\Scenario\\scenario2.xml");
	}

	@Override
	public void subInit(Universe universe) {
		enemy = new Sprite("data\\RPG\\monster.png", 10.0f);
		enemy.setPosition(15.0, 15.0);
		universe.place(enemy);

		// 敵を画面の中央に
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

	// 戦闘用画面の作成
	public BaseScenarioGameContainer createSubRWTContainer() {
		subContainer = new FightContainer();
		return subContainer;
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		// 迷路ゲームステージを構成するオブジェクトの位置とプレイヤーの位置をもとに速度を0にするかどうかを調べる。
		Position2D gridPoint = map.getNeighborGridPoint(player);

		// 速度が0にするフラグが立っていれば、速度を0にする
		if (gridPoint != null) {
			player.setPosition(gridPoint);
			player.setVelocity(0.0, 0.0);
			disableControl = false;
		}

		// キャラが移動していなければ、キー操作の処理を行える。
		if(!disableControl){
			// キー操作の処理
			// 左
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				player.setVelocity(-4.0, 0.0);
				disableControl = true;
			}
			// 右
			else if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				player.setVelocity(4.0, 0.0);
				disableControl = true;

			}
			// 上
			else if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				player.setVelocity(0.0, 4.0);
				disableControl = true;
			}
			// 下
			else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				player.setVelocity(0.0, -4.0);
				disableControl = true;
			}
		}
		player.motion(interval, map);



		// 衝突判定
		if (player.checkCollision(king)) {
			// プレイヤーと王様がぶつかった場合
			scenario.fire("王様とぶつかる");	// 「王様とぶつかる」というイベントを発生する（シナリオが進む）
		}
	}




	@Override
	public void action(String action, Event event, ScenarioState nextState) {
		// シナリオ進行による世界への作用をここに書く
		if (action.equals("startFight")) {
			changeToSubContainer();
		} else if (action.equals("endFight")) {
			changeToMainContainer();
		}
	}

	/**
	 * ゲームのメイン
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleRolePlayingGame game = new TemplateRPG2D();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

}
