package it.uniba.dib.sms2324.num15.PronuntiApp.models.external_api.ffmpegkit_api;

import java.io.File;

import com.arthenica.ffmpegkit.FFmpegKit;

public class AudioConverter {
    private static final int SAMPLE_RATE_INPUT = 16000;
    private static final int CHANNEL_CONFIG_INPUT = 1;
    private static final String CODEC_INPUT = "s16le";
    private static final String CODEC_OUTPUT = "libmp3lame";
    private static final String SAMPLE_RATE_OUTPUT = "32k";
    private static final String OVERWRITE = "-y";

    /*public static void convertiAudio(File inputFile, File outputFile) {
        String inputFilePath = inputFile.getAbsolutePath();
        String outputFilePath = outputFile.getAbsolutePath();

        String command = OVERWRITE + " -f " + CODEC_INPUT +
                " -ar " + SAMPLE_RATE_INPUT +
                " -ac " + CHANNEL_CONFIG_INPUT +
                " -i " + inputFilePath +
                " -acodec " + CODEC_OUTPUT +
                " -ab " + SAMPLE_RATE_OUTPUT + " " + outputFilePath;

        FFmpegKit.execute(command);
        FFmpegKit.cancel();
    }*/

    public static File convertiAudio(File inputFile) {
        String filePath = inputFile.getAbsolutePath();

        String command = OVERWRITE + " -f " + CODEC_INPUT +
                " -ar " + SAMPLE_RATE_INPUT +
                " -ac " + CHANNEL_CONFIG_INPUT +
                " -i " + filePath +
                " -acodec " + CODEC_OUTPUT +
                " -ab " + SAMPLE_RATE_OUTPUT + " " + filePath;

        FFmpegKit.execute(command);
        FFmpegKit.cancel();

        return inputFile;
    }

}
