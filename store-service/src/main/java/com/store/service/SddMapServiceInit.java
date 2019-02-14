package com.store.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.maps.model.LatLng;
import com.store.service.domain.Sdd;
import com.store.service.domain.SddStore;

@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:sddmap_service.properties")

public class SddMapServiceInit {
	private HashMap<String, List<Sdd>> storeServedLocationMap = new HashMap<>();
	private Logger log = LoggerFactory.getLogger(SddMapServiceInit.class);
	private HashMap<String, SddStore> storeMap = new HashMap<>();


	@Value("${sdd.store.latlng.file.json}")
	private String storeLatLongJsonFile;
	@PostConstruct
	void init() {
		JsonReader jsonReader = null;
		try {
			jsonReader = new JsonReader(new FileReader(storeLatLongJsonFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		try {
			jsonReader.beginArray();
			List<Sdd> storeServedlatLngsList = new LinkedList<>();
			while (jsonReader.hasNext()) {
				Sdd sdd = gson.fromJson(jsonReader, Sdd.class);
				if (sdd == null)
					continue;
				// Create a Map only for Stores
				if (storeMap.get(sdd.getStoreId()) == null) {
					SddStore sddStore = new SddStore();
					sddStore.setStoreId(sdd.getStoreId());
					sddStore.setStoreLocationNbr(sdd.getStoreLocationNbr());
					sddStore.setStoreName(sdd.getStoreName());
					sddStore.setAddressLine1(sdd.getAddressLine1());
					sddStore.setCity(sdd.getCity());
					sddStore.setState(sdd.getState());
					LatLng latLng = new LatLng(sdd.getStorePhysicalZipCodeLatLng().getLat(), sdd.getStorePhysicalZipCodeLatLng().getLng());
					sddStore.setStoreLatLng(latLng);
					storeMap.put(sddStore.getStoreId(), sddStore);
					///log.debug("Adding to Stroe Map: " + sddStore);
				}

				if (storeServedLocationMap.get(sdd.getStoreId()) == null) {
					storeServedlatLngsList = new LinkedList<>();
					storeServedlatLngsList.add(sdd);
				} else {
					storeServedlatLngsList = storeServedLocationMap.get(sdd.getStoreId());
					storeServedlatLngsList.add(sdd);
				}
				storeServedLocationMap.put(sdd.getStoreId(), storeServedlatLngsList);
			}

			log.debug("storeMap.size():" + storeMap.size() + " storeServedLocationMap.size():" + storeServedLocationMap.size());

		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			log.error("JsonIOException" + e.getMessage(), e);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			log.error("JsonSyntaxException" + e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("IOException" + e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Exception" + e.getMessage(), e);
		}
	}

	public HashMap<String, SddStore> getSddStores() {
		return storeMap;
	}
	
	public HashMap<String, List<Sdd>>getSddServedStoresLocation() {
		return storeServedLocationMap;
	}

}
