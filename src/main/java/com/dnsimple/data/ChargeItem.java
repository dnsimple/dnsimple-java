
package com.dnsimple.data;

import com.dnsimple.data.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChargeItem {
    private final String description;
    private final String amount;
    private final Number productId;
    private final String productType;
    private final String productReference;

    public ChargeItem(String description, String amount, Number productId, String productType, String productReference) {
        this.description = description;
        this.amount = amount;
        this.productId = productId;
        this.productType = productType;
        this.productReference = productReference;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAmount() {
        return this.amount;
    }

    public Number getProductId() {
        return this.productId;
    }

    public String getProductType() {
        return this.productType;
    }

    public String getProductReference() {
        return this.productReference;
    }
}
    