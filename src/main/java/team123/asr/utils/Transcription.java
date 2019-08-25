package team123.asr.utils;

public class Transcription {
    private String _word;
    private int _timestampInSeconds;

    public Transcription(String word, int timestamp){
        _word = word;
        _timestampInSeconds = timestamp;
    }

    public String getWord() {
        return _word;
    }

    public int getVolumeInDecibels() {
        return _timestampInSeconds;
    }
}
