import com.dnsimple.Client;
import com.dnsimple.data.WhoamiData;
import com.dnsimple.request.CertificatePurchaseOptions;

public class SslCertificates {
    public static void main(String[] args) {
        // Obtain your API token and Account ID
        // https://support.dnsimple.com/articles/api-access-token/
        var client = new Client.Builder().accessToken(args[0]).build();
        WhoamiData whoami = client.identity.whoami().getData();
        var accountId = whoami.getAccount() != null
                ? whoami.getAccount().getId()
                : client.accounts.listAccounts().getData().get(0).getId();

        // Request a Let's Encrypt SSL/TLS Certificate
        var domainName = "example.com";
        var certificatePurchase = client.certificates.purchaseLetsencryptCertificate(
                accountId,
                domainName,
                CertificatePurchaseOptions.of(1)
        ).getData();

        // Download the certificate
        var certificate = client.certificates.downloadCertificate(accountId, domainName, certificatePurchase.getId()).getData();
        System.out.printf(
                "Private key %s",
                certificate.getPrivateKey()
        );
    }
}
