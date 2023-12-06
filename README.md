# Instructions to run the program

## Prerequiisists

Before we start, make sure yoou have the following:

* PostreSQL
* intelliJ IDEA community edition 
* Posgres-JDBC driver (https://jdbc.postgresql.org/download/)

Download the repo:

First of all go to the git repository (https://github.com/alialiyev818/Assignment-2), click on the code button and click on download ZIP.

## On intelliJ IDEA: 

1) Open the 'the_app' folder with intelliJ IDEA

2) Click on 'file' option on top of the editor and select 'project structure'

3) from the left side menu there, go to the option called 'project'.

4) select an SDK version. If available selecting 20 is recommended.

5) Also select the language level the same as you chose the SDK version. Again '20-no new language features' is the recommended option but if you have chosen a different SDK version, make sure that it matches that. Please don't choose any of the preview versions. 

6) Click on apply and select modules from the side menu. There, click on dependencies. Select the same SDK version (which you chose in the Project section) from Module SDK option under the dependencies tab.

7) Click on the plus icon and select 'JARs or Directories'. Then you need to locate the JDBC driver (postgresql-XX.X.X.jar) on your device, which I have included in the things you will need as 'Posgres-JDBC driver'. You can find the link there as well. Once you find it, click on it once and select okay. 

8) Once you add it, there should be a total of 3 elements, in the following order: 

- Oracle OpenDJK version (20 in my case)

- postgresql-XX.X.X.jar

- Module Source

The order is important and you need to make sure that it's in this order before running the application

9) Make sure that the Dependencies storage format is selected as intelliJ IDEA (.iml) on the bottom of this window and click on apply and then okay.


## Creating the database:

1) Create a postgres database either using the terminal or pgAdmin. 

2) Find the SQL querries located in the submitted word files.

3) Either connect to the database using terminal and run the CREATE querries in order one by one or do the same directly in pgadmin using the database's query tool. (You still should run the CREATE querries one by one).

4) Keep in mind the name of the database, username name of postgres (it's postgres by default), and the password you have created when you installed postgres.


## Setting up the code:

1) Main java file is located in the src folder, so go ahead and click on the 'bookstore.java' file twice to view the code.

2) There, locate the following code and fill in the information that I told you to remember in the previous step. Regarding the database name, you just need to change last part 'bookstore' to the name of your new database:

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "8118";