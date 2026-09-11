/* Porra de Copyright carai */

package com.minecraft.core;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.minecraft.core.account.AccountStorage;
import com.minecraft.core.account.system.AccountDeposit;
import com.minecraft.core.clan.service.ClanService;
import com.minecraft.core.database.mojang.MojangAPI;
import com.minecraft.core.database.mysql.MySQL;
import com.minecraft.core.database.redis.Redis;
import com.minecraft.core.server.ServerCategory;
import com.minecraft.core.server.ServerStorage;
import com.minecraft.core.server.ServerType;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class Constants {
    public static final String SERVER_NAME = System.getProperty("server_name", "Ateu");
    public static final String SERVER_WEBSITE = System.getProperty("server_website", "www.ateumc.com");
    public static final String SERVER_DISCORD = System.getProperty("server_discord", "discord.gg/ateumc");
    public static final String SERVER_STORE = System.getProperty("server_store", "loja.ateumc.com");
    public static final String SERVER_STORE = System.getProperty("server_play", "ateumc.com");
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###,###,###.##");
    public static final DecimalFormat SIMPLE_DECIMAL_FORMAT = create();
    public static final UUID CONSOLE_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static final UUID DEVELOPER_ADMIN = UUID.fromString("6053260e-1e8e-4e9e-81d5-832e9f862533"); //UUID do seu Nickname, meu caso: NesquikDeveloper (NameMC UUID)

    public static final File MISC_DIRECTORY = new File(System.getProperty("misc_directory", "/home/nesquikdeveloper"));
    public static final File UPDATER_DIRECTORY = new File(System.getProperty("updater_directory", MISC_DIRECTORY.getPath() + File.separator + "updater"));
    public static final File SERVERS_DIRECTORY = new File(System.getProperty("servers_directory", MISC_DIRECTORY.getPath() + File.separator + "servers"));
    public static final File HG_STRUCTURES_DIRECTORY = new File(System.getProperty("hg_structures_directory", MISC_DIRECTORY.getPath() + File.separator + "hg" + File.separator + "structures"));
    public static final File MAPS_DIRECTORY = new File(System.getProperty("maps_directory", MISC_DIRECTORY.getPath() + File.separator + "maps" + File.separator + "builds"));
    public static final File HG_MAPS_DIRECTORY = new File(System.getProperty("hg_maps_directory", MISC_DIRECTORY.getPath() + File.separator + "maps" + File.separator + "hg"));
    public static final File DUELS_MAPS_DIRECTORY = new File(System.getProperty("duels_maps_directory", MISC_DIRECTORY.getPath() + File.separator + "maps" + File.separator + "duels"));
    public static final File BEDWARS_MAPS_DIRECTORY = new File(System.getProperty("bedwars_maps_directory", MISC_DIRECTORY.getPath() + File.separator + "maps" + File.separator + "bedwars"));

    /**
     * MySQL Configuration
     */
    public static final String MYSQL_HOST = System.getProperty("mysql_host", "127.0.0.1");
    public static final int MYSQL_PORT = Integer.getInteger("mysql_port", 3306);
    public static final String MYSQL_USERNAME = System.getProperty("mysql_username", "server");
    public static final String MYSQL_PASSWORD = System.getProperty("mysql_password", "nesquikdeveloper");
    public static final String MYSQL_DATABASE = System.getProperty("mysql_database", "flamemc");

    /**
     * Redis Configuration
     */
    public static final String REDIS_HOST = System.getProperty("redis_host", "127.0.0.1");
    public static final int REDIS_PORT = Integer.getInteger("redis_port", 6379);
    public static final String REDIS_PASSWORD = System.getProperty("redis_password", "nesquikdeveloper");

    public static final Gson GSON = new Gson();
    public static final Random RANDOM = new Random();
    public static final JsonParser JSON_PARSER = new JsonParser();
    public static final AccountStorage accountStorage = new AccountStorage();
    public static final Pattern NICKNAME_PATTERN = Pattern.compile("[a-zA-Z0-9_]{3,16}");
    public static final MojangAPI mojangAPI = new MojangAPI();

    public static MySQL mySQL;
    public static MySQL getMySQL() {return mySQL;}
    public static void setMySQL(MySQL mySQL) {Constants.mySQL = mySQL;}
    public static Redis redis;
    public static Redis getRedis() {return redis;}
    public static void setRedis(Redis redis) {Constants.redis = redis;}
    public static final ExecutorService ASYNC = Executors.newCachedThreadPool(new ThreadFactoryBuilder().build());

    public static ServerType serverType = ServerType.UNKNOWN, lobbyType = ServerType.UNKNOWN;
    public static ServerStorage serverStorage;
    public static AccountDeposit accountDeposit;
    private static final ClanService clanService = new ClanService();

    public static ServerCategory getServerCategory() {
        return serverType.getServerCategory();
    }

    public static ClanService getClanService() {
        return clanService;
    }

    public static ServerStorage getServerStorage() {
        return serverStorage;
    }

    public static ServerType getLobbyType() {
        return lobbyType;
    }

    public static void setLobbyType(ServerType lobbyType) {
        Constants.lobbyType = lobbyType;
    }

    public static void setServerStorage(ServerStorage serverStorage) {
        Constants.serverStorage = serverStorage;
    }

    public static ServerType getServerType() {
        return serverType;
    }

    public static void setServerType(ServerType serverType) {
        Constants.serverType = serverType;
    }

    public static MojangAPI getMojangAPI() {
        return mojangAPI;
    }

    public static boolean isValid(String nickname) {
        return NICKNAME_PATTERN.matcher(nickname).matches();
    }

    public static void setAccountDeposit(AccountDeposit accountDeposit) {
        Constants.accountDeposit = accountDeposit;
    }

    public static AccountDeposit getAccountDeposit() {
        return accountDeposit;
    }

    public static UUID getCrackedUniqueId(String username) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username.toUpperCase()).getBytes(StandardCharsets.UTF_8));
    }

    public static boolean isUniqueId(String var1) {
        try {
            UUID.fromString(var1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static AccountStorage getAccountStorage() {
        return accountStorage;
    }

    public static String KEY(int lenght, boolean specialChars) {
        String PATTERN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        if (specialChars)
            PATTERN = PATTERN + "!@#$%¨&*()-_=";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            double index = Math.random() * PATTERN.length();
            builder.append(PATTERN.charAt((int) index));
        }
        return builder.toString();
    }

    private static DecimalFormat create() {
        DecimalFormat df = new DecimalFormat("#.#");
        DecimalFormatSymbols sym = DecimalFormatSymbols.getInstance();
        sym.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(sym);
        return df;
    }

}