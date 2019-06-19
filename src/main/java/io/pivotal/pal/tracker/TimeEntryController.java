package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {



    private TimeEntryRepository repos;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;



    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            MeterRegistry meterRegistry
    ) {
        this.repos = timeEntriesRepo;

        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        System.out.println("Test......" +timeEntry.toString() + "rep0 "+repos);
        actionCounter.increment();
        timeEntrySummary.record(repos.list().size());
        TimeEntry timeEntryToCreate1 =  repos.create(timeEntry);
        return new ResponseEntity<TimeEntry>(timeEntryToCreate1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntryToCreate1 =  repos.find(id);
        if(null != timeEntryToCreate1){
            actionCounter.increment();
            return new ResponseEntity<TimeEntry>(timeEntryToCreate1, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        System.out.println("In Put" +timeEntry.toString());
        TimeEntry timeEntryToCreate =  repos.update(id, timeEntry);
        if(null != timeEntryToCreate){
            actionCounter.increment();
           System.out.println(" Project Id" +timeEntryToCreate.getProjectId());
            return new ResponseEntity<TimeEntry>(timeEntryToCreate, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {

        repos.delete(timeEntryId);
        actionCounter.increment();
        timeEntrySummary.record(repos.list().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        List<TimeEntry> timeEntryList =  repos.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntryList, HttpStatus.OK);

    }
}