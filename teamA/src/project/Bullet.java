package project;

import framework.game2D.Sprite;


public class Bullet extends Sprite{

	public Bullet(String imageFile) {
		super(imageFile);
	}

	public boolean isInScreen(double viewRangeWidth, double viewRangeHeight) {
		if (this.getPosition().getX() < viewRangeWidth
				&& this.getPosition().getX() > -1.0 * viewRangeWidth / 2.0) {
			if (this.getPosition().getY() < viewRangeHeight
					&& this.getPosition().getY() > -1.0 * viewRangeHeight / 2.0) {
				return true;
			}
		}
		return false;
	}
}

