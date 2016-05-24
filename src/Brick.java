
//theworldisquiethere

import java.awt.*;

public class Brick extends BlockList {

	private int x, y;
	private boolean expired = false;

	public Brick(int container, int container2) {

		x = container;
		y = container2;

		for (int i = 0; i < 2; i++)

			holder.add(new Block(x + i, y));

	}

	public int getX() {

		return holder.get(0).getX();		

	}

	public int getY() {

		return holder.get(0).getY();		

	}

	public void expire() {

		expired = true;

	}

	public boolean expired() {

		return expired;

	}

	public void draw(Graphics g) {

		for (Block temp : holder)

			if (!expired) temp.draw(g);

	}

}
