package top.peacefuly.minecraftChatGPT;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import top.peacefuly.minecraftChatGPT.command.ChatCommand;

import java.util.Objects;

public final class MinecraftChatGPT extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("chat").setExecutor(new ChatCommand(this));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equals("reload")) {
            reloadConfig();
            commandSender.sendMessage("config has reloaded");
        }
        return false;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
