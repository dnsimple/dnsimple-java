# Changelog

## main

## 3.0.0

- BREAKING: `DomainCollaborators` have been removed. Please use our Domain Access Control feature.
- CHANGED: Updated dependencies
- CHANGED: Drop support for Java JDK 23
- CHANGED: Add support for Java JDK 24

## 2.0.0

- NEW: Added `alias_email` and `destination_email` to `EmailForward`
- CHANGED: Deprecated `from` and `to` fields in `EmailForward`
- CHANGE: Drop support for Java JDK 17
- CHANGE: Add support for Java JDK 23
- CHANGE: `DomainCollaborators` have been deprecated and will be removed in the next major version. Please use our Domain Access Control feature.

## 1.0.0

- BREAKING: Remove the `privateKey` property of `CertificateBundle`. Use `Certificates.getCertificatePrivateKey()` instead. See [dnsimple-java#118](https://github.com/dnsimple/dnsimple-java/issues/118)
- CHANGE: Remove support for Java JDK < 17
- CHANGE: Add support for Java JDK 21

## 0.14.0

ENHANCEMENTS:

- NEW: Added `Secondary`, `LastTransferredAt`, `Active` to `Zone` (dnsimple/dnsimple-java)

## 0.13.0

FEATURES:

- NEW: Added `listCharges` method to `Billing` to manage account billing charges. (dnsimple/dnsimple-java#163)

## 0.12.0

FEATURES:

- NEW: Added `listRegistrantChanges`, `createRegistrantChange`, `checkRegistrantChange`, `getRegistrantChange`, and `deleteRegistrantChange` methods to `Registrar` to manage registrant changes. (dnsimple/dnsimple-java#159)
- NEW: Added `getDomainTransferLock`, `enableDomainTransferLock`, and `disableDomainTransferLock` methods to `Registrar` to manage domain transfer locks. (dnsimple/dnsimple-java#161)

## 0.11.0

FEATURES:

- NEW: Added `Dnsimple.Zones.activateDns` to activate DNS services (resolution) for a zone. (dnsimple/dnsimple-java#157)
- NEW: Added `Dnsimple.Zones.deactivateDns` to deactivate DNS services (resolution) for a zone. (dnsimple/dnsimple-java#157)

## Release 0.10.0

- BREAKING: `purchaseLetsencryptCertificateRenewal` now takes a `CertificateRenewalPurchaseOptions` object to support the `signatureAlgorithm` field (dnsimple/dnsimple-java#146)
- BREAKING: Remove deprecated certificate methods and constructors that use contact ID, which is no longer supported (dnsimple/dnsimple-java#146)
- NEW: Implement support for `signatureAlgorithm` in `CertificatePurchaseOptions` (dnsimple/dnsimple-java#146)
- NEW: Implement the getDomainRegistration and getDomainRenewal Registrar APIs (dnsimple/dnsimple-java#147)

## Release 0.9.6

- Update dependencies

## Release 0.9.5

- CHANGED: Deprecate Certificate's `contact_id` (dnsimple/dnsimple-java#123)
- CHANGED: Add getter for attribute errors in `BadRequestException` (dnsimple/dnsimple-java#135)

## Release 0.9.4

- Added automated release process

## Release 0.9.3

- CHANGED: Add support for DNSSEC key-data interface (dnsimple/dnsimple-java#109)
- CHANGED: `DSOptions.of()` is deprecated in favor of `DSOptions.dsData()` (dnsimple/dnsimple-java#109)

## Release 0.9.2

- Fix: avoid sending `regions=["global"]` for global zone records and prune the `regions` attribute from the request payload instead

## Release 0.9.1

- CHANGED: `Registrar.getDomainPremiumPrice()` is deprecated in favour of `Registrar.getDomainPrices()`

## Release 0.9.0

- NEW: Added `client.registrar.getDomainPrices` to retrieve whether a domain is premium and the prices to register, transfer, and renew. (dnsimple/dnsimple-java#93)

## Release 0.8.1

- Fix: Align arguments of `Domain.listPushes()` with current API (#81)
- Added project with code examples

## Release 0.8.0

**Warning**: This release contains breaking changes. Please contact DNSimple if you need assistance migrating your project to this version of the Java API Client

- Complete review of input and output data types and names to be more aligned to the API docs.
- Complete review of error management: now error classes are unchecked and there's more fragmentation to represent specific API related scenarios.
- Replace the Google API HTTP client with a Java 11 native HTTP client.
  - Users can now provide their own `HttpRequestFactory` alternative implementation.
- Removed deprecated members from the 0.x branch.
- Removed all endpoint related interfaces to prevent unwanted extension of this library.

## Release 0.7.1

- FIXED: Bug that produced an IllegalArgumentException in all requests that are responded with a HTTP 204 No Content ([GH-30](https://github.com/dnsimple/dnsimple-java/pull/30))

  As a result, now we package the Apache HTTP Client library as a transient dependency

- CHANGED: `Domain.getExpiresOn()` is deprecated in favor of `Domain.getExpiresAt()` ([GH-37](https://github.com/dnsimple/dnsimple-java/pull/37))
- CHANGED: `Certificate.getExpiresOn()` is deprecated in favor of `Certificate.getExpiresAt()` ([GH-40](https://github.com/dnsimple/dnsimple-java/pull/40))

## Release 0.7.0

- NEW: Added `client.registrar.getDomainTransfer` to retrieve a domain transfer. (dnsimple/dnsimple-java#33)
- NEW: Added `client.registrar.cancelDomainTransfer` to cancel an in progress domain transfer. (dnsimple/dnsimple-java#33)
- NEW: Added `DomainTransfer.getStatusDescription()` to identify the failure reason of a transfer. (dnsimple/dnsimple-java#33).

## Release 0.6.0

- NEW: Added WHOIS privacy renewal (GH-12)

## Release 0.5.0

- NEW: Added zone distribution and zone record distribution (GH-11)

## Release 0.4.0

- NEW: Added Let's Encrypt certificate methods (GH-8)

- CHANGED: Updated registrar URLs (GH-5)
- CHANGED: RegisterDomainResponse/TransferDomainResponse/RenewDomainResponse now properly map data to a DomainRegistration, DomainTransfer, and DomainRenewal (GH-10)
- CHANGED: Updated lib to use the most up-to-date fixtures (GH-9)
