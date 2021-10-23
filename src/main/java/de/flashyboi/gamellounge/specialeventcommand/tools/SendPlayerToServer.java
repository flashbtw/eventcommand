package de.flashyboi.gamellounge.specialeventcommand.tools;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.flashyboi.gamellounge.specialeventcommand.SpecialEventCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class SendPlayerToServer {

    public FileConfiguration config = SpecialEventCommand.getPlugin().getConfig();

    private final String failmessage = config.getString("server_not_online_message");
    SpecialEventCommand plugin = SpecialEventCommand.getPlugin();

    public void sendPlayer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1",25567), 40);
            s.close();
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
            player.sendMessage("Verbinden zu...");
        } catch(NullPointerException | IllegalArgumentException | IOException e) {
            player.sendMessage(ChatColor.RED + failmessage);
            e.printStackTrace();
            SpecialEventCommand.log().warning(ChatColor.RED + "No Bungeecord Server connected or Event Server offline!");
        }
    }
}
