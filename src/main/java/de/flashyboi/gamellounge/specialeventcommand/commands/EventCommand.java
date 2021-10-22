package de.flashyboi.gamellounge.specialeventcommand.commands;

import de.flashyboi.gamellounge.specialeventcommand.SpecialEventCommand;
import de.flashyboi.gamellounge.specialeventcommand.tools.SendPlayerToServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class EventCommand implements CommandExecutor {
    SendPlayerToServer send = new SendPlayerToServer();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        } else {
            Player player = ((Player) sender).getPlayer();
            String servername = SpecialEventCommand.getPlugin().getConfig().getString("server_name");
            send.sendPlayer(player, servername);
        }
        return true;
    }
}
