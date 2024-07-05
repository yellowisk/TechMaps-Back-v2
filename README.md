<p align="center">
  <img src="src/main/resources/media/techmaps-brand-shortened-logo.png" height="128">
  <p align="center">TechMaps strives towards providing students roadmaps that cover topics of the software development world.</p>
</p>


## Creating the Database

In IntelliJ Ultimate, open the database tab and connect to your machine's postgresql database.

Then go to the directory postgres/sql where you'll execute the following files:
- ``V0_setup.sql``
- ``V1_create_schema.sql``
- ``V2_populate_schema.sql``
- ``V4_populate_step_description.sql``

Afterwards, select and run all the code in ``V0_setup.sql`` in a session in the DB connection you just made. After successfully running the code in this file you'll have created a database called "techmaps" with an owner "techmaps-app". You should be able to verify it in pgAdmin4.

Now connect to the "techmaps" database in the same way you connected to the postgresql one. Afterwards, select and run all the code in ``V1_create_schema.sql`` in a session in the DB connection you just made. Then repeat the same proccess with the ``V2_populate_schema.sql`` file.

## Running the Application

After making sure your database is set up and populated, you can run the java file ``src/main/java/com/acing/techmaps/TechMapsApplication.java``. The server will start running on <http://localhost:8757>.

Log in with one of the users created and send the generated JWT in all your other requests when using the application. Every 30 minutes, refresh the JWT to keep using the application.

## Thank you, contributors!
<a href="https://github.com/yellowisk/TechMaps-API/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=yellowisk/TechMaps-Back-v2">
</a>
