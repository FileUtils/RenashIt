package com.manulaiko.renashit.launcher;

import com.manulaiko.renashit.launcher.arguments.*;
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
    public static final String version = "0.0.0";

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args)
    {
        ArgumentParser ap = new ArgumentParser(args);

        ap.add(new Help());
        ap.add(new Debug());
        ap.add(new Recursive());
        ap.add(new Unique());
        ap.add(new Algorithm());
        ap.add(new Path());
        ap.add(new Expression());

        ap.parse();
    }
}
