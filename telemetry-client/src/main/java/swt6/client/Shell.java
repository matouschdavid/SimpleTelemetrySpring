package swt6.client;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Shell {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Shell.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean("promptProvider")
    public PromptProvider getPrompt() {
        return () ->
                new AttributedString("telemetry> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
