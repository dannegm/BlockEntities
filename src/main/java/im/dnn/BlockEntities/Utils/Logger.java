package im.dnn.BlockEntities.Utils;

import im.dnn.BlockEntities.BlockEntities;

public class Logger {
    static Logger singleton = null;
    static BlockEntities plugin;

    public static void setPlugin (BlockEntities plugin) {
        Logger.plugin = plugin;
    }

    private static void createInstance() {
        if (singleton == null) {
            singleton = new Logger();
        }
    }

    public static void info (String msg) {
        Logger.createInstance();
        if (plugin.settings.isDebug()) {
            plugin.getLogger().info(msg);
        }
    }

    public static void warning (String msg) {
        Logger.createInstance();
        plugin.getLogger().warning(msg);
    }

    public static void importantInfo (String msg) {
        Logger.createInstance();
        plugin.getLogger().info(msg);
    }
}
