package com.komodo.personservice.dto;

import com.komodo.personservice.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingAcknowledgement {

    private String status;
    private double totalFare;
    private String pnrNo;
    private Person person;
}
