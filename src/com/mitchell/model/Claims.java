package com.mitchell.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Claims")
@XmlAccessorType(XmlAccessType.FIELD)
public class Claims {

	@XmlElement(name = "MitchellClaim")
	protected List<MitchellClaimType> claimList = null;

	public List<MitchellClaimType> getClaimList() {
		return claimList;
	}

	public void setClaimList(List<MitchellClaimType> claimList) {
		this.claimList = claimList;
	}

}
