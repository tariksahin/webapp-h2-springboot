package org.example.demo.repository;

import org.example.demo.CoincapEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CoincapRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(CoincapEntry coincapEntry) {
        try {
            jdbcTemplate.update("insert into COIN_PRICE_HISTORY (symbol, price, date) values(?, ?, ?)",
                    new Object[]{"BTC", coincapEntry.getPrice(), coincapEntry.getDate()});
        } catch (DuplicateKeyException e) {
            //do nothing and don't save this record
        }
    }

    public List<CoincapEntry> get10LatestRecords() {

        return jdbcTemplate.query("select * from COIN_PRICE_HISTORY order by date desc FETCH FIRST 10 ROWS ONLY", new CoincapRecordRowMapper());
    }


    public void deleteById(int id) {
        jdbcTemplate.update("delete from COIN_PRICE_HISTORY where id = ?", id);
    }
}
