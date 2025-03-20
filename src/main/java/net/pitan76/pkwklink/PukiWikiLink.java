package net.pitan76.pkwklink;

import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.fabric.ExtendModInitializer;
import net.pitan76.pkwklink.command.PWLCommand;
import net.pitan76.pukibot.PukiBot;

public class PukiWikiLink extends ExtendModInitializer {

    public static PukiBot pukiBot;

    @Override
    public void init() {
        PWLConfig.init();

        CommandRegistry.register("pkwklink", new PWLCommand());
    }

    @Override
    public String getId() {
        return "pkwklink";
    }
}
