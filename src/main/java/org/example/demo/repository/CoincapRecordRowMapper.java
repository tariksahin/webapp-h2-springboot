package org.example.demo.repository;

import org.example.demo.CoincapEntry;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoincapRecordRowMapper implements RowMapper<CoincapEntry> {

    @Override
    public CoincapEntry mapRow(ResultSet rs, int rowNum) throws SQLException {

        final CoincapEntry coincapEntry = new CoincapEntry();
        coincapEntry.setId(rs.getInt("ID"));
        coincapEntry.setSymbol(rs.getString("SYMBOL"));
        coincapEntry.setPrice(rs.getBigDecimal("PRICE"));
        coincapEntry.setDate(rs.getTimestamp("DATE").toLocalDateTime());

        return coincapEntry;
    }
}
