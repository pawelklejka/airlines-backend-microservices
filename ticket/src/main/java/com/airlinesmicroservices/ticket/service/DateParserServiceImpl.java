package com.airlinesmicroservices.ticket.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
public class DateParserServiceImpl implements DateParserService{

    @Override
    public LocalDateTime parseDateTimeFromString(String date) {

        String[] dateAndTime = date.split("T");
        System.out.println(date);
        Stream.of(dateAndTime).forEach(System.out::println);
        String[] startDate = dateAndTime[0].split("-");
        String[] startTime = dateAndTime[1].split(":");

        Stream.of(startDate).forEach(System.out::println);
        Stream.of(startTime).forEach(System.out::println);

        Integer[] startIntegerTime = new Integer[22];
        Integer[] startIntegerDate = new Integer[3];

        for(int c = 0; c < startDate.length; c++) {
            startIntegerDate[c] = Integer.parseInt(startDate[c]);
        }
        for(int c = 0; c < startTime.length; c++){
            startIntegerTime[c] = Integer.parseInt(startTime[c]);
        }
        return LocalDateTime.of(startIntegerDate[0],startIntegerDate[1],startIntegerDate[2],startIntegerTime[0],startIntegerTime[1]);
    }

    @Override
    public LocalDate parseDateFromString(String date) {
        System.out.println(date);
        String[] dateOfBirth = date.split("-");

        Integer[] startIntegerDate = new Integer[3];

        for(int c = 0; c < dateOfBirth.length; c++) {
            startIntegerDate[c] = Integer.parseInt(dateOfBirth[c]);
        }
        return LocalDate.of(startIntegerDate[0], startIntegerDate[1], startIntegerDate[2]);
    }
}
