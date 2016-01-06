
//theworldisquiethere

import javax.swing.*;
import java.awt.*;

public class Sorcery extends JFrame {

	JPanel atlas = new JPanel();
	Witchcraft magic = new Witchcraft();

	public Sorcery() {

		super("Arkenoid");

		prepareAtlas();

		buildAbox();

	}

	private void prepareAtlas() {

		atlas.setPreferredSize(new Dimension(520, 320));
		atlas.setLayout(new BoxLayout(atlas, BoxLayout.Y_AXIS));
		atlas.add(magic);

	}

	private void buildAbox() {

		setContentPane(atlas);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	
	}

	public static void main(String args[]) {

		Sorcery box = new Sorcery();

	}

}
