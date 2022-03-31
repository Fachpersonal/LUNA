    # R
R is a short way of Resources, because thats what this class does it manages some Resources that the system needs like the buildNr etc.
## Attributes
```txt
+s buildNr : int
+s ioT : Thread
+s core : Core
+s fileHelper : FileHelper
+s logger : Logger
+s config : HashMap<String, String>
+s language : HashMap<String, String>
+s user :  HashMap<String, UserData>
```

## Operations
```txt
+s loadConfig(defaultConfig : boolean) : boolean
+s loadLanguage(defaultLanguage : boolean) : boolean
+s loadUser()
+s encryptString(message : String) : String
```

### loadConfig(boolean) : boolean
This operation loads the config for the system
Depends on the *boolean value*, it chooses the *'.res/system.conf'* or default values that are *hardcoded*, and loads them so the system can use them. The content of *'.res/system.conf'* is getted by [[FileHelper#readFile(File)]], fileHelper is assigned by [[Core]] but stored in it own **[[R#Attributes|view Attributes]]**.  This operation is able to throw an **IOException** if something goes wrong!
### loadLanguage(boolean) : boolean
This operation loads the language for the system
The operation also uses [[FileHelper#readFile(File)]] to gets content out of a file, but this depends on the *boolean value* that is given, this value determines rather to choose *'.res/system.lang'* or the default hardcoded Strings. FileHelper is assigned by [[Core]] but stored in it own **[[R#Attributes|view Attributes]]**. This operation is able to throw an **IOException** if something goes wrong!
### loadUser()
This operation loads all Users that are locally safed in *'.res/userData/'*. With the help of [[FileHelper#readFile(File)]] and the content that is stored inside of a the file you *get all important infos about a user*, like userName and age and so on, the data gets checked and later on stored inside an new [[UserData]] Object and else if some of the data is invalid it gets 'Reported'([[Logger#ERROR(String)]]). This operation is able to throw an **IOException** if something goes wrong!
### encryptString(String) : String
This Operation uses the [SHA-256 Algorithm](https://en.bitcoinwiki.org/wiki/SHA-256) to encrypt a the message and *returns the newly encrypted String*.