package tech.dimitar.spring.batch.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.dimitar.spring.batch.jobs.JobCreator;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobCreator jobCreator;
    private final JobLauncher jobLauncher;

    @GetMapping("/launch")
    public String handle() {
        log.info("Launching new job...");
        try {
            final String arg = UUID.randomUUID().toString();
            Job job = jobCreator.createCustomJob(arg);
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", new Date().getTime())
                    .addString("arg", arg)
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            log.info("Failed to launch a job: " + e.getMessage());
        }

        return "Done";
    }

    @GetMapping("/alive")
    public String alive() {
        return "Alive!";
    }

}
