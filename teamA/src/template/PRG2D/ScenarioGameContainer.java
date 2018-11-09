package template.PRG2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTBar;
import framework.RWT.RWTLabel;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;
import framework.gameMain.BaseScenarioGameContainer;

/**
 * �V�i���I�Q�[���p���
 * @author Nitta
 *
 */
public class ScenarioGameContainer extends BaseScenarioGameContainer {

	private RWTBar life;

	public ScenarioGameContainer() {
		super();
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		super.build(gc);
		canvas.setRelativePosition(0.0f, 0.0f);		// 3D�\�����̍���[
		canvas.setRelativeSize(0.75f, 1.0f);		// 3D�\�����̃T�C�Y
		addCanvas(canvas);
		dialog.setRelativePosition(0.75f, 0.75f);	// �_�C�A���O�̍���[
		dialog.setFont(new Font("", Font.PLAIN, 12));	// �����̃t�H���g
		dialog.setColor(Color.WHITE);				// �����̐F
		addWidget(dialog);
		life = new RWTBar(100, 100);
		life.setRelativePosition(0.8f, 0.1f);
		life.setRelativeSize(0.18f, 0.02f);
		addWidget(life);
		RWTLabel lifeStr = new RWTLabel(0.8f, 0.09f, "�̗�", Color.WHITE, new Font("MS����", Font.BOLD + Font.ITALIC, 13));
		addWidget(lifeStr);
		repaint();
	}


	public void gagechange(int Plife) {
	}





	@Override
	public void keyPressed(RWTVirtualKey key) {
	}

	@Override
	public void keyReleased(RWTVirtualKey key) {
		if (key.getPlayer() == 0 && key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			scenario.fire("A");
		}
	}

	@Override
	public void keyTyped(RWTVirtualKey key) {
	}

	public void changeLifeBar(int value) {
		life.setValue(value);
		repaint();
	}

}
