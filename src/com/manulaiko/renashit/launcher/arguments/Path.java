package com.manulaiko.renashit.launcher.arguments;

import java.io.File;

import com.manulaiko.renashit.launcher.Settings;
import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Path argument.
 * ==============
 *
 * Sets path to scan.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Path extends Argument
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
                arg.equalsIgnoreCase("-p") ||
                arg.equalsIgnoreCase("--path")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        if(this.value().isEmpty()) {
            Console.println("Path can't be empty!");
            Console.println("Using './' instead.");

            return;
        }

        File path = new File(this.value());
        if(!path.exists()) {
            Console.println("Path `"+ this.value() +"` does not exist!");
            Console.println("Using './' instead.");

            return;
        }

        if(!path.isDirectory()) {
            Console.println("Path `"+ this.value() +"` isn't a directory!");
            Console.println("Using './' instead.");

            return;
        }

        Settings.path = path;

        Console.debug("`"+ path.getAbsolutePath() +"` is going to be scanned!");
    }
}
