package com.store.service.domain;

public class LatLng {
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
		return "Lat:" + lat + " " + "Lng:" + lng;
	}
}
