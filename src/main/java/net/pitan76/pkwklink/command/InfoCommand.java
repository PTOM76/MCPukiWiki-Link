package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.pkwklink.PukiWikiLink;

import java.io.IOException;
import java.util.Map;

public class InfoCommand extends LiteralCommand {
    
    @Override
    public void init(CommandSettings settings) {
        super.init(settings);
    }

    @Override
    public void execute(ServerCommandEvent e) {
        try {
            Map<String, Object> map = PukiWikiLink.pukiBot.getInfo(true, false);

            if (map.containsKey("page_title")) {
                String title = map.get("page_title").toString();
                String admin = map.get("modifier").toString();

                e.sendSuccessWithTranslatable(PukiWikiLink.PREFIX + "\n" +
                        "§7-§r {message.pkwklink.title}: " + title + "\n" +
                        "§7-§r {message.pkwklink.admin}: " + admin);
                return;
            }

            String msg = map.containsKey("msg") ? map.get("msg").toString() : "no message";
            e.sendFailure(PukiWikiLink.PREFIX + "Failed to get info from the PukiWiki: " + msg);
        } catch (IOException ex) {
            e.sendFailure(PukiWikiLink.PREFIX + "Network error");
            ex.printStackTrace();
        }
    }
}
