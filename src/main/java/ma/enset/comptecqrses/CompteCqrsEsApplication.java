package ma.enset.comptecqrses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class CompteCqrsEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteCqrsEsApplication.class, args);
    }

}
