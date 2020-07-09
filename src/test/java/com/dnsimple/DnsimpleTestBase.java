package com.dnsimple;

import com.dnsimple.endpoints.http.HttpEndpointClient;
import com.dnsimple.endpoints.http.java11.Java11HttpRequestFactory;
import com.dnsimple.tools.TestHttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * A base class that DNSimple tests can inherit from to provide set everything
 * required to test expectations agains HTTP fixture files.
 */
public abstract class DnsimpleTestBase {
    protected static final String TEST_ACCESS_TOKEN = "test-access-token";
    protected static final String TEST_USER_AGENT = "test-user-agent";
    protected static TestHttpServer server;
    protected Client client;

    @BeforeClass
    public static void init() {
        server = new TestHttpServer(12345);
        server.start();
    }

    @AfterClass
    public static void tearDown() {
        server.stop();
    }

    @Before
    public void setUp() {
        server.reset();
        client = new Client.Builder()
                .apiBase(server.getBaseURL())
                .extraUserAgent(TEST_USER_AGENT)
                .accessToken(TEST_ACCESS_TOKEN)
                .build();
    }
}
