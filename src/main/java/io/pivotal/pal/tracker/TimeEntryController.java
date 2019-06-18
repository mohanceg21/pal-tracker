package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {



    private TimeEntryRepository repos;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repos=timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        System.out.println("Test......" +timeEntry.toString() + "rep0 "+repos);

        TimeEntry timeEntryToCreate1 =  repos.create(timeEntry);
        return new ResponseEntity<TimeEntry>(timeEntryToCreate1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntryToCreate1 =  repos.find(id);
        if(null != timeEntryToCreate1){
            return new ResponseEntity<TimeEntry>(timeEntryToCreate1, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        System.out.println("In Put" +timeEntry.toString());
        TimeEntry timeEntryToCreate =  repos.update(id, timeEntry);
        if(null != timeEntryToCreate){
           System.out.println(" Project Id" +timeEntryToCreate.getProjectId());
            return new ResponseEntity<TimeEntry>(timeEntryToCreate, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {
        repos.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList =  repos.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntryList, HttpStatus.OK);

    }
}