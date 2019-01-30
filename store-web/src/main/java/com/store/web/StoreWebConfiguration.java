package com.store.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

//@PropertySource(value="file:${risk.app.properties.full.path}/application.properties",ignoreResourceNotFound = true)
public class StoreWebConfiguration {

    @Value("${store.lat.lng.file.path}")
   private String storeLatLongFilePath;

}
