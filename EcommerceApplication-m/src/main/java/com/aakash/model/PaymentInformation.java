package com.aakash.model;

import jakarta.persistence.Column;

public class PaymentInformation {

	
	@Column(name="cardholder_Name")
	private String cardholderName;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_Date")
	private String expirationDate;
	
	@Column(name="cvv")
	private String cvv;
	
}
