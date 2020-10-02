Feature: valifdation for place Api's

@addPlace @Regression
  Scenario: Verify addPlace Api
  
    Given add place payload "<name>" "<language>" "<address>"
    When user calls "addPlaceApi" with "post" http request
    Then the api is success with status code 200
    And "status" in response body is "OK"
    And verify plcaeId created maps to "<name>" using "getPlaceApi"
    
    Examples:
     |name|language|address|
    |venkat|Greek|kadapa|
 #   |royal|kannada|bagalore|
 
@deltePlace @Regression
 Scenario: verify if delete place api is working 
 Given delete Place payload
 When user calls "deletePlaceApi" with "post" http request
 Then the api is success with status code 200
 And "status" in response body is "OK"
 
