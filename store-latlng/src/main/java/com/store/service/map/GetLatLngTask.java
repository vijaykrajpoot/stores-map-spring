package com.store.service.map;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class GetLatLngTask implements Callable<Sdd> {
	private Logger log = LoggerFactory.getLogger(GetLatLngTask.class);
	private Sdd sdd;
	static GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCAeq78kvgSx4px62xk93wCMkdm79gmG1c")
			.build();
	private static String SP = " ";

	public GetLatLngTask(Sdd sdd) {
		this.sdd = sdd;
	}

	public Sdd call() {
		try {

			StringBuilder builder = new StringBuilder();
			Sdd.LatLng latLng = invokeGoogle(sdd.getServedLocationZipCode());
			if (latLng == null) {
				log.error("No Lat-Long for ServedLocationZipCode:" + sdd.getServedLocationZipCode());
			} else {
				this.sdd.setServedLocationZipCodeLatLng(latLng);
			}

			// System.out.println("message.getStoreLocationZipCode():" +
			// this.sdd.getStorePhysicalZipCode());
			String storeAddressTogetLatLng = builder.append(sdd.addressLine1)
					.append(SP)
					.append(sdd.city)
					.append(SP)
					.append(sdd.state)
					.append(SP)
					.append(sdd.storePhysicalZipCode)
					.toString();
			latLng = invokeGoogle(storeAddressTogetLatLng);
			if (latLng == null) {
				log.debug("No Lat-Long for Physical Store Location:" + sdd.getServedLocationZipCode());
			} else {
				log.debug("LAT-LNG for Store address:" + storeAddressTogetLatLng + " latLng.lat:" + latLng.lat + "latLng.lat:" + latLng.lat);
				this.sdd.setStorePhysicalZipCodeLatLng(latLng);
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.sdd;

	}

	public Sdd.LatLng invokeGoogle(String address) throws ApiException, InterruptedException, IOException {
		GeocodingResult[] geocodingResult = GeocodingApi.newRequest(context)
				.address(address)
				.await();
		Sdd.LatLng sddlatLng = null;
		if (geocodingResult != null && geocodingResult.length > 0) {
			sddlatLng = new Sdd().new LatLng(geocodingResult[0].geometry.location.lat, geocodingResult[0].geometry.location.lng);
			
			// System.out.println("geocodingResult[0]:" + geocodingResult[0]);
		}
		return sddlatLng;
	}

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		Sdd sdd = new Sdd();
		sdd.setAddressLine1("110 E REMINGTON DR");
		sdd.setServedLocationZipCode("110 E REMINGTON DR");
		GetLatLngTask getLatLngTask = new GetLatLngTask(sdd);
		Sdd.LatLng latLng = getLatLngTask.invokeGoogle(sdd.getServedLocationZipCode());

		System.out.println("getLatLngTask:" + latLng);
	}

	void m1() {

		// GeocodingApi.newRequest(null).
	}

}
