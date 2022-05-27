<h1> Healthcare Management System</h1>

Healthcare Management System is a Hospital Management System using **JavaFx, Windows Forms and Oracle Database, applying OOP and design Patterns Concepts.**
</br></br>

<h1> Description </h1>
This project is for hospital workflow management and has multiple views:

- Doctor 
- Pharmacist 
- Laboratory Technician 
- Receptionist 
- Administrator

</br>

<h1> Prerequisites </h1>

- Install Netbeans
- Install JasperReports
- Any Oracle Database

</br>

<h1>Getting into the project</h1>

**<h3>Schema</h3>** 
 [Database SQL Script](db/Healthcare_Database.txt)</br>
 [JasperReport Script](db/JasperReport%20Script.txt)</br>

**<h1> Login page </h1>** 
- Each user can log in to their account
</br>
<p align="left"> <img src="screenshot\login.png" alt="angular" width="700" height="500"/>

**<h1> Change Password page </h1>**


- The user enters their username and then clicks **Recover button**.

- The system displays a recovery question, which the user answers before pressing **Submit button**.

- The user enters a new password and clicks **Submit button**.

- The system displays a message. Once the user clicks "OK" the system redirects the user to the **login page**.
</br>
<p align="left"> <img src="screenshot\forget1.png" alt="angular" width="500" height="300"/>
<p align="center"> <img src="screenshot\forget2.png" alt="angular" width="500" height="300"/>
<p align="right"> <img src="screenshot\forget3.png" alt="angular" width="500" height="300"/>
</br></br>

**<h3> According to the username and password, the system will redirect the user to his account</h3>**

**<h1> Reciptionist page</h1>**
- Consists of 4 pages</br>

**<h3>Appointments page</h3>** </br>
The receptionist searches for the available doctor in each department to keep an appointment with the patient.
<p align="left"> <img src="screenshot\reciptionist-appointment.png" alt="angular" width="700" height="500"/>

**<h3>Outpatient Registration page</h3>** 
The receptionist records the outpatient's data on the system with his appointment.

- The receptionist can look for anything on the table.
- When any row is selected, the data is displayed and then it can be updated or deleted.
<p align="left"> <img src="screenshot\reciptionist-outpatient_search-update-delete.png" alt="angular" width="700" height="500"/>

**<h3>Inpatient Registration page</h3>** 
The receptionist records the data of inpatients and their relatives on the system.

- The receptionist can look for anything on the table.
- When any row is selected, the data is displayed and then it can be updated or deleted.
<p align="left"> <img src="screenshot\reciptionist-inpatient.png" alt="angular" width="700" height="500"/>

**<h3>Bill page</h3>** 
- The receptionist looks for the patient by ID.
- The system displays the patient's name and test, medication, and room details.
- The receptionist filters it by date, then enters the date and current costs for that date, then clicks the Add button.
- The system displays the total cost, and then the user clicks the "Print" button.

<p align="left"> <img src="screenshot\reciptionist-bill1.png" alt="angular" width="500" height="300"/>
<p align="center"> <img src="screenshot\reciptionist-bill2.png" alt="angular" width="500" height="300"/>
<p align="right"> <img src="screenshot\reciptionist-bill3.png" alt="angular" width="400" height="400"/>

**<h1> Doctor page</h1>**

- Today's appointments were added by the receptionist with patient details. 
- The doctor filters the day's appointments by the appointments column. 
- The doctor adds the next appointment, time, and prescription and then prints it.
- The doctor can look for anything on the table
<p align="left"> <img src="screenshot\doctor.png" alt="angular" width="500" height="300"/>
<p align="center"> <img src="screenshot\doctor1.png" alt="angular" width=300" height="400"/>

**<h1> Pharmacist page</h1>**
- The pharmacist can look up anything on the table.
- The pharmacist adds medicines to patients who ask for them in the system.
<p align="left"> <img src="screenshot\pharmacist1.png" alt="angular" width="500" height="300"/>

- The pharmacist can look up anything on the table.
- The pharmacist can add, update, and delete medicines. 
<p align="left"> <img src="screenshot\pharmacy2.png" alt="angular" width=500" height="300"/>

**<h1> Laboratory Technician page</h1>**
- The laboratory technician can look up anything on the table.
- The laboratory technician adds laboratory tests to patients who ask for them in the system.
<p align="left"> <img src="screenshot\test1.png" alt="angular" width="500" height="300"/>

- The laboratory technician can look up anything on the table.
- The laboratory technician can add, update, and delete laboratory tests. 
<p align="left"> <img src="screenshot\test2.png" alt="angular" width=500" height="300"/>

**<h1> Administrator page</h1>**
- The administrator can search for anything on the table.
- The administrator can add, update and delete employees.
<p align="left"> <img src="screenshot\admin.png" alt="angular" width="500" height="300"/>

- This scroll page analyzes salaries, number of departments, employees, medicines, lab tests, and number of employees in each department.
<p align="left"> <img src="screenshot\admin2.png" alt="angular" width=500" height="300"/>
<p align="right"> <img src="screenshot\admin3.png" alt="angular" width=500" height="300"/>


