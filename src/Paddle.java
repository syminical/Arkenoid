
//theworldisquiethere

import java.awt.*;

public class Paddle extends BlockList {

	public Paddle() {

		for (int i = 0; i < 4; i++)

			holder.add(new Block(11 + i, 12));

	}

	public void right() {

		for (Block temp : holder)

			temp.right();

	}

	public void left() {

		for (Block temp: holder)

			temp.left();

	}

	public void draw(Graphics g) {

		for (Block temp : holder)

			temp.draw(g);

	}

}
