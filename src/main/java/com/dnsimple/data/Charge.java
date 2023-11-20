package com.dnsimple.data;

import com.dnsimple.data.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Charge {
    private final String invoicedAt;
    private final BigDecimal totalAmount;
    private final BigDecimal balanceAmount;
    private final String reference;
    private final String state;
    private final List<ChargeItem> items;

    public Charge(String invoicedAt, String totalAmount, String balanceAmount, String reference, String state, List<ChargeItem> items) {
        this.invoicedAt = invoicedAt;
        this.totalAmount = new BigDecimal(totalAmount);
        this.balanceAmount = new BigDecimal(balanceAmount);
        this.reference = reference;
        this.state = state;
        this.items = items;
    }

    public String getInvoicedAt() {
        return this.invoicedAt;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return this.balanceAmount;
    }

    public String getReference() {
        return this.reference;
    }

    public String getState() {
        return this.state;
    }

    public List<ChargeItem> getItems() {
        return this.items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charge charge = (Charge) o;
        return Objects.equals(invoicedAt, charge.invoicedAt)
                && Objects.equals(totalAmount, charge.totalAmount)
                && Objects.equals(balanceAmount, charge.balanceAmount)
                && Objects.equals(reference, charge.reference)
                && Objects.equals(state, charge.state)
                && Objects.equals(items, charge.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoicedAt, totalAmount, balanceAmount, reference, state, items);
    }
}
    