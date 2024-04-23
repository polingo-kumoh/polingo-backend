package com.tangtang.polingo.translate.service.stt;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AudioConverter {
    public void convert3gpToMp3(String sourcePath, String destinationPath) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(
                "ffmpeg",
                "-i", sourcePath,
                "-acodec", "libmp3lame",
                "-ar", "44100",
                destinationPath);

        log.info("builder = {}", builder.command());

        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Conversion failed, exit code: " + exitCode);
        }
    }
}

