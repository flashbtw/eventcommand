package de.flashyboi.gamellounge.specialeventcommand;

import de.flashyboi.gamellounge.specialeventcommand.commands.EventCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SpecialEventCommand extends JavaPlugin {

    public static SpecialEventCommand plugin;
    public static Logger log() {
        return plugin.getLogger();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        log().info("Loading Config..");
        ConfigInit();
        log().info("Config loaded!");
        log().info("Loading BungeeCord Bridge..");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "bungeecord:main");
        log().info("BungeeCord Bridge loaded!");
        log().info("Loading Commands..");
        this.getCommand("event").setExecutor(new EventCommand());
        log().info("Commands loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log().info("Unloading BungeeCord Bridge..");
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "bungeecord:main");
        log().info("Unloaded BungeeCord Bridge!");
    }

    public static SpecialEventCommand getPlugin() {
        return plugin;
    }

    public void ConfigInit() {
        this.saveDefaultConfig();
    }

}
