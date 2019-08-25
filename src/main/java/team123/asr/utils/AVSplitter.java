package team123.asr.utils;

import java.io.File;

public interface AVSplitter {
    /**
     * The "splitAudioFromVideo" method takes a video file and creates a new, audio only version of that file.
     * @param videoFile: The name of the video file.
     * @return: The name of the audio only file.
     */
    public String splitAudioFromVideo(String videoFile);
}
