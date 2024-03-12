package com.dnsimple.endpoints;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.CertificateBundle;
import com.dnsimple.data.CertificatePurchase;
import com.dnsimple.data.CertificateRenewal;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.CertificatePurchaseOptions;
import com.dnsimple.request.CertificateRenewalPurchaseOptions;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.SignatureAlgorithm;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.GET;
import static com.dnsimple.http.HttpMethod.POST;
import static com.dnsimple.tools.CustomMatchers.number;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CertificatesTest extends DnsimpleTestBase {
    @Test
    public void testListCertificatesSupportsPagination() {
        client.certificates.listCertificates(1, "example.com", ListOptions.empty().page(1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/certificates?page=1"));
    }

    @Test
    public void testListCertificatesSupportsExtraRequestOptions() {
        client.certificates.listCertificates(1, "example.com", ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/certificates?foo=bar"));
    }

    @Test
    public void testListCertificatesSupportsSorting() {
        client.certificates.listCertificates(1, "example.com", ListOptions.empty().sortAsc("expires_on"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/certificates?sort=expires_on%3Aasc"));
    }

    @Test
    public void testListCertificatesProducesCertificateList() {
        server.stubFixtureAt("listCertificates/success.http");
        List<Certificate> certificates = client.certificates.listCertificates(1, "dnsimple.us").getData();
        assertThat(certificates, hasSize(2));
        assertThat(certificates.get(0).getId(), is(101973L));
    }

    @Test
    public void testListCertificatesExposesPaginationInfo() {
        server.stubFixtureAt("listCertificates/success.http");
        PaginatedResponse<Certificate> certificates = client.certificates.listCertificates(1, "bingo.pizza");
        assertThat(certificates.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetCertificate() {
        server.stubFixtureAt("getCertificate/success.http");
        SimpleResponse<Certificate> response = client.certificates.getCertificate(1010, "bingo.pizza", 101967);
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/101967"));
        Certificate certificate = response.getData();
        assertThat(certificate.getId(), is(101967L));
        assertThat(certificate.getDomainId(), is(289333L));
        assertThat(certificate.getCommonName(), is("www.bingo.pizza"));
        assertThat(certificate.getAlternateNames(), is(empty()));
        assertThat(certificate.getYears(), is(1));
        assertThat(certificate.getState(), is("issued"));
        assertThat(certificate.getAuthorityIdentifier(), is("letsencrypt"));
        assertThat(certificate.hasAutoRenew(), is(false));
        assertThat(certificate.getCreatedAt(), is(OffsetDateTime.of(2020, 6, 18, 18, 54, 17, 0, UTC)));
        assertThat(certificate.getUpdatedAt(), is(OffsetDateTime.of(2020, 6, 18, 19, 10, 14, 0, UTC)));
        assertThat(certificate.getExpiresAt(), is(OffsetDateTime.of(2020, 9, 16, 18, 10, 13, 0, UTC)));
        assertThat(certificate.getCertificateRequest(), is("" +
                "-----BEGIN CERTIFICATE REQUEST-----\n" +
                "MIICmTCCAYECAQAwGjEYMBYGA1UEAwwPd3d3LmJpbmdvLnBpenphMIIBIjANBgkq\n" +
                "hkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw4+KoZ9IDCK2o5qAQpi+Icu5kksmjQzx\n" +
                "5o5g4B6XhRxhsfHlK/i3iU5hc8CONjyVv8j82835RNsiKrflnxGa9SH68vbQfcn4\n" +
                "IpbMz9c+Eqv5h0Euqlc3A4DBzp0unEu5QAUhR6Xu1TZIWDPjhrBOGiszRlLQcp4F\n" +
                "zy6fD6j5/d/ylpzTp5v54j+Ey31Bz86IaBPtSpHI+Qk87Hs8DVoWxZk/6RlAkyur\n" +
                "XDGWnPu9n3RMfs9ag5anFhggLIhCNtVN4+0vpgPQ59pqwYo8TfdYzK7WSKeL7geu\n" +
                "CqVE3bHAqU6dLtgHOZfTkLwGycUh4p9aawuc6fsXHHYDpIL8s3vAvwIDAQABoDow\n" +
                "OAYJKoZIhvcNAQkOMSswKTAnBgNVHREEIDAeggtiaW5nby5waXp6YYIPd3d3LmJp\n" +
                "bmdvLnBpenphMA0GCSqGSIb3DQEBCwUAA4IBAQBwOLKv+PO5hSJkgqS6wL/wRqLh\n" +
                "Q1zbcHRHAjRjnpRz06cDvN3X3aPI+lpKSNFCI0A1oKJG7JNtgxX3Est66cuO8ESQ\n" +
                "PIb6WWN7/xlVlBCe7ZkjAFgN6JurFdclwCp/NI5wBCwj1yb3Ar5QQMFIZOezIgTI\n" +
                "AWkQSfCmgkB96d6QlDWgidYDDjcsXugQveOQRPlHr0TsElu47GakxZdJCFZU+WPM\n" +
                "odQQf5SaqiIK2YaH1dWO//4KpTS9QoTy1+mmAa27apHcmz6X6+G5dvpHZ1qH14V0\n" +
                "JoMWIK+39HRPq6mDo1UMVet/xFUUrG/H7/tFlYIDVbSpVlpVAFITd/eQkaW/\n" +
                "-----END CERTIFICATE REQUEST-----\n"));
    }

    @Test
    public void testGetCertificateWhenNotFound() {
        server.stubFixtureAt("notfound-certificate.http");
        assertThat(() -> client.certificates.getCertificate(1, "weppos.net", 2),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testDownloadCertificate() {
        server.stubFixtureAt("downloadCertificate/success.http");
        SimpleResponse<CertificateBundle> response = client.certificates.downloadCertificate(1010, "weppos.net", 1);
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/weppos.net/certificates/1/download"));
        CertificateBundle certificateBundle = response.getData();
        assertThat(certificateBundle.getServerCertificate(), is("" +
                "-----BEGIN CERTIFICATE-----\n" +
                "MIIE7TCCA9WgAwIBAgITAPpTe4O3vjuQ9L4gLsogi/ukujANBgkqhkiG9w0BAQsF\n" +
                "ADAiMSAwHgYDVQQDDBdGYWtlIExFIEludGVybWVkaWF0ZSBYMTAeFw0xNjA2MTEx\n" +
                "NzQ4MDBaFw0xNjA5MDkxNzQ4MDBaMBkxFzAVBgNVBAMTDnd3dy53ZXBwb3MubmV0\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzCcMfWoQRt5AMEY0HUb\n" +
                "2GaraL1GsWOo6YXdPfe+YDvtnmDw23NcoTX7VSeCgU9M3RKs19AsCJcRNTLJ2dmD\n" +
                "rAuyCTud9YTAaXQcTOLUhtO8T8+9AFVIva2OmAlKCR5saBW3JaRxW7V2aHEd/d1s\n" +
                "s1CvNOO7jNppc9NwGSnDHcn3rqNv/U3MaU0gpJJRqsKkvcLU6IHJGgxyQ6AbpwJD\n" +
                "IqBnzkjHu2IuhGEbRuMjyWLA2qtsjyVlfPotDxUdVouUQpz7dGHUFrLR7ma8QAYu\n" +
                "Ofl1ZMyrc901HGMa7zwbnFWurs3fed7vAosTRZIjnn72/3Wo7L9RiMB+vwr3NX7c\n" +
                "9QIDAQABo4ICIzCCAh8wDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUF\n" +
                "BwMBBggrBgEFBQcDAjAMBgNVHRMBAf8EAjAAMB0GA1UdDgQWBBRh9q/3Zxbk4yA/\n" +
                "t7j+8xA+rkiZBTAfBgNVHSMEGDAWgBTAzANGuVggzFxycPPhLssgpvVoOjB4Bggr\n" +
                "BgEFBQcBAQRsMGowMwYIKwYBBQUHMAGGJ2h0dHA6Ly9vY3NwLnN0Zy1pbnQteDEu\n" +
                "bGV0c2VuY3J5cHQub3JnLzAzBggrBgEFBQcwAoYnaHR0cDovL2NlcnQuc3RnLWlu\n" +
                "dC14MS5sZXRzZW5jcnlwdC5vcmcvMCUGA1UdEQQeMByCCndlcHBvcy5uZXSCDnd3\n" +
                "dy53ZXBwb3MubmV0MIH+BgNVHSAEgfYwgfMwCAYGZ4EMAQIBMIHmBgsrBgEEAYLf\n" +
                "EwEBATCB1jAmBggrBgEFBQcCARYaaHR0cDovL2Nwcy5sZXRzZW5jcnlwdC5vcmcw\n" +
                "gasGCCsGAQUFBwICMIGeDIGbVGhpcyBDZXJ0aWZpY2F0ZSBtYXkgb25seSBiZSBy\n" +
                "ZWxpZWQgdXBvbiBieSBSZWx5aW5nIFBhcnRpZXMgYW5kIG9ubHkgaW4gYWNjb3Jk\n" +
                "YW5jZSB3aXRoIHRoZSBDZXJ0aWZpY2F0ZSBQb2xpY3kgZm91bmQgYXQgaHR0cHM6\n" +
                "Ly9sZXRzZW5jcnlwdC5vcmcvcmVwb3NpdG9yeS8wDQYJKoZIhvcNAQELBQADggEB\n" +
                "AEqMdWrmdIyQxthWsX3iHmM2h/wXwEesD0VIaA+Pq4mjwmKBkoPSmHGQ/O4v8RaK\n" +
                "B6gl8v+qmvCwwqC1SkBmm+9C2yt/P6WhAiA/DD+WppYgJWfcz2lEKrgufFlHPukB\n" +
                "DzE0mJDuXm09QTApWlaTZWYfWKY50T5uOT/rs+OwGFFCO/8o7v5AZRAHos6uzjvq\n" +
                "AtFZj/FEnXXMjSSlQ7YKTXToVpnAYH4e3/UMsi6/O4orkVz82ZfhKwMWHV8dXlRw\n" +
                "tQaemFWTjGPgSLXJAtQO30DgNJBHX/fJEaHv6Wy8TF3J0wOGpzGbOwaTX8YAmEzC\n" +
                "lzzjs+clg5MN5rd1g4POJtU=\n" +
                "-----END CERTIFICATE-----\n"));
        assertThat(certificateBundle.getRootCertificate(), is(isEmptyOrNullString()));
        assertThat(certificateBundle.getIntermediateCertificates(), hasSize(1));
        assertThat(certificateBundle.getIntermediateCertificates().get(0), is("" +
                "-----BEGIN CERTIFICATE-----\n" +
                "MIIEqzCCApOgAwIBAgIRAIvhKg5ZRO08VGQx8JdhT+UwDQYJKoZIhvcNAQELBQAw\n" +
                "GjEYMBYGA1UEAwwPRmFrZSBMRSBSb290IFgxMB4XDTE2MDUyMzIyMDc1OVoXDTM2\n" +
                "MDUyMzIyMDc1OVowIjEgMB4GA1UEAwwXRmFrZSBMRSBJbnRlcm1lZGlhdGUgWDEw\n" +
                "ggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDtWKySDn7rWZc5ggjz3ZB0\n" +
                "8jO4xti3uzINfD5sQ7Lj7hzetUT+wQob+iXSZkhnvx+IvdbXF5/yt8aWPpUKnPym\n" +
                "oLxsYiI5gQBLxNDzIec0OIaflWqAr29m7J8+NNtApEN8nZFnf3bhehZW7AxmS1m0\n" +
                "ZnSsdHw0Fw+bgixPg2MQ9k9oefFeqa+7Kqdlz5bbrUYV2volxhDFtnI4Mh8BiWCN\n" +
                "xDH1Hizq+GKCcHsinDZWurCqder/afJBnQs+SBSL6MVApHt+d35zjBD92fO2Je56\n" +
                "dhMfzCgOKXeJ340WhW3TjD1zqLZXeaCyUNRnfOmWZV8nEhtHOFbUCU7r/KkjMZO9\n" +
                "AgMBAAGjgeMwgeAwDgYDVR0PAQH/BAQDAgGGMBIGA1UdEwEB/wQIMAYBAf8CAQAw\n" +
                "HQYDVR0OBBYEFMDMA0a5WCDMXHJw8+EuyyCm9Wg6MHoGCCsGAQUFBwEBBG4wbDA0\n" +
                "BggrBgEFBQcwAYYoaHR0cDovL29jc3Auc3RnLXJvb3QteDEubGV0c2VuY3J5cHQu\n" +
                "b3JnLzA0BggrBgEFBQcwAoYoaHR0cDovL2NlcnQuc3RnLXJvb3QteDEubGV0c2Vu\n" +
                "Y3J5cHQub3JnLzAfBgNVHSMEGDAWgBTBJnSkikSg5vogKNhcI5pFiBh54DANBgkq\n" +
                "hkiG9w0BAQsFAAOCAgEABYSu4Il+fI0MYU42OTmEj+1HqQ5DvyAeyCA6sGuZdwjF\n" +
                "UGeVOv3NnLyfofuUOjEbY5irFCDtnv+0ckukUZN9lz4Q2YjWGUpW4TTu3ieTsaC9\n" +
                "AFvCSgNHJyWSVtWvB5XDxsqawl1KzHzzwr132bF2rtGtazSqVqK9E07sGHMCf+zp\n" +
                "DQVDVVGtqZPHwX3KqUtefE621b8RI6VCl4oD30Olf8pjuzG4JKBFRFclzLRjo/h7\n" +
                "IkkfjZ8wDa7faOjVXx6n+eUQ29cIMCzr8/rNWHS9pYGGQKJiY2xmVC9h12H99Xyf\n" +
                "zWE9vb5zKP3MVG6neX1hSdo7PEAb9fqRhHkqVsqUvJlIRmvXvVKTwNCP3eCjRCCI\n" +
                "PTAvjV+4ni786iXwwFYNz8l3PmPLCyQXWGohnJ8iBm+5nk7O2ynaPVW0U2W+pt2w\n" +
                "SVuvdDM5zGv2f9ltNWUiYZHJ1mmO97jSY/6YfdOUH66iRtQtDkHBRdkNBsMbD+Em\n" +
                "2TgBldtHNSJBfB3pm9FblgOcJ0FSWcUDWJ7vO0+NTXlgrRofRT6pVywzxVo6dND0\n" +
                "WzYlTWeUVsO40xJqhgUQRER9YLOLxJ0O6C8i0xFxAMKOtSdodMB3RIwt7RFQ0uyt\n" +
                "n5Z5MqkYhlMI3J1tPRTp1nEt9fyGspBOO05gi148Qasp+3N+svqKomoQglNoAxU=\n" +
                "-----END CERTIFICATE-----"));
    }

    @Test
    public void testCertificatePrivateKey() {
        server.stubFixtureAt("getCertificatePrivateKey/success.http");
        SimpleResponse<CertificateBundle> response = client.certificates.getCertificatePrivateKey(1010, "weppos.net", 1);
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/weppos.net/certificates/1/private_key"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPurchaseLetsencryptCertificate() {
        server.stubFixtureAt("purchaseLetsencryptCertificate/success.http");
        CertificatePurchaseOptions options = CertificatePurchaseOptions.of("www").autoRenew().alternateNames("web", "theweb").signatureAlgorithm(SignatureAlgorithm.ECDSA);
        SimpleResponse<CertificatePurchase> response = client.certificates.purchaseLetsencryptCertificate(1010, "bingo.pizza", options);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt"));
        Map<String, Object> payload = server.getRecordedRequest().getJsonObjectPayload();
        assertThat(payload, hasEntry("auto_renew", true));
        assertThat(payload, hasEntry("name", "www"));
        // Hamcrest can't reconcile the types involved in this matcher
        // because Object (the map's value type) can't be coerced into
        // Iterable (the type enforced by the `contains` matcher). That
        // means that we can't do the following assertion:
        //   assertThat(payload, hasEntry("alternate_names", contains("www", "blabla")));
        //
        // Instead, we check the key and value in separated assertions,
        // casting values as needed. This produces an UncheckedCast warning
        // that can be safely ignored.
        assertThat(payload, hasKey("alternate_names"));
        assertThat((List<String>) payload.get("alternate_names"), contains("web", "theweb"));
        CertificatePurchase purchase = response.getData();
        assertThat(purchase.getId(), is(101967L));
        assertThat(purchase.getCertificateId(), is(101967L));
        assertThat(purchase.getState(), is("new"));
        assertThat(purchase.hasAutoRenew(), is(false));
        assertThat(purchase.getCreatedAt(), is(OffsetDateTime.of(2020, 6, 18, 18, 54, 17, 0, UTC)));
        assertThat(purchase.getUpdatedAt(), is(OffsetDateTime.of(2020, 6, 18, 18, 54, 17, 0, UTC)));
    }

    @Test
    public void testIssueLetsencryptCertificate() {
        server.stubFixtureAt("issueLetsencryptCertificate/success.http");
        SimpleResponse<Certificate> response = client.certificates.issueLetsencryptCertificate(1010, "bingo.pizza", 101967);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt/101967/issue"));
        Certificate certificate = response.getData();
        assertThat(certificate.getId(), is(101967L));
        assertThat(certificate.getDomainId(), is(289333L));
    }

    @Test
    public void testPurchaseLetsencryptCertificateRenewal() {
        server.stubFixtureAt("purchaseRenewalLetsencryptCertificate/success.http");
        SimpleResponse<CertificateRenewal> response = client.certificates.purchaseLetsencryptCertificateRenewal(1010, "bingo.pizza", 101967, CertificateRenewalPurchaseOptions.empty().autoRenew().signatureAlgorithm(SignatureAlgorithm.RSA));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt/101967/renewals"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), hasEntry("auto_renew", true));
        CertificateRenewal renewal = response.getData();
        assertThat(renewal.getId(), is(65082L));
        assertThat(renewal.getOldCertificateId(), is(101967L));
        assertThat(renewal.getNewCertificateId(), is(101972L));
        assertThat(renewal.getState(), is("new"));
        assertThat(renewal.hasAutoRenew(), is(false));
        assertThat(renewal.getCreatedAt(), is(OffsetDateTime.of(2020, 6, 18, 19, 56, 20, 0, UTC)));
        assertThat(renewal.getUpdatedAt(), is(OffsetDateTime.of(2020, 6, 18, 19, 56, 20, 0, UTC)));
    }

    @Test
    public void testIssueLetsencryptCertificateRenewal() {
        server.stubFixtureAt("issueLetsencryptCertificate/success.http");
        SimpleResponse<Certificate> response = client.certificates.issueLetsencryptCertificateRenewal(1010, "bingo.pizza", 101967, 65082);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt/101967/renewals/65082/issue"));
        Certificate certificate = response.getData();
        assertThat(certificate.getId(), is(101967L));
        assertThat(certificate.getDomainId(), is(289333L));
    }
}
