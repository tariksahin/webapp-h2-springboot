package org.example.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class CoincapEntry implements Serializable {

    private Integer id;
    private String symbol;
    private BigDecimal price;
    private LocalDateTime date;
}
