package de.flashyboi.gamellounge.specialeventcommand.tools;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.flashyboi.gamellounge.specialeventcommand.SpecialEventCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SendPlayerToServer {

    public FileConfiguration config = SpecialEventCommand.getPlugin().getConfig();

    public static String player;
    public static String server;
    private String failmessage = config.getString("server_not_online_message");
    ByteArrayDataOutput outStream = ByteStreams.newDataOutput();
    SpecialEventCommand plugin = SpecialEventCommand.getPlugin();

    public void sendPlayer(Player player, String server) {
        try {
            outStream.writeUTF("Connect");
            outStream.writeUTF(server);
            player.sendPluginMessage(plugin, "bungeecord:main", outStream.toByteArray());
            player.sendMessage("Verbinden zu...");
        } catch(NullPointerException | IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + failmessage);
            e.printStackTrace();
            SpecialEventCommand.log().warning(ChatColor.RED + "No Bungeecord Server connected!");
        }
    }
}
