package im.dnn.BlockEntities.Utils;

import im.dnn.BlockEntities.BlockEntities;
import im.dnn.BlockEntities.Constants.Keys;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Helpers {
    public static String locationToString (Location location) {
        return location.getWorld().getName() + "_z" + location.getBlockX() + "y" + location.getBlockY() + "z" + location.getBlockZ();
    }
    public static String format(String message) {
        return message.replaceAll("&", "§");
    }

    public static boolean hasPermission (Player player, String permission) {
        return player.isOp() || player.hasPermission(permission);
    }

    public static void sendMessageToOpPlayers (BlockEntities plugin, String message) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendMessage(message);
            }
        }
    }

    public static void sendFormattedMessage (CommandSender sender, String message) {
        sender.sendMessage(format(message));
    }

    public static void sendMessage (BlockEntities plugin, CommandSender sender, String pathToMessage) {
        String prefix = plugin.settings.getString(Keys.MESSAGES_PREFIX);
        String message = plugin.settings.getString(pathToMessage);
        sendFormattedMessage(sender, prefix + message);
    }
}
