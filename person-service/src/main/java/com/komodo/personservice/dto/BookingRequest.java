package com.komodo.personservice.dto;

import com.komodo.personservice.entity.PaymentInfo;
import com.komodo.personservice.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private Person person;

    private PaymentInfo paymentInfo;
}
