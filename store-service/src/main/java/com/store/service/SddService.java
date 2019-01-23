package com.store.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.service.domain.Sdd;
import com.store.service.domain.SddStore;

@Service
public class SddService {
	@Autowired
	private SddMapServiceInit sddMapServiceInit;

	public List<SddStore> getSddStores() {
		List<SddStore> list = null;
		Collection<SddStore> sddStores = sddMapServiceInit.getSddStores().values();

		if (sddStores instanceof List) {
			list = (List<SddStore>) sddStores;
		} else {
			list = new ArrayList<SddStore>(sddStores);
		}

		return list;
	}
	
	public List<Sdd> getSddStoresServedLocationByStoreId(String storeId) {
		HashMap<String, List<Sdd>> sddStores = sddMapServiceInit.getSddServedStoresLocation();
		return sddStores.get(storeId);
	}

}
