package net.pitan76.pkwklink.command.page;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.BooleanCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.BooleanCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.pkwklink.PukiWikiLink;

import java.io.IOException;
import java.util.Map;

public class AppendCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        addArgumentCommand(new StringCommand() {

            @Override
            public void init(CommandSettings settings) {
                settings.permissionLevel(2);

                addArgumentCommand(new StringCommand() {

                    @Override
                    public void init(CommandSettings settings) {
                        settings.permissionLevel(2);

                        addArgumentCommand(new BooleanCommand() {
                            @Override
                            public void execute(BooleanCommandEvent e) {
                                append(e, e.getValue());
                            }

                            @Override
                            public String getArgumentName() {
                                return "notimestamp";
                            }
                        });
                    }

                    @Override
                    public void execute(StringCommandEvent e) {
                        append(e, false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "source";
                    }
                });
            }

            @Override
            public void execute(StringCommandEvent e) {
                e.sendSuccess(PukiWikiLink.PREFIX + " /pkwklink page append <page> <source>");
            }

            @Override
            public String getArgumentName() {
                return "page";
            }
        });
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess(PukiWikiLink.PREFIX + " /pkwklink page append <page>");
    }

    protected static void append(ServerCommandEvent e, boolean notimestamp) {
        String page = e.getArgument("page", String.class);
        String line = e.getArgument("source", String.class);

        try {
            Map<String, Object> map = PukiWikiLink.pukiBot.getPage(page);
            if (map == null) {
                e.sendFailure(PukiWikiLink.PREFIX + "Failed to get the page");
                return;
            }
            if (!map.containsKey("source")) {
                e.sendFailure(PukiWikiLink.PREFIX + "Failed to get the source");
                return;
            }

            String source = map.get("source").toString();
            source = source.replaceAll("\\\\n", "\n");
            source += line;

            Map<String, Object> map2 = PukiWikiLink.pukiBot.writePage(page, source, false, notimestamp, true);
            if (map2 == null) {
                e.sendFailure(PukiWikiLink.PREFIX + "Failed to append the page");
                return;
            }

            String msg = map.containsKey("msg") ? map.get("msg").toString() : "no message";
            int code = Integer.parseInt(map.get("code").toString());
            if (map.containsKey("code") && code == 201) {
                e.sendSuccess(PukiWikiLink.PREFIX + "Appended the page: " + msg);
                return;
            }

            e.sendFailure(PukiWikiLink.PREFIX + "Failed to append the page: " + msg);

        } catch (IOException ex) {
            e.sendFailure(PukiWikiLink.PREFIX + "Network error");
            ex.printStackTrace();
        }
    }
}
