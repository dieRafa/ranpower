package preprocessors;

import interfaces.IFilter;

import java.awt.Color;

import processing.core.PImage;
import ddf.minim.AudioBuffer;

public class LineCreator implements IFilter {
	// private static ExecutorService threadHandler = Executors
	// .newFixedThreadPool(2); // maybe use
	// Runtime.getRuntime().availableProcessors()

	public static final int PARAM1 = 13; // hat irgendwie einfluss darauf, wie
											// viel schwarz im bild ist

	@Override
	public void compute(PImage image, int bufferSize, AudioBuffer left,
			AudioBuffer right) {
		for (int i = 0; i < image.width; i++) {
			for (int k = 0; k < image.height; k++) {
				int leftIntI = (int) (left.get(i) * 255f);
				int rightIntI = (int) (right.get(i) * 255f);
				int leftIntK = (int) (left.get(k) * 255f);
				int rightIntK = (int) (right.get(k) * 255f);

				leftIntI = leftIntI < 0 ? leftIntI * -1 : leftIntI;
				rightIntI = rightIntI < 0 ? rightIntI * -1 : rightIntI;
				leftIntK = leftIntK < 0 ? leftIntK * -1 : leftIntK;
				rightIntK = rightIntK < 0 ? rightIntK * -1 : rightIntK;

				int mul = leftIntI * leftIntK;

				if (mul != 0 && mul % PARAM1 == 0) {
					int red = leftIntK;
					int green = rightIntI;
					int blue = (leftIntI + rightIntK) / 2;
					image.set(i, k, new Color(red, green, blue).getRGB());
				}
			}
		}
	}
}
