package org.example;

import org.example.concurrent.*;
import org.example.entities.*;
import org.example.enums.*;
import org.example.services.*;
import org.example.services.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadDemo {

    public static void main(String[] args) throws Exception {

        // verifying multithreading with quick and small data...
        UserService userSvc = new UserServiceImpl();
        DeveloperService devSvc = new DeveloperServiceImpl();

        User admin = new User("ConsoleAdmin", Role.ADMIN);
        userSvc.addUser(admin);

        Developer dev = new Developer("ThreadDev", Role.DEVELOPER, 12,
                new String[]{"Java"}, admin);
        devSvc.addDeveloper(dev);

        // 1. Thread subclass
        Thread ticketMaker = new TicketCreatorThread(dev, admin);
        ticketMaker.start();

        // 2. Runnable with Thread
        Thread todoPrinter = new Thread(new BoardPrinterRunnable(TicketStatus.TODO), "TodoPrinter");
        todoPrinter.start();

        // 3. Callable with ExecutorService
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<Integer>> futures = pool.invokeAll(Arrays.asList(
                new TicketCountCallable(TicketStatus.TODO),
                new TicketCountCallable(TicketStatus.IN_PROGRESS)));

        // wait and display results
        for (Future<Integer> f : futures) {
            System.out.println("Result from Callable: " + f.get());
        }
        pool.shutdown();
    }
}