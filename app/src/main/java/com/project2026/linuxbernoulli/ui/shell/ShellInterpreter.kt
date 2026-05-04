package com.project2026.linuxbernoulli.ui.shell

import com.project2026.linuxbernoulli.data.model.Command

class ShellInterpreter {

    fun parseCommand(input: String): String {
        val firstArg = input.substringBefore(" ")
        val remainingArgs = input.substringAfter(" ")
        val output = when (firstArg) {
            "help" -> help()
            "ls" -> ls()
            "cd" -> cd(remainingArgs)
            "pwd" -> pwd()
            "mkdir" -> mkdir(remainingArgs)
            "rm" -> rm(remainingArgs)
            "cp" -> cp(remainingArgs)
            "mv" -> mv(remainingArgs)
            "touch" -> touch(remainingArgs)
            "cat" -> cat(remainingArgs)
            "grep" -> grep(remainingArgs)
            "find" -> find(remainingArgs)
            "chmod" -> chmod(remainingArgs)
            "chown" -> chown(remainingArgs)
            "sudo" -> sudo(remainingArgs)
            "apt" -> apt(remainingArgs)
            "ps" -> ps(remainingArgs)
            "kill" -> kill(remainingArgs)
            "top" -> top(remainingArgs)
            "df" -> df(remainingArgs)
            "free" -> free(remainingArgs)
            "echo" -> echo(remainingArgs)
            "ping" -> ping(remainingArgs)
            "ssh" -> ssh(remainingArgs)
            else -> return "-bash: $firstArg: command not found"
        }
        return output;
    }

    fun help(): String {
        val output = buildString {
            this.appendLine("Here are some commands to try")
            this.appendLine("ls")
            this.appendLine("cd")
            this.appendLine("pwd")
            this.appendLine("mkdir")
            this.appendLine("rm")
            this.appendLine("cp")
            this.appendLine("mv")
            this.appendLine("touch")
            this.appendLine("cat")
            this.appendLine("grep")
            this.appendLine("find")
            this.appendLine("chmod")
            this.appendLine("chown")
            this.appendLine("sudo")
            this.appendLine("apt")
            this.appendLine("ps")
            this.appendLine("kill")
            this.appendLine("top")
            this.appendLine("df")
            this.appendLine("free")
            this.appendLine("echo")
            this.appendLine("ping")
            this.appendLine("ssh")
        }
        return output;
    }

    //most of the below functions are place holders awaiting more thorough implementation

    fun ls(): String {
        return "dev             home            media           opt             root            sys             usr\n" +
                "etc             lib             mnt             proc            run             tmp             var";
    }

    fun cd(args: String): String {
        return "You just changed the working directory to $args";
    }

    fun pwd(): String {
        return "/";
    }

    fun mkdir(args: String): String {
        return "You just created a directory named $args."
    }

    fun rm(args: String): String {
        return "You just deleted a file named $args. Careful!"
    }

    fun cp(args: String): String {
        return "You just copied a file named $args."
    }

    fun mv(args: String): String {
        try {
            if (!args.matches(Regex("^\\w+\\s+\\w+$"))) {return "Usage: mv <source> <destination>"}
            val source = args.substringBefore(" ")
            val destination = args.substringAfter(" ")
            return "You just moved a file named $source to $destination"
        } catch (e: Exception) {
            return "Usage: mv <source> <destination>"
        }
    }

    fun touch(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun cat(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun grep(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun find(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun chmod(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun chown(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun sudo(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun apt(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun ps(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun kill(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun top(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun df(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun free(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun echo(args: String): String {
        return args
    }

    fun ping(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun ssh(args: String): String {
        // Todo implement
        return "Command not implemented"
    }
}