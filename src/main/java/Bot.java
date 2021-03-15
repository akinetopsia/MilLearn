import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static final String TOKEN = "1421074097:AAEcInRtS3iRAK5almCc3iXsQAryGFTe3Ac";
    public static final String USERNAME = "MiLearnBot";
    public static URL WIKI_URL, MEDIA_URL;

    static {
        try {
            WIKI_URL = new URL("https://en.wikipedia.org/wiki/Special:Random");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            MEDIA_URL = new URL("https://www.mediawiki.org/wiki/Special:Random");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("/menu") || update.getMessage().getText().equals("/menu@MilLearnBot")){
                    try {
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(update.hasCallbackQuery()){
            try {
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("/start@MilLearnBot")){
                    try {
                        execute(new SendMessage(update.getMessage().getChatId()+"","Based on your preferences, our telegram bot will send you articles and tutorials that will help you to have more skills.\n\n" +
                                "How does this bot work?\n" +
                                "This bot has three commands:\n" +
                                "/start - if you are reading this text, then you have already selected this command. An introduction to this bot is shown here.\n" +
                                "/menu - if you press a menu command, you can choose where you want the random article from.\n" +
                                "/help - this explains how the bot works, as well as additional information."));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("/help") || update.getMessage().getText().equals("/help@MilLearnBot")){
                    try {
                        execute(new SendMessage(update.getMessage().getChatId()+"","/start - if you are reading this text, then you have already selected this command. An introduction to this bot is shown here.\n" +
                                "/menu - if you press a menu command, you can choose where you want the random article from.\n" +
                                "/help - this explains how the bot works, as well as additional information.\n" +
                                "\n" +
                                "This telegram bot was developed by Suleyman Demirel University students for educational purposes.\n" +
                                "\n" +
                                "01.03.2021"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Wikipedia");
        inlineKeyboardButton1.setUrl(WIKI_URL.toString());
        inlineKeyboardButton2.setText("MediaWiki");
        inlineKeyboardButton2.setUrl(MEDIA_URL.toString());
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("WikiHow").setUrl("http://www.wikihow.com/Special:Randomizer"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Choose where you want to select the article from").setReplyMarkup(inlineKeyboardMarkup);
    }
    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
