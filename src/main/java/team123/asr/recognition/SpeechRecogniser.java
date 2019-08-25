package team123.asr.recognition;

import team123.asr.utils.Transcription;

public interface SpeechRecogniser {
    /**
     * The recogniseFromFile method takes an audio file and transcribes it.
     * @param audioFile: The full path of the audio file to be transcribed.
     * @return: The transcription in String format.
     */
    public String recogniseFromFile(String audioFile);

    /**
     * The recogniseWithTimestamps takes an audio file and transcribes it with timestamps included.
     * @param audioFile: The full path of the audio file to be transcribed.
     * @return: An array of type Transcription that represents a transcribed word with its timestamp.
     */
    public Transcription[] recogniseWithTimestamps(String audioFile);
}
