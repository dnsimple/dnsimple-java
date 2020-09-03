import com.dnsimple.Client;
import com.dnsimple.data.WhoamiData;
import com.dnsimple.request.ZoneRecordOptions;

public class Dns {
    public static void main(String[] args) {
        // Obtain your API token and Account ID
        // https://support.dnsimple.com/articles/api-access-token/
        var client = new Client.Builder().accessToken(args[0]).build();
        WhoamiData whoami = client.identity.whoami().getData();
        var accountId = whoami.getAccount() != null
                ? whoami.getAccount().getId()
                : client.accounts.listAccounts().getData().get(0).getId();

        // Create a new record
        String zoneName = "example.com";
        var record = client.zones.createZoneRecord(accountId, zoneName, ZoneRecordOptions.of(
                "www",
                "CNAME",
                "foo.example.com"
        )).getData();
        System.out.printf(
                "Zone record with ID %d for %s.example.com with content %s has been created%n",
                record.getId(),
                record.getName(),
                record.getContent()
        );

        // List all records on a zone
        var records = client.zones.listZoneRecords(accountId, zoneName).getData();
        System.out.printf(
                "Zone example.com has %d records%n",
                records.size()
        );

        // Delete a record
        client.zones.deleteZoneRecord(accountId, zoneName, record.getId());

        // Check if a zone change is fully distributed to all our nameservers across the globe
        // (not available on sandbox)
        var zoneDistribution = client.zones.checkZoneDistribution(accountId, zoneName).getData();
        System.out.printf(
                "The zone example.com is %s%n",
                zoneDistribution.isDistributed() ? "distributed" : "not distributed"
        );
    }
}
