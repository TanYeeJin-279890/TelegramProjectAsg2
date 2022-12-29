package my.uum;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws TelegramApiException{
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new JeanBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        DatabaseSQLite db = new DatabaseSQLite();
        //db.searchList("S007");
        //db.checkUser("S007");
        //db.updateStatus("28193","MR003","28/12/2022","09:00");
        db.displayList();

    }
}
