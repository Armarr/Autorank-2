package me.armar.plugins.autorank.pathbuilder.requirement;

import me.armar.plugins.autorank.language.Lang;
import org.bukkit.entity.Player;

public class WorldRequirement extends AbstractRequirement {

    String worldName = null;

    @Override
    public String getDescription() {
        return Lang.WORLD_REQUIREMENT.getConfigValue(worldName);
    }

    @Override
    public String getProgressString(final Player player) {
        return player.getWorld().getName() + "/" + worldName;
    }

    @Override
    public boolean meetsRequirement(final Player player) {

        final String world = player.getWorld().getName();

        return world.equals(worldName);
    }

    @Override
    public boolean initRequirement(final String[] options) {

        if (options.length > 0) {
            worldName = options[0];
        }

        if (worldName == null) {
            this.registerWarningMessage("No world is specified");
            return false;
        }

        return true;
    }

    @Override
    public boolean needsOnlinePlayer() {
        return true;
    }
}
