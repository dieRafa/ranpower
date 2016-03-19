import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PApplet;

public class ProcessingFrameWrapper extends PApplet {

	private JFrame frame;
	private SmoothCanvas canvas;

	/**
	 * Don't override this function, so processing will use an AWT surface
	 * instead of an GL surface!
	 */
	@Override
	public final void settings() {
	}

	/**
	 * Returns the frame of this application. The frame has to be resizable so
	 * it will behave properly!
	 * 
	 * @return The main frame of this application.
	 * @throws Exception
	 *             If the application has not been started there is no Frame to
	 *             get.
	 */
	public JFrame getJFrame() throws Exception {
		if (frame != null) {
			return frame;
		}

		PSurfaceAWT surf = (PSurfaceAWT) getSurface();

		if (surf == null) {
			throw new Exception("Applet not started yet!");
		}

		canvas = (SmoothCanvas) surf.getNative();
		frame = (JFrame) canvas.getFrame();
		frame.setResizable(true);
		return frame;
	}

	/**
	 * Returns the canvas of this application.
	 * 
	 * @return The canvas of this application.
	 * @throws Exception
	 *             If the application has not been started there is no canvas to
	 *             get.
	 */
	public SmoothCanvas getCanvas() throws Exception {
		if (canvas != null) {
			return canvas;
		}

		getJFrame();
		return canvas;
	}

	@Override
	public void runSketch() {
		super.runSketch();
	}

}
