package com.dnsimple.endpoints;

import com.dnsimple.data.RegistrantChange;
import com.dnsimple.data.RegistrantChangeCheck;
import com.dnsimple.request.CheckRegistrantChangeInput;
import com.dnsimple.request.CreateRegistrantChangeInput;
import com.dnsimple.request.ListOptions;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.dnsimple.http.HttpMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegistrarRegistrantChangeTest extends DnsimpleTestBase {
    @Test
    public void testListRegistrantChanges() {
        server.stubFixtureAt("listRegistrantChanges/success.http");
        var changes = client.registrar.listRegistrantChanges(101, ListOptions.empty()).getData();
        assertThat(changes, hasSize(1));
        var change = changes.get(0);
        assertThat(change, is(new RegistrantChange(
                101L,
                101L,
                101L,
                101L,
                "new",
                new HashMap<>(),
                true,
                null,
                "2017-02-03T17:43:22Z",
                "2017-02-03T17:43:22Z"
        )));
    }

    @Test
    public void testCreateRegistrantChange() {
        server.stubFixtureAt("createRegistrantChange/success.http");
        var change = client.registrar.createRegistrantChange(101, new CreateRegistrantChangeInput(
                "101",
                "101",
                new HashMap<>()
        )).getData();
        assertThat(change, is(new RegistrantChange(
                101L,
                101L,
                101L,
                101L,
                "new",
                new HashMap<>(),
                true,
                null,
                "2017-02-03T17:43:22Z",
                "2017-02-03T17:43:22Z"
        )));
    }

    @Test
    public void testCheckRegistrantChange() {
        server.stubFixtureAt("checkRegistrantChange/success.http");
        var change = client.registrar.checkRegistrantChange(101, new CheckRegistrantChangeInput(
                "101",
                "101"
        )).getData();
        assertThat(change, is(new RegistrantChangeCheck(
                101L,
                101L,
                new ArrayList<>(),
                true
        )));
    }

    @Test
    public void testGetRegistrantChange() {
        server.stubFixtureAt("getRegistrantChange/success.http");
        var change = client.registrar.getRegistrantChange(101, 101).getData();
        assertThat(change, is(new RegistrantChange(
                101L,
                101L,
                101L,
                101L,
                "new",
                new HashMap<>(),
                true,
                null,
                "2017-02-03T17:43:22Z",
                "2017-02-03T17:43:22Z"
        )));
    }

    @Test
    public void testDeleteRegistrantChange() {
        server.stubFixtureAt("deleteRegistrantChange/success.http");
        client.registrar.deleteRegistrantChange(101, 101);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
    }
}
