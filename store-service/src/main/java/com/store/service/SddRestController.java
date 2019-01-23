package com.store.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.service.domain.Sdd;
import com.store.service.domain.SddMapResponse;
import com.store.service.domain.SddStore;

@RestController
@RequestMapping(value="/api")
public class SddRestController {
	private Logger log = LoggerFactory.getLogger(SddRestController.class);
	@Autowired
	private SddService sddService;

	@RequestMapping("/map")
	public SddMapResponse getMap(@RequestParam(value = "address") String address) {
		System.out.println("address:" + address);
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value= "/stores" )
	public ResponseEntity<List<SddStore>> getAllSddStores() {
		log.debug("getSddStores:Invoked");
		return new ResponseEntity<List<SddStore>>(sddService.getSddStores(),HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value= "/stores/servedlocation/{storeId}" )
	public ResponseEntity<List<Sdd>> getAllStoreServedLocation(@PathVariable("storeId") String storeId) {
		log.debug("getAllStoreServedLocation:Invoked");
		return new ResponseEntity<List<Sdd>>(sddService.getSddStoresServedLocationByStoreId(storeId),HttpStatus.OK);
	}
	
}
