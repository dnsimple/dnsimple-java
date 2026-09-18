package com.dnsimple.data;

import java.util.List;

public class ZoneRecordBatchChange {
    private final List<ZoneRecord> creates;
    private final List<ZoneRecord> updates;
    private final List<ZoneRecordBatchDelete> deletes;

    public ZoneRecordBatchChange(List<ZoneRecord> creates, List<ZoneRecord> updates, List<ZoneRecordBatchDelete> deletes) {
        this.creates = creates;
        this.updates = updates;
        this.deletes = deletes;
    }

    public List<ZoneRecord> getCreates() {
        return creates;
    }

    public List<ZoneRecord> getUpdates() {
        return updates;
    }

    public List<ZoneRecordBatchDelete> getDeletes() {
        return deletes;
    }
}
