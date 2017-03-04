package com.manulaiko.renashit.launcher;

import java.io.File;
import java.util.regex.Pattern;

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
    public static final String SHA1   = "sha1";
    public static final String SHA256 = "sha256";
    public static final String SHA512 = "sha512";

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
    public static String algorithm = Settings.MD5;

    /**
     * Path to parse.
     */
    public static File path = new File("./");

    /**
     * Regular expression.
     */
    public static Pattern pattern = Pattern.compile("(.*)");
}
