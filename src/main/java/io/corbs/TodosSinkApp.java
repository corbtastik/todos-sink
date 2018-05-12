package io.corbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding(TodosSinkApp.SinkChannels.class)
public class TodosSinkApp {

    private static final Logger LOG = LoggerFactory.getLogger(TodosSinkApp.class);

    interface SinkChannels {
        @Input
        SubscribableChannel input();
    }

    @StreamListener("input")
    void createTodo(Todo todo) {
        LOG.debug("TodosSinkApp createTodo invoked with todo " + todo.toString());
    }

	public static void main(String[] args) {
		SpringApplication.run(TodosSinkApp.class, args);
	}
}


