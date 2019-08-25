package team123.asr.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FFmpegCaller implements AVSplitter, FileSegmenter, VolumeDetector{

    @Override
    public String splitAudioFromVideo(String videoFile) {
        String outFileName = videoFile + "AudioOnly";
        FFmpegSplitVideoFromAudio(videoFile, outFileName);
        return outFileName;
    }

    @Override
    public String[] segmentIntoMultipleFiles(String audioFile, SegmentationType segType) {
        String[] namesOfSplitFiles;
        if (segType.equals(SegmentationType.NONE)) {
            namesOfSplitFiles = new String[1];
            namesOfSplitFiles[0] = audioFile;
            return namesOfSplitFiles;
        } else if (segType.equals(SegmentationType.BASIC)) {
            //TODO Implement basic split algorithm
            //FfmpegSegmentAudio(audioFile, );
        } else if (segType.equals(SegmentationType.ADAPTIVE)) {
            //TODO Implement adaptive split algorithm
            //FfmpegSegmentAudio(audioFile, );
        }

        return new String[0];
    }

    @Override
    public AudioFrameInfo[] generateVolumeInfo(String audioFile) {
        //TODO refactor this method to put data inot correct output format
        AudioFrameInfo[] audioInfo = new AudioFrameInfo[0];
        FFmpegGetVolumeInfoFromAudio(audioFile);
        return audioInfo;
    }

    public static void FfmpegSegmentAudio(String inputFileName, String outputFileName, int startTimeSeconds, int endTimeSeconds) {
        String SplitCommand = "ffmpeg -i " + inputFileName  + " -ss " + startTimeSeconds + " -to " + endTimeSeconds + " -c copy " + outputFileName;
        List<String> params = java.util.Arrays.asList("ffmpeg", "-i", inputFileName, "-ss", startTimeSeconds+"", "-to", endTimeSeconds+"", "-c", "copy", outputFileName);
        callCommandDisplayingOutput(params);
    }

    public static void FFmpegSplitVideoFromAudio(String inputFileName, String outputFileName){
        String SeparateCommand = "ffmpeg -i " + inputFileName + " -f wav -vn " + outputFileName;
        List<String> params = java.util.Arrays.asList("ffmpeg", "-i", inputFileName, "-f", "wav", "-vn", outputFileName);
        callCommandDisplayingOutput(params);
    }

    public static void FFmpegConvert(String inputFileName) {
        String SeparateCommand = "ffmpeg -i input.flac output.wav";
        String outputFileName = "output.wav";
        List<String> params = java.util.Arrays.asList("ffmpeg", "-i", inputFileName, outputFileName);
        callCommandDisplayingOutput(params);
    }

    public static void FFmpegGetVolumeInfoFromAudio(String inputFileName){
        String SeparateCommand = "ffmpeg -i 777-126732-0005.flac -af astats=metadata=1:reset=1,ametadata=print:key=lavfi.astats.Overall.RMS_level: -f null -";
        List<String> params = java.util.Arrays.asList("ffmpeg", "-i", inputFileName, "-af", "astats=metadata=1:reset=1,ametadata=print:key=lavfi.astats.Overall.RMS_level:", "-f", null, "-");
        callCommandDisplayingOutput(params);
    }

    private static void callCommandDisplayingOutput(List command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File("D:\\EclipseWorkspace\\toktEZjad\\src\\application\\JadAssets"));
            Process process = pb.start();
            displayOutput(process);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayOutput(Process pr) throws IOException {
        BufferedReader input =
                new BufferedReader(new InputStreamReader(pr.getInputStream()));

        BufferedReader error =
                new BufferedReader(new InputStreamReader(pr.getErrorStream()));

        StringBuilder inputBuilder = new StringBuilder();
        String line = null;
        while ( (line = input.readLine()) != null) {
            inputBuilder.append(line);
            inputBuilder.append(System.getProperty("line.separator"));
        }

        StringBuilder errorBuilder = new StringBuilder();
        String errorline = null;
        while ( (errorline = error.readLine()) != null) {
            errorBuilder.append(errorline);
            errorBuilder.append(System.getProperty("line.separator"));
        }

        String result = inputBuilder.toString();
        String errorResult = errorBuilder.toString();

        System.out.println("Program output: " + result);
        System.out.println("Error output: " + errorResult);
    }
}
