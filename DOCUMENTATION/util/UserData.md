# UserData

This stores all data of a user

## Attributes

```txt
- username : String
- password : String
```

## Operations

```txt
c UserData(username : String, password : String, createFile : boolean)
+ gUsername() : String
+ gPassword() : String
```

### UserData(String, String, boolean)

This constructor saves the given arguments into its [[#Attributes]]. The last attribute which is a *boolean value* desides if the freshly created User should be saved in a file or not. If so it uses the [[FileHelper#writeFile(String, ArrayList\<String\>, boolean]] operation to create a new UserFile and saves it in *'.res/userData/'*. The *fileName* is the [[R#encryptString(String) : String|encrypted value]] of the username.

## Getter & Setter

### gUsername() : String

returns *[[UserData#Attributes|username]]*

### gPassword() : String

returns *[[UserData#Attributes|password]]* **[[R#encryptString(String) : String|encrypted]]**
