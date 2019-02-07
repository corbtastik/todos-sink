package io.corbs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

import java.util.Collections;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Todo {
    private Integer id;
    private String title = "";
    private Boolean completed = false;
    private Set<String> hashtags = Collections.emptySet();
}

@SpringBootApplication
@EnableBinding(TodosSinkApp.SinkChannels.class)
public class TodosSinkApp {

    private static final Logger LOG = LoggerFactory.getLogger(TodosSinkApp.class);

    interface SinkChannels {
        @Input
        SubscribableChannel foo();
    }

    @StreamListener("foo")
    void createTodoListener(Todo todo) {
        LOG.info("TodosSinkApp createTodoListener handled todo " + todo.toString());
        if(!todo.getHashtags().isEmpty()) {
            LOG.info("Oh look a Todo with #hashtags...this must be important.");
            LOG.info("Hashtags: " + todo.getHashtags());
        }
    }

	public static void main(String[] args) {
		SpringApplication.run(TodosSinkApp.class, args);
	}
}


