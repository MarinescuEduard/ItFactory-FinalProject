# Final Project - IT Factory Course

This project is a small application that is meant to manage a database filled with users.
The application is created using the SprinBoot framework and uses Postman in order to test the API.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

1. Clone the repository.
2. If you are missing any dependency, under pom.xml add the following dependencies and rebuild the project:

![dependencies Screenshot](screenshots/dependencies.png)

3. Under src->main->resources->application.properties complete the following fields:

![applicationProperties Screenshot](screenshots/applicationProperties.png)

- spring.datasource.url - I am using the default "5432" port. Make sure to change to the port you are using.
- spring.datasource.username - Fill in you SpringBoot username
- spring.datasource.password - Fill in your SpringBoot password
- server.port - Please fill in "9099" as this is the port used for all the API requests created in this project. If you
  fill a different port please make sure you adjust in the Postman requests.

4. Open pgAdmin and create a new database called "finalProject" OR alternatively, I added the .sql file in the repository. It was created using pg_dump.

In order to install the database using the SQL file, we have to open command prompt (cmd.exe).

- In the cmd window, we have to change the directory to where PostgreSQL is installed and select the "bin" folder. As an example, we change the directory to H:\PostgreSQL\bin.
- Then we introduce the following command in cmd window: -psql -U "user"" -d ""database" -1 -f "download folder of .sql file" where "user" should be changed with your PostgreSQL username, "database" should be changed to the database name, in this case is "finalProject", and "download folder" should be changed to the root of the download folder of .sql file.
- After that, you will be asked to introduce your PostgreSQL password. The database should be installed now.


## Usage

Make sure you have both Postman and pgAdmin opened.
Please follow this steps in order an read the entire sentence before proceeding. Thank you!

1. Run the main application under src->main->java->com.itfactory->JavaFinalProject.
   After this step, we should see the table created in pgAdmin, under the Tables tab, like the screenshot below.

![pgAdmin Screenshot](screenshots/pgAdmin.png)

2. In Postman we can create the following POST Request: http://localhost:9099/persons/addPerson
   In the Body of the request, we can switch the input to raw and Json as the picture below.

![addPerson Screenshot](screenshots/addPerson.png)

3. In the body of the request we add the following text, and then we send the request in order to create the first
   person.

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

![beforeEachScreenshot Screenshot](/screenshots/beforeEachScreenshot.png)

4. In order to see our database with the persons that we added in the previous steps,
   we create a GET request with the following line: http://localhost:9099/persons/getPersonList. We should see the
   following result.
   ![getPersonList Screenshot](screenshots/getPersonList.png)

5. We can also check a specific person using their ID. In order to do this, we create a new GET request with the
   following line: http://localhost:9099/persons/1
   The first person that we added should be returned to us as seen in this screenshot.

![findById Screenshot](screenshots/findById.png)

6. Moving forward, we will proceed to modify the last name of the third person we added.
   In order to do this, we will create a Patch request with the following line: http://localhost:9099/persons/3
   In the body of the request we can add the following raw Text "Gheorghe" as seen in this picture:

![changeLastName Screenshot](screenshots/changeLastName.png)

We should see the last name changed an get the status 200 OK like this snip here.

![changeLastNameOutcome Screenshot](screenshots/changeLastNameOutcome.png)

7. Now we will modify the e-mail address of this person. To do that, we create another Patch request with this
   code: http://localhost:9099/persons/email/3
   In the body we add the following raw text "AndreiGheorghe@test.com".

![changeEmail Screenshot](screenshots/changeEmail.png)

We should see the e-mail changed.

![changeEmailOutcome Screenshot](screenshots/changeEmailOutcome.png)

8. Finally, we can also delete a person by ID, so we will proceed deleting the person with ID 3.
   To do this, we create a new Delete request with this line http://localhost:9099/persons/3.
   We should get 204 No Content status.

![deleteById Screenshot](screenshots/deleteById.png)

9. Now we can switch back to IntelliJ and under src->test->java->com.itfactory->JavaFinalProjectTests, we can run all
   tests for the class.
   All 9 tests should be passed.

   NOTE: For the purpose of the tests, we do not need to add any persons to the database as I there is this @BeforeEach
annotation which adds 3 Person objects to the database before each test and an @AfterEach annotation that clears the database of the 3 persons added for test purposes.

![tests Screenshot](screenshots/testsv2.png)

