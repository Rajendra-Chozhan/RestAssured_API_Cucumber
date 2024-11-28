Feature: To implement this feature to run Sample Requests

  @GET_API1


 Scenario: Login the website
    Given User url for website
    Then user enter username and password
    Then user clicks login button
    Given build the request parameters for APII
      | Field | Value  |
      | Path  | <path> |