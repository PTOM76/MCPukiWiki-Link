package net.pitan76.pkwklink.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
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

            String title = map.get("page_title").toString();
            String admin = map.get("modifier").toString();

            e.sendSuccess("§5[§aPukiWikiLink§5]§r\n" +
                "- Title: " + title + "\n" +
                "- Admin: " + admin, false);

            // map の内容をjsonに変換して表示
            //Gson gson = new Gson();
            //String json = gson.toJson(map);
            //e.sendSuccess("§5[§aPukiWikiLink§5] §r" + json, false);

        } catch (IOException ex) {
            e.sendFailure("§5[§aPukiWikiLink§5] §rFailed to get information from the PukiWiki");
            ex.printStackTrace();
        }
    }
}
