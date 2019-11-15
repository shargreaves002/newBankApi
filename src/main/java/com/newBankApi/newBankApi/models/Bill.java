package com.newBankApi.newBankApi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSS")
    private String creation_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("payment_date")
    @Column(name = "payment_date")
    private String paymentDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("recurring_date")
    @Column(name = "recurring_date")
    private String recurringDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("upcoming_payment_date")
    @Column(name = "upcoming_payment_date")
    private String upcomingPaymentDate;

    @JsonProperty("payment_amount")
    @Column(name = "payment_amount")
    private Double paymentAmount;


    @JsonProperty("account_id")
    @Column(name = "account_id")
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private BillStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date() {
        this.creation_date = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss.SSS").format(new Date());
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRecurringDate() {
        return recurringDate;
    }

    public void setRecurringDate(String recurringDate) {
        this.recurringDate = recurringDate;
    }

    public String getUpcomingPaymentDate() {
        return upcomingPaymentDate;
    }

    public void setUpcomingPaymentDate(String upcomingPaymentDate) {
        this.upcomingPaymentDate = upcomingPaymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }
}
