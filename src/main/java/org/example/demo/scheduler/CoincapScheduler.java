package org.example.demo.scheduler;

import org.example.demo.CoincapEntry;
import org.example.demo.coincap.CoincapService;
import org.example.demo.repository.CoincapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoincapScheduler {

    @Autowired
    private CoincapService coincapService;

    @Autowired
    private CoincapRepository coincapRepository;

    @Scheduled(fixedDelay = 15000)
    public void scheduleFixedDelayTask() {
        List<CoincapEntry> coincapEntries = coincapService.fetchSomeRecords();
        coincapEntries.forEach(eachRecord -> coincapRepository.save(eachRecord));
    }
}
