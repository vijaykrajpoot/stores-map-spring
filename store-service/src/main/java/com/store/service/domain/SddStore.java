package com.store.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SddStore {

	private String storeId;
	private String storeLocationNbr;
	private String storeName;
	private String addressLine1;
	private String city;
	private String state;
	private String storeZipCode;
	private com.google.maps.model.LatLng storeLatLng;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreLocationNbr() {
		return storeLocationNbr;
	}

	public void setStoreLocationNbr(String storeLocationNbr) {
		this.storeLocationNbr = storeLocationNbr;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStoreZipCode() {
		return storeZipCode;
	}

	public void setStoreZipCode(String storeZipCode) {
		this.storeZipCode = storeZipCode;
	}

	

	public com.google.maps.model.LatLng getStoreLatLng() {
		return storeLatLng;
	}

	public void setStoreLatLng(com.google.maps.model.LatLng storeLatLng) {
		this.storeLatLng = storeLatLng;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);

	}
}
