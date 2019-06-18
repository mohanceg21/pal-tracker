package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long,TimeEntry> testMap = new HashMap<Long,TimeEntry>();
    long counter;


    @Override
   public  TimeEntry create(TimeEntry timeEntry){
        System.out.println("timeEntry "+timeEntry +"Counter" +counter);
        counter = counter+1L;
        System.out.println("timeEntry "+timeEntry +"2" +counter);
        timeEntry.setId(counter);
        testMap.put(counter,timeEntry);
        return timeEntry;
    }

    @Override
    public  TimeEntry find(long id){
        if(testMap.containsKey(id)){
            TimeEntry timeEntry = testMap.get(id);
            return timeEntry;
        }
        return null;

    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<TimeEntry>(testMap.values());
        return list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        System.out.println("tme" +timeEntry.toString());
        System.out.println("OUTSIDE "+testMap.entrySet());
        if(testMap.containsKey(id)){
            timeEntry.setId(id);
            testMap.put(id, timeEntry);

            System.out.println("inside "+testMap.entrySet());
            return timeEntry;

        }
        return null;
    }

    @Override
    public void delete(long id) {
        testMap.remove(id);

    }
}