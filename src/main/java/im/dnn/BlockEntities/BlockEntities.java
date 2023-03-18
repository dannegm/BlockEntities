package im.dnn.BlockEntities;

import im.dnn.BlockEntities.Commands.BlockCommand;
import im.dnn.BlockEntities.Commands.MainCommand;
import im.dnn.BlockEntities.Commands.MainCompleter;
import im.dnn.BlockEntities.Constants.Commands;
import im.dnn.BlockEntities.Listeners.BlockListener;
import im.dnn.BlockEntities.Managers.BlockManager;
import im.dnn.BlockEntities.Utils.Logger;
import im.dnn.BlockEntities.Utils.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEntities extends JavaPlugin {
    public Settings settings;

    @Override
    public void onEnable() {
        this.settings = new Settings(this);
        Logger.setPlugin(this);
        Logger.info("Enabled plugin PackManager");

        BlockManager blockManager = new BlockManager(this);
        getServer().getPluginManager().registerEvents(new BlockListener(this, blockManager), this);

        BlockCommand blockCommand = new BlockCommand(this);
        getCommand(Commands.BASE).setExecutor(new MainCommand(this, blockCommand));
        getCommand(Commands.BASE).setTabCompleter(new MainCompleter(blockCommand));
    }
}
