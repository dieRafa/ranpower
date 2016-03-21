/**
 * This sketch demonstrates how to use the <code>loop</code> method of a <code>Playable</code> class. 
 * The class used here is <code>AudioPlayer</code>, but you can also loop an <code>AudioSnippet</code>.
 * When you call <code>loop()</code> it will make the <code>Playable</code> playback in an infinite loop.
 * If you want to make it stop looping you can call <code>play()</code> and it will finish the current loop 
 * and then stop. Press 'l' to start the player looping.
 *
 */

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) throws Exception {
		ProcessingFrameWrapper p = new MinimProcessor();
		p.runSketch();

		JFrame jframe = p.getJFrame();
		SwingUtilities.invokeLater(() -> {
			jframe.setSize(new Dimension(400, 400));
			jframe.setLocationRelativeTo(null);
			try {
				p.getCanvas().requestFocus();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}