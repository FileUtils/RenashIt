package com.manulaiko.renashit.launcher;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manulaiko.renashit.launcher.arguments.*;
import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.filesystem.Directory;
import com.manulaiko.tabitha.utils.ArgumentParser;

/**
 * Main class.
 * ===========
 *
 * Bootstrap the application and that shit.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Main
{
    /**
     * Application version.
     */
    public static final String version = "1.0.1";

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args)
    {
        // Parse arguments
        ArgumentParser ap = new ArgumentParser(args);

        ap.add(new Help());
        ap.add(new Debug());
        ap.add(new Recursive());
        ap.add(new Unique());
        ap.add(new Algorithm());
        ap.add(new Path());
        ap.add(new Expression());

        ap.parse();

        Console.println("FileUtils/RenashIt v"+ Main.version +" by Manulaiko");
        Console.println();

        Console.println("Scanning `"+ Settings.path.getAbsolutePath() +"`...");
        List<com.manulaiko.tabitha.filesystem.File> files = Main.scanDir(Settings.path);
        Console.println("Successfully scanned `"+ Settings.path.getAbsolutePath() +"` with "+ files.size() +" files!");

        Console.println("Calculating hashes and removing duplicates (if specified)...");
        Map<String, String> hashes = Main.calculateHashes(files);
        Console.println("Successfully calculated "+ hashes.size() +" hashes!");

        Console.println("Renaming files...");
        hashes.forEach(Main::rename);
        Console.println("Done!");
    }

    /**
     * Scans given directory.
     *
     * @param path Path to directory.
     *
     * @return File list.
     */
    private static List<com.manulaiko.tabitha.filesystem.File> scanDir(File path)
    {
        if(!path.isDirectory()) {
            Console.println("`"+ path.getAbsolutePath() +"` is not a directory!");

            System.exit(0);
        }

        Directory dir;
        List<com.manulaiko.tabitha.filesystem.File> files = new ArrayList<>();

        try {
            dir = new Directory(path.getAbsolutePath());
        } catch(Exception e) {
            Console.println("Couldn't scan directory!");
            Console.debug(e.getMessage());

            if(Settings.debug) {
                e.printStackTrace();
            }

            return files;
        }

        Console.debug("Scanning `"+ path.getAbsolutePath() +"`...");

        files.addAll(dir.getFilesByRegexp(Settings.pattern));

        if(!Settings.recursive) {
            Console.debug(files.size() +" files found!");

            return files;
        }

        dir.listDirectories().forEach((d)->files.addAll(Main.scanDir(new File(d.path))));

        Console.debug(files.size() +" files found!");

        return files;
    }

    /**
     * Calculates and returns hashes.
     *
     * @param files Files to calculate.
     *
     * @return Array with Checksum => Path.
     */
    private static Map<String, String> calculateHashes(List<com.manulaiko.tabitha.filesystem.File> files)
    {
        Map<String, String> hashes = new HashMap<>();
        int deleted = 0;

        for(com.manulaiko.tabitha.filesystem.File f : files) {
            File   file = new File(f.path);
            String hash = Main._getHash(file);

            if(hash.isEmpty()) {
                continue;
            }

            if(!hashes.containsKey(hash)) {
                Console.debug("Hash for `"+ file.getAbsolutePath() +"`: "+ hash);

                hashes.put(hash, file.getAbsolutePath());

                continue;
            }

            if(Settings.unique) {
                Console.debug(file.getAbsoluteFile() +" is duplicated!");

                if(file.delete()) {
                    deleted++;
                    Console.debug("Deleted!");
                }
            }
        }

        Console.debug("Deleted "+ deleted +" duplicated files!");

        return hashes;
    }

    /**
     * Calculates and returns a file hash.
     *
     * @param file File to calculate.
     *
     * @return File hash.
     */
    private static String _getHash(File file)
    {
        try {
            Settings.algorithm.update(Files.readAllBytes(file.toPath()));

            byte[] hash = Settings.algorithm.digest();

            return DatatypeConverter.printHexBinary(hash);
        } catch(Exception e) {
            Console.println("Couldn't calculate hash for `"+ file.getAbsolutePath() +"`!");
            Console.debug(e.getMessage());

            if(Settings.debug) {
                e.printStackTrace();
            }
        }

        return "";
    }

    /**
     * Renames a file.
     *
     * @param hash File hash.
     * @param path Path to file.
     */
    private static void rename(String hash, String path)
    {
        try {
            com.manulaiko.tabitha.filesystem.File f = new com.manulaiko.tabitha.filesystem.File(path);
            java.nio.file.Path p = Paths.get(path);

            String newPath = p.getParent().toAbsolutePath() + File.separator + hash.toLowerCase() +"."+ f.extension;

            Files.move(p, p.resolveSibling(newPath));

            Console.debug(path +" -> "+ newPath);
        } catch(Exception e) {
            Console.println("Couldn't rename `"+ path +"`!");
            Console.debug(e.getMessage());

            if(Settings.debug) {
                e.printStackTrace();
            }
        }
    }
}
