#Author: mentor@testingementor.com

@BA
Feature: Language
  
  @Language @SmokeTest
  Scenario: Assert default language
    Given I am on the homepage
    Then the default language should show "United Kingdom - English"