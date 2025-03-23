package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.pkwklink.PWLConfig;
import net.pitan76.pkwklink.PukiWikiLink;
import net.pitan76.pukibot.PukiBot;

public class LinkCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        super.init(settings);
        settings.permissionLevel(3);

        addArgumentCommand(new StringCommand() {

            @Override
            public void init(CommandSettings settings) {
                settings.permissionLevel(3);

                addArgumentCommand(new StringCommand() {
                    @Override
                    public void init(CommandSettings settings) {
                        settings.permissionLevel(3);
                    }

                    @Override
                    public void execute(StringCommandEvent e) {
                        String url = e.getContext().getArgument("url", String.class);
                        String token = e.getValue();

                        PWLConfig.url = url;
                        PWLConfig.token = token;

                        PWLConfig.saveConfig();

                        // 1/2 length masking
                        String masked = token.substring(0, token.length() / 2) + "*".repeat(token.length() / 2);

                        PukiWikiLink.pukiBot = new PukiBot(url, token);
                        e.sendSuccess(PukiWikiLink.PREFIX + "PukiWikiLink has been linked to " + url + " with token " + masked, false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "token";
                    }
                });
            }

            @Override
            public void execute(StringCommandEvent e) {
                e.sendSuccess(PukiWikiLink.PREFIX + "/pkwklink link <url> <token>", false);
            }

            @Override
            public String getArgumentName() {
                return "url";
            }
        });
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess(PukiWikiLink.PREFIX + "/pkwklink link <url> <token>", false);
    }
}
