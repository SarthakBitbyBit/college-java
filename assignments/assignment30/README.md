# assignment 30: jsp crud operations

## title and exercise descriptions
- title: write a jsp application to demonstrate crud operations.
- exercise 1: employee management system built with jsp to add, view, update, and delete employee records (`employee_id`, `name`, `department`, `salary`).
- exercise 2: patient management system built with jsp to perform crud operations on hospital patient records (`patient_id`, `name`, `age`, `disease`, `treatment_status`).

## concepts demonstrated
- jsp presentation layer communicating with mysql database via jdbc
- parameterized sql statements via `PreparedStatement` to prevent sql injection
- create (insert), read (select), update (update), and delete (delete) workflows
- html form validation, error message reporting, and html output escaping to prevent xss
- database schema design with primary keys and constraints

## source files
- `30_jsp_crud.sql`: master sql script for database `jsp_lab`, creating `Employee` and `Patient` tables with seed records.
- `webapp/index.jsp`: web application home page linking to both crud modules.
- `webapp/db_config.jsp`: reusable database connection configuration and html escaping helper.
- `webapp/employees.jsp`: complete employee crud interface with inline creation, listing, editing, and deletion.
- `webapp/patients.jsp`: complete patient crud interface with inline creation, listing, editing, and deletion.

## prerequisites and dependencies
- mysql server 8.4 (running locally on port 3306)
- mysql connector/j jdbc driver (`mysql-connector-j-9.4.0.jar`)
- apache tomcat (version 9 or 10) servlet/jsp container
- java development kit (jdk 25 or compatible)

## database initialization
execute the provided setup script with mysql administrative credentials:
```bash
mysql -u root -p < 30_jsp_crud.sql
```
this script creates the `jsp_lab` database, grants privileges to `javauser` (password `Java1234`), creates the tables `Employee` and `Patient`, and populates sample records.

## deployment to apache tomcat
1. copy the `mysql-connector-j-9.4.0.jar` driver into tomcat library folder:
   ```bash
   cp /home/sarthakbitbybit/College/JAVA/Threads/assignments/assignment19/lib/mysql-connector-j-9.4.0.jar /path/to/tomcat/lib/
   ```
2. copy or symlink the `webapp/` folder to tomcat's `webapps/` directory:
   ```bash
   cp -r webapp/ /path/to/tomcat/webapps/assignment30
   ```
3. start the tomcat server:
   ```bash
   /path/to/tomcat/bin/startup.sh
   ```
4. access the application in your web browser:
   ```text
   http://localhost:8080/assignment30/index.jsp
   ```

## crud operations tested
- **create:** new employee (#104) and new patient (#204) inserted using parameterized preparedstatement queries.
- **read:** formatted html tables query records using `SELECT ... ORDER BY id` with xss escaping.
- **update:** clicking 'edit' pre-populates the input form; saving changes triggers `UPDATE ... WHERE id = ?`.
- **delete:** clicking 'delete' invokes `DELETE FROM ... WHERE id = ?` with client confirmation prompt.

## screenshots
- employee crud page: `screenshots/employee_crud.png`
- patient crud page: `screenshots/patient_crud.png`

## conclusion
jsp combined with jdbc and preparedstatement provides an effective web-based interface for database manipulation. create, read, update, and delete workflows were implemented with proper validation, sql injection prevention, and responsive error handling.
