# Logger
This class-name explains everything it self *hehe*

## Attributes

## Operations
```txt
c Logger()
+ LOG(msg : String)
+ INFO(msg : String)
+ WARNING(msg : String)
+ ERROR(msg : String)
+ ERROR(e : Exception)
- getDate() : String
- getTime() : String
```

### Logger()
starts [[ModuleStructure#start()|Module]]
### LOG(String)
Outputs message into Console and writes/appends it into a file.
Message example: "build-[[R#Attributes|buildNr]] \<[[Logger#getTime() : String|getTime()]]\> msg"
The filePath is determined by [[R#Attributes|R.config.get("logDir")]]
The message gets writen with the help of [[FileHelper#writeFile(String, String, boolean)]]
### INFO(String)
Outputs message into Console and writes/appends it into a file.
Message example: "build-[[R#Attributes|buildNr]] \<[[Logger#getTime() : String|getTime()]]\> \[INFO\] :: msg"
The filePath is determined by [[R#Attributes|R.config.get("logDir")]]
The message gets writen with the help of [[FileHelper#writeFile(String, String, boolean)]]
### WARNING(String)
Outputs message into Console and writes/appends it into a file.
Message example: "build-[[R#Attributes|buildNr]] \<[[Logger#getTime() : String|getTime()]]\> \[WARNING\] :: msg"
The filePath is determined by [[R#Attributes|R.config.get("logDir")]]
The message gets writen with the help of [[FileHelper#writeFile(String, String, boolean)]]
### ERROR(String)
Outputs message into Console and writes/appends it into a file.
Message example: "build-[[R#Attributes|buildNr]] \<[[Logger#getTime() : String|getTime()]]\> \[ERROR\] :: msg"
The filePath is determined by [[R#Attributes|R.config.get("logDir")]]
The message gets writen with the help of [[FileHelper#writeFile(String, String, boolean)]]
### ERROR(Exception)
Outputs message into Console and writes/appends it into a file.
Message example: "build-[[R#Attributes|buildNr]] \<[[Logger#getTime() : String|getTime()]]\> \[ERROR\] :: Exception.printStackTrace()"
The filePath is determined by [[R#Attributes|R.config.get("logDir")]]
The message gets writen with the help of [[FileHelper#writeFile(String, String, boolean)]]
### getDate() : String
returns current Date \[dd.mm.yyyy\]
### getTime() : String
returns current Time \[HH:mm:ss\]