package org.sid.config;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        registry.addFormatter(new Formatter<LocalTime>() {
            @Override
            public LocalTime parse(String text, Locale locale) {
                return LocalTime.parse(text, timeFormatter);
            }

            @Override
            public String print(LocalTime object, Locale locale) {
                return object.format(timeFormatter);
            }
        });
    }
}
