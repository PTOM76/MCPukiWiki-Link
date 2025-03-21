package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.pkwklink.PukiWikiLink;

import java.io.IOException;
import java.util.Map;

public class PageCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        settings.permissionLevel(3);
        addArgumentCommand("read", new LiteralCommand() {

            @Override
            public void init(CommandSettings settings) {
                addArgumentCommand(new StringCommand() {

                    @Override
                    public void init(CommandSettings settings) {
                        settings.permissionLevel(3);
                    }

                    @Override
                    public void execute(StringCommandEvent e) {
                        String page = e.getValue();

                        Map<String, Object> map = PukiWikiLink.pukiBot.getPage(page);

                        if (map == null) {
                            e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to get the page");
                            return;
                        }

                        String source = map.get("source").toString();

                        e.sendSuccess("Title: " + page + "\nSource: " + source, false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "page";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent e) {
                e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page read <page>", false);

            }
        });

        addArgumentCommand("write", new LiteralCommand() {

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
                                String page = e.context.getArgument("page", String.class);
                                String source = e.getValue();

                                try {
                                    Map<String, Object> map = PukiWikiLink.pukiBot.writePage(page, source, false, false, true);

                                    if (map == null) {
                                        e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to write the page");
                                        return;
                                    }
                                } catch (IOException ex) {
                                    e.sendFailure("§5[§aPukiWikiLink§5] §rNetwork error");
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
                        e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page write <page> <source>", false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "page";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent e) {
                e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page write <page>", false);

            }
        });
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page <type:[read/write]> <page>", false);
    }
}
