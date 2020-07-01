package com.dnsimple;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.GET;
import static com.dnsimple.tools.HttpMethod.POST;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.CertificateBundle;
import com.dnsimple.data.CertificatePurchase;
import com.dnsimple.data.CertificateRenewal;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.response.DownloadCertificateResponse;
import com.dnsimple.response.GetCertificatePrivateKeyResponse;
import com.dnsimple.response.GetCertificateResponse;
import com.dnsimple.response.IssueLetsencryptRenewalResponse;
import com.dnsimple.response.IssueLetsencryptResponse;
import com.dnsimple.response.ListCertificatesResponse;
import com.dnsimple.response.PurchaseLetsencryptRenewalResponse;
import com.dnsimple.response.PurchaseLetsencryptResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

public class CertificatesTest extends DnsimpleTestBase {

  @Test
  public void testListCertificatesSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
    client.certificates.listCertificates("1", "example.com", singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/certificates?page=1"));
  }

  @Test
  public void testListCertificatesSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
    client.certificates.listCertificates("1", "example.com", singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/certificates?foo=bar"));
  }

  @Test
  public void testListCertificatesSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
    client.certificates.listCertificates("1", "example.com", singletonMap("sort", "expires_on:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/certificates?sort=expires_on%3Aasc"));
  }

  @Test
  public void testListCertificatesProducesCertificateList() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("listCertificates/success.http");

    List<Certificate> certificates = client.certificates.listCertificates("1", "dnsimple.us").getData();
    assertThat(certificates, hasSize(2));
    assertThat(certificates.get(0).getId(), is(101973));
  }

  @Test
  public void testListCertificatesExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("listCertificates/success.http");

    ListCertificatesResponse certificates = client.certificates.listCertificates("1", "bingo.pizza");
    assertThat(certificates.getPagination().getCurrentPage(), is(1));
  }

  @Test
  public void testGetCertificate() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("getCertificate/success.http");

    GetCertificateResponse response = client.certificates.getCertificate("1010", "bingo.pizza", "101967");
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/101967"));

    Certificate certificate = response.getData();
    assertThat(certificate.getId(), is(101967));
    assertThat(certificate.getDomainId(), is(289333));
    assertThat(certificate.getName(), is("www"));
    assertThat(certificate.getCommonName(), is("www.bingo.pizza"));
    assertThat(certificate.getYears(), is(1));
    assertThat(certificate.getCsr(), is("" +
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
    assertThat(certificate.getState(), is("issued"));
    assertThat(certificate.getAuthorityIdentifier(), is("letsencrypt"));
    assertThat(certificate.getCreatedAt(), is("2020-06-18T18:54:17Z"));
    assertThat(certificate.getUpdatedAt(), is("2020-06-18T19:10:14Z"));
    assertThat(certificate.getExpiresOn(), is("2020-09-16"));
    assertThat(certificate.getExpiresAt(), is("2020-09-16T18:10:13Z"));
  }

  @Test
  public void testGetCertificateWhenNotFound() {
    server.stubFixtureAt("notfound-certificate.http");

    assertThat(() -> client.certificates.getCertificate("1", "weppos.net", "2"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testDownloadCertificate() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("downloadCertificate/success.http");

    DownloadCertificateResponse response = client.certificates.downloadCertificate("1010", "weppos.net", "1");
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/weppos.net/certificates/1/download"));

    CertificateBundle certificateBundle = response.getData();
    assertThat(certificateBundle.getPrivateKey(), is(nullValue()));
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
  public void testCertificatePrivateKey() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("getCertificatePrivateKey/success.http");

    GetCertificatePrivateKeyResponse response = client.certificates.getCertificatePrivateKey("1010", "weppos.net", "1");
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/weppos.net/certificates/1/private_key"));

    CertificateBundle certificateBundle = response.getData();
    assertThat(certificateBundle.getPrivateKey(), is("" +
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEowIBAAKCAQEAtzCcMfWoQRt5AMEY0HUb2GaraL1GsWOo6YXdPfe+YDvtnmDw\n" +
        "23NcoTX7VSeCgU9M3RKs19AsCJcRNTLJ2dmDrAuyCTud9YTAaXQcTOLUhtO8T8+9\n" +
        "AFVIva2OmAlKCR5saBW3JaRxW7V2aHEd/d1ss1CvNOO7jNppc9NwGSnDHcn3rqNv\n" +
        "/U3MaU0gpJJRqsKkvcLU6IHJGgxyQ6AbpwJDIqBnzkjHu2IuhGEbRuMjyWLA2qts\n" +
        "jyVlfPotDxUdVouUQpz7dGHUFrLR7ma8QAYuOfl1ZMyrc901HGMa7zwbnFWurs3f\n" +
        "ed7vAosTRZIjnn72/3Wo7L9RiMB+vwr3NX7c9QIDAQABAoIBAEQx32OlzK34GTKT\n" +
        "r7Yicmw7xEGofIGa1Q2h3Lut13whsxKLif5X0rrcyqRnoeibacS+qXXrJolIG4rP\n" +
        "Tl8/3wmUDQHs5J+6fJqFM+fXZUCP4AFiFzzhgsPBsVyd0KbWYYrZ0qU7s0ttoRe+\n" +
        "TGjuHgIe3ip1QKNtx2Xr50YmytDydknmro79J5Gfrub1l2iA8SDm1eBrQ4SFaNQ2\n" +
        "U709pHeSwX8pTihUX2Zy0ifpr0O1wYQjGLneMoG4rrNQJG/z6iUdhYczwwt1kDRQ\n" +
        "4WkM2sovFOyxbBfoCQ3Gy/eem7OXfjNKUe47DAVLnPkKbqL/3Lo9FD7kcB8K87Ap\n" +
        "r/vYrl0CgYEA413RAk7571w5dM+VftrdbFZ+Yi1OPhUshlPSehavro8kMGDEG5Ts\n" +
        "74wEz2X3cfMxauMpMrBk/XnUCZ20AnWQClK73RB5fzPw5XNv473Tt/AFmt7eLOzl\n" +
        "OcYrhpEHegtsD/ZaljlGtPqsjQAL9Ijhao03m1cGB1+uxI7FgacdckcCgYEAzkKP\n" +
        "6xu9+WqOol73cnlYPS3sSZssyUF+eqWSzq2YJGRmfr1fbdtHqAS1ZbyC5fZVNZYV\n" +
        "ml1vfXi2LDcU0qS04JazurVyQr2rJZMTlCWVET1vhik7Y87wgCkLwKpbwamPDmlI\n" +
        "9GY+fLNEa4yfAOOpvpTJpenUScxyKWH2cdYFOOMCgYBhrJnvffINC/d64Pp+BpP8\n" +
        "yKN+lav5K6t3AWd4H2rVeJS5W7ijiLTIq8QdPNayUyE1o+S8695WrhGTF/aO3+ZD\n" +
        "KQufikZHiQ7B43d7xL7BVBF0WK3lateGnEVyh7dIjMOdj92Wj4B6mv2pjQ2VvX/p\n" +
        "AEWVLCtg24/+zL64VgxmXQKBgGosyXj1Zu2ldJcQ28AJxup3YVLilkNje4AXC2No\n" +
        "6RCSvlAvm5gpcNGE2vvr9lX6YBKdl7FGt8WXBe/sysNEFfgmm45ZKOBCUn+dHk78\n" +
        "qaeeQHKHdxMBy7utZWdgSqt+ZS299NgaacA3Z9kVIiSLDS4V2VeW7riujXXP/9TJ\n" +
        "nxaRAoGBAMWXOfNVzfTyrKff6gvDWH+hqNICLyzvkEn2utNY9Q6WwqGuY9fvP/4Z\n" +
        "Xzc48AOBzUr8OeA4sHKJ79sJirOiWHNfD1swtvyVzsFZb6moiNwD3Ce/FzYCa3lQ\n" +
        "U8blTH/uqpR2pSC6whzJ/lnSdqHUqhyp00000000000000000000\n" +
        "-----END RSA PRIVATE KEY-----\n"));
  }

  @Test
  public void testPurchaseLetsencryptCertificate() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("purchaseLetsencryptCertificate/success.http");

    PurchaseLetsencryptResponse response = client.certificates.purchaseLetsencryptCertificate("1010", "bingo.pizza", new HashMap<String, Object>());
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt"));

    CertificatePurchase purchase = response.getData();
    assertThat(purchase.getId(), is(101967));
    assertThat(purchase.getCertificateId(), is(101967));
  }

  @Test
  public void testIssueLetsencryptCertificate() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("issueLetsencryptCertificate/success.http");

    IssueLetsencryptResponse response = client.certificates.issueLetsencryptCertificate("1010", "bingo.pizza", "101967");
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt/101967/issue"));

    Certificate certificate = response.getData();
    assertThat(certificate.getId(), is(101967));
    assertThat(certificate.getDomainId(), is(289333));
  }

  @Test
  public void testPurchaseLetsencryptCertificateRenewal() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("purchaseRenewalLetsencryptCertificate/success.http");

    PurchaseLetsencryptRenewalResponse response = client.certificates.purchaseLetsencryptCertificateRenewal("1010", "bingo.pizza", "101967", new HashMap<String, Object>());
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt/101967/renewals"));

    CertificateRenewal renewal = response.getData();
    assertThat(renewal.getId(), is(65082));
    assertThat(renewal.getOldCertificateId(), is(101967));
    assertThat(renewal.getNewCertificateId(), is(101972));
  }

  @Test
  public void testIssueLetsencryptCertificateRenewal() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("issueLetsencryptCertificate/success.http");

    IssueLetsencryptRenewalResponse response = client.certificates.issueLetsencryptCertificateRenewal("1010", "bingo.pizza", "101967", "65082");
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/bingo.pizza/certificates/letsencrypt/101967/renewals/65082/issue"));

    Certificate certificate = response.getData();
    assertThat(certificate.getId(), is(101967));
    assertThat(certificate.getDomainId(), is(289333));
  }
}
