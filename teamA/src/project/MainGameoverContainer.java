package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
import framework.RWT.RWTLabel;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;

public class MainGameoverContainer extends RWTContainer {
	private Main game;

	public MainGameoverContainer(Main game) {
		this.game = game;
	}

	public void build(GraphicsConfiguration gc) {
		RWTLabel startLabel = new RWTLabel();
		startLabel.setString("Gameover");
		startLabel.setColor(Color.WHITE);
		startLabel.setRelativePosition(0.5f, 0.5f);
		Font f = new Font("", Font.PLAIN, 30);
		startLabel.setFont(f);
		addWidget(startLabel);
	}






	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			game.play();
		}
	}

	public void keyReleased(RWTVirtualKey key) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(RWTVirtualKey key) {
		// TODO Auto-generated method stub

	}
}
