Feature: Testing the Admin Feature
Background:
Given the admin has loaded the application in the browser
When the admin clicks  on the login Button navbar 
When the admin enters email in textbox
And the admin enters the password in the textbox
And the admin clicks on the login button

@Module1
Scenario: Testing the Delete Hotel With Valid Data
And the admin clicks on Hotels
And the Admin Clicks on Any Of The Hotel
Then the Admin Clicks on Delete Button 

@Module2
Scenario: Testing the Add Hotel With Valid Data
And the Admin clicks on Add Hotel 
And the Admin enters Hotel Name
And the Admin enters Hotel Location
And the Admin enters Contact Number
And the Admin enters Image
Then the Admin clicks on submit 

@Module3
Scenario: Testing the Add Hotel With Valid Data
And the Admin clicks on Add Hotel 
And the Admin enters Hotel Name
And the Admin enters Hotel Location
And the Admin enters Contact Number
And the Admin enters Image
Then the Admin clicks on submit 

@Module4
Scenario: Testing the Details of Users With Valid Data
And the Admin clicks on Users Dropdown
And the Admin clicks On users list


