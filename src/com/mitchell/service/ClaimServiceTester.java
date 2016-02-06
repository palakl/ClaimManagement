package com.mitchell.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mitchell.exception.ClaimNotFoundException;
import com.mitchell.model.MitchellClaimType;

public class ClaimServiceTester {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/ClaimManagement/rest/ClaimService/claims/read";
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		ClaimServiceTester tester = new ClaimServiceTester();

		// initialize the tester
		tester.init();

		// test get all claims Web Service Method
		tester.testGetAllClaims();

		// test get claim Web Service Method
		tester.testGetClaim();

		/*
		 * //test get all users Web Service Method tester.testGetAllUsers();
		 * 
		 * //test get user Web Service Method tester.testGetUser();
		 * 
		 * //test update user Web Service Method tester.testUpdateUser();
		 * 
		 * //test add user Web Service Method tester.testAddUser();
		 * 
		 * //test delete user Web Service Method tester.testDeleteUser();
		 */
	}

	// Test: Get list of claims
	// Test: Check if the list is not empty

	private void testGetAllClaims() {

		GenericType<List<MitchellClaimType>> list = new GenericType<List<MitchellClaimType>>() {
		};
		List<MitchellClaimType> claims = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_XML).get(list);
		String result = PASS;
		if (claims.isEmpty()) {
			result = FAIL;
		}

		System.out
				.println("Test case name: testGetAllClaim, Result: " + result);
	}

	// Test: Get claim with claim number
	// Test: Check if the claim exist
	private void testGetClaim() {

		String result = null;
		try {

			MitchellClaimType claim = client
					.target(REST_SERVICE_URL)
					.path("/{claimnumber}")
					.resolveTemplate("claimnumber",
							"22c9c23bac142856018ce14a26b6c299")
					.request(MediaType.APPLICATION_XML)
					.get(MitchellClaimType.class);

			if (claim != null) {

				result = PASS;
			}
		} catch (ClaimNotFoundException e) {

			result = FAIL;

		} finally {

			System.out.println("Test case name: testGetClaim, Result: "
					+ result);
		}

	}

}
