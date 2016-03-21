import interfaces.IFilter;

import java.util.ArrayList;
import java.util.List;

import preprocessors.LineCreator;
import processing.core.PImage;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MinimProcessor extends ProcessingFrameWrapper {

	private Minim minim;
	private AudioPlayer groove;
	private List<IFilter> filters = new ArrayList<IFilter>();

	@Override
	public void setup() {
		filters.add(new LineCreator());

		minim = new Minim(this);
		groove = minim.loadFile("groove.mp3", 400); // hier das file angeben
													// und wie viel bytes
													// nacheinander
													// eingelesen werden
		groove.play();
	}

	/**
	 * Hier zeichnen. Google nach Processing 3.0 f�r die Zeichenbefehle (sowas
	 * wie 'line' oder 'ellipse').
	 */
	@Override
	public void draw() {
		// PImage image = new PImage(400, 400, PImage.RGB);
		PImage image = new PImage(400, 400, PImage.ARGB);
		filters.forEach(f -> f.compute(image, groove.bufferSize() - 1,
				groove.left, groove.right));
		image(image, 0, 0);
		// saveFrame("frames/img###.png");

		loadPixels();
	}

	@Override
	public void keyPressed() {
		if (key == 'l')
			groove.loop();
	}
}
