package com.manulaiko.renashit.launcher.arguments;

import com.manulaiko.renashit.launcher.Settings;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Unique argument.
 * ================
 *
 * Enables unique mode.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Unique extends Argument
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
                arg.equalsIgnoreCase("-u") ||
                arg.equalsIgnoreCase("--unique")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        Settings.unique = true;
    }
}
