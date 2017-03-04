package com.manulaiko.renashit.launcher.arguments;

import java.util.regex.Pattern;

import com.manulaiko.renashit.launcher.Settings;
import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Expression argument.
 * ====================
 *
 * Sets the regular expression to match.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Expression extends Argument
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
                arg.equalsIgnoreCase("-e") ||
                arg.equalsIgnoreCase("--expression")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        if(this.value().isEmpty()) {
            Console.println("Expression can't be empty!");
            Console.println("Using '(.*)' instead.");

            return;
        }

        try {
            Pattern p = Pattern.compile(this.value());
            Settings.pattern = p;

            Console.debug("`"+ this.value() +"` is going to be the regexp!");
        } catch(Exception e) {
            Console.println("Malformed regular expression!");
            Console.println("Using '(.*)' instead.");
        }
    }
}
