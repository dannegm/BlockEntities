package im.dnn.BlockEntities.Commands;

import im.dnn.BlockEntities.BlockEntities;
import im.dnn.BlockEntities.Constants.Keys;
import im.dnn.BlockEntities.Constants.Permissions;
import im.dnn.BlockEntities.Models.BlockItem;
import im.dnn.BlockEntities.Utils.Helpers;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class BlockCommand {
    private final BlockEntities plugin;
    public HashMap<String, BlockItem> blockTypes;
    public List<String> blockTypesKeys;

    private HashMap<String, BlockItem> loadBlockList(BlockEntities plugin) {
        List<Map<?, ?>> blockList = plugin.settings.getMapList(Keys.BLOCKS);
        HashMap<String, BlockItem> blockTypesList = new HashMap<>();

        for(Map<?, ?> blockElement : blockList) {
            String key = (String) blockElement.get("key");
            BlockItem blockItem = new BlockItem(blockElement);
            blockTypesList.put(key, blockItem);
        }

        return blockTypesList;
    }

    private List<String> getBlockTypesKeys (HashMap<String, BlockItem> blockTypes) {
        Set<String> keySet = blockTypes.keySet();
        return new ArrayList<>(keySet);
    }

    public BlockCommand(BlockEntities plugin) {
        this.plugin = plugin;
        this.reload();
    }

    public void reload () {
        this.blockTypes = this.loadBlockList(this.plugin);
        this.blockTypesKeys = this.getBlockTypesKeys(this.blockTypes);
    }

    private boolean giveBlockItem (String blockType, Player player, int amount) {
        if (!Helpers.hasPermission(player, Permissions.GET)) {
            Helpers.sendMessage(this.plugin, player, Keys.MESSAGES_CANT_GET);

            return false;
        }

        BlockItem blockItem = this.blockTypes.get(blockType);

        if (!this.blockTypes.containsKey(blockType)) {
            Helpers.sendMessage(this.plugin, player, Keys.MESSAGES_CMD_BLOCK_NOT_EXISTS);
            return false;
        }

        player.getInventory().addItem(blockItem.getItem(amount));
        return true;
    }

    public boolean onCommand (CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            Helpers.sendMessage(this.plugin, sender, Keys.MESSAGES_CMD_NOT_PLAYER);
            return false;
        }

        if (args.length < 2) {
            Helpers.sendMessage(this.plugin, sender, Keys.MESSAGES_CMD_BLOCK_MISSING);
            return false;
        }

        if (args.length > 2 && args[2].matches("\\d+")) {
            return this.giveBlockItem(args[1], (Player) sender, Integer.parseInt(args[2]));
        }

        return this.giveBlockItem(args[1], (Player) sender, 1);
    }

    public List<String> completer(String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length > 3) {
            return suggestions;
        }

        if (args.length > 2) {
            suggestions.add("<amount>");
            return suggestions;
        }

        if (args.length > 1) {
            suggestions.addAll(this.blockTypesKeys);
            return suggestions;
        }

        return suggestions;
    }
}
