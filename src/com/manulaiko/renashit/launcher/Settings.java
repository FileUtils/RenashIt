package com.manulaiko.renashit.launcher;

import java.io.File;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import com.manulaiko.tabitha.Console;

/**
 * Settings class.
 * ===============
 *
 * Application settings.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Settings
{
    ///////////////////////////////
    // Start Constant Definition //
    ///////////////////////////////
    public static final String MD5    = "md5";
    public static final String SHA1   = "sha-1";
    public static final String SHA256 = "sha-256";

    /**
     * Whether we're running in debug mode or not.
     */
    public static boolean debug = false;

    /**
     * Whether we're running in recursive mode or not.
     */
    public static boolean recursive = false;

    /**
     * Whether we're running in unique mode or not.
     */
    public static boolean unique = false;

    /**
     * Hashing algorithm.
     */
    public static MessageDigest algorithm = Settings.getAlgorithm(Settings.MD5);

    /**
     * Path to parse.
     */
    public static File path = new File("./");

    /**
     * Regular expression.
     */
    public static Pattern pattern = Pattern.compile("(.*)");

    /**
     * Returns an instance of a MessageDigest algorithm.
     *
     * @param algo Algorithm to instance.
     *
     * @return MessageDigest instance.
     */
    public static MessageDigest getAlgorithm(String algo)
    {
        try {
            return MessageDigest.getInstance(algo.toUpperCase());
        } catch(Exception e) {
            Console.println(algo +" is not a supported algorithm!");

            System.exit(0);
        }

        return null;
    }
}
