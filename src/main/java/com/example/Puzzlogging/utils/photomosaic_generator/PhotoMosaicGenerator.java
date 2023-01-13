package com.example.Puzzlogging.utils.photomosaic_generator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class PhotoMosaicGenerator {

    public File generatePhotoMosaic(Long memberId) {

        try {
            System.out.println("Generate Start");
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("cmd /c cd ./photomosaic_generator && python photomosaic_generator.py --res 20 --tile 10 --member " + memberId);

            printStream(process);
            System.out.println(process.pid() + " Generate Finished");
            return new File("./photomosaic_generator/" + memberId + "/mosaic.jpg");

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


    public File mkMemberDir(Long memberId) {

        String memberDirPath = "." + File.separator + "photomosaic_generator"  + File.separator +  memberId;
        File memberDir = new File(memberDirPath);

        if (!memberDir.exists()) {
            try {
                memberDir.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
                throw new RuntimeException(e);
            }
        }

        mkMemberMainImageDir(memberDirPath);
        mkMemberFillerImagesDir(memberDirPath);

        return memberDir;
    }

    private void mkMemberFillerImagesDir(String memberDirPath) {
        File memberFillerImagesDir = new File(memberDirPath  + File.separator +  "filler_images");

        if (!memberFillerImagesDir.exists()) {
            try {
                memberFillerImagesDir.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private void mkMemberMainImageDir(String memberDirPath) {
        File memberMainImageDir = new File(memberDirPath  + File.separator +  "main_image");

        if (!memberMainImageDir.exists()) {
            try {
                memberMainImageDir.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
