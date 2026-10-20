# assignment 29: java server pages (jsp) basic concepts

## title and exercise descriptions
- title: write a jsp application to demonstrate the basic concepts of java server pages.
- exercise 1: student information form accepting student details (roll number, name, course, marks) and rendering a structured, formatted profile on a result jsp page.
- exercise 2: student registration application accepting user registration details (name, email, phone number, password), performing input validation, securely masking passwords, and rendering confirmation on a result jsp page.

## concepts demonstrated
- jsp architecture and lifecycle (translation into servlet, compilation, and execution)
- jsp declarations (`<%! ... %>`), scriptlets (`<% ... %>`), and expressions (`<%= ... %>`)
- http request parameter retrieval via `request.getParameter()`
- server-side form validation (field presence, email format, phone digits, password length)
- cross-site scripting (xss) prevention via html character escaping
- sensitive credential protection (masking passwords on confirmation screens)

## source files
- `webapp/index.jsp`: application portal linking to both exercises.
- `webapp/student_form.jsp`: user input form for student details.
- `webapp/student_result.jsp`: jsp result page displaying formatted student details.
- `webapp/register_form.jsp`: user registration form for student accounts.
- `webapp/register_result.jsp`: registration processing page with validation and masked password output.

## prerequisites and dependencies
- apache tomcat (version 9 or 10) servlet/jsp container
- java development kit (jdk 25 or compatible)
- standard web browser

## deployment to apache tomcat
1. copy or symlink the `webapp/` folder to tomcat's `webapps/` directory as `assignment29`:
   ```bash
   cp -r webapp/ /path/to/tomcat/webapps/assignment29
   ```
2. start the tomcat server:
   ```bash
   /path/to/tomcat/bin/startup.sh
   ```
3. open the application in your browser:
   ```text
   http://localhost:8080/assignment29/index.jsp
   ```

## expected workflows and outputs

### 1. student information (`student_form.jsp` & `student_result.jsp`)
- inputs: roll no `101`, name `Rahul Sharma`, course `Computer Science and Engineering`, marks `88.5`
- output: styled profile card displaying student details with success confirmation.

### 2. student registration (`register_form.jsp` & `register_result.jsp`)
- inputs: name `Priya Patel`, email `priya.patel@example.com`, phone `9876543210`, password `SecretPassword123`
- validation: verifies non-empty inputs, valid email containing `@` and `.`, 10-digit phone, and minimum 6-character password.
- output: registration confirmation showing submitted details with password masked as `••••••••`.

## screenshots
- student information result: `screenshots/student_info.png`
- registration confirmation: `screenshots/registration_result.png`

## conclusion
java server pages enable dynamic server-side web content generation by embedding java logic within standard html markup. input processing, validation, output sanitization, and credential masking were demonstrated.
