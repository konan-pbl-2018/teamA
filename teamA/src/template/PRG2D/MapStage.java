package template.PRG2D;

import java.math.BigDecimal;

import framework.game2D.Map2D;

/**
 * ���H�Q�[���̃X�e�[�W�̃N���X
 * @author T.Kuno
 *
 */
public class MapStage extends Map2D {
	// �R���X�g���N�^
	public MapStage() {
		super(new String[]{
				"data\\RPG\\grass1.jpg",
				"data\\RPG\\tegakitree.png",
				"data\\RPG\\flower.jpg",
				"data\\RPG\\road1.jpg",
				"data\\RPG\\road2.jpg",
				"data\\RPG\\road3.jpg",
				"data\\RPG\\beach.jpg",
				"data\\RPG\\tree.jpg",		// �������牺�͏�Q��
				"data\\RPG\\rockwall.png",
				"data\\RPG\\block.jpg",
				"data\\RPG\\sea1.jpg",
				"data\\RPG\\sea2.jpg"},
		7);
	}

	// ���ۃ��\�b�h�̎���
	//�@0�`6:�ړ��\�@7�ȏ�:��Q���@
	@Override
	public int[][] createMap() {
		int[][] map = {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
