package net.pitan76.pkwklink.command.page;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.pkwklink.PukiWikiLink;

import java.util.Map;

public class ReadCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        addArgumentCommand(new StringCommand() {

            @Override
            public void init(CommandSettings settings) {
                settings.permissionLevel(2);
            }

            @Override
            public void execute(StringCommandEvent e) {
                String page = e.getValue();
                Map<String, Object> map = PukiWikiLink.pukiBot.getPage(page);

                if (map == null) {
                    e.sendFailure(PukiWikiLink.PREFIX + "Failed to get the page");
                    return;
                }

                if (map.containsKey("source")) {
                    String source = map.get("source").toString();
                    source = source.replaceAll("\\\\n", "\n");

                    e.sendSuccess("Title: " + page + "\nSource: \n" + source);
                    return;
                }

                // source があるときはmsgがないので、あとにmsgを取得することにした
                String msg = map.containsKey("msg") ? map.get("msg").toString() : "no message";
                e.sendFailure(PukiWikiLink.PREFIX + "Failed to get the page: " + msg);
            }

            @Override
            public String getArgumentName() {
                return "page";
            }
        });
    }

    @Override
    public void execute(ServerCommandEvent e) {
        e.sendSuccess(PukiWikiLink.PREFIX + " /pkwklink page read <page>");
    }
}
