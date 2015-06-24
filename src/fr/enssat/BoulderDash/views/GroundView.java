package fr.enssat.BoulderDash.views;

import fr.enssat.BoulderDash.exceptions.ModelNotReadyException;
import fr.enssat.BoulderDash.models.LevelModel;

import javax.swing.*;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * FieldView
 *
 * FieldView, created by controller; we notice that we don't need to make
 * levelModel observable; Because of the sprites we have to refresh the game
 * windows very often so don't need of observers/observable mechanism
 *
 * @author Colin Leverger <me@colinleverger.fr>
 * @since 2015-06-19
 *
 *        This view is basically drawing into the Frame the levelModel.
 *
 */
public abstract class GroundView extends JPanel implements Observer {
	protected LevelModel levelModel;

	/**
	 * Class constructor
	 *
	 * @param levelModel
	 *            Level model
	 */
	public GroundView(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.levelModel.addObserver(this);
	}

	/**
	 * Draws the map
	 *
	 * @param width
	 *            Map width
	 * @param height
	 *            Map height
	 * @param g
	 *            Map graphical object
	 */
	public void drawTerrain(int width, int height, Graphics g) {
		// Draw items
		if (this.levelModel.getMode() == "game") {
			if (this.levelModel.isGameRunning()) {
				//DEBUG
				System.out.println(this.levelModel.getMode());
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						g.drawImage(this.levelModel.getImage(x, y), (x * 16), (y * 16), this);
					}
				}
			} else {
				if(this.levelModel.getRockford().getHasExplosed()){
					this.displayLoose();
				} else {
					this.displayWin();
				}
			}
		} else {
			System.out.println(this.levelModel.getMode());
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					g.drawImage(this.levelModel.getImage(x, y), (x * 16), (y * 16), this);
				}
			}
			if (this.levelModel.getShowCursor()) {
	            g.drawImage(
	                    this.levelModel.getCursorImage(),
	                    ((this.levelModel.getCursorXPosition() + 1) * 16),
	                    ((this.levelModel.getCursorYPosition() + 1) * 16),
	                    this
	            );
			}
		}
	}

	/**
	 * Set the view to inform the user that he won
	 */
	private void displayWin() {
		JOptionPane.showMessageDialog(this, "A basic JOptionPane message dialog");
		System.out.println("displayWin");
	}

	/**
	 * Set the view to inform the user that he is not good at this game
	 */
	private void displayLoose() {
		JOptionPane.showMessageDialog(this, "A basic JOptionPane message dialog");
		System.out.println("displayLoose");
	}

	/**
	 * Paints the map
	 *
	 * @param g
	 *            Map graphical object
	 */
	public void paint(Graphics g) {
		this.drawTerrain(this.levelModel.getSizeWidth(), this.levelModel.getSizeHeight(), g);
	}

	/**
	 * Updates the view
	 *
	 * @param obs
	 *            Observable item
	 * @param obj
	 *            Object item
	 */
	@Override
	public void update(Observable obs, Object obj) {
		repaint();
	}
}