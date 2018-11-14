package project;


import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.util.ArrayList;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Velocity2D;
import framework.gameMain.BaseScenarioGameContainer;
import framework.gameMain.IGameState;
import framework.gameMain.SimpleRolePlayingGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
//import template.PRG2D.Player;
import template.PRG2D.ScenarioGameContainer;

public class Main extends SimpleRolePlayingGame {
	private Map map;
	private Player player;
	//private Enemy enemy;
	private ArrayList<Enemy> enemylist = new ArrayList<Enemy>();
	private int lastenemytime = 0;
	//private int enemycount = 0;
	private double enemyrandom;
	private int enemyplace;

	private Bullet Bullet;
	private long lastMyShipBulletShootTime = 0;
	private ArrayList<Bullet> BulletList = new ArrayList<Bullet>();
	private int enemycounter=0;
	private int killcounter=0;

	private int Bossflag=0;
	//way.up   は 0 と同じ
	//way.rightは 1 と同じ
	//way.down は 2 と同じ
	//way.left は 3 と同じ
	public enum way{
		up,
		right,
		down,
		left
	}
	private way Direction=way.up;
	// 速度によって物体が動いている時にボタンを押せるかどうかを判定するフラグ
	//private boolean disableControl = false;





	private IGameState initialGameState = null;
	private IGameState finalGameState = null;

	public  Main(){
		super();
		initialGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				Main.this.frame = frame;
				RWTContainer container = new MainStartContainer(Main.this);
				changeContainer(container);
			}

			@Override
			public boolean useTimer() {
				return false;
			}
			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		finalGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				Main.this.frame = frame;
				RWTContainer container = new MainEndingContainer(Main.this);
				changeContainer(container);
			}
			@Override
			public boolean useTimer() {
				return false;
			}
			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		setCurrentGameState(initialGameState);
	}

	protected void changeContainer(RWTContainer container) {
		// TODO 自動生成されたメソッド・スタブ
		frame.setContentPane(container);
		GraphicsConfiguration gc = null;
		container.build(gc);
	}

	public void restart() {
		stop();
		setCurrentGameState(initialGameState);
		start();
	}

	public void play() {
		stop();
		setCurrentGameState(this);
		start();
	}

	public void ending() {
		stop();
		setCurrentGameState(finalGameState);
		start();
	}






	@Override
	public void init(Universe universe) {

		map = new Map();
		universe.place(map);
		camera.addTarget(map);

		// プレイヤーの配置
		player = new Player("data\\RPG\\player.png");
		player.setPosition(14.0, 20.0);
		player.setCollisionRadius(0.5);
		universe.place(player);
		/* 敵の配置
		enemy = new Enemy("data\\\\images\\\\弾.png");
		enemy.setPosition(18.0, 10.0);
		enemy.setCollisionRadius(0.5);
		universe.place(enemy);
		*/

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
		float playerMoveX = 0, playerMoveY = 0;
		// 左
		if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
			playerMoveX -= 4.0;
			//disableControl = true;
			Direction = way.left;
		}
		// 右
		if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
			playerMoveX += 4.0;
			//disableControl = true;
			Direction = way.right;

		}
		// 上
		if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
			playerMoveY += 4.0;
			Direction = way.up;
		}
		// 下
		if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
			playerMoveY -= 4.0;
			Direction = way.down;
		}

		// 弾の発射
		if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_A)) {
			if (System.currentTimeMillis() - lastMyShipBulletShootTime > 1000) {
				if (Direction != null) {
					Bullet = new Bullet("data\\images\\弾.png");
				}
				Bullet.setPosition(player.getPosition());
				if (Direction == way.up) {
					Bullet.setVelocity(new Velocity2D(0.0, 5.0));
				}
				if (Direction == way.right) {
					Bullet.setVelocity(new Velocity2D(5.0, 0.0));
				}
				if (Direction == way.down) {
					Bullet.setVelocity(new Velocity2D(0.0, -5.0));
				}
				if (Direction == way.left) {
					Bullet.setVelocity(new Velocity2D(-5.0, 0.0));
				}
				universe.place(Bullet);
				BulletList.add(Bullet);
				lastMyShipBulletShootTime = System.currentTimeMillis();
			}
		}

		// プレイヤーの弾を動かす
		for (int i = 0; i < BulletList.size(); i++) {
			Bullet Bullet = BulletList.get(i);
			Bullet.motion(interval); // プレイヤーの弾の移動
			if (Bullet.isInScreen(viewRangeWidth, viewRangeHeight) == false) {
				// プレイヤーの弾を消す
				universe.displace(Bullet);
				BulletList.remove(i);
			}



		}
		if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_B)) {
			/*
			if (enemy.checkCollision(player)) {//現時点ではプレイヤーとぶつかったらってことになってる
				System.out.println("knife");
			}
			*/
			//ナイフで攻撃
		}

		//プレイヤーを移動させる
		player.setVelocity(playerMoveX, playerMoveY);
		player.motion(interval);
		//マップとの当たり判定を調査し、壁にぶつかっていたらプレイヤーの位置を戻す
		if (map.checkCollision(player).isCheckColision()) {
			player.setVelocity(-playerMoveX, -playerMoveY);
			player.motion(interval);
		}

		//敵のスポーン
		lastenemytime++;
		if (lastenemytime > 100) {
			if(enemycounter<10) {
			if (enemylist.size() < 6) {
				Enemy e = new Enemy("data\\images\\Enemy.gif");///////////////////////////////////////

				enemyrandom=Math.random();
				enemyplace=(int) (enemyrandom*4);

				if(enemyplace==1) {
				e.setPosition(14.0, 34.0);
				}
				if(enemyplace==2) {
				e.setPosition(28.0, 18.0);
				}
				if(enemyplace==3) {
				e.setPosition(14.0, 12.0);
				}
				if(enemyplace==4) {
				e.setPosition(2.0, 18.0);
				}
				e.setCollisionRadius(0.5);
				universe.place(e);
				enemylist.add(e);
				lastenemytime = 0;
				enemycounter++;
			}
		}
		}

		//敵を動かす
		for (int j = 0; j < enemylist.size(); j++) {
			Enemy e = enemylist.get(j);
			e.enemyMove(player.getPosition());
			e.motion(interval);
			//迂回動作
			if(map.checkCollision(enemylist.get(j)).isCheckColision()) {
				System.out.println(j+"col");
				e.setVelocity(
						e.getVelocity().getY()-e.getVelocity().getX(),
						-e.getVelocity().getX()-e.getVelocity().getY()
						);
				e.motion(interval);
			}
			//プレイヤーとの接触
			if (e.checkCollision(player)) {
				System.out.println("enemy"+ j +"'s attack");
				((ScenarioGameContainer)container).changeLifeBar(player.playerDamage(1));
			}
			//弾との接触
			for(int i=0;i<BulletList.size();i++) {
				Bullet Bullet = BulletList.get(i);
				if(e.checkCollision(Bullet)) {
					universe.displace(Bullet);
					BulletList.remove(i);
					if(Bossflag==0) {
					e.HP=e.HP-100;
					}
					if(Bossflag==1) {
						e.HP=e.HP-20;
					}
				}
			}
			if(e.HP<=0) {
				universe.displace(e);
				enemylist.remove(j);
				killcounter++;

			}
			System.out.println("撃破数"+killcounter);
		}
		//ボスの出現
		if(killcounter==10) {
			if(Bossflag==0) {
			Enemy e = new Enemy("data\\enemy\\snake.gif");
			e.setPosition(14.0, 18.0);
			universe.place(e);
			enemylist.add(e);
			Bossflag=1;
			}
		}

		/*if(enemy2.checlCollision(tama)){
		 	enemy2.HP=enemy2.enemyDamage(power)
		 }*/



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
