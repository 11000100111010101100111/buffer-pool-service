package com.kit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.kit.*", "io.kit.*"})
@EnableAsync
@EnableCaching
@Slf4j
public class ApplicationStarter {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ApplicationStarter.class, args);
        log.info("\n ________  ___  ___  ________ ________ _______   ________          ________  ________  ________  ___\n" +
                "|\\   __  \\|\\  \\|\\  \\|\\  _____\\\\  _____\\\\  ___ \\ |\\   __  \\        |\\   __  \\|\\   __  \\|\\   __  \\|\\  \\\n" +
                "\\ \\  \\|\\ /\\ \\  \\\\\\  \\ \\  \\__/\\ \\  \\__/\\ \\   __/|\\ \\  \\|\\  \\       \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\n" +
                " \\ \\   __  \\ \\  \\\\\\  \\ \\   __\\\\ \\   __\\\\ \\  \\_|/_\\ \\   _  _\\       \\ \\   ____\\ \\  \\\\\\  \\ \\  \\\\\\  \\ \\  \\\n" +
                "  \\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\_| \\ \\  \\_| \\ \\  \\_|\\ \\ \\  \\\\  \\|       \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\\\\\  \\ \\  \\____\n" +
                "   \\ \\_______\\ \\_______\\ \\__\\   \\ \\__\\   \\ \\_______\\ \\__\\\\ _\\        \\ \\__\\    \\ \\_______\\ \\_______\\ \\_______\\\n" +
                "    \\|_______|\\|_______|\\|__|    \\|__|    \\|_______|\\|__|\\|__|        \\|__|     \\|_______|\\|_______|\\|_______|\n" +
                "==============================================\n" +
                "Buffer'Pool has stared!\n" +
                "==============================================");
    }
}
