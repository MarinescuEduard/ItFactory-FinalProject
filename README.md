# Final Project - IT Factory Course

This project is a small application that is meant to manage a database filled with users. 
The application is created using the SprinBoot framework and uses Postman in order to test the API.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

Step 1: - Clone the repository.
Step 2: - Under pom.xml add the following dependencies: <To be filled>
Step 3: - Under src->main->resources->application.properties complete the following fields: <To be filled>

## Usage

Make sure you have both Postman and pgAdmin opened.

Step 1: Run the main application under src->main->java->com.itfactory->JavaFinalProject.
After this step, we should see the table created in pgAdmin4 like the screenshot below <To be filled>

Step 2: In Postman we can create the following POST Request: http://localhost:9099/persons/addPerson
In the Body of the request, we can switch the input to raw and Json as the picture below <To be filled>

Step 3: In the body of the request we add the following text, and then we send the request in order to create the first person.

{
"firstName":"Ion",
"lastName":"Mihai",
"emailPerson":"IonMihai@test.com",
"agePerson":22
}

We can then fill the body with the following text to create the second person:

{
"firstName":"Alex",
"lastName":"Marian",
"emailPerson":"AlexMarian@test.com",
"agePerson":26
}

And finally, insert this text to create the third person:

{
"firstName":"Andrei",
"lastName":"Vasile",
"emailPerson":"AndreiVasile@test.com",
"agePerson":30
}

Now we have 3 persons in out database.

Step 4: In order to see our persons in the database, we create a GET request with the following line: http://localhost:9099/persons/getPersonList