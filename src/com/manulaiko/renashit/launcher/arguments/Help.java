package com.manulaiko.renashit.launcher.arguments;

import com.manulaiko.renashit.launcher.Main;
import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Help argument.
 * ==============
 *
 * Prints help info and exists.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Help extends Argument
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
                arg.equalsIgnoreCase("-h") ||
                arg.equalsIgnoreCase("--help")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        Console.println(
                "FileUtils/RenashIt v"+ Main.version +" by Manulaiko.\n"                                   +
                "Rename files to its chosen hash string.\n"                                                +
                "\n"                                                                                       +
                "Usage:\n"                                                                                 +
                "    java -jar renashit.jar ([options])\n"                                                 +
                "\n"                                                                                       +
                "Options:\n"                                                                               +
                "    -d    --debug              Enables debug mode.\n"                                     +
                "    -h    --help               Prints this help and quits\n"                              +
                "    -r    --recursive          Renames files of subdirectories too.\n"                    +
                "    -u    --unique             Deletes duplicated files.\n"                               +
                "    -a    --algorithm=algo     Sets the hashing algorithm to `algo` (default = `md5`).\n" +
                "    -p    --path=path          Path to parse (default = `./`).\n"                         +
                "    -e    --expression=expr    Sets the regular expression (default = '(.*)')."           +
                "\n"                                                                                       +
                "Supported algorithms:\n"                                                                  +
                "    MD5\n"                                                                                +
                "    SHA1\n"                                                                               +
                "    SHA256\n"                                                                             +
                "\n"                                                                                       +
                "Examples:\n"                                                                              +
                "    java -jar renashit.jar -d -r -p=Documents/Images/4Chum\n"                             +
                "    java -jar renashit.jar -r\n"                                                          +
                "    java -jar renashit.jar -a=sha256 -u\n"                                                +
                "    java -jar renashit-jar -e=\\.(jpe?g|png|gif) -u -r\n"                                 +
                "\n"                                                                                       +
                "Sources are available at https://github.com/FileUtils/RenashIt"
        );

        System.exit(0);
    }
}
