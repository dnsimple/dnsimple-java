# Changelog

This project uses [Semantic Versioning 2.0.0](http://semver.org/), the format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## 5.0.0 - 2026-01-23

### Removed

- Removed deprecated `getDomainPremiumPrice`. Use `getDomainPrices` instead. (dnsimple/dnsimple-developer#916)
- Removed deprecated `getWhoisPrivacy` (dnsimple/dnsimple-developer#919)
- Removed deprecated `renewWhoisPrivacy` (dnsimple/dnsimple-developer#919)

## 4.0.1

### Changed

- Update release process

## 4.0.0

### Added

- Added `active` to `EmailForward`

### Removed

- **BREAKING:** Removed `from` and `to` fields from `EmailForward`. Please use `alias_email` and `destination_email` instead.

## 3.0.2

### Changed

- Update release process

## 3.0.1

### Changed

- Update release process

## 3.0.0

### Changed

- Updated dependencies
- Drop support for Java JDK 23
- Add support for Java JDK 24

### Removed

- **BREAKING:** `DomainCollaborators` have been removed. Please use our Domain Access Control feature.

## 2.0.0 - 2024-12-12

### Added

- Added `alias_email` and `destination_email` to `EmailForward`

### Changed

- Drop support for Java JDK 17
- Add support for Java JDK 23

### Deprecated

- Deprecated `from` and `to` fields in `EmailForward`
- `DomainCollaborators` have been deprecated and will be removed in the next major version. Please use our Domain Access Control feature.

## 1.0.0 - 2024-03-12

### Changed

- Remove support for Java JDK < 17
- Add support for Java JDK 21

### Removed

- **BREAKING:** Removed the `privateKey` property of `CertificateBundle`. Use `Certificates.getCertificatePrivateKey()` instead. See [dnsimple-java#118](https://github.com/dnsimple/dnsimple-java/issues/118)

## 0.14.0 - 2023-12-12

### Added

- Added `Secondary`, `LastTransferredAt`, `Active` to `Zone` (dnsimple/dnsimple-java)

## 0.13.0 - 2023-11-20

### Added

- Added `listCharges` method to `Billing` to manage account billing charges. (dnsimple/dnsimple-java#163)

## 0.12.0 - 2023-09-21

### Added

- Added `listRegistrantChanges`, `createRegistrantChange`, `checkRegistrantChange`, `getRegistrantChange`, and `deleteRegistrantChange` methods to `Registrar` to manage registrant changes. (dnsimple/dnsimple-java#159)
- Added `getDomainTransferLock`, `enableDomainTransferLock`, and `disableDomainTransferLock` methods to `Registrar` to manage domain transfer locks. (dnsimple/dnsimple-java#161)

## 0.11.0 - 2023-08-11

### Added

- Added `Dnsimple.Zones.activateDns` to activate DNS services (resolution) for a zone. (dnsimple/dnsimple-java#157)
- Added `Dnsimple.Zones.deactivateDns` to deactivate DNS services (resolution) for a zone. (dnsimple/dnsimple-java#157)

## 0.10.0 - 2023-03-03

### Added

- Implement support for `signatureAlgorithm` in `CertificatePurchaseOptions` (dnsimple/dnsimple-java#146)
- Implement the getDomainRegistration and getDomainRenewal Registrar APIs (dnsimple/dnsimple-java#147)

### Changed

- **BREAKING:** `purchaseLetsencryptCertificateRenewal` now takes a `CertificateRenewalPurchaseOptions` object to support the `signatureAlgorithm` field (dnsimple/dnsimple-java#146)

### Removed

- **BREAKING:** Removed deprecated certificate methods and constructors that use contact ID, which is no longer supported (dnsimple/dnsimple-java#146)

## 0.9.6 - 2023-02-22

### Changed

- Update dependencies

## 0.9.5 - 2022-09-19

### Added

- Add getter for attribute errors in `BadRequestException` (dnsimple/dnsimple-java#135)

### Deprecated

- Deprecated Certificate's `contact_id` (dnsimple/dnsimple-java#123)

## 0.9.4 - 2022-03-07

### Changed

- Added automated release process

## 0.9.3 - 2021-11-17

### Added

- Add support for DNSSEC key-data interface (dnsimple/dnsimple-java#109)

### Deprecated

- `DSOptions.of()` is deprecated in favor of `DSOptions.dsData()` (dnsimple/dnsimple-java#109)

## 0.9.2 - 2021-06-29

### Fixed

- Avoid sending `regions=["global"]` for global zone records and prune the `regions` attribute from the request payload instead

## 0.9.1 - 2021-06-07

### Deprecated

- `Registrar.getDomainPremiumPrice()` is deprecated in favour of `Registrar.getDomainPrices()`

## 0.9.0 - 2021-04-21

### Added

- Added `client.registrar.getDomainPrices` to retrieve whether a domain is premium and the prices to register, transfer, and renew. (dnsimple/dnsimple-java#93)

## 0.8.1 - 2020-09-07

### Added

- Added project with code examples

### Fixed

- Align arguments of `Domain.listPushes()` with current API (#81)

## 0.8.0

### Changed

- **BREAKING:** Complete review of input and output data types and names to be more aligned to the API docs.
- **BREAKING:** Complete review of error management: now error classes are unchecked and there's more fragmentation to represent specific API related scenarios.
- Replace the Google API HTTP client with a Java 11 native HTTP client. Users can now provide their own `HttpRequestFactory` alternative implementation.

### Removed

- Removed deprecated members from the 0.x branch.
- Removed all endpoint related interfaces to prevent unwanted extension of this library.

## 0.7.1 - 2020-07-08

### Fixed

- Bug that produced an IllegalArgumentException in all requests that are responded with a HTTP 204 No Content ([GH-30](https://github.com/dnsimple/dnsimple-java/pull/30)). As a result, now we package the Apache HTTP Client library as a transient dependency.

### Deprecated

- `Domain.getExpiresOn()` is deprecated in favor of `Domain.getExpiresAt()` ([GH-37](https://github.com/dnsimple/dnsimple-java/pull/37))
- `Certificate.getExpiresOn()` is deprecated in favor of `Certificate.getExpiresAt()` ([GH-40](https://github.com/dnsimple/dnsimple-java/pull/40))

## 0.7.0 - 2020-05-19

### Added

- Added `client.registrar.getDomainTransfer` to retrieve a domain transfer. (dnsimple/dnsimple-java#33)
- Added `client.registrar.cancelDomainTransfer` to cancel an in progress domain transfer. (dnsimple/dnsimple-java#33)
- Added `DomainTransfer.getStatusDescription()` to identify the failure reason of a transfer. (dnsimple/dnsimple-java#33)

## 0.6.0 - 2019-02-01

### Added

- Added WHOIS privacy renewal (GH-12)

## 0.5.0 - 2019-02-01

### Added

- Added zone distribution and zone record distribution (GH-11)

## 0.4.0 - 2018-01-28

### Added

- Added Let's Encrypt certificate methods (GH-8)

### Changed

- Updated registrar URLs (GH-5)
- RegisterDomainResponse/TransferDomainResponse/RenewDomainResponse now properly map data to a DomainRegistration, DomainTransfer, and DomainRenewal (GH-10)
- Updated lib to use the most up-to-date fixtures (GH-9)
