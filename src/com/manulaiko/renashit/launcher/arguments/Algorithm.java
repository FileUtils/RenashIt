package com.manulaiko.renashit.launcher.arguments;

import com.manulaiko.renashit.launcher.Settings;
import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Algorithm argument.
 * ===================
 *
 * Sets hashing algorithm.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Algorithm extends Argument
{
    /**
     * Checks that the argument can be handled
     * by this instance.
     *
     * @param arg Argument.
     *
     * @return `true` if the argument can be handled by this instance, `false` if not.
     */
    @Override
    public boolean canHandle(String arg)
    {
        return (
                arg.equalsIgnoreCase("-a") ||
                arg.equalsIgnoreCase("--algorithm")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        String algorithm = this.value().toLowerCase();

        switch(algorithm)
        {
            case "md5":
                Settings.algorithm = Settings.getAlgorithm(Settings.MD5);
                break;

            case "sha1":
            case "sha-1":
                Settings.algorithm = Settings.getAlgorithm(Settings.SHA1);
                break;

            case "sha256":
            case "sha-256":
                Settings.algorithm = Settings.getAlgorithm(Settings.SHA256);
                break;

            default:
                Console.println("`"+ algorithm +"` isn't a valid algorithm! Use '-h' for a list of algorithms.");
                Console.println("Using MD5 instead.");
        }

        Console.debug(Settings.algorithm.getAlgorithm() +" algorithm will be used!");
    }
}
