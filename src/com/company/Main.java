package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        String pathZip = "D:\\Games\\savegames\\test.zip";
        String path = "D:\\Games\\savegames\\";
        String pathObjects = "D:\\Games\\savegames\\save.dat";
        openZip(path, pathZip);
        openProgress(pathObjects);
    }

    private static void openProgress(String pathObjects) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathObjects))) {
            GameProgress gameProgress = (GameProgress) ois.readObject();
            System.out.printf(gameProgress.toString());
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    private static void openZip(String path, String zip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(path + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
