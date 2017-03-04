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
                Settings.algorithm = Settings.MD5;
                break;

            case "sha1":
                Settings.algorithm = Settings.SHA1;
                break;

            case "sha256":
                Settings.algorithm = Settings.SHA256;
                break;

            case "sha512":
                Settings.algorithm = Settings.SHA512;
                break;

            default:
                Console.println("`"+ algorithm +"` isn't a valid algorithm! Use '-h' for a list of algorithms.");
                Console.println("Using MD5 instead.");
                this.value("MD5");
        }

        Console.debug(this.value().toUpperCase() +" algorithm will be used!");
    }
}
