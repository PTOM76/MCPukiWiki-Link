package net.pitan76.pkwklink.command.page;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
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
                settings.permissionLevel(3);

                addArgumentCommand(new StringCommand() {

                    @Override
                    public void init(CommandSettings settings) {
                        settings.permissionLevel(3);
                    }

                    @Override
                    public void execute(StringCommandEvent e) {
                        String page = e.getArgument("page", String.class);
                        String line = e.getValue();

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
                            source = source.replaceAll("\\n", "\n");
                            source += "\n" + line;

                            Map<String, Object> map2 = PukiWikiLink.pukiBot.writePage(page, source, false, false, true);

                            if (map2 == null) {
                                e.sendFailure(PukiWikiLink.PREFIX + "Failed to append the page");
                                return;
                            }

                            int code = Integer.parseInt(map2.get("code").toString());
                            if (map2.containsKey("code") && code == 201) {
                                String msg = map2.get("msg").toString();
                                e.sendSuccess(PukiWikiLink.PREFIX + "Appended the page: " + msg);
                                return;
                            }


                            e.sendFailure(PukiWikiLink.PREFIX + "Failed to append the page");


                        } catch (IOException ex) {
                            e.sendFailure(PukiWikiLink.PREFIX + "Network error");
                            ex.printStackTrace();
                        }
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
}
