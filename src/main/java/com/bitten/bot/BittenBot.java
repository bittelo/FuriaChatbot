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

        if (textoMensagem.startsWith("/start") ||textoMensagem.startsWith("oi") || textoMensagem.startsWith("oii") || textoMensagem.startsWith("ola") || textoMensagem.startsWith("olá")){
                resposta = "Olá Furioso! O que você precisa hoje? Você pode acessar os comandos com o /comandos";
        } else if(textoMensagem.startsWith("/proximojogo") || textoMensagem.startsWith("proximojogo")){
                resposta ="Com base no site https://draft5.gg/equipe/330-FURIA, a FURIA ainda não tem um próximo jogo agendado.\n";

        } else if (textoMensagem.startsWith("/ultimojogo") || textoMensagem.startsWith("ultimojogo")){
                resposta ="Última Partida da FURIA:\n\n" +
                        "FURIA 0 - 2 THE MONGOLZ\n" +
                        "PGL Bucharest 2025\n\n" +
                        "Confira a partida: https://draft5.gg/partida/36342-FURIA-vs-The-MongolZ-PGL-Bucharest-2025";

        } else if (textoMensagem.startsWith("/comunidade") || textoMensagem.startsWith("comunidade")){
            resposta = "Converse com outros furiosos!\nDiscord: https://discord.com/invite/furia\n";
        } else if(textoMensagem.startsWith("/loja") || textoMensagem.startsWith("loja")){
            resposta = "Vista o estilo FURIA: moda para quem vive o jogo\n https://www.furia.gg/";
        } else if(textoMensagem.startsWith("/elenco") || textoMensagem.startsWith("elenco")){
            resposta = "Atualmente o elenco da FURIA conta com:\n \uD83C\uDDF0\uD83C\uDDFFMOLODY\n \uD83C\uDDF1\uD83C\uDDFBYEKINDAR\n \uD83C\uDDE7\uD83C\uDDF7FalleN\n \uD83C\uDDE7\uD83C\uDDF7KSCERATO\n \uD83C\uDDE7\uD83C\uDDF7yuurih";
        }
        else if (textoMensagem.startsWith("/comandos")){
            resposta = "Aqui está uma lista de comandos: \nproximojogo\nultimojogo\ncomunidade\nloja\nelenco";
        } else {
            resposta = "Não entendi!\\nDigite /help para ver os comandos disponíveis.";
        }

        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }

}
