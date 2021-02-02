package de.bschueller.quarkus.client;

import de.bschueller.quarkus.contact.Contact;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.bschueller.quarkus.contact.ContactResource;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import org.jboss.logging.Logger;

/**
 * https://quarkus.io/guides/scheduler
 * https://quarkus.io/guides/rest-client
 */
@ApplicationScoped
public class RandomScheduledClient {
    private static final Logger LOG = Logger.getLogger( RandomScheduledClient.class);

    @Inject
    @RestClient
    ContactClient contactClient;

    private AtomicInteger counter = new AtomicInteger();

    public int get() {
        return counter.get();
    }

    @Scheduled(every="3s")
    void ternary() {
        counter.incrementAndGet();
        LOG.info(counter.get());
        Uni< Contact > contact = contactClient.getContact( 1L );
        LOG.info(contact);
    }

  /*  @Scheduled(cron="0 15 10 * * ?")
    void cronJob(ScheduledExecution execution) {
        counter.incrementAndGet();
        System.out.println(execution.getScheduledFireTime());
    }

    @Scheduled(cron = "{cron.expr}")
    void cronJobWithExpressionInConfig() {
        counter.incrementAndGet();
        System.out.println("Cron expression configured in application.properties");
    } */
}