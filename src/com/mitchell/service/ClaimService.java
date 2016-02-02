package com.mitchell.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.mitchell.dao.ClaimDao;
import com.mitchell.dao.ClaimDaoImpl;
import com.mitchell.model.MitchellClaimType;
import com.mitchell.model.VehicleInfoType;

@Path("/ClaimService")
public class ClaimService {

	static ClaimDao claimDao = new ClaimDaoImpl();

	@PUT
	@Path("/claims/create")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String createClaim(MitchellClaimType claim) throws IOException,
			JAXBException {
		return claimDao.createClaim(claim);
	}

	@GET
	@Path("/claims/read/{claimnumber}")
	@Produces(MediaType.APPLICATION_XML)
	public JAXBElement<MitchellClaimType> getClaim(
			@PathParam("claimnumber") String claimNumber) {
		return claimDao.getClaim(claimNumber);
	}

	@GET
	@Path("/claims/readclaim/{startrange}/{endrange}")
	@Produces(MediaType.APPLICATION_XML)
	public List<JAXBElement<MitchellClaimType>> getClaimsFromRange(
			@PathParam("startrange") String startRange,
			@PathParam("endrange") String endRange) {
		return claimDao.getClaims(startRange, endRange);
	}

	@GET
	@Path("/claims/read")
	@Produces(MediaType.APPLICATION_XML)
	public List<JAXBElement<MitchellClaimType>> getAllClaims() {
		return claimDao.getAllClaims();
	}

	@GET
	@Path("/claims/read/{claimnumber}/{vin}")
	@Produces(MediaType.APPLICATION_JSON)
	public VehicleInfoType getVehicleInfo(
			@PathParam("claimnumber") String claimNumber,
			@PathParam("vin") String vin) {

		return claimDao.getvehicleInfo(claimNumber, vin);
	}

	@DELETE
	@Path("/claims/delete/{claimnumber}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteClaim(@PathParam("claimnumber") String claimNumber) {
		return claimDao.deleteClaim(claimNumber);
	}

	@POST
	@Path("/claims/update")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public JAXBElement<MitchellClaimType> updateClaim(MitchellClaimType claim) {
		return claimDao.updateClaim(claim);
	}
	

	
	/*
	 * @PUT
	 * 
	 * @Path("/claims/createclaim")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public void createClaim() throws
	 * IOException, JAXBException { claimDao.createClaim(); }
	 * 
	 * @GET
	 * 
	 * @Path("/claims/readclaim/{claimnumber}")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public MitchellClaimType
	 * getClaim(@PathParam("claimnumber") String claimNumber) { return
	 * claimDao.getClaim(claimNumber); }
	 * 
	 * @DELETE
	 * 
	 * @Path("/claims/deleteclaim/{claimnumber}")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public String
	 * deleteClaim(@PathParam("claimnumber") String claimNumber) { return
	 * claimDao.deleteClaim(claimNumber); }
	 * 
	 * @GET
	 * 
	 * @Path("/claims/readclaims/{startrange}/{endrange}")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public List<MitchellClaimType>
	 * getClaimsFromRange(@PathParam("startrange") String startRange,
	 * 
	 * @PathParam("endrange") String endRange ) { return
	 * claimDao.getClaims(startRange, endRange); }
	 */

}
