package team123.asr.utils;

public class VolumeDetector {
    public void generateVolumeInfo() {
        String infoCommand = "ffmpeg -i 777-126732-0005.flac -af astats=metadata=1:reset=1,ametadata=print:key=lavfi.astats.Overall.RMS_level:file=log.txt -f null -";
    }
}
