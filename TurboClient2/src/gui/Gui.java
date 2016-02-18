package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.IGameStateEngine;
import dto.GameState;
import dto.GameState.PlayerData;
import gui.interfaces.IGui;

public class Gui implements IGui {
	private final class TurboKeyListener implements KeyListener {
		private IGameStateEngine engine;

		public TurboKeyListener(IGameStateEngine engine) {
			this.engine = engine;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("Key Pressed!");
			engine.keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Key Pressed!");
			engine.keyPressed(e);
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Key Pressed!");
			engine.keyPressed(e);
			
		}
	}

	JFrame frame = new JFrame();
	private JPanel panel;
	private JLabel label;
	private  IGameStateEngine engine;
	
	public Gui(IGameStateEngine engine){
		this.engine = engine;
		panel = new JPanel();
		label = new JLabel("test");
		frame.setSize(800, 600);
		frame.add(panel);
		panel.add(label);
		frame.setVisible(true);
		frame.addKeyListener(new TurboKeyListener(engine));
	}

	@Override
	public void drawGameState(GameState gameState) {
		for (PlayerData p : gameState.getPlayers().values())
		label.setText(String.valueOf(p.health));
		panel.repaint();

	}

}
