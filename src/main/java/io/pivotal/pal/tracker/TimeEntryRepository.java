package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeEntryRepository {
    ResponseEntity create(TimeEntry any);

    void find(long timeEntryId);

    ResponseEntity<List<TimeEntry>> list();

    ResponseEntity update(long eq, TimeEntry any);

    ResponseEntity delete(long timeEntryId);
}
