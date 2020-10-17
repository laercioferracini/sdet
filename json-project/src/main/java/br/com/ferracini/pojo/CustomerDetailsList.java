package br.com.ferracini.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;

import java.util.List;

public class CustomerDetailsList {
    private List<CustomerDetails> customerDetailsList;

    public List<CustomerDetails> getData() {
        return customerDetailsList;
    }

    public void setData(List<CustomerDetails> customerDetails) {
        this.customerDetailsList = customerDetails;
    }

    @Override
    public String toString() {
        return Try.of(() -> new ObjectMapper().writeValueAsString(this)).getOrElse("{}");
    }
}