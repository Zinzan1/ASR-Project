package team123.asr.utils;

import java.util.Map;

public interface VolumeDetector {
    /**
     * This method takes in the name of an audio file and generates audio information at each "frame" of the audio
     * @param audioFile: The full path of the audio file.
     * @return:
     */
    public AudioFrameInfo[] generateVolumeInfo(String audioFile);
}
