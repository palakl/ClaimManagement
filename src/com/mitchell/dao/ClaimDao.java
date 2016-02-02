package com.mitchell.dao;

import java.util.List;

import javax.xml.bind.JAXBElement;

import com.mitchell.model.MitchellClaimType;
import com.mitchell.model.VehicleInfoType;

public interface ClaimDao {

	String createClaim(MitchellClaimType claim);

	List<JAXBElement<MitchellClaimType>> getAllClaims();

	JAXBElement<MitchellClaimType> getClaim(String claimNumber);

	List<JAXBElement<MitchellClaimType>> getClaims(String startRange,
			String endRange);

	JAXBElement<MitchellClaimType> updateClaim(MitchellClaimType claim);

	String deleteClaim(String claimNumber);

	VehicleInfoType getvehicleInfo(String claiNumber, String vin);

}
