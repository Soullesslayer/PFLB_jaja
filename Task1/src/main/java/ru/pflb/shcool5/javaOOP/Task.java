package ru.pflb.shcool5.javaOOP;
import ru.pflb.mq.dummy.implementation.*;
import ru.pflb.mq.dummy.interfaces.*;
import ru.pflb.mq.dummy.exception.DummyException;
import java.util.Arrays;
import java.util.List;


public class Task {
    public static void main(String[] args)  throws DummyException  {
        // Создаем список сообщений
        List<String> messages = Arrays.asList("Четыре", "Пять", "Шесть");

        // Инициализируем соединение, сессию и продюсер
        Connection connect = new ConnectionImpl();
        try {
            connect.start();
            Session ses = connect.createSession(true);
            Destination dest = new DestinationImpl("BublikQueue");
            Producer produ = new ProducerImpl(dest);

            // Используем итератор для отправки сообщений
            for (String message : messages) {
                produ.send(message);
                System.out.println("Сообщение отправлено: " + message);
                Thread.sleep(2000);  // Пауза 2 секунды между отправками
            }

            // Закрываем соединение
            ses.close();
            connect.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}