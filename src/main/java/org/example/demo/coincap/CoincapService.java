package org.example.demo.coincap;

import org.example.demo.CoincapEntry;
import java.util.List;

public interface CoincapService {

    List<CoincapEntry> fetchSomeRecords();

    List<CoincapEntry> get10Latest();

    void deleteById(int id);

}
