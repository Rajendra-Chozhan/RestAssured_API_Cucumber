@DELETE_Feature
Feature: To implement this feature to run GET Requests

  @DELETE_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "DELETE" request to "Rest" API and validate response code as 200 for "<TestcaseName>"

    Examples:
      | TestcaseName    | path                |
      | Delete_activity | api/v1/Activities/1 |


  @DELETE_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "DELETE" request to "Rest" API and validate response code as 200 for "<TestcaseName>"

    Examples:
      | TestcaseName  | path               |
      | Delete_author | api/v1/Authors/123 |


  @DELETE_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "DELETE" request to "Rest" API and validate response code as 200 for "<TestcaseName>"

    Examples:
      | TestcaseName | path             |
      | Delete_book  | api/v1/Books/996 |

  @DELETE_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "DELETE" request to "Rest" API and validate response code as 200 for "<TestcaseName>"

    Examples:
      | TestcaseName      | path               |
      | Delete_coverphoto | api/v1/CoverPhotos |
