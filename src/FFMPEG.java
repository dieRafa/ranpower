import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class FFMPEG {
	ByteBuffer byteBuffer;
	IntBuffer intBuffer;
	private OutputStream video;

	public FFMPEG() {
		video = videoOutputStream("test.avi", 25);
		byteBuffer = ByteBuffer.allocate(400 * 400 * 4);
		intBuffer = byteBuffer.asIntBuffer();
	}

	private OutputStream videoOutputStream(String fname, float fps) {
		try {
			return new ProcessBuilder("ffmpeg", "-f", "rawvideo", // input
																	// format
					"-pix_fmt", "argb", // pixel format
					"-r", fps + "", // frame rate
					"-s", "400x400", // input size (window size)
					"-i", "-", // input (stdin)
					"-y", // force overwrite
					"-qp", "0", // highest quantization quality profile,
					"-c:v", "libx264", fname // output file
			// inherit stderr to catch ffmpeg errors
			).redirectError(ProcessBuilder.Redirect.INHERIT).start()
					.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void write(int[] pixels) {
		try {
			intBuffer.rewind();
			intBuffer.put(pixels);
			video.write(byteBuffer.array());
		} catch (IOException ioe) {
		}
	}
}
