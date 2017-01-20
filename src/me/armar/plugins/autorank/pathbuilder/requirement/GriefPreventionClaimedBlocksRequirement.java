package me.armar.plugins.autorank.pathbuilder.requirement;

import org.bukkit.entity.Player;

import me.armar.plugins.autorank.language.Lang;
import me.staartvin.statz.hooks.Dependency;
import me.staartvin.statz.hooks.handlers.GriefPreventionHandler;

public class GriefPreventionClaimedBlocksRequirement extends Requirement {

    int claimedBlocks = -1;
    private GriefPreventionHandler handler = null;

    @Override
    public String getDescription() {
        return Lang.GRIEF_PREVENTION_CLAIMED_BLOCKS_REQUIREMENT.getConfigValue(claimedBlocks);
    }

    @Override
    public String getProgress(final Player player) {
        final int level = handler.getNumberOfClaimedBlocks(player.getUniqueId());

        return level + "/" + claimedBlocks;
    }

    @Override
    public boolean meetsRequirement(final Player player) {

        if (!handler.isAvailable())
            return false;

        final int level = handler.getNumberOfClaimedBlocks(player.getUniqueId());

        return level >= claimedBlocks;
    }

    @Override
    public boolean setOptions(final String[] options) {

        handler = (GriefPreventionHandler) this.getDependencyManager()
                .getDependencyHandler(Dependency.GRIEF_PREVENTION);

        if (options.length > 0) {
            claimedBlocks = Integer.parseInt(options[0]);
        }

        return claimedBlocks != -1 && handler != null;
    }
}
