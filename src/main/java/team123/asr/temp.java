package team123.asr;

import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import team123.asr.utils.KeyReader;
import team123.asr.utils.OutputWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class temp {
        private static final String SubscriptionKey = KeyReader.readKeyFromTextFile();
        private static final String ServiceRegion = "australiaeast";
        private static Semaphore stopRecognitionSemaphore;

        // Speech recognition with audio stream
        public static void recognitionWithAudioStreamAsync() throws InterruptedException, ExecutionException, FileNotFoundException
        {
            stopRecognitionSemaphore = new Semaphore(0);

            // Creates an instance of a speech config with specified
            // subscription key and service region. Replace with your own subscription key
            // and service region (e.g., "westus").
            SpeechConfig config = SpeechConfig.fromSubscription(SubscriptionKey, ServiceRegion);

            String clean = "D:\\Uni\\2019\\Sem1\\Software project\\Speech_Corpus\\dev-clean\\LibriSpeech\\dev-clean\\777\\126732\\777-126732-0009.wav";
            String outputClean = "output1.txt";

            // Create an audio stream from a wav file.
            // Replace with your own audio file name.
            AudioConfig audioInput = AudioConfig.fromWavFileInput(clean);

            // Creates a speech recognizer using audio stream input.
            SpeechRecognizer recognizer = new SpeechRecognizer(config, audioInput);
            {
                // Subscribes to events.
                recognizer.recognizing.addEventListener((s, e) -> {
                    System.out.println("RECOGNIZING: Text=" + e.getResult().getText());
                });

                recognizer.recognized.addEventListener((s, e) -> {
                    if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                        System.out.println("RECOGNIZED: Text=" + e.getResult().getText());

                        List<String> list = new ArrayList<String>();
                        list.add(e.getResult().getText());
                        OutputWriter.writeToTxtFile(outputClean, list);
                    }
                    else if (e.getResult().getReason() == ResultReason.NoMatch) {
                        System.out.println("NOMATCH: Speech could not be recognized.");
                    }
                });

                recognizer.canceled.addEventListener((s, e) -> {
                    System.out.println("CANCELED: Reason=" + e.getReason());

                    if (e.getReason() == CancellationReason.Error) {
                        System.out.println("CANCELED: ErrorCode=" + e.getErrorCode());
                        System.out.println("CANCELED: ErrorDetails=" + e.getErrorDetails());
                        System.out.println("CANCELED: Did you update the subscription info?");
                    }

                    stopRecognitionSemaphore.release();
                });

                recognizer.sessionStarted.addEventListener((s, e) -> {
                    System.out.println("\nSession started event.");
                });

                recognizer.sessionStopped.addEventListener((s, e) -> {
                    System.out.println("\nSession stopped event.");

                    // Stops translation when session stop is detected.
                    System.out.println("\nStop translation.");
                    stopRecognitionSemaphore.release();
                });

                // Starts continuous recognition. Uses stopContinuousRecognitionAsync() to stop recognition.
                recognizer.startContinuousRecognitionAsync().get();

                // Waits for completion.
                stopRecognitionSemaphore.acquire();

                // Stops recognition.
                recognizer.stopContinuousRecognitionAsync().get();
            }
        }
}


