package com.dnsimple.data;

import java.util.Objects;

public class DomainTransferLock {
    private final Boolean enabled;

    public DomainTransferLock(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainTransferLock that = (DomainTransferLock) o;
        return Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled);
    }
}
