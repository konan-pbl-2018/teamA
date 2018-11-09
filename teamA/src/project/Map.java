package project;

import framework.game2D.Map2D;

public class Map extends Map2D {
		// コンストラクタ
		public Map() {
			super(new String[]{
					"data\\ground\\8.png", //0 s
					"data\\ground\\18.png", //1 s
					"data\\ground\\16.png",  //2
					"data\\ground\\4.png", //3 s
					"data\\RPG\\road2.jpg", //4
					"data\\RPG\\road3.jpg", //5
					"data\\ground\\16.jpg", //6
					"data\\ground\\22.png", //7 ここから下は障害物
					"data\\ground\\6.png" , //8 s
					"data\\ground\\24.png", //9 s
					"data\\ground\\21.png", //10 s
					"data\\ground\\15.png" , //11 s
					"data\\ground\\16.png", //12 s
					"data\\ground\\14.png", //13 s
					"data\\ground\\17.png", //14 s
					"data\\ground\\19.png", //15 s
					"data\\ground\\30.png", //16 s
					"data\\ground\\31.png", //17 s
					"data\\ground\\32.png", //18 s
					},
			7 );
		}
		//あいうえ
		// 抽象メソッドの実装
		//　0～6:移動可能　7以上:障害物　
		@Override
		public int[][] createMap() {
			int[][] map = {
					{16,18,18,18,18,18,18,18,18,18,18,18,18,18,18,17},
					{15,1,1,1,1,10,1,1,1,1,1,1,9,1,1,14},
					{15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,14},
					{15,1,1,1,1,1,1,10,1,1,1,1,1,1,1,14},
					{15,1,1,1,1,1,1,1,1,1,1,10,1,1,1,14},
					{15,1,10,1,1,1,1,1,1,1,1,1,1,1,1,14},
					{15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,14},
					{15,1,1,1,10,10,1,1,1,1,1,1,9,1,1,14},
					{15,1,1,1,10,10,1,1,1,1,1,1,1,1,1,14},
					{15,9,1,1,1,1,1,1,1,1,10,1,1,1,1,14},
					{15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,14},
					{15,1,1,9,1,1,1,1,1,1,1,1,1,10,1,14},
					{15,1,1,1,1,1,1,1,1,1,1,1,1,1,10,14},
					{13,11,11,11,11,11,11,11,11,11,11,11,11,11,11,12},
					{3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0},
					{3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0},
					{3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0},
					{3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0},
					{3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0}
			};
			return map;
		}
		/*
		public boolean checkGridPoint(Player mazeSpritePlayer) {
			// 丸め誤差処理用変数の生成
			double mazeSpritePositionX = new BigDecimal(mazeSpritePlayer
					.getPosition().getX()).setScale(1, BigDecimal.ROUND_DOWN)
					.doubleValue();
			double mazeSpritePositionY = new BigDecimal(mazeSpritePlayer
					.getPosition().getY()).setScale(1, BigDecimal.ROUND_DOWN)
					.doubleValue();

			// ステージの構成オブジェクトの位置とプレイヤーの位置が同じかどうかっ判定する
			for (int i = 0; i < this.getStageObjectList().size(); i++) {
				if (
						mazeSpritePositionX == this.getStageObjectList().get(i).getPosition().getX()
						&& mazeSpritePositionY == this.getStageObjectList().get(i).getPosition().getY()
					){
					return true;
				}

			}
			return false;
		}
		*/
	}


