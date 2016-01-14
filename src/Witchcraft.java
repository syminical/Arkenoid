
//theworldisquiethere

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Witchcraft extends JPanel {

	private final int bSize = 20, maxX = 500, maxY = 300, boxesX = 25, boxesY = 15, origin = 10;
	public static int tick = 15;
	private boolean gameRunning = true, firstClick = false, fpsToggle = false, f = false, p = false, right = false, left = false;
	private int fps = 0, direction = -1, borderColour = 175, keyTracker = 0;
	private double clock = 0;
	private Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 30);
	private Font manaFont = new Font("Comic Sans MS", Font.BOLD, 20);
	private int[][] grid = new int[20][20];
	private Paddle paddle = new Paddle();
	private ArrayList<Brick> bricks = new ArrayList<Brick>();

	public Witchcraft() {

		setBackground(Color.BLACK);

		listeners();

	}

	private void listeners() {

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");	
		this.getActionMap().put("right", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				if (!right) {

					right = true; 

					direction = 0;

				}

			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "notRight");
		this.getActionMap().put("notRight", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				right = false;

				if (left) direction = 1; else direction = -1;

			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		this.getActionMap().put("left", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				if (!left) {

					left = true;

					direction = 1;

				}

			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "notLeft");
		this.getActionMap().put("notLeft", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				left = false;

				if (right) direction = 0; else direction = -1;

			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "test");
		this.getActionMap().put("test", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				bricks.get(0).expire();

			}

		});

	}

	public void start() {

		double start = 0, end = start, totalTime = 0, totalFrames = 0, tracker = 0, sleepTime = 0, fUpdate = 0, tUpdate = 0, holder;

		clock = System.currentTimeMillis();

		bricks.add(new Brick(1, 1));

		while (true) {

			start = System.currentTimeMillis();

			if (start - tUpdate >= 1000 / tick) {

				updateBorder();
				move();
				tUpdate = System.currentTimeMillis();

			}

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

	private void move() {

		if (direction == 0 && paddle.getX() < 21) paddle.right();

		else if (direction == 1 && paddle.getX() > 0) paddle.left();

	}

	private void updateBorder() {

		if (borderColour < 255) borderColour += 20; else borderColour = 75;

	}

	private void drawBorder(Graphics g) {

		int temp = borderColour;
		boolean inv = false;

		for (int i = 0; i < 10; i++) {

			if (temp == 255) inv = false; else if (temp == 75) inv = true;

			g.setColor(new Color(temp, temp, temp));

			g.drawLine(0, (origin - i - 1), (2 * origin + maxX), (origin - i - 1));
			g.drawLine((origin + maxX + i + 1), 0, (origin + maxX + i + 1), (2 * origin + maxY));
			g.drawLine(0, (origin + maxY + i + 1), (2 * origin + maxX), (origin + maxY + i + 1));
			g.drawLine((origin - i - 1), 0, (origin - i - 1), (2 * origin + maxY));

			if (inv) temp += 20; else temp -= 20;
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

	private void drawBricks(Graphics g) {

		for (Brick temp : bricks)

			temp.draw(g);

	}

	private void drawBlocks(Graphics g) {

		drawBricks(g);
		drawBorder(g);
		//drawGrid(g);
		paddle.draw(g);

	}

	public void paint(Graphics g) {

		super.paint(g);

		drawBlocks(g);
		drawFps(g);

	}

}
