@POST_Feature
Feature: To implement this feature to run GET Requests

  @POST_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "POST" request to "Rest" API and validate response code as 201 for "<TestcaseName>"
    Then User Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName  | path         |
      | create_user_1 | /api/users/1 |