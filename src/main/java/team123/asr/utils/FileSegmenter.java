package team123.asr.utils;

import java.io.File;

public interface FileSegmenter {
    /**
     * The "segmentIntoMultipleFiles" method takes in the name of an audio file (with extension) and segments it based on the strategy chosen
     * @param audioFile: The name of the audio file that shoud be split.
     * @param segType: The segmentation strategy.
     * @return: An array of type String that contains all the names of the files that were created from splitting the original.
     */
    public String[] segmentIntoMultipleFiles(String audioFile, SegmentationType segType);
}
