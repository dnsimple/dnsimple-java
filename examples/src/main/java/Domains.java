import com.dnsimple.Client;
import com.dnsimple.data.WhoamiData;
import com.dnsimple.request.ContactOptions;
import com.dnsimple.request.RegistrationOptions;

public class Domains {
    public static void main(String[] args) {
        // Obtain your API token and Account ID
        // https://support.dnsimple.com/articles/api-access-token/
        var client = new Client.Builder().accessToken(args[0]).build();
        WhoamiData whoami = client.identity.whoami().getData();
        var accountId = whoami.getAccount() != null
                ? whoami.getAccount().getId()
                : client.accounts.listAccounts().getData().get(0).getId();

        // Check for domain availability
        var domainName = "chuchu-to-check.com";
        var available = client.registrar.checkDomain(accountId, domainName).getData().isAvailable();
        System.out.printf(
                "Domain %s is %s%n",
                domainName,
                available ? "available" : "not available"
        );

        // Before we register a domain we need to create a contact
        var contact = client.contacts.createContact(accountId, ContactOptions.of(
                "Jane",
                "Smith",
                "111 SW 1at Street",
                "Miami",
                "FL",
                "11111",
                "US",
                "jane.smith@example.com",
                "+1 321 555 4444"
        )).getData();
        System.out.printf(
                "Contact %s has been created",
                contact.getId()
        );

        // Register the domain
        var registration = client.registrar.registerDomain(
                accountId,
                domainName,
                RegistrationOptions.of(contact.getId())
        ).getData();
        System.out.printf(
                "Domain %s has been registered with ID %d and state %s%n",
                domainName,
                registration.getId(),
                registration.getState()
        );

        // List all domains in account
        var domainList = client.domains.listDomains(accountId).getData();
        System.out.printf(
                "Your account has %d domains%n",
                domainList.size()
        );
    }
}
