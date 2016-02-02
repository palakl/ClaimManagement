package com.mitchell.dao;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mitchell.model.Claims;
import com.mitchell.model.MitchellClaimType;
import com.mitchell.model.VehicleInfoType;

public class ClaimStoreImpl implements ClaimStore {

	final String file = "mitchell-claims.xml";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public MitchellClaimType getClaim(String claimNumber) {

		Claims claims = getAllClaims();
		if (claims != null) {

			List<MitchellClaimType> claimList = claims.getClaimList();
			MitchellClaimType claim = null;
			for (MitchellClaimType c : claimList) {

				if (claimNumber.equals(c.getClaimNumber())) {

					claim = c;
					break;
				}
			}

			return claim;

		} else {

			return null;
		}

	}

	@Override
	public List<MitchellClaimType> getClaims(String startRange, String endRange) {

		Claims claims = getAllClaims();
		if (claims != null) {

			List<MitchellClaimType> claimList = claims.getClaimList();
			List<MitchellClaimType> tempList = new ArrayList<MitchellClaimType>();
			try {
				Date startDate = formatter.parse(startRange);
				Date endDate = formatter.parse(endRange);

				for (MitchellClaimType claim : claimList) {

					Date currentDate = claim.getLossDate()
							.toGregorianCalendar().getTime();

					if (currentDate.after(startDate)
							&& currentDate.before(endDate)) {
						tempList.add(claim);
					}

				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return tempList;

		} else
			return null;
	}

	@Override
	public String updateClaim(MitchellClaimType claim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleInfoType getVehicleInfo(String vin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims getAllClaims() {

		File file = new File("mitchell-claims.xml");
		Claims claims = null;
		if (file.exists()) {

			claims = unmarshal(file);
			return claims;

		} else {

			return null;
		}

	}

	@Override
	public void saveClaim(MitchellClaimType claim) {

		Claims claims = getAllClaims();
		List<MitchellClaimType> claimList = null;

		if (claims == null)
			claims = new Claims();

		claimList = claims.getClaimList();

		if (claimList == null)
			claimList = new ArrayList<MitchellClaimType>();

		claimList.add(claim);
		claims.setClaimList(claimList);
		marshal(claims, new File(file));

	}

	public void marshal(Claims claims, File file) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Claims.class,
					MitchellClaimType.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(claims, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Claims unmarshal(File file) {

		JAXBContext jaxbContext;
		Claims claims = null;
		try {
			jaxbContext = JAXBContext.newInstance(Claims.class,
					MitchellClaimType.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			claims = (Claims) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {

			e.printStackTrace();
		}

		return claims;

	}

	@Override
	public void createClaim() {

		MitchellClaimType claim = null;
		try {

			File file = new File("create-claim.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(MitchellClaimType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			claim = (MitchellClaimType) jaxbUnmarshaller.unmarshal(file);
			saveClaim(claim);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String deleteClaim(String claimNumber) {

		Claims claims = getAllClaims();
		if (claims != null) {

			List<MitchellClaimType> claimList = claims.getClaimList();
			for (MitchellClaimType c : claimList) {

				if (claimNumber.equals(c.getClaimNumber())) {

					claimList.remove(c);
					break;
				}
			}

			claims.setClaimList(claimList);
			marshal(claims, new File(file));
			return "Claim Deleted Successfully";

		} else
			return "Claim Not Found";
	}

}
