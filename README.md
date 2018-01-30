# Bioinformatics and Genomics Center

Author: Rick Medemblik<br />
Contact: rmedemblik93@gmail.com<br />

### DESCRIPTION

Web interface for every user with no prior programming knowledge to start a pipeline and see the results.

### REQUIREMENTS

-	Linux operating system. This software is developed on Linux Ubuntu 16.04<br />
	**WARNING: Experiences when using different operating systems may vary.**
-	Grails 3.3.1
- PostgreSQL 9.5.10
- Java 8
- IDE for Grails

### INSTALLATION

-	Clone the BIGC repository to the desired location on your system.<br />
	`https://github.com/rm93/BIGC.git`
  
#### Grails
The easiest way to install Grails version 3.3.1 is with [SDKMAN!](http://www.sdkman.io)<br />
-	`curl -s "https://get.sdkman.io" | bash`<br />
- `source "$HOME/.sdkman/bin/sdkman-init.sh"`
- `sdk install grails 3.3.1`

#### PostgreSQL
To install PostgreSQL follow the below commands.
 - `sudo apt-get update`
 - `sudo apt-get install postgresql postgresql-contrib`

### PREPARATIONS

#### Create database

Login by PostgreSQL with:<br />
`sudo -u postgres psql`<br />

Make a database with:<br />
`postgres=# CREATE DATABASE dbname;`<br />

#### change parameter

- Open the project with a IDE of your choice.
- Open the file application.yml wich can be found at BIGC/grails-app/conf/.
- Find the line `production: dataSource` below in the document.
- Change the database name to your database name.

### USAGE

To start the web interface you open a terminal and navigate to the location where the project is stored and run the command `grails run-app`.
