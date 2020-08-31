@Put_Delete
Feature: Feature to check the Put & Delete Method of API

Background: User has Bearer token for Authorization
	Given I am authorized User
	
@UpdateExisting
	Scenario: Update the existing record
		When I enter the details to update
		And Send PUT HTTP request
		Then Verify the responce code
		
@UpdateCreated
	Scenario: Update the created record
		When I enter details to update
		And Send HTTP PUT request
		Then Verify the HTTP responce code
		
@Delete
	Scenario: Delete a record
		When Send DELETE HTTP request
		Then Verify responce code
