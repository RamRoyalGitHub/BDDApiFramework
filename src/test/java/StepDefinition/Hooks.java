package StepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deltePlace")
	public void name() throws IOException {
		
		StepDef stepDef = new StepDef();
		if (StepDef.place_id == null) {
			stepDef.add_place_payload("Royal", "Telugu", "Whitefield");
			stepDef.user_calls_with_http_request("addPlaceApi", "POST");
			stepDef.verify_plcae_id_created_maps_to_using("Royal", "getPlaceApi");
		}

		
	}

}
