
@Post
Feature: Feature to check the Post Method of API

Background: User has Bearer token for Authorization
	Given I am a authorized User

  @Create
  Scenario: Able to create a new record successfully
  	When I enter required details to create a record
  	And Send POST HTTP request
  	Then Verfiy the HTTP Response Code

  @Error
  Scenario: Verify the response for unsuccessful record 
		When I do not enter details required to create a record
		And Send POST HTTP request
		Then Verify HTTP Responce
		
	@RegisteredEmail
	Scenario: Verify the response for existing email record
		When I enter details with existing email id
		And Send POST HTTP request
		Then Verify Responce Code