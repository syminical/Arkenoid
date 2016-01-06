
//theworldisquiethere

import javax.swing.*;
import java.awt.*;

public class Witchcraft extends JPanel {

	private final int bSize = 20, maxX = 500, maxY = 300, boxesX = 25, boxesY = 15, origin = 10;
	public static int tick = 15;
	private boolean gameRunning = false, firstClick = false, fpsToggle = false, f = false, p = false;
	private int fps = 0, direction = -1;
	private double clock = 0;
	private Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 30);
	private Font manaFont = new Font("Comic Sans MS", Font.BOLD, 20);
	private int[][] grid = new int[20][20];
	private Paddle paddle = new Paddle();

	public Witchcraft() {

		setBackground(Color.BLACK);

	}

	public void start() {

		double start = 0, end = start, totalTime = 0, totalFrames = 0, tracker = 0, sleepTime = 0, fUpdate = 0, tUpdate = 0, holder;

		clock = System.currentTimeMillis();

		while (true) {

			start = System.currentTimeMillis();
/*
			if (start - tUpdate >= 1000 / tick) {

				updateBorder();
				move();
				tUpdate = System.currentTimeMillis();

			}
*/
			if (start - fUpdate >= ((gameRunning)? 1000/30 : 1000/10)) {

				repaint();
				totalFrames++;
				fUpdate = System.currentTimeMillis();

			}

			end = System.currentTimeMillis();

			totalTime += (end - start);

			sleepTime = (1000 / ((gameRunning)? 30 : 10) - (end - start));

			if (sleepTime <= 0)
	
				tracker += (-1) * sleepTime;

			else {

				if (tracker > 0) {

					if (sleepTime <= tracker) {

						tracker -= sleepTime;

						sleepTime = 0;

					} else {

						sleepTime -= tracker;

						tracker = 0;

					}

				}

				try {

					Thread.sleep((int)sleepTime);
			
				} catch(InterruptedException e) {

					System.out.println("[sleep fail]");

				};

			}

			totalTime += (System.currentTimeMillis() - end);

			if (totalTime >= 1000) {

				fps = (int)totalFrames;

				totalFrames = 0;
				totalTime = 0;
				end = 0;
				tracker = 0;

			}

		}

	}

	private void drawGrid(Graphics g) {

		g.setColor(new Color(0x212121));

		for (int i = 0; i <= boxesX; i++)

			g.drawLine((i * bSize) + origin, origin, (i * bSize) + origin, maxY + origin);

		for (int i = 0; i <= boxesY; i++)

			g.drawLine(origin, (i * bSize) + origin, maxX + origin, (i * bSize) + origin);

		g.fillRect(0, 0, origin, origin);
		g.fillRect(maxX + origin, 0, origin, origin);
		g.fillRect(maxX + origin, maxY + origin, origin, origin);
		g.fillRect(0, maxY + origin, origin, origin);

	}

	private void drawFps(Graphics g) {

		g.setColor(Color.WHITE);

		g.drawString("fps: [" + fps + "] | tick: [" + tick + "]", 15, maxY + (2 * origin));

	}

	private void drawBlocks(Graphics g) {

		drawGrid(g);
		paddle.draw(g);

	}

	public void paint(Graphics g) {

		super.paint(g);

		drawBlocks(g);
		drawFps(g);

	}

}
