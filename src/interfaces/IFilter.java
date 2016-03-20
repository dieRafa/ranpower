package interfaces;

import processing.core.PImage;
import ddf.minim.AudioBuffer;

public interface IFilter {
	void compute(PImage image, int bufferSize, AudioBuffer left,
			AudioBuffer right);
}
