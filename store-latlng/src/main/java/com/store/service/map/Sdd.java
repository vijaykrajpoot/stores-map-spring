package com.store.service.map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Sdd {

	String storeId;
	String storeLocationNbr;
	String servedLocationZipCode;
	LatLng servedLocationZipCodeLatLng;
	String servedLocationAddress;
	String storeName;
	String addressLine1;
	String city;
	String state;
	String storePhysicalZipCode;
	LatLng storePhysicalZipCodeLatLng;

	
	
	
	public String getServedLocationAddress() {
		return servedLocationAddress;
	}

	public void setServedLocationAddress(String servedLocationAddress) {
		this.servedLocationAddress = servedLocationAddress;
	}

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

	public String getServedLocationZipCode() {
		return servedLocationZipCode;
	}

	public void setServedLocationZipCode(String servedLocationZipCode) {
		this.servedLocationZipCode = servedLocationZipCode;
	}

	public LatLng getServedLocationZipCodeLatLng() {
		return servedLocationZipCodeLatLng;
	}

	public void setServedLocationZipCodeLatLng(LatLng servedLocationZipCodeLatLng) {
		this.servedLocationZipCodeLatLng = servedLocationZipCodeLatLng;
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

	public String getStorePhysicalZipCode() {
		return storePhysicalZipCode;
	}

	public void setStorePhysicalZipCode(String storePhysicalZipCode) {
		this.storePhysicalZipCode = storePhysicalZipCode;
	}

	public LatLng getStorePhysicalZipCodeLatLng() {
		return storePhysicalZipCodeLatLng;
	}

	public void setStorePhysicalZipCodeLatLng(LatLng storePhysicalZipCodeLatLng) {
		this.storePhysicalZipCodeLatLng = storePhysicalZipCodeLatLng;
	}

	@Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this);
				
	}

	class LatLng {
		public LatLng(double lat, double lng) {
			this.lat = lat;
			this.lng = lng;
		}

		double lat;
		double lng;

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		@Override
		public String toString() {
			return "Lat:" + lat + "-" + "Lng:" + lng;
		}

	}

}
