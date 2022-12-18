package com.example.Puzzlogging.utils.photomosaic_generator;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class PhotoMosaicGenerator {

    public File generatePhotoMosaic() {

        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("cmd /c cd ../photomosaic_generator && python photomosaic_generator.py");
            printStream(process);

            return new File("../photomosaic_generator/mosaic.jpg");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printStream(Process process) throws IOException, InterruptedException {
        process.waitFor();
        try (InputStream psout = process.getInputStream()) {
            copy(psout, System.out);
        }
    }

    public void copy(InputStream input, OutputStream output) throws IOException, InterruptedException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }
}
