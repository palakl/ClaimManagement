package com.mitchell.dao;

import java.util.List;

import com.mitchell.model.Claims;
import com.mitchell.model.MitchellClaimType;
import com.mitchell.model.VehicleInfoType;

public interface ClaimStore {
	
	MitchellClaimType getClaim(String claimNumber);
	
	void createClaim();
	
	List<MitchellClaimType> getClaims(String startRange, String endRange);
	
	String updateClaim(MitchellClaimType claim);
	
	VehicleInfoType getVehicleInfo(String vin);
	
	Claims getAllClaims();
	
	void saveClaim(MitchellClaimType claim);
	
	String deleteClaim(String claimNumber);

}
