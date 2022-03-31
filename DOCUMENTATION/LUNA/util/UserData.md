# UserData
This stores all data of a user

## Attributes
```txt
- username : String
- password : String
- firstName : String
- surName : String
- birthday : LocalDate
- age : int
- loggedin = false : boolean
```

## Operations
```txt
+s login() : UserData
+s register() : UserData
c UserData(username : String, password : String, firstName : String, surName : String, birthday : LocalDate, age : int, createFile : boolean)
+ gUsername() : String
+ gPassword() : String
+ gFirstName() : String
+ gSurName() : String
+ gBirthday() : LocalDate
+ gAge() : int
+ gLoggedIn() : boolean
```

### login() : UserData
This operation allows you to log into the system. This operation is able to throw an **IOException** if something goes wrong! or it [[Logger#ERROR(Exception) |logs]] the Exception. This operation *returns a UserData Object* after a successfull register.
### register() : UserData
This operation allows you to register into the system. This operation is able to throw an **IOException** if something goes wrong! or it [[Logger#ERROR(Exception) |logs]] the Exception. This operation *returns a UserData Object* after a successfull login or [[#register() : UserData]].
### UserData(String, String, String, String, LocalData, int, boolean)
This constructor saves the given arguments into its [[#Attributes]]. The last attribute which is a *boolean value* desides if the freshly created User should be saved in a file or not. If so it uses the [[FileHelper#writeFile(String, ArrayList\<String\>, boolean]] operation to create a new UserFile and saves it in *'.res/userData/'*. The *fileName* is the [[R#encryptString(String) : String|encrypted value]] of the username.
## Getter & Setter
### gUsername() : String
returns *[[UserData#Attributes|username]]* 
### gPassword() : String
returns *[[UserData#Attributes|password]]* **[[R#encryptString(String) : String|encrypted]]**
### gFirstname() : String
returns *[[UserData#Attributes|firstName]]*
### gSurName() : String
returns *[[UserData#Attributes|surName]]*
### gBirthday() : LocalDate
returns *[[UserData#Attributes|birthday]]*
### gAge() : int
returns *[[UserData#Attributes|age]]*
### gLoggedIn() : boolean
returns *[[UserData#Attributes|loggedin]]*