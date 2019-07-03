package team123.asr;
//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE.md file in the project root for full license information.
//

import java.util.ArrayList;
import java.util.List;

/**
 * Quickstart: recognize speech using the Speech SDK for Java.
 */
public class Main {

    /**
     * @param args Arguments are ignored in this sample.
     */
    public static void main(String[] args) {
        try {
            AzureRecogniser.recognitionWithAudioStreamAsync();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.exit(0);
    }
}
