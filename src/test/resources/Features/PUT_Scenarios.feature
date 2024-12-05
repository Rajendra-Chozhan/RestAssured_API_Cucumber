@PUT_Feature
Feature: To implement this feature to run GET Requests

  @PUT_API
  Scenario Outline: Verify the PUT Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |
    Then User Trigger "PUT" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the PUT api data for "<TestcaseName>"
    Examples:
      | TestcaseName | path             |  |  |
      | put_authors  | api/v1/Authors/1 |  |  |


  @PUT_API
  Scenario Outline: Verify the PUT Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "PUT" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the PUT api data for "<TestcaseName>"
    Examples:
      | TestcaseName | path             |
      | put_books    | api/v1/Books/123 |

  @PUT_API
  Scenario Outline: Verify the PUT Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "PUT" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the PUT api data for "<TestcaseName>"
    Examples:
      | TestcaseName   | path                |
      | put_activities | api/v1/Activities/1 |


  @PUT_API
  Scenario Outline: Verify the PUT Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "PUT" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the PUT api data for "<TestcaseName>"
    Examples:
      | TestcaseName   | path                |
      | put_coverphoto | api/v1/CoverPhotos/1 |