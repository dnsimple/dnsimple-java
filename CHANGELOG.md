# Changelog

### master

- NEW: Added `client.registrar.getDomainTransfer` to retrieve a domain transfer. (dnsimple/dnsimple-java#33)
- NEW: Added `client.registrar.cancelDomainTransfer` to cancel an in progress domain transfer. (dnsimple/dnsimple-java#33)
- NEW: Added `DomainTransfer.getStatusDescription()` to identify the failure reason of a transfer. (dnsimple/dnsimple-java#33).

### Release 0.6.0

- NEW: Added WHOIS privacy renewal (GH-12)

### Release 0.5.0

- NEW: Added zone distribution and zone record distribution (GH-11)

#### Release 0.4.0

- NEW: Added Let's Encrypt certificate methods (GH-8)

- CHANGED: Updated registrar URLs (GH-5)
- CHANGED: RegisterDomainResponse/TransferDomainResponse/RenewDomainResponse now properly map data to a DomainRegistration, DomainTransfer, and DomainRenewal (GH-10)
- CHANGED: Updated lib to use the most up-to-date fixtures (GH-9)
