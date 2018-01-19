package com.dnsimple;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.CertificateBundle;
import com.dnsimple.data.CertificatePurchase;
import com.dnsimple.data.CertificateRenewal;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListCertificatesResponse;
import com.dnsimple.response.GetCertificateResponse;
import com.dnsimple.response.DownloadCertificateResponse;
import com.dnsimple.response.GetCertificatePrivateKeyResponse;
import com.dnsimple.response.PurchaseLetsencryptResponse;
import com.dnsimple.response.IssueLetsencryptResponse;
import com.dnsimple.response.PurchaseLetsencryptRenewalResponse;
import com.dnsimple.response.IssueLetsencryptRenewalResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CertificatesTest extends DnsimpleTestBase {

  @Test
  public void testListCertificatesSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/certificates?page=1");

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);

    client.certificates.listCertificates(accountId, domainId, options);
  }

  @Test
  public void testListCertificatesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/certificates?foo=bar");

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");

    client.certificates.listCertificates(accountId, domainId, options);
  }

  @Test
  public void testListCertificatesSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/certificates?sort=expires_on%3Aasc");

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "expires_on:asc");

    client.certificates.listCertificates(accountId, domainId, options);
  }

  @Test
  public void testListCertificatesProducesCertificateList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listCertificates/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListCertificatesResponse response = client.certificates.listCertificates(accountId, domainId);

    List<Certificate> certificates = response.getData();
    assertEquals(2, certificates.size());
    assertEquals(1, certificates.get(0).getId().intValue());
  }

  @Test
  public void testListCertificatesExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listCertificates/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListCertificatesResponse response = client.certificates.listCertificates(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetCertificate() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/1", HttpMethods.GET, resource("getCertificate/success.http"));

    String accountId = "1010";
    String domainId = "weppos.net";
    String certificateId = "1";

    GetCertificateResponse response = client.certificates.getCertificate(accountId, domainId, certificateId);

    Certificate certificate = response.getData();
    assertEquals(1, certificate.getId().intValue());
    assertEquals(2, certificate.getDomainId().intValue());
    assertEquals("www", certificate.getName());
    assertEquals("www.weppos.net", certificate.getCommonName());
    assertEquals(1, certificate.getYears().intValue());
    assertEquals("-----BEGIN CERTIFICATE REQUEST-----\nMIICljCCAX4CAQAwGTEXMBUGA1UEAwwOd3d3LndlcHBvcy5uZXQwggEiMA0GCSqG\nSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC3MJwx9ahBG3kAwRjQdRvYZqtovUaxY6jp\nhd09975gO+2eYPDbc1yhNftVJ4KBT0zdEqzX0CwIlxE1MsnZ2YOsC7IJO531hMBp\ndBxM4tSG07xPz70AVUi9rY6YCUoJHmxoFbclpHFbtXZocR393WyzUK8047uM2mlz\n03AZKcMdyfeuo2/9TcxpTSCkklGqwqS9wtTogckaDHJDoBunAkMioGfOSMe7Yi6E\nYRtG4yPJYsDaq2yPJWV8+i0PFR1Wi5RCnPt0YdQWstHuZrxABi45+XVkzKtz3TUc\nYxrvPBucVa6uzd953u8CixNFkiOefvb/dajsv1GIwH6/Cvc1ftz1AgMBAAGgODA2\nBgkqhkiG9w0BCQ4xKTAnMCUGA1UdEQQeMByCDnd3dy53ZXBwb3MubmV0ggp3ZXBw\nb3MubmV0MA0GCSqGSIb3DQEBCwUAA4IBAQCDnVBO9RdJX0eFeZzlv5c8yG8duhKP\nl0Vl+V88fJylb/cbNj9qFPkKTK0vTXmS2XUFBChKPtLucp8+Z754UswX+QCsdc7U\nTTSG0CkyilcSubdZUERGej1XfrVQhrokk7Fu0Jh3BdT6REP0SIDTpA8ku/aRQiAp\np+h19M37S7+w/DMGDAq2LSX8jOpJ1yIokRDyLZpmwyLxutC21DXMGoJ3xZeUFrUT\nqRNwzkn2dJzgTrPkzhaXalUBqv+nfXHqHaWljZa/O0NVCFrHCdTdd53/6EE2Yabv\nq5SFTkRCpaxrvM/7a8Tr4ixD1/VKD6rw3+WC00000000000000000000\n-----END CERTIFICATE REQUEST-----\n", certificate.getCsr());
    assertEquals("issued", certificate.getState());
    assertEquals("letsencrypt", certificate.getAuthorityIdentifier());
    assertEquals("2016-06-11T18:47:08Z", certificate.getCreatedAt());
    assertEquals("2016-06-11T18:47:37Z", certificate.getUpdatedAt());
    assertEquals("2016-09-09", certificate.getExpiresOn());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetCertificateWhenNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-certificate.http"));

    String accountId = "1";
    String domainId = "weppos.net";
    String certificateId = "2";

    client.certificates.getCertificate(accountId, domainId, certificateId);
  }

  @Test
  public void testDownloadCertificate() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/1/download", HttpMethods.GET, resource("downloadCertificate/success.http"));

    String accountId = "1010";
    String domainId = "weppos.net";
    String certificateId = "1";

    DownloadCertificateResponse response = client.certificates.downloadCertificate(accountId, domainId, certificateId);

    CertificateBundle certificateBundle = response.getData();
    assertEquals(null, certificateBundle.getPrivateKey());
    assertEquals("-----BEGIN CERTIFICATE-----\nMIIE7TCCA9WgAwIBAgITAPpTe4O3vjuQ9L4gLsogi/ukujANBgkqhkiG9w0BAQsF\nADAiMSAwHgYDVQQDDBdGYWtlIExFIEludGVybWVkaWF0ZSBYMTAeFw0xNjA2MTEx\nNzQ4MDBaFw0xNjA5MDkxNzQ4MDBaMBkxFzAVBgNVBAMTDnd3dy53ZXBwb3MubmV0\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzCcMfWoQRt5AMEY0HUb\n2GaraL1GsWOo6YXdPfe+YDvtnmDw23NcoTX7VSeCgU9M3RKs19AsCJcRNTLJ2dmD\nrAuyCTud9YTAaXQcTOLUhtO8T8+9AFVIva2OmAlKCR5saBW3JaRxW7V2aHEd/d1s\ns1CvNOO7jNppc9NwGSnDHcn3rqNv/U3MaU0gpJJRqsKkvcLU6IHJGgxyQ6AbpwJD\nIqBnzkjHu2IuhGEbRuMjyWLA2qtsjyVlfPotDxUdVouUQpz7dGHUFrLR7ma8QAYu\nOfl1ZMyrc901HGMa7zwbnFWurs3fed7vAosTRZIjnn72/3Wo7L9RiMB+vwr3NX7c\n9QIDAQABo4ICIzCCAh8wDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUF\nBwMBBggrBgEFBQcDAjAMBgNVHRMBAf8EAjAAMB0GA1UdDgQWBBRh9q/3Zxbk4yA/\nt7j+8xA+rkiZBTAfBgNVHSMEGDAWgBTAzANGuVggzFxycPPhLssgpvVoOjB4Bggr\nBgEFBQcBAQRsMGowMwYIKwYBBQUHMAGGJ2h0dHA6Ly9vY3NwLnN0Zy1pbnQteDEu\nbGV0c2VuY3J5cHQub3JnLzAzBggrBgEFBQcwAoYnaHR0cDovL2NlcnQuc3RnLWlu\ndC14MS5sZXRzZW5jcnlwdC5vcmcvMCUGA1UdEQQeMByCCndlcHBvcy5uZXSCDnd3\ndy53ZXBwb3MubmV0MIH+BgNVHSAEgfYwgfMwCAYGZ4EMAQIBMIHmBgsrBgEEAYLf\nEwEBATCB1jAmBggrBgEFBQcCARYaaHR0cDovL2Nwcy5sZXRzZW5jcnlwdC5vcmcw\ngasGCCsGAQUFBwICMIGeDIGbVGhpcyBDZXJ0aWZpY2F0ZSBtYXkgb25seSBiZSBy\nZWxpZWQgdXBvbiBieSBSZWx5aW5nIFBhcnRpZXMgYW5kIG9ubHkgaW4gYWNjb3Jk\nYW5jZSB3aXRoIHRoZSBDZXJ0aWZpY2F0ZSBQb2xpY3kgZm91bmQgYXQgaHR0cHM6\nLy9sZXRzZW5jcnlwdC5vcmcvcmVwb3NpdG9yeS8wDQYJKoZIhvcNAQELBQADggEB\nAEqMdWrmdIyQxthWsX3iHmM2h/wXwEesD0VIaA+Pq4mjwmKBkoPSmHGQ/O4v8RaK\nB6gl8v+qmvCwwqC1SkBmm+9C2yt/P6WhAiA/DD+WppYgJWfcz2lEKrgufFlHPukB\nDzE0mJDuXm09QTApWlaTZWYfWKY50T5uOT/rs+OwGFFCO/8o7v5AZRAHos6uzjvq\nAtFZj/FEnXXMjSSlQ7YKTXToVpnAYH4e3/UMsi6/O4orkVz82ZfhKwMWHV8dXlRw\ntQaemFWTjGPgSLXJAtQO30DgNJBHX/fJEaHv6Wy8TF3J0wOGpzGbOwaTX8YAmEzC\nlzzjs+clg5MN5rd1g4POJtU=\n-----END CERTIFICATE-----\n", certificateBundle.getServerCertificate());
    assertTrue(Data.isNull(certificateBundle.getRootCertificate()));
    assertEquals(1, certificateBundle.getIntermediateCertificates().size());
    assertEquals("-----BEGIN CERTIFICATE-----\nMIIEqzCCApOgAwIBAgIRAIvhKg5ZRO08VGQx8JdhT+UwDQYJKoZIhvcNAQELBQAw\nGjEYMBYGA1UEAwwPRmFrZSBMRSBSb290IFgxMB4XDTE2MDUyMzIyMDc1OVoXDTM2\nMDUyMzIyMDc1OVowIjEgMB4GA1UEAwwXRmFrZSBMRSBJbnRlcm1lZGlhdGUgWDEw\nggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDtWKySDn7rWZc5ggjz3ZB0\n8jO4xti3uzINfD5sQ7Lj7hzetUT+wQob+iXSZkhnvx+IvdbXF5/yt8aWPpUKnPym\noLxsYiI5gQBLxNDzIec0OIaflWqAr29m7J8+NNtApEN8nZFnf3bhehZW7AxmS1m0\nZnSsdHw0Fw+bgixPg2MQ9k9oefFeqa+7Kqdlz5bbrUYV2volxhDFtnI4Mh8BiWCN\nxDH1Hizq+GKCcHsinDZWurCqder/afJBnQs+SBSL6MVApHt+d35zjBD92fO2Je56\ndhMfzCgOKXeJ340WhW3TjD1zqLZXeaCyUNRnfOmWZV8nEhtHOFbUCU7r/KkjMZO9\nAgMBAAGjgeMwgeAwDgYDVR0PAQH/BAQDAgGGMBIGA1UdEwEB/wQIMAYBAf8CAQAw\nHQYDVR0OBBYEFMDMA0a5WCDMXHJw8+EuyyCm9Wg6MHoGCCsGAQUFBwEBBG4wbDA0\nBggrBgEFBQcwAYYoaHR0cDovL29jc3Auc3RnLXJvb3QteDEubGV0c2VuY3J5cHQu\nb3JnLzA0BggrBgEFBQcwAoYoaHR0cDovL2NlcnQuc3RnLXJvb3QteDEubGV0c2Vu\nY3J5cHQub3JnLzAfBgNVHSMEGDAWgBTBJnSkikSg5vogKNhcI5pFiBh54DANBgkq\nhkiG9w0BAQsFAAOCAgEABYSu4Il+fI0MYU42OTmEj+1HqQ5DvyAeyCA6sGuZdwjF\nUGeVOv3NnLyfofuUOjEbY5irFCDtnv+0ckukUZN9lz4Q2YjWGUpW4TTu3ieTsaC9\nAFvCSgNHJyWSVtWvB5XDxsqawl1KzHzzwr132bF2rtGtazSqVqK9E07sGHMCf+zp\nDQVDVVGtqZPHwX3KqUtefE621b8RI6VCl4oD30Olf8pjuzG4JKBFRFclzLRjo/h7\nIkkfjZ8wDa7faOjVXx6n+eUQ29cIMCzr8/rNWHS9pYGGQKJiY2xmVC9h12H99Xyf\nzWE9vb5zKP3MVG6neX1hSdo7PEAb9fqRhHkqVsqUvJlIRmvXvVKTwNCP3eCjRCCI\nPTAvjV+4ni786iXwwFYNz8l3PmPLCyQXWGohnJ8iBm+5nk7O2ynaPVW0U2W+pt2w\nSVuvdDM5zGv2f9ltNWUiYZHJ1mmO97jSY/6YfdOUH66iRtQtDkHBRdkNBsMbD+Em\n2TgBldtHNSJBfB3pm9FblgOcJ0FSWcUDWJ7vO0+NTXlgrRofRT6pVywzxVo6dND0\nWzYlTWeUVsO40xJqhgUQRER9YLOLxJ0O6C8i0xFxAMKOtSdodMB3RIwt7RFQ0uyt\nn5Z5MqkYhlMI3J1tPRTp1nEt9fyGspBOO05gi148Qasp+3N+svqKomoQglNoAxU=\n-----END CERTIFICATE-----", certificateBundle.getIntermediateCertificates().get(0));
  }

  @Test
  public void testCertificatePrivateKey() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/1/private_key", HttpMethods.GET, resource("getCertificatePrivateKey/success.http"));

    String accountId = "1010";
    String domainId = "weppos.net";
    String certificateId = "1";

    GetCertificatePrivateKeyResponse response = client.certificates.getCertificatePrivateKey(accountId, domainId, certificateId);
    assertEquals("-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAtzCcMfWoQRt5AMEY0HUb2GaraL1GsWOo6YXdPfe+YDvtnmDw\n23NcoTX7VSeCgU9M3RKs19AsCJcRNTLJ2dmDrAuyCTud9YTAaXQcTOLUhtO8T8+9\nAFVIva2OmAlKCR5saBW3JaRxW7V2aHEd/d1ss1CvNOO7jNppc9NwGSnDHcn3rqNv\n/U3MaU0gpJJRqsKkvcLU6IHJGgxyQ6AbpwJDIqBnzkjHu2IuhGEbRuMjyWLA2qts\njyVlfPotDxUdVouUQpz7dGHUFrLR7ma8QAYuOfl1ZMyrc901HGMa7zwbnFWurs3f\ned7vAosTRZIjnn72/3Wo7L9RiMB+vwr3NX7c9QIDAQABAoIBAEQx32OlzK34GTKT\nr7Yicmw7xEGofIGa1Q2h3Lut13whsxKLif5X0rrcyqRnoeibacS+qXXrJolIG4rP\nTl8/3wmUDQHs5J+6fJqFM+fXZUCP4AFiFzzhgsPBsVyd0KbWYYrZ0qU7s0ttoRe+\nTGjuHgIe3ip1QKNtx2Xr50YmytDydknmro79J5Gfrub1l2iA8SDm1eBrQ4SFaNQ2\nU709pHeSwX8pTihUX2Zy0ifpr0O1wYQjGLneMoG4rrNQJG/z6iUdhYczwwt1kDRQ\n4WkM2sovFOyxbBfoCQ3Gy/eem7OXfjNKUe47DAVLnPkKbqL/3Lo9FD7kcB8K87Ap\nr/vYrl0CgYEA413RAk7571w5dM+VftrdbFZ+Yi1OPhUshlPSehavro8kMGDEG5Ts\n74wEz2X3cfMxauMpMrBk/XnUCZ20AnWQClK73RB5fzPw5XNv473Tt/AFmt7eLOzl\nOcYrhpEHegtsD/ZaljlGtPqsjQAL9Ijhao03m1cGB1+uxI7FgacdckcCgYEAzkKP\n6xu9+WqOol73cnlYPS3sSZssyUF+eqWSzq2YJGRmfr1fbdtHqAS1ZbyC5fZVNZYV\nml1vfXi2LDcU0qS04JazurVyQr2rJZMTlCWVET1vhik7Y87wgCkLwKpbwamPDmlI\n9GY+fLNEa4yfAOOpvpTJpenUScxyKWH2cdYFOOMCgYBhrJnvffINC/d64Pp+BpP8\nyKN+lav5K6t3AWd4H2rVeJS5W7ijiLTIq8QdPNayUyE1o+S8695WrhGTF/aO3+ZD\nKQufikZHiQ7B43d7xL7BVBF0WK3lateGnEVyh7dIjMOdj92Wj4B6mv2pjQ2VvX/p\nAEWVLCtg24/+zL64VgxmXQKBgGosyXj1Zu2ldJcQ28AJxup3YVLilkNje4AXC2No\n6RCSvlAvm5gpcNGE2vvr9lX6YBKdl7FGt8WXBe/sysNEFfgmm45ZKOBCUn+dHk78\nqaeeQHKHdxMBy7utZWdgSqt+ZS299NgaacA3Z9kVIiSLDS4V2VeW7riujXXP/9TJ\nnxaRAoGBAMWXOfNVzfTyrKff6gvDWH+hqNICLyzvkEn2utNY9Q6WwqGuY9fvP/4Z\nXzc48AOBzUr8OeA4sHKJ79sJirOiWHNfD1swtvyVzsFZb6moiNwD3Ce/FzYCa3lQ\nU8blTH/uqpR2pSC6whzJ/lnSdqHUqhyp00000000000000000000\n-----END RSA PRIVATE KEY-----\n", response.getData().getPrivateKey());
  }

  @Test
  public void testPurchaseLetsencryptCertificate() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "weppos.net";
    Map<String,Object> attributes = new HashMap<String,Object>();

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/letsencrypt", HttpMethods.POST, new HttpHeaders(), attributes, resource("purchaseLetsencryptCertificate/success.http"));

    PurchaseLetsencryptResponse response = client.certificates.purchaseLetsencryptCertificate(accountId, domainId, attributes);
    CertificatePurchase purchase = response.getData();
    assertEquals(300, purchase.getId().intValue());
    assertEquals(300, purchase.getCertificateId().intValue());
  }

  @Test
  public void testIssueLetsencryptCertificate() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "weppos.net";
    String purchaseId = "2";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/letsencrypt/2/issue", HttpMethods.POST, new HttpHeaders(), null, resource("issueLetsencryptCertificate/success.http"));

    IssueLetsencryptResponse response = client.certificates.issueLetsencryptCertificate(accountId, domainId, purchaseId);
    Certificate certificate = response.getData();
    assertEquals(200, certificate.getId().intValue());
    assertEquals(300, certificate.getDomainId().intValue());
  }

  @Test
  public void testPurchaseLetsencryptCertificateRenewal() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "weppos.net";
    String certificateId = "2";
    Map<String,Object> attributes = new HashMap<String,Object>();

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/letsencrypt/2/renewals", HttpMethods.POST, new HttpHeaders(), attributes, resource("purchaseRenewalLetsencryptCertificate/success.http"));

    PurchaseLetsencryptRenewalResponse response = client.certificates.purchaseLetsencryptCertificateRenewal(accountId, domainId, certificateId, attributes);
    CertificateRenewal renewal = response.getData();
    assertEquals(999, renewal.getId().intValue());
    assertEquals(200, renewal.getOldCertificateId().intValue());
    assertEquals(300, renewal.getNewCertificateId().intValue());
  }

  @Test
  public void testIssueLetsencryptCertificateRenewal() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "weppos.net";
    String certificateId = "2";
    String renewalId = "3";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/weppos.net/certificates/letsencrypt/2/renewals/3/issue", HttpMethods.POST, new HttpHeaders(), null, resource("issueLetsencryptCertificate/success.http"));

    IssueLetsencryptRenewalResponse response = client.certificates.issueLetsencryptCertificateRenewal(accountId, domainId, certificateId, renewalId);
    Certificate certificate = response.getData();
    assertEquals(200, certificate.getId().intValue());
    assertEquals(300, certificate.getDomainId().intValue());
  }
}
