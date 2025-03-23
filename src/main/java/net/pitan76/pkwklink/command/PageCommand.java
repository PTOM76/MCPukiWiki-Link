package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.pkwklink.PukiWikiLink;
import net.pitan76.pkwklink.command.page.AppendCommand;
import net.pitan76.pkwklink.command.page.ReadCommand;
import net.pitan76.pkwklink.command.page.WriteCommand;

import java.io.IOException;
import java.util.Map;

public class PageCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        settings.permissionLevel(3);
        addArgumentCommand("read", new ReadCommand());
        addArgumentCommand("write", new WriteCommand());
        addArgumentCommand("append", new AppendCommand());
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess(PukiWikiLink.PREFIX + " /pkwklink page [read/write/append] <page> (<source>)");
    }
}
