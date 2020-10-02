package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.LocationMaps;
import pojo.MapAddApi;

public class TestDataBuild {

	public MapAddApi addPlacePayload() {

		MapAddApi map = new MapAddApi();
		map.setAccuracy(50);
		map.setAddress("29, side layout, cohen 09");
		map.setLanguage("French-IN");
		map.setName("Frontline house");
		map.setName("(+91) 983 893 3937");
		map.setWebsite("http://google.com");
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");

		LocationMaps location = new LocationMaps();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		map.setLocation(location);
		map.setTypes(list);
		return map;

	}

	public MapAddApi addPlacePayload(String name, String language, String address) {

		MapAddApi map = new MapAddApi();
		map.setAccuracy(50);
		map.setAddress(address);
		map.setLanguage(language);
		map.setName(name);
		map.setPhone_number("(+91) 983 893 3937");
		map.setWebsite("http://google.com");
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");

		LocationMaps location = new LocationMaps();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		map.setLocation(location);
		map.setTypes(list);
		return map;

	}
	
	public String deleteApiPayload(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"  \r\n" + 
				"}";
		
	}

}
