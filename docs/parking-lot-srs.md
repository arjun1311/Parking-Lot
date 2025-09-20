Parking Lot Management System - Software Requirements Specification (SRS)
1. Introduction
1.1 Purpose

The purpose of this document is to define the functional and non-functional requirements for a Parking Lot Management System that enables users to park two-wheelers and four-wheelers, purchase tickets, and pay for parking in a single-level parking facility.
This SRS serves as the baseline for design (HLD/LLD), development, and testing.

1.2 Scope

The system will:

Manage parking operations for a single-level parking lot with separate capacities for two-wheelers and four-wheelers.

Provide mobile applications (Android and iOS) for real-time availability checks, ticketing, and payments.

Generate digital and/or printed tickets with unique identifiers and slot information.

Support scalability so new levels can be added in future phases with minimal code changes.

The primary stakeholders include parking lot customers, parking attendants (if any), and system administrators.

1.3 Definitions, Acronyms, and Abbreviations

Ticket - A unique record representing a vehicle’s entry, booked duration, and assigned slot.

Overstay - Parking beyond the booked duration.

Grace Period - Extra time allowed after booked duration before overstay charges apply.

2. Overall Description
2.1 Product Perspective

The Parking Lot Management System is a stand-alone service with a backend (e.g., Spring Boot), database (e.g., MySQL/PostgreSQL), and mobile clients.
It interacts with:

Payment Gateways (UPI, card, wallet, cash recording)

Notification Service (for overstay alerts)

2.2 User Characteristics

Customer/User - Owns a two-wheeler or four-wheeler and uses the mobile app to check availability, purchase a ticket, and pay for parking.

Admin - Manages system configuration (slot capacity, pricing updates).

3. Functional Requirements
3.1 Parking Lot Specifications

Single parking level.

Capacity of 100 four-wheeler slots and 50 two-wheeler slots.

One entry gate and one exit gate.

Slot assignment is automatic (system-assigned).

3.2 Ticketing

Ticket is issued on vehicle entry and returned/closed on exit.

User must enter vehicle license plate number at entry.

Ticket contains:

Unique ticket ID (primary key)

Assigned slot number

Vehicle type (two-wheeler/four-wheeler)

Booked duration

Entry timestamp

3.3 Pricing & Billing

₹20 per hour for both vehicle categories.

User books an initial parking duration at entry.

Overstay charges:

₹20 for every additional 30 minutes beyond booked duration.

Overstay is rounded up to the next 30-minute block.

A 20-minute grace period is allowed after the booked duration before overstay charges apply.

Example:

Booked: 1 hour → ₹20

Actual stay: 1 hour 10 min → still ₹20 (within grace)

Actual stay: 1 hour 31 min → ₹40 (two 30-min blocks)

3.4 Real-Time Availability

Users can check:

Current available slots by vehicle type.

Estimated cost if parked beyond booked time.

Users receive push notifications for:

Imminent overstay (e.g., 10 minutes before grace expires).

Successful payment confirmation.

3.5 Payment Methods

System supports Cash, UPI, Credit Card, and Wallet transactions.

Payment is processed at exit.

3.6 Ticket Validation

Each ticket has a unique ID and associated slot number.

Ticket is valid for a single entry/exit session only.

The system verifies ticket authenticity before processing exit.

3.7 Lost Ticket Handling

If a user loses a ticket:

System retrieves booking details based on license plate.

User pays the booked amount plus ₹10 penalty.

Penalty is reflected in the re-issued ticket (hard copy or soft copy).

4. Non-Functional Requirements

Scalability – Design should allow adding new parking levels or more slots with minimal code changes.

Reliability – System must preserve all ticket and payment data during power outages or crashes.

Performance – Check-in and check-out operations must complete within 2 seconds.

UI/UX – Mobile app must have a simple, intuitive interface on both Android and iOS platforms.

Security – Ticket IDs must be securely generated to prevent forgery. Payment data must comply with applicable standards (e.g., PCI DSS for card payments).

5. Edge Cases

Early Exit – Vehicle leaves before booked time → User pays the full booked amount (no refunds).

Overstay Rounding – Partial 30-minute overstays are rounded up to the next 30-minute block.

Parking Full – Entry request is rejected gracefully with an availability message.

Lost Ticket – User must provide license plate; penalty applied as described.

6. Future Enhancements (Out of Scope for MVP)

Multi-level parking support.

Dynamic pricing (e.g., peak/off-peak rates).

Reservation of slots in advance.

Integration with ANPR (Automatic Number Plate Recognition) cameras.

7. Appendices

References: None at this time.

Assumptions:

Users have internet connectivity for mobile app operations.

Payment gateway integrations (UPI, card) are available.

End of Document