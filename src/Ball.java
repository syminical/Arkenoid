
//theworldisquiethere

public class Ball extends Block {

	private int velX = 0, velY = -1;

	public Ball(int container, int container2) {

		super(container, container2);

	}

	public void invX() {

		velX = -velX;

	}

	public void invY() {

		velY = -velY;

	}

	public void xL() {

		velX = -1;

	}

	public void xM() {

		velX = 0;

	}

	public void xR() {

		velX = 1;

	}
	
	public void move() {

		x += velX;
		y -= velY;

	}

	public boolean up() {

		if (velY > 0) return true; else return false;

	}

	public boolean right() {

		if (velX > 0) return true; else return false;

	}

	public boolean down() {

		if (velY < 0) return true; else return false;

	}

	public boolean left() {

		if (velX < 0) return true; else return false;

	}

}
