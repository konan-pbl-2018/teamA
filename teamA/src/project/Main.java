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

	// 速度によって物体が動いている時にボタンを押せるかどうかを判定するフラグ
	private boolean disableControl = false;

	@Override
	public void init(Universe universe) {
		map = new Map();
		universe.place(map);
		camera.addTarget(map);

		// プレイヤーの配置
		player = new Player("data\\RPG\\player.png");
		player.setPosition(14.0, 14.0);
		player.setCollisionRadius(0.5);
		universe.place(player);
;

		// 敵の配置
		enemy = new Enemy("data\\images\\Enemy.gif");
		enemy.setPosition(18.0, 10.0);
		enemy.setCollisionRadius(0.5);
		universe.place(enemy);

		//弾
		bullet=new Bullet("data\\images\\弾.png");
		bullet.setPosition(18, 10);
		bullet.setCollisionRadius(0.5);
		universe.place(bullet);//とりあえず配置してます


		// プレイヤーを画面の中央に
		setCenter(player);

		// シナリオの設定
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
		// 迷路ゲームステージを構成するオブジェクトの位置とプレイヤーの位置をもとに速度を0にするかどうかを調べる。
		//Position2D gridPoint = map.getNeighborGridPoint(player);

		// 速度が0にするフラグが立っていれば、速度を0にする
		/*if (gridPoint != null) {
			player.setPosition(gridPoint);
			player.setVelocity(0.0, 0.0);
			disableControl = false;
		}*/

		// キャラが移動していなければ、キー操作の処理を行える。
			// キー操作の処理
			float playerMoveX=0,playerMoveY=0;
			// 左
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				playerMoveX-=4.0;
				//disableControl = true;
			}
			// 右
			if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				playerMoveX+=4.0;
				//disableControl = true;

			}
			// 上
			if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				playerMoveY+=4.0;
			}
			// 下
			if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				playerMoveY-=4.0;
			}
			//玉
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_A)) {

				//弾を発射
			}
			if(virtualController.isKeyDown(0,RWTVirtualController.BUTTON_B)) {
				if(enemy.checkCollision(player)) {//現時点ではプレイヤーとぶつかったらってことになってる
					System.out.println("knife");
				}
				//ナイフで攻撃
			}
			enemy.enemyMove(player.getPosition());

		//プレイヤーを移動させる
		player.setVelocity(playerMoveX, playerMoveY);
		player.motion(interval);
		//マップとの当たり判定を調査し、壁にぶつかっていたらプレイヤーの位置を戻す
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

		// 衝突判定
		/*if (player.checkCollision(king)) {
			// プレイヤーと王様がぶつかった場合
			scenario.fire("王様とぶつかる");	// 「王様とぶつかる」というイベントを発生する（シナリオが進む）
		}*/
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
		Main game = new Main();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

	@Override
	public BaseScenarioGameContainer createSubRWTContainer() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
