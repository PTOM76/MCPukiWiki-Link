package net.pitan76.pkwklink.command;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
                            Text.literal("aaaa").formatted(Formatting.BLACK)
                                    .append(Text.literal("bbbb").formatted(Formatting.RED));

                            e.sendFailure("§5[§aTest§5] §rFailed to get the page");
                            return;
                        }

                        String source = map.get("source").toString();
                        source = source.replaceAll("\\\\n", "\n");

                        e.sendSuccess("Title: " + page + "\nSource: \n" + source, false);
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

                                    int code = Integer.parseInt(map.get("code").toString());
                                    if (map.containsKey("code") && code == 201) {
                                        String msg = map.get("msg").toString();
                                        e.sendSuccess("§5[§aPukiWikiLink§5] §rAppended the page: " + msg, false);
                                        return;
                                    }

                                    e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to append the page");

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

        addArgumentCommand("append", new LiteralCommand() {

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
                                String line = e.getValue();

                                try {
                                    Map<String, Object> map = PukiWikiLink.pukiBot.getPage(page);
                                    if (map == null) {
                                        e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to get the page");
                                        return;
                                    }
                                    if (!map.containsKey("source")) {
                                        e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to get the source");
                                        return;
                                    }

                                    String source = map.get("source").toString();

                                    source = source.replaceAll("\\n", "\n");

                                    source += "\n" + line;

                                    Map<String, Object> map2 = PukiWikiLink.pukiBot.writePage(page, source, false, false, true);

                                    if (map2 == null) {
                                        e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to append the page");
                                        return;
                                    }

                                    int code = Integer.parseInt(map2.get("code").toString());
                                    if (map2.containsKey("code") && code == 201) {
                                        String msg = map2.get("msg").toString();
                                        e.sendSuccess("§5[§aPukiWikiLink§5] §rAppended the page: " + msg, false);
                                        return;
                                    }

                                    e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to append the page");


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
                        e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page append <page> <source>", false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "page";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent e) {
                e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page append <page>", false);

            }
        });
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess("§5[§aPukiWikiLink§5] §r /pkwklink page <type:[read/write]> <page>", false);
    }
}
