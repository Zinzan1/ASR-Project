package team123.asr.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FFmpegCaller {
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

    public static void FFmpegConvert(String audioFile) {
        //ffmpeg -i input.flac output.wav
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
