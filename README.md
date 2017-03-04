# RenashIt
Rename files to its chosen hash string.

Usage:

    java -jar renashit.jar ([options])

Options:

    -d    --debug              Enables debug mode.
    -h    --help               Prints this help and quits
    -r    --recursive          Renames files of subdirectories too.
     -u    --unique             Deletes duplicated files.
     -a    --algorithm=algo     Sets the hashing algorithm to `algo` (default = `md5`).
     -p    --path=path          Path to parse (default = `./`).
     -e    --expression=expr    Sets the regular expression (default = '(.*)').

Supported algorithms:

 * MD5
 * SHA1
 * SHA256
 * SHA512

Examples:

    java -jar renashit.jar -d -r -p=Documents/Images/4Chum
    java -jar renashit.jar -r
    java -jar renashit.jar -a=sha256 -u\
    java -jar renashit-jar -e=\\.(jpe?g|png|gif) -u -r