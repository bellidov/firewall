package com.luxoft.recruitment.cstr.datalayer;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class BlackListProvider {
    
    private static BlackListProvider provider;

    private String PATH_NAME = "D:\\pios";
    private String FILE_NAME = "blacklist.txt";
    //private static final Logger LOGGER = Logger.getLogger(BlackListProvider.class.getName());
    
    private List<String> blackList;

    private BlackListProvider(){}
    
    {
        readFromFile(Paths.get(PATH_NAME, FILE_NAME));

        BlackListObserver observer = new BlackListObserver(this);
        Thread thread = new Thread(observer);

        thread.start();
    }
    
    public static BlackListProvider getInstance(){
        if(provider == null) {
            provider = new BlackListProvider();
        }

        return provider;
    }
    
    public List<String> getBlackList() {
        if(blackList == null) {
            blackList = new ArrayList<>();
        }
        return blackList;
    }

    private void readFromFile(Path file){
        this.getBlackList().clear();
        try (Stream<String> stream = Files.lines(file)) {
            stream.forEach(x -> this.getBlackList().add(x));
        } catch (IOException e) {
            System.out.println("Unable to read from the file " + file.getFileName() + " as it does not exist.");        }
    }

    //inner class to observe the changes inside of black list file
    private class BlackListObserver implements Runnable {
        BlackListProvider provider;

        BlackListObserver(BlackListProvider provider){
            this.provider = provider;
        }

        private void read() {
            provider.readFromFile(Paths.get(PATH_NAME, FILE_NAME));
        }
        @Override
        public void run() {
            try {
                Path path = Paths.get(PATH_NAME);
                WatchService service = FileSystems.getDefault().newWatchService();

                path.register(service, StandardWatchEventKinds.ENTRY_MODIFY);

                WatchKey key;
                while((key = service.take()) != null) {
                    Thread.sleep( 50 );
                    for(WatchEvent<?> event : key.pollEvents()) {
                        System.out.println("Event kind: " + event.kind() + ". File affected: " + event.context() + ".");
                        read();
                    }

                    key.reset();
                }

            } catch (IOException e) {
                System.out.println("Unable to watch the blacklist file as the directory " + PATH_NAME + " does not exist.");
            } catch (InterruptedException e) {
                System.out.println("An InterruptedException has occurred.");
            }
        }
    }
}
