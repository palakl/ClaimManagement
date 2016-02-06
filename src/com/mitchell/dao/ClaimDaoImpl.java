package com.mitchell.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.commons.beanutils.PropertyUtils;

import com.mitchell.exception.ClaimNotFoundException;
import com.mitchell.model.MitchellClaimType;
import com.mitchell.model.ObjectFactory;
import com.mitchell.model.VehicleInfoType;

public class ClaimDaoImpl implements ClaimDao {

	static HashMap<String, MitchellClaimType> dataStore;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	ObjectFactory factory = new ObjectFactory();

	public ClaimDaoImpl() {

		dataStore = new HashMap<String, MitchellClaimType>();
	}

	@Override
	public String createClaim(MitchellClaimType claim) {

		if (dataStore.containsKey(claim.getClaimNumber())) {
			return "Claim Already Exist";
		} else {
			dataStore.put(claim.getClaimNumber(), claim);
			return "Claim Added Successfully";
		}
	}

	@Override
	public JAXBElement<MitchellClaimType> getClaim(String claimNumber) {

		if (dataStore.containsKey(claimNumber)) {

			MitchellClaimType claim = dataStore.get(claimNumber);
			return factory.createMitchellClaim(claim);
		} else {
			throw new ClaimNotFoundException("Claim Number" + claimNumber
					+ " not found in the data store");
		}
	}

	@Override
	public List<JAXBElement<MitchellClaimType>> getClaims(String startRange,
			String endRange) {

		List<JAXBElement<MitchellClaimType>> claimList = new ArrayList<JAXBElement<MitchellClaimType>>();

		try {

			Date startDate = formatter.parse(startRange);
			Date endDate = formatter.parse(endRange);

			for (Map.Entry<String, MitchellClaimType> entry : dataStore
					.entrySet()) {

				Date currentDate = entry.getValue().getLossDate()
						.toGregorianCalendar().getTime();

				if (currentDate.after(startDate) && currentDate.before(endDate)) {

					claimList.add(factory.createMitchellClaim(entry.getValue()));

				}

			}

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return claimList;

	}

	@Override
	public JAXBElement<MitchellClaimType> updateClaim(MitchellClaimType claim) {

		if (dataStore.containsKey(claim.getClaimNumber())) {

			MitchellClaimType temp = dataStore.get(claim.getClaimNumber());
			try {
				
				
				
				//BeanUtils.copyProperties(claim, temp);
				PropertyDescriptor[] properties = PropertyUtils
						.getPropertyDescriptors(temp);
				for (PropertyDescriptor descriptor : properties) {
					if (PropertyUtils.isReadable(temp, descriptor.getName())
							&& PropertyUtils.isWriteable(temp,
									descriptor.getName())) {
						Method readMethod = descriptor.getReadMethod();
						Object value = readMethod.invoke(claim);
						if (value != null) {
							Method writeMethod = descriptor.getWriteMethod();
							writeMethod.invoke(temp, value);
						}
					}
				}
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			return factory.createMitchellClaim(temp);

		} else {

			throw new ClaimNotFoundException("Claim Number "
					+ claim.getClaimNumber() + " not found in data store ");

		}
	}

	@Override
	public String deleteClaim(String claimNumber) {

		if (dataStore.containsKey(claimNumber)) {
			dataStore.remove(claimNumber);
			return "Claim Deleted Successfully";

		} else {

			throw new ClaimNotFoundException("Claim Number " + claimNumber
					+ " not found in datastore");
		}

	}

	@Override
	public VehicleInfoType getvehicleInfo(String claimNumber,
			String vin) {

		JAXBElement<MitchellClaimType> claim = getClaim(claimNumber);
		
		for (VehicleInfoType vehicle : claim.getValue().getVehicles()
				.getVehicleDetails()) {

			if (vehicle.getVin().equals(vin)) {

				return vehicle;
			}

		}

		return null;

	}

	@Override
	public List<JAXBElement<MitchellClaimType>> getAllClaims() {

		List<JAXBElement<MitchellClaimType>> claimList = new ArrayList<JAXBElement<MitchellClaimType>>();
		for (Map.Entry<String, MitchellClaimType> entry : dataStore.entrySet()) {

			claimList.add(factory.createMitchellClaim(entry.getValue()));
		}
		return claimList;
	}

}
