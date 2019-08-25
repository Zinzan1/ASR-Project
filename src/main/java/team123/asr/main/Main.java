package team123.asr.main;
//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE.md file in the project root for full license information.
//

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import team123.asr.recognition.AzureRecogniser;
import team123.asr.utils.FFmpegCaller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Quickstart: recognize speech using the Speech SDK for Java.
 */
public class Main {

    /**
     * @param args Arguments are ignored in this sample.
     */
    public static void main(String[] args) {
        try {
            Thread t1 = new Thread() {
                public void run() {
                    try {
                        AzureRecogniser.recognitionWithAudioStreamAsync();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            };

            CommandLineParser parser = new DefaultParser();
//            Thread t2 = new Thread() {
//                public void run() {
//                    try {
//                        temp.recognitionWithAudioStreamAsync();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            t2.start();
            t1.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
//        FFmpegCaller.FfmpegSegmentAudio("testaudio.wav", "testaudio2.wav", 0, 240);
//        FFmpegCaller.FFmpegSplitVideoFromAudio("test.mp4", "random.wav");

    }
}
