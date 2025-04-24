package com.bitten.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import io.github.cdimascio.dotenv.Dotenv;

public class RegistroBot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TELEGRAM_TOKEN");
        String username = dotenv.get("BOT_USER_NAME");

        if (token == null || token.isEmpty() || username == null || username.isEmpty()) {
            System.err.println("Token ou nome de usuário não podem estar vazios.");
            return;
        }

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new BittenBot(token, username));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
