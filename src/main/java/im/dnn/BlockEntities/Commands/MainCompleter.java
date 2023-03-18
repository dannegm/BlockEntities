package im.dnn.BlockEntities.Commands;

import im.dnn.BlockEntities.Constants.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MainCompleter implements TabCompleter {
    private final BlockCommand blockCommand;

    public MainCompleter (BlockCommand blockCommand) {
        this.blockCommand = blockCommand;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            suggestions.add(Commands.HELP);
            suggestions.add(Commands.RELOAD);
            suggestions.add(Commands.GET);
            return suggestions;
        }

        if (args.length > 1 && args[0].equals(Commands.GET)) {
            return blockCommand.completer(args);
        }

        return suggestions;
    }
}
