package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.pkwklink.PukiWikiLink;

public class PWLCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        super.init(settings);
        settings.permissionLevel(0);

        addArgumentCommand("help", new HelpCommand());
        addArgumentCommand("reload", new ReloadCommand());
        addArgumentCommand("link", new LinkCommand());
        addArgumentCommand("info", new InfoCommand());
        addArgumentCommand("page", new PageCommand());
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess(PukiWikiLink.PREFIX + "Hello, PukiWikiLink!");
    }

    public static void help(ServerCommandEvent e) {
        e.sendSuccess(PukiWikiLink.PREFIX + "Help");
        e.sendSuccess("§7-§r /pkwklink help - Show this help");
        e.sendSuccess("§7-§r /pkwklink reload - Reload the PukiWikiLink");
        e.sendSuccess("§7-§r /pkwklink link <url> <token> - Link the PukiWiki");
        e.sendSuccess("§7-§r /pkwklink info - Try to get information from the PukiWiki");
        e.sendSuccess("§7-§r /pkwklink page [read/write/append] <page> (<source>) (<notimestamp>) - Read/Write/Append the page");

    }
}
