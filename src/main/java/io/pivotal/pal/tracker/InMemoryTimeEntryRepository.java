package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class InMemoryTimeEntryRepository {

    public  TimeEntry create(TimeEntry timeEntry){

        return timeEntry;
    }


    public  TimeEntry find(long id){

        return null;
    }

    public boolean list() {
        return true;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        return null;
    }

    public void delete(long id) {


    }
}
