package net.pitan76.pkwklink;

import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.fabric.ExtendModInitializer;
import net.pitan76.pkwklink.command.PWLCommand;
import net.pitan76.pukibot.PukiBot;

public class PukiWikiLink extends ExtendModInitializer {

    public static final String MOD_ID = "pkwklink";
    public static final String MOD_NAME = "PukiWikiLink";

    public static final String PREFIX = "§9[§b" + MOD_NAME + "§9]§r ";

    public static PukiBot pukiBot;

    @Override
    public void init() {
        PWLConfig.init();
        CommandRegistry.register("pkwklink", new PWLCommand());
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    public static PukiBot getPukiBot() {
        return pukiBot;
    }

    public static String getUrl() {
        return pukiBot.getUrl();
    }

    public static String getToken() {
        return pukiBot.getToken();
    }
}
