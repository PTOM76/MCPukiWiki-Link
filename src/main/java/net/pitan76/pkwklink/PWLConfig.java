package net.pitan76.pkwklink;

import net.pitan76.easyapi.config.JsonConfig;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.pukibot.PukiBot;

import java.io.File;

public class PWLConfig {

    public static final File CONFIG_FILE = new File(PlatformUtil.getConfigFolderAsFile(), "pkwklink.json");
    public static final JsonConfig CONFIG = new JsonConfig();

    public static String url;
    public static String token;

    public static void init() {
        if (!CONFIG_FILE.exists())
            createConfig();

        CONFIG.load(CONFIG_FILE);

        url = CONFIG.getStringOrDefault("url", "https://pukiwiki.example.com/");
        token = CONFIG.getStringOrDefault("token", "********************************");

        PukiWikiLink.pukiBot = new PukiBot(url, token);
    }

    public static void createConfig() {
        url = "https://pukiwiki.example.com/";
        token = "********************************";

        saveConfig();
    }

    public static void saveConfig() {
        CONFIG.set("url", url);
        CONFIG.set("token", token);

        if (!CONFIG_FILE.getParentFile().exists())
            CONFIG_FILE.getParentFile().mkdirs();

        CONFIG.save(CONFIG_FILE, true);
    }

    public static void reloadConfig() {
        CONFIG.load(CONFIG_FILE);

        url = CONFIG.getStringOrDefault("url", url);
        token = CONFIG.getStringOrDefault("token", token);

        PukiWikiLink.pukiBot = new PukiBot(url, token);
    }
}
