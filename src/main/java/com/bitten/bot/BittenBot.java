package com.bitten.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BittenBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername(){
        return DadosBot.BOT_USER_NAME;
    }
    @Override
    public String getBotToken(){
        return DadosBot.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            var mensagem = responder(update);
            try{
                execute(mensagem);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }

    private SendMessage responder(Update update){
        var textoMensagem = update.getMessage().getText().toLowerCase();
        var chatId = update.getMessage().getChatId().toString();

        var resposta = ""

        if ("data"equals(textoMensagem)){
            reposta = getData();
        } else if (textoMensagem.startsWith("hora")){
            resposta = getHora();
        } else if (textoMensagem.startsWith("oi") || textoMensagem.startsWith("oii") || textoMensagem.startsWith("ola") || textoMensagem.startsWith("olá")){
                resposta = "Olá Furioso! O que você precisa hoje? ";


        }
    }

}
