
//theworldisquiethere

import java.awt.*;

public class Paddle extends BlockList {

	public Paddle() {

		for (int i = 0; i < 4; i++)

			holder.add(new Block(11 + i, 12));

	}

	public void r() {

		for (Block temp : holder)

			temp.r();

	}

	public void l() {

		for (Block temp: holder)

			temp.l();

	}

	public void draw(Graphics g) {

		for (Block temp : holder)

			temp.draw(g);

	}

	public int getX() {

		return holder.get(0).getX();

	}

	public int getY() {

		return 12;

	}

}
