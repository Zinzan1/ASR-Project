package team123.asr.utils;

public class AudioFrameInfo {
    private double audioFrameTimeInSeconds;
    private double volumeInDecibels;

    public AudioFrameInfo(double frameSeconds, double volume){
        audioFrameTimeInSeconds = frameSeconds;
        volumeInDecibels = volume;
    }

    public double getAudioFrameTimeInSeconds() {
        return audioFrameTimeInSeconds;
    }

    public double getVolumeInDecibels() {
        return volumeInDecibels;
    }
}
