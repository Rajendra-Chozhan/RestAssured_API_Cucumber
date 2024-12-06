@GET_Feature
Feature: To implement this feature to run GET Requests

  @GET_API_Negative1
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 400 and status Title as <title> and Error description as <errordescription> for <TestcaseName>
    Examples:
      | TestcaseName            | path              | errordescription             | title                                   |
      | get_author_Alphabet     | api/v1/Authors/H  | The value 'H' is not valid.  | One or more validation errors occurred. |
      | get_author_Space        | api/v1/Authors/ H | The value ' H' is not valid. | One or more validation errors occurred. |
      | get_author_SplCharacter | api/v1/Authors/!@ | The value '!@' is not valid. | One or more validation errors occurred. |


  @GET_API_Negative
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 204
    Examples:
      | TestcaseName           | path                                              |  |  |
      | get_airline_no_content | v1/airlines/ 73dd5420-3bf9-48f3-a0b6-17cf7aa61b19 |  |  |
      | get_author_Space       | v1/passenger/ 667ab7627ad8fb5bd04343ac            |  |  |