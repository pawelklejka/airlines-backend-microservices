package com.airlinesmicroservices.tourist.service;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface DateParserService {
    LocalDateTime parseDateTimeFromString(String date);
    LocalDate parseDateFromString(String date);
}
