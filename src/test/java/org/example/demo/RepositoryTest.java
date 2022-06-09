package org.example.demo;

import org.example.demo.repository.CoincapRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = DemoProjectApplication.class)
public class RepositoryTest {
    @Autowired
    private CoincapRepository coincapRepository;
    @Test
    public void testGet10Latest() {
        CoincapEntry coincapEntry = getEntry();
        coincapRepository.save(coincapEntry);
        CoincapEntry result = coincapRepository.get10LatestRecords().get(0);
        assertEquals(coincapEntry.getId(), result.getId());
    }

    @Test
    public void testDeleteById() {
        CoincapEntry entry = getEntry();
        coincapRepository.save(entry);
        coincapRepository.deleteById(entry.getId());
        List<CoincapEntry> coincapEntries = new ArrayList<>();
        coincapRepository.get10LatestRecords().forEach(e -> coincapEntries.add(e));
        assertEquals(coincapEntries.size(), 0);
    }

    @Test
    public void testSave() {
        CoincapEntry coincapEntry = getEntry();
        coincapRepository.save(coincapEntry);
        CoincapEntry found = coincapRepository.get10LatestRecords().get(0);
        assertEquals(coincapEntry.getId(), found.getId());
    }

    private CoincapEntry getEntry() {
        CoincapEntry entry = new CoincapEntry();
        entry.setId(1);
        entry.setSymbol("BTC");
        entry.setPrice(BigDecimal.valueOf(33402.1702931233230629004538059234619140625));
        entry.setDate(LocalDateTime.parse("2021-06-25T00:00"));
        return entry;
    }
}
