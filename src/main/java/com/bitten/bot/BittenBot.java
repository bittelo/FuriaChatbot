package com.bitten.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BittenBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;

    public BittenBot(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public String getBotUsername(){
        return botUsername;
    }
    @Override
    public String getBotToken(){
        return botToken;
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

        var resposta = "";

        if ("data".equals(textoMensagem)) {
            resposta = getData();
        } else if (textoMensagem.startsWith("hora")){
            resposta = getHora();
        } else if (textoMensagem.startsWith("/start") ||textoMensagem.startsWith("oi") || textoMensagem.startsWith("oii") || textoMensagem.startsWith("ola") || textoMensagem.startsWith("olá")){
                resposta = "Olá Furioso! O que você precisa hoje? Você pode acessar os comandos com o /comandos";
        } else if(textoMensagem.startsWith("/proximojogo") || textoMensagem.startsWith("proximojogo")){

        } else if (textoMensagem.startsWith("/ultimojogo") || textoMensagem.startsWith("ultimojogo")){

        } else if (textoMensagem.startsWith("/comandos")){
            resposta = "Aqui está uma lista de comandos: \nproximojogo\nultimojogo\ncomunidade\nloja\nelenco";
        } else {
            resposta = "Não entendi!\\nDigite /help para ver os comandos disponíveis.";
        }

        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }

    private String getData() {
        var formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "A data atual é: " + formatter.format(new Date());
    }

    private String getHora() {
        var formatter = new SimpleDateFormat("HH:mm:ss");
        return "A hora atual é: " + formatter.format(new Date());
    }
}
