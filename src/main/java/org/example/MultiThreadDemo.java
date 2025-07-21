package org.example;

import org.example.concurrent.*;
import org.example.config.DBConfigLoader;
import org.example.entities.*;
import org.example.enums.*;
import org.example.services.*;
import org.example.services.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadDemo {

    /*
    public static void main(String[] args) throws Exception {

        // verifying multithreading with quick and small data...
        UserService userSvc = new UserServiceImpl();
        DeveloperService devSvc = new DeveloperServiceImpl();

        User admin = new User("extendsThreadsDemoAdmin", Role.ADMIN);
        userSvc.addUser(admin);

        Developer dev = new Developer("extendsThreadDev", Role.DEVELOPER, 2,
                new String[]{"Java", "SQL"}, admin);
        devSvc.addDeveloper(dev);

        // 1. Thread subclass
        Thread ticketMaker = new TicketCreatorThread(dev, admin);
        System.out.println("==== extends Thread Multithreading Demo STARTS ====");
        ticketMaker.start();
        System.out.println("==== extends Thread Multithreading Demo ENDS ====");

        // 2. Runnable with Thread
        Thread todoPrinter = new Thread(new BoardPrinterRunnable(TicketStatus.TODO), "TodoPrinter");
        System.out.println("==== Runnable Multithreading Demo STARTS ====");
        todoPrinter.start();
        System.out.println("==== Runnable Multithreading Demo ENDS ====");

        // 3. Callable with ExecutorService that returns Futures
        int poolSize = DBConfigLoader.getThreadPoolSize();
        System.out.println("Pool size from app.properties: " + poolSize);
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        List<Future<Integer>> futures = pool.invokeAll(Arrays.asList(
                new TicketCountCallable(TicketStatus.TODO),
                new TicketCountCallable(TicketStatus.IN_PROGRESS)));

        System.out.println("==== Callable Multithreading Demo STARTS ====");
        // wait and display results
        for (Future<Integer> f : futures) {
            System.out.println("Futures (promises) from Callable: " + f.get());
        }
        System.out.println("==== Callable Multithreading Demo ENDS and shutting_down_pool ====");
        pool.shutdown();

    }
    */

}