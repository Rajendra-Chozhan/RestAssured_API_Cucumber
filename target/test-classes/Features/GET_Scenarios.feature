@GET_Feature
Feature: To implement this feature to run GET Requests

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName  | path                                             |
      | get_airline_1 | v1/airlines/73dd5420-3bf9-48f3-a0b6-17cf7aa61b19 |


  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName  | path                                             |
      | get_airline_2 | v1/airlines/93c10d0b-1afe-4867-b40c-bff2f2bd625f |

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName  | path                                             |
      | get_airline_3 | v1/airlines/00cad03a-a19f-407c-a334-6fce979fd1e3 |

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName  | path                                             |
      | get_airline_4 | v1/airlines/56d7ffa4-84a8-4756-997c-4c8427aafed7 |

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName  | path                                             |
      | get_airline_5 | v1/airlines/4b61f673-9315-4a23-98f7-31d0ce0d265a |

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName    | path          |
      | get_passenger_1 | v1/passenger/667ab7627ad8fb5bd04343ac |


  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName    | path          |
      | get_passenger_2 | v1/passenger/667ab7637ad8fb9a144343b1 |


  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName    | path          |
      | get_passenger_3 | v1/passenger/667ab7647ad8fb66b84343b6 |

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName    | path                                             |
      | get_passenger_4 | v1/passenger/667ab79e7ad8fb6bef4343d4 |

  @GET_API
  Scenario Outline: Verify the GET Request with Valid Details as <TestcaseName>
    Given build the request parameters for API
      | Field | Value  |
      | Path  | <path> |

    Then Trigger "GET" request to "Rest" API and validate response code as 200 and Status description as "OK"
    Then Validate the GET api data for "<TestcaseName>"
    Examples:
      | TestcaseName    | path         |
      | get_passenger_5 | v1/passenger/667ab7617ad8fb2f294343a7 |

  @GET_API
  Scenario Outline: Reqres GET API test
    Given the valid endpoint to fetch users
    When the request is send to server with page number as “<Id>”
    Then validate the response of first user record having email as “<title>”

    Examples:
      | Id | title                                                 |
      | 1  | Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops |