# R

R is a short way of Resources, because thats what this class does it manages some Resources that the system needs like the buildNr etc.

## Attributes

```txt
+s buildNr : int
+s core : Core
+s fileHelper : FileHelper
+s logger : Logger
+s users :  HashMap<String, UserData>
```

## Operations

```txt
+s loadUsers()
+s encryptString(message : String) : String
```

### loadUsers()

This operation loads all Users that are locally safed in *'.res/userData/'*. With the help of [[FileHelper#readFile(File)]] you get the content that is stored inside the file, you *get all important infos about a user*, the data gets checked and later on stored inside an new [[UserData]] Object and else if some of the data is invalid it gets 'Reported'([[Logger#ERROR(String)]]). This operation is able to throw an **IOException** if something goes wrong!

### encryptString(String) : String

This Operation uses the [SHA-256 Algorithm](https://en.bitcoinwiki.org/wiki/SHA-256) to encrypt a the message and *returns the newly encrypted String*.
