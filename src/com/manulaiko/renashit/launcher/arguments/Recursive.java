package com.manulaiko.renashit.launcher.arguments;

import com.manulaiko.renashit.launcher.Settings;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Recursive argument.
 * ================
 *
 * Enables recursive mode.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Recursive extends Argument
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
                arg.equalsIgnoreCase("-r") ||
                arg.equalsIgnoreCase("--recursive")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        Settings.recursive = true;
    }
}
