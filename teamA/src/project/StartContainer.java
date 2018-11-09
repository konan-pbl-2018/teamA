package project;

import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
import framework.RWT.RWTLabel;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;

public class StartContainer extends RWTContainer {
	private Main game;

	public StartContainer(Main game) {
		this.game = game;
	}

	public void build(GraphicsConfiguration gc) {
		RWTLabel startLabel = new RWTLabel();
		startLabel.setString("Start");
		startLabel.setRelativePosition(0.3f, 0.5f);
		Font f = new Font("", Font.PLAIN, 60);
		startLabel.setFont(f);
		addWidget(startLabel);
	}






	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			//game.;
		}
	}

	public void keyReleased(RWTVirtualKey key) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(RWTVirtualKey key) {
		// TODO Auto-generated method stub

	}
}
