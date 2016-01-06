
//theworldisquiethere

import java.awt.*;

public class Block {

	private int colour = 0xffffff, x = 12, y = 7, velX = 0, velY = 0;
	private boolean expired = false;

	public Block(int container, int container2) {

		x = container;
		y = container2;

	}

	public void draw(Graphics g) {

		g.setColor(new Color(colour));

		g.fillRect(x * 20 + 10, y * 20 + 10, 20, 20);

	}

	public void right() {

		if (x < 24) x++;

	}

	public void left() {

		if (x > 0) x--;

	}

	public int getColour() {

		return colour;

	}

	public int getX() {

		return x;

	}

	public int getY() {

		return y;

	}

	public void invX() {

		velX = -velX;

	}

	public void invY() {

		velY = -velY;

	}

	public boolean expired() {

		return expired;

	}

	public void setColour(int container) {

		colour = container;

	}

	public void setLocation(int container, int container2) {

		x = container;
		y = container2;

	}

	public void expire() {

		expired = true;

	}

}
