import java.awt.Color;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MinimProcessor extends ProcessingFrameWrapper {

	private Minim minim;
	private AudioPlayer groove; 

	@Override
	public void setup() {
		minim = new Minim(this);
		groove = minim.loadFile("groove.mp3", 512); // hier das file angeben
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
		int bufferSize = groove.bufferSize() - 1; // groesse des buffers

		AudioBuffer leftAudio = groove.left; // gibt dir mit .get(index) den
												// float Wert an der Stelle
												// index des linken audios

		AudioBuffer rightAudio = groove.right; // gibt dir mit .get(index) den
												// float Wert an der Stelle
												// index des rechten audios

		background(0); // zeichnet den Hintergrund schwarz (f�r bewegte Bilder
						// zum L�schen, bevor das n�chste Bild gezeichnet wird,
						// also wegnehmen, wenn wir immer ins selbe Bild malen
						// wollen)

		// set setzt ein pixel auf eine farbe
		set(130, 130, new Color(0, 255, 0).getRGB()); // setze pixel an der
														// stelle 130, 130 auf
														// gr�n

		stroke(255, 0, 0); // linienfarbe = rot, alle linien und ellipsen usw
							// werden jetzt rot gezeichnet

		for (int i = 0; i < bufferSize; i++) {

			// line zeichnet eine linie
			line(i, 50 + leftAudio.get(i) * 50, i + 1,
					50 + leftAudio.get(i + 1) * 50);
			line(i, 150 + rightAudio.get(i) * 50, i + 1,
					150 + rightAudio.get(i + 1) * 50);

			// ellipse zeichnet einen kreis
//			ellipse(width / 2 + leftAudio.get(i) * 50,
//					height / 2 + leftAudio.get(i + 1) * 50, 100, 100);
		}
	}

	@Override
	public void keyPressed() {
		if (key == 'l')
			groove.loop();
	}

}
