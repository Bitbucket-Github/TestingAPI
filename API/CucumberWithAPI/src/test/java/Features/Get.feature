@get
Feature: Feature to check Get Method of API

Background: User has Bearer token for Authorization
	Given I am a Authorized User

  @AllUsers
  Scenario: User able to Get the List of Users
    When Send GET HTTP request
    Then I receive valid HTTP response code
	@SingleUser
	Scenario: User able to Get the Single User details
		When Send Get HTTP request for Single User
		Then I receive valid HTTP response code
		
	@UsernotFound
	Scenario: Response for User details not found
		When Send Get HTTP request for user
		Then Verify the reponse Code

