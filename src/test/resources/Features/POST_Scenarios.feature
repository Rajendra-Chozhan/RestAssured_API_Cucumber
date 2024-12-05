@POST_Feature
Feature: To implement this feature to run GET Requests

  @POST_API
  Scenario Outline: Verify the POST Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "POST" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the POST api data for "<TestcaseName>"
    Examples:
      | TestcaseName | path            |
      | post_authors | /api/v1/Authors |

  @POST_API
  Scenario Outline: Verify the POST Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "POST" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the POST api data for "<TestcaseName>"
    Examples:
      | TestcaseName    | path               |
      | post_activities | /api/v1/Activities |

  @POST_API
  Scenario Outline: Verify the POST Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "POST" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the POST api data for "<TestcaseName>"
    Examples:
      | TestcaseName | path          |
      | post_books   | /api/v1/Books |

  @POST_API
  Scenario Outline: Verify the POST Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then User Trigger "POST" request to "Rest" API and validate response code as 200 for "<TestcaseName>"
    Then User Validate the POST api data for "<TestcaseName>"
    Examples:
      | TestcaseName     | path |
      | post_coverphotos | /api/v1/CoverPhotos    |