package com.project2026.linuxbernoulli.ui.shell

import com.project2026.linuxbernoulli.data.model.Command

class ShellInterpreter {

    fun parseCommand(input: String): String {
        val firstArg = input.substringBefore(" ")
        val remainingArgs = input.substringAfter(" ")
        val output = when (firstArg) {
            "help" -> help()
            "ls" -> "file1.txt file2.txt"
            "cd" -> cd(remainingArgs)
            "pwd" -> TODO()
            "mkdir" -> TODO()
            "rm" -> TODO()
            "cp" -> TODO()
            "mv" -> TODO()
            "touch" -> TODO()
            "cat" -> TODO()
            "grep" -> TODO()
            "find" -> TODO()
            "chmod" -> TODO()
            "chown" -> TODO()
            "sudo" -> TODO()
            "apt" -> TODO()
            "ps" -> TODO()
            "kill" -> TODO()
            "top" -> TODO()
            "df" -> TODO()
            "free" -> TODO()
            "echo" -> echo(remainingArgs)
            "ping" -> TODO()
            "ssh" -> TODO()
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

    fun cd(args: String): String {
        // Todo implement
        return "Command not implemented"
    }

    fun echo(args: String): String {
        return args
    }
}