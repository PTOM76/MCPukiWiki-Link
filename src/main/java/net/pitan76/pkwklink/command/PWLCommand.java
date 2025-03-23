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
        e.sendSuccess(PukiWikiLink.PREFIX + " Help", false);
        e.sendSuccess("- /pkwklink help - Show this help", false);
        e.sendSuccess("- /pkwklink reload - Reload the PukiWikiLink", false);
        e.sendSuccess("- /pkwklink link <url> <token> - Link the PukiWiki", false);
        e.sendSuccess("- /pkwklink info - Try to get information from the PukiWiki", false);
        e.sendSuccess("- /pkwklink page [read/write/append] <page> (<source>) - Read/Write/Append the page", false);

    }
}
