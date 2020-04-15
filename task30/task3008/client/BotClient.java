package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String userName = message.split(": ")[0];
                String text = message.split(": ")[1];

                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat day = new SimpleDateFormat("d");
                SimpleDateFormat month = new SimpleDateFormat("MMMM");
                SimpleDateFormat year = new SimpleDateFormat("yyyy");
                SimpleDateFormat time = new SimpleDateFormat("H:mm:ss");
                SimpleDateFormat hour = new SimpleDateFormat("H");
                SimpleDateFormat minutes = new SimpleDateFormat("m");
                SimpleDateFormat seconds = new SimpleDateFormat("s");

                Date date = Calendar.getInstance().getTime();
                switch (text) {
                    case "дата":
                        sendTextMessage("Информация для " + userName + ": " + df.format(date));
                        break;
                    case "день":
                        sendTextMessage("Информация для " + userName + ": " + day.format(date));
                        break;
                    case "месяц":
                        sendTextMessage("Информация для " + userName + ": " + month.format(date));
                        break;
                    case "год":
                        sendTextMessage("Информация для " + userName + ": " + year.format(date));
                        break;
                    case "время":
                        sendTextMessage("Информация для " + userName + ": " + time.format(date));
                        break;
                    case "час":
                        sendTextMessage("Информация для " + userName + ": " + hour.format(date));
                        break;
                    case "минуты":
                        sendTextMessage("Информация для " + userName + ": " + minutes.format(date));
                        break;
                    case "секунды":
                        sendTextMessage("Информация для " + userName + ": " + seconds.format(date));
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
