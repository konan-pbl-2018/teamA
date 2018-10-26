package project;

import java.math.BigDecimal;

import framework.game2D.Map2D;
import template.PRG2D.Player;

public class Map extends Map2D {
		// �R���X�g���N�^
		public Map() {
			super(new String[]{
					"data\\floor\\grass.png",
					"data\\RPG\\tegakitree.png",
					"data\\floor\\rock-floor.png",
					"data\\Object\\Rock-Object.png",
					"data\\RPG\\road2.jpg",
					"data\\RPG\\road3.jpg",
					"data\\RPG\\beach.jpg",
					"data\\Object\\Rock-Object.png",		// �������牺�͏�Q��
					"data\\Background\\rockwall.png",
					"data\\Background\\rockwall2.png",
					"data\\Object\\BigForest.jpg",
					"data\\RPG\\sea2.jpg"},
			7);
		}

		// ���ۃ��\�b�h�̎���
		//�@0�`6:�ړ��\�@7�ȏ�:��Q���@
		@Override
		public int[][] createMap() {
			int[][] map = {
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
					{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
					{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
					{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
					{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
					{0,0,0,0,0,0,0,0,0,0,0,10,0,0,0,0},
					{0,0,0,0,0,10,10,0,0,0,0,0,10,0,0,0},
					{0,0,0,0,0,0,10,0,0,10,0,0,0,0,0,0},
					{0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0},
					{0,0,2,0,0,10,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			};
			return map;
		}

		public boolean checkGridPoint(Player mazeSpritePlayer) {
			// �ۂߌ덷�����p�ϐ��̐���
			double mazeSpritePositionX = new BigDecimal(mazeSpritePlayer
					.getPosition().getX()).setScale(1, BigDecimal.ROUND_DOWN)
					.doubleValue();
			double mazeSpritePositionY = new BigDecimal(mazeSpritePlayer
					.getPosition().getY()).setScale(1, BigDecimal.ROUND_DOWN)
					.doubleValue();

			// �X�e�[�W�̍\���I�u�W�F�N�g�̈ʒu�ƃv���C���[�̈ʒu���������ǂ��������肷��
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
	}


