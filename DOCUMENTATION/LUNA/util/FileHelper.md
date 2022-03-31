# FileHelper
FileHelper is supposed to unify all file interactions within one class!
This is basically a class which helps you managing files
## Arttributes
```txt
- file : File
- content : ArrayList<String>
- fileLength : int
```
## Operations
```txt
c FileHelper()
+ readFile(userFile : File)
+ writeFile(filePath : String, content : ArrayList<String>, append : boolean)
+ writeFile(filePath : String, content : String , append : boolean)
+ writeSafedFile(filePath : String, content : ArrayList<String>, append : boolean)
+ unloadSafedFile()
+ gFile() : File
+ gContent() : ArrayList<String>
+ gFileLength() : int
```

### FileHelper()
initialises variables
### readFile(File)
This operation is able to throw an **IOException** if something goes wrong!
This operation *reads the given file* and *saves its important values* like file line length in [[FileHelper#Arttributes]]
### writeFile(String, ArrayList\<String\>, boolean)
This operation is able to throw an **IOException** if something goes wrong!
This operation *writes into given filePath* and depends on the *boolean* it only appends given content or overrides the whole file.
### writeFile(String, String, boolean)
This operation is able to throw an **IOException** if something goes wrong!
This operation *writes into given filePath* and depends on the *boolean* it only appends given content or overrides the whole file.
### writeSafedFile(String, ArrayList\<String\>, boolean)
This operation is able to throw an **IOException** if something goes wrong!
This operation *writes into given filePath* and depends on the *boolean* it only appends given content or overrides the whole file. On top of that it also *saves important values* in [[FileHelper#Arttributes]]
### unloadSafedFile()
Takes all [[FileHelper#Arttributes]] and sets it to its default values
## Getter & Setter
### gFile() : File
returns *[[FileHelper#Arttributes|file]]* 
### gContent() : ArrayList\<String\>
returns *[[FileHelper#Arttributes|content]]* 
### gFileLength() : int
returns *[[FileHelper#Arttributes|fileLength]]* 