package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public class HelpCommand extends LiteralCommand {
    
    @Override
    public void init(CommandSettings settings) {
        super.init(settings);
    }

    @Override
    public void execute(ServerCommandEvent e) {
        PWLCommand.help(e);
    }
}
