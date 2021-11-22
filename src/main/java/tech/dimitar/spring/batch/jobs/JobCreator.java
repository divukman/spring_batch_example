package tech.dimitar.spring.batch.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.stereotype.Component;
import tech.dimitar.spring.batch.listeners.JobCompletionNotificationListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobCreator {
    private final JobBuilderFactory jobBuilderFactory;
    private final JobCompletionNotificationListener listener;
    private final Step step1;

    public Job createCustomJob(String customJobArg) {
        log.info("Creating job: " + customJobArg);

        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}
