package com.store.service.map;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.maps.errors.ApiException;

/**
 * Hello world!
 *
 */
public class App {

	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException, ExecutionException {
		log.debug("Fun started.....");
		try {
			writeJson(readJsonStream());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Sdd> readJsonStream() throws IOException, ApiException, InterruptedException, ExecutionException {
		int cnt = 0;
		ExecutorService executor = Executors.newFixedThreadPool(100);
		JsonReader jsonReader = new JsonReader(new FileReader("/Users/m735438/Macys/sdd-map/sdd_store_location_raw.json"));
		Gson gson = new Gson();
		List<Sdd> messages = new LinkedList<Sdd>();
		jsonReader.beginArray();
		List<Future<Sdd>> futureSddList = new LinkedList<Future<Sdd>>();

		while (jsonReader.hasNext()) {
			cnt++;
			Sdd sdd = gson.fromJson(jsonReader, Sdd.class);
			log.trace("Store Location Without Lt/Lng:" + sdd.toString());
			futureSddList.add(executor.submit(new GetLatLngTask(sdd)));
		}
		jsonReader.endArray();
		jsonReader.close();
		System.out.println("No of Records read:" + cnt + "List.size:" + futureSddList.size());

		cnt = 0;
		for (Future<Sdd> futureSdd : futureSddList) {
			cnt++;
			Sdd sdd = futureSdd.get();
			messages.add(sdd);
		}
		System.out.println("No of Records Processed:" + cnt);
		// shut down the executor service now
		executor.shutdown();
		return messages;

	}

	static void writeJson(List<Sdd> sdd) throws IOException {
		// Create a new Gson object
		Gson gson = new Gson();
		// convert the Java object to json
		String jsonString = gson.toJson(sdd);
		// Write JSON String to file
		FileWriter fileWriter = new FileWriter("/Users/m735438/Macys/sdd-map/sdd_store_location_lat_lng.json");
		fileWriter.write(jsonString);
		fileWriter.close();

	}

}
