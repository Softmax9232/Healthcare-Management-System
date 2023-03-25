<h1> Healthcare Management System</h1>

Healthcare Management System (Desktop app) is a hospital management system that uses **JavaFX, Windows Forms, and Oracle database while applying OOP, design pattern concepts, SOLID principles, refactoring, and clean coding.**
</br></br>

<h1> Description </h1>
This project is for hospital workflow management and has multiple views:

- Doctor 
- Pharmacist 
- Laboratory Technician 
- Receptionist 
- Administrator

<h1> Prerequisites </h1>

- Install Netbeans IDE 8.2 & JDK 8u202 
- Install MYSQL JDBC Driver 5.1 
- Install Xampp 
- Install JasperReports 
- Any Oracle Database 

<h1>Getting into the project</h1>

**<h3>Schema</h3>** 
 [Database SQL Script](Healthcare_Database.txt)</br>
 [JasperReport Script](JasperReport%20Script.txt)</br>

**<h1> Login page </h1>** 
- Each user can log in to their account
</br>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/Screenshots/login%20page.PNG" alt="" width="722" height="534"/>

**<h1> Change Password page </h1>**


- The user enters their username and then clicks **Recover button**.

- The system displays a recovery question, which the user answers before pressing **Submit button**.

- The user enters a new password and clicks **Submit button**.

- The system displays a message. Once the user clicks "OK" the system redirects the user to the **login page**.
</br>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/forget1.PNG" alt="" width="710" height="500"/>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/forget2.PNG" alt="" width="700" height="500"/>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/forget3.PNG" alt="" width="700" height="500"/>
</br></br>

**<h3> According to the username and password, the system will redirect the user to his account</h3>**

**<h1> Reciptionist page</h1>**
- Consists of 4 pages</br>

**<h3>Appointments page</h3>** </br>
The receptionist searches for the available doctor in each department to keep an appointment with the patient.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/reciptionist-appointment.PNG" alt="" width="999" height="600"/>

**<h3>Outpatient Registration page</h3>** 
The receptionist records the outpatient's data on the system with his appointment.

- The receptionist can look for anything on the table.
- When any row is selected, the data is displayed and then it can be updated or deleted.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/reciptionist-outpatient_search-update-delete.PNG" alt="" width="1012" height="608"/>

**<h3>Inpatient Registration page</h3>** 
The receptionist records the data of inpatients and their relatives on the system.

- The receptionist can look for anything on the table.
- When any row is selected, the data is displayed and then it can be updated or deleted.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/reciptionist-inpatient.PNG" alt="" width="1008" height="600"/>

**<h3>Bill page</h3>** 
- The receptionist looks for the patient by ID.
- The system displays the patient's name and test, medication, and room details.
- The receptionist filters it by date, then enters the date and current costs for that date, then clicks the Add button.
- The system displays the total cost, and then the user clicks the "Print" button.

<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/reciptionist-bill1.PNG" alt="" width="1006" height="600"/>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/reciptionist-bill2.PNG" alt="" width="1000" height="600"/>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/reciptionist-bill3.PNG" alt="" width="533" height="664"/>

**<h1> Doctor page</h1>**

- Today's appointments were added by the receptionist with patient details. 
- The doctor filters the day's appointments by the appointments column. 
- The doctor adds the next appointment, time, and prescription and then prints it.
- The doctor can look for anything on the table
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/doctor.PNG" alt="" width="961" height="600"/>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/doctor1.PNG" alt="" width=518" height="712"/>

**<h1> Pharmacist page</h1>**
- The pharmacist can look up anything on the table.
- The pharmacist adds medicines to patients who ask for them in the system.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/pharmacist1.PNG" alt="" width="960" height="600"/>

- The pharmacist can look up anything on the table.
- The pharmacist can add, update, and delete medicines. 
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/pharmacy2.PNG" alt="" width=965" height="600"/>

**<h1> Laboratory Technician page</h1>**
- The laboratory technician can look up anything on the table.
- The laboratory technician adds laboratory tests to patients who ask for them in the system.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/test1.PNG" alt="" width="961" height="600"/>

- The laboratory technician can look up anything on the table.
- The laboratory technician can add, update, and delete laboratory tests. 
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/test2.PNG" alt="angular" width=958" height="600"/>

**<h1> Administrator page</h1>**
- The administrator can search for anything on the table.
- The administrator can add, update and delete employees.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/admin.PNG" alt="angular" width="960" height="600"/>

- This scroll page analyzes salaries, number of departments, employees, medicines, lab tests, and number of employees in each department.
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/admin2.PNG" alt="angular" width=700" height="500"/>
<p align="left"> <img src="https://github.com/amira921/Healthcare-Management-System/blob/main/screenshot/admin3.PNG" alt="angular" width=700" height="500"/>


