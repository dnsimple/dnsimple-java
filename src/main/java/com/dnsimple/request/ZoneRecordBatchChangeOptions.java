package com.dnsimple.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class ZoneRecordBatchChangeOptions {
    private final List<Map<String, Object>> creates;
    private final List<Map<String, Object>> updates;
    private final List<Map<String, Object>> deletes;

    private ZoneRecordBatchChangeOptions(List<Map<String, Object>> creates, List<Map<String, Object>> updates, List<Map<String, Object>> deletes) {
        this.creates = creates;
        this.updates = updates;
        this.deletes = deletes;
    }

    public static ZoneRecordBatchChangeOptions empty() {
        return new ZoneRecordBatchChangeOptions(emptyList(), emptyList(), emptyList());
    }

    /**
     * Add a record to create
     */
    public ZoneRecordBatchChangeOptions create(ZoneRecordOptions record) {
        return new ZoneRecordBatchChangeOptions(append(creates, record.asPayload()), updates, deletes);
    }

    /**
     * Add a record to update
     */
    public ZoneRecordBatchChangeOptions update(Number id, ZoneRecordUpdateOptions record) {
        var payload = record.asPayload();
        payload.put("id", id);
        return new ZoneRecordBatchChangeOptions(creates, append(updates, payload), deletes);
    }

    /**
     * Add a record to delete
     */
    public ZoneRecordBatchChangeOptions delete(Number id) {
        return new ZoneRecordBatchChangeOptions(creates, updates, append(deletes, Map.<String, Object>of("id", id)));
    }

    /**
     * @return a map with this object's attributes and prunes the empty operation lists
     */
    public Map<String, Object> asPayload() {
        var map = new HashMap<String, Object>();
        if (!creates.isEmpty())
            map.put("creates", creates);
        if (!updates.isEmpty())
            map.put("updates", updates);
        if (!deletes.isEmpty())
            map.put("deletes", deletes);
        return map;
    }

    private static List<Map<String, Object>> append(List<Map<String, Object>> list, Map<String, Object> item) {
        var result = new ArrayList<>(list);
        result.add(item);
        return result;
    }
}
