# Bioinformatics and Genomics Center

**Licence: GNU General Public License v3.0 (copy provided in directory)**<br />
Author: Rick Medemblik<br />
Contact: rmedemblik93@gmail.com<br />

### DESCRIPTION

Web interface for every user with no prior programming knowledge to start a pipeline and see the results.

### REQUIREMENTS

- Linux operating system. This software is developed on Linux Ubuntu 16.04<br />
  **WARNING: Experiences when using different operating systems may vary.**
- Grails 3.3.1
- PostgreSQL 9.5.10
- Java 8
- IDE for Grails
- Pipelines supported on the web interface.<br />
**At the moment only the [amplicon](https://github.com/Tommyvanwijk/RIVM_amplicon_pipeline) pipeline is supported.**

### INSTALLATION

- Clone the BIGC repository to the desired location on your system.<br />
  `https://github.com/rm93/BIGC.git`
- Install the suported pipelines with the instructions given on there github page.
  
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

- Login by PostgreSQL with:<br />
`sudo -u postgres psql`<br />
- Make a database with:<br />
`postgres=# CREATE DATABASE <dbname>;`<br />

#### Change parameter

- Open the project with a IDE of your choice.
- Open the file application.yml wich can be found at BIGC/grails-app/conf/.
- Find the line `production: development` below in the document.
- Change the database name to your database name.

#### Change upload size

- Open the project with a IDE of your choice.
- Open the file application.yml wich can be found at BIGC/grails-app/conf/.
- Find the line `controllers: upload` in the document.
- Change the upload size.

#### Change path parameter

- Open the project with a IDE of your choice.
- Open the file AmpliconController wich can be found at BIGC/grails-app/controllers/bigc/.
- Change the `path` parameter at the top of the `upload` function to the location were the project data would be stored.
- Change the `path` parameter at the top of the `deleteRecord` function to the location were the project data is stored (this is the same path as above).

#### Add the first amplicon

- Open the commandline interface
- Login by PostgreSQL with:<br />
`sudo -u postgres psql`
- Go to the newly created database with:<br />
`\c <dbname>`
- Add the following command:
`INSERT INTO amplicon (id, version, amplicon) VALUES (1, 0, '16sv4');`

#### Add the first department

- Open the commandline interface
- Login by PostgreSQL with:<br />
`sudo -u postgres psql`
- Go to the newly created database with:<br />
`\c <dbname>`
- Add the following command:
`INSERT INTO partner (id, version, short_name, name, country) VALUES (1, 0, 'IIV', 'National Institute for Public Health and the Environment', 'The Netherlands');`

### USAGE

To start the web interface you open a terminal and navigate to the location where the project is stored and run the command `grails run-app`.
