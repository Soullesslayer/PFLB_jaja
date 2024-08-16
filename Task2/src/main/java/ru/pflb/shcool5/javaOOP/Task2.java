package ru.pflb.shcool5.javaOOP;
import ru.pflb.mq.dummy.implementation.*;
import ru.pflb.mq.dummy.interfaces.*;
import ru.pflb.mq.dummy.exception.DummyException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Task2 {
    public static void main(String[] args)  throws DummyException  {
        String fP = args[0];
        Connection connect = new ConnectionImpl();
        try {
            connect.start();
            Session ses = connect.createSession(true);
            Destination dest = new DestinationImpl("BublikQueue");
            Producer produ = new ProducerImpl(dest);
            while (true) {
                try (BufferedReader br = new BufferedReader(new FileReader(fP))) {
                    String massage;
                    while ((massage = br.readLine()) != null) {
                        produ.send(massage);
                        System.out.println("Сообщение отправлено: " + massage);
                        Thread.sleep(2000);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                    break;
                }
                }
            ses.close();
            connect.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}