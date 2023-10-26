
package com.dnsimple.data;

import com.dnsimple.data.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChargeItem {
    private final String description;
    private final String amount;
    private final Long productId;
    private final String productType;
    private final String productReference;

    public ChargeItem(String description, String amount, Long productId, String productType, String productReference) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargeItem that = (ChargeItem) o;
        return Objects.equals(description, that.description)
                && Objects.equals(amount, that.amount)
                && Objects.equals(productId, that.productId)
                && Objects.equals(productType, that.productType)
                && Objects.equals(productReference, that.productReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, productId, productType, productReference);
    }
}
    