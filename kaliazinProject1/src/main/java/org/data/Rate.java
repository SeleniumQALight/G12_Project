package org.data;

import java.math.BigDecimal;

public class Rate extends org.apache.poi.ss.formula.functions.Rate {private final BigDecimal buy;
    private final BigDecimal sale;

    public Rate(BigDecimal buy, BigDecimal sale) {
        this.buy = buy;
        this.sale = sale;
    }

    public BigDecimal getBuy() { return buy; }
    public BigDecimal getSale() { return sale; }

    @Override
    public String toString() {
        return "Rate{buy=" + buy + ", sale=" + sale + '}';
    }
}
