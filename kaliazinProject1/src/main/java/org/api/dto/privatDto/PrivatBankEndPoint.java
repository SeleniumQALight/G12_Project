package org.api.dto.privatDto;

public interface PrivatBankEndPoint {
    String EXCHANGE_RATES = "/exchange_rates?json&date={date}";
    String PUBINFO = "/pubinfo?json&exchange&coursid=5";
}

