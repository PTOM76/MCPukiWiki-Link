package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.pkwklink.PWLConfig;
import net.pitan76.pkwklink.PukiWikiLink;

public class ReloadCommand extends LiteralCommand {
    
    @Override
    public void init(CommandSettings settings) {
        super.init(settings);
    }

    @Override
    public void execute(ServerCommandEvent e) {
        PWLConfig.reloadConfig();
        e.sendSuccess(PukiWikiLink.PREFIX + "The config has been reloaded", false);
    }
}
