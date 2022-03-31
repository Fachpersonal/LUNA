# Core
This is the very Core of this Project-Luna, from here everything starts.
This class is responsible to start all [[ModuleStructure|modules]], this class is the equivalent of a heart

## Attributes

## Operations
```txt
c Core()
```

### Core()
Starts [[ModuleStructure#start()|module]] and assignes all [[R#Attributes]] and also starts other [[ModuleStructure|modules]]. It also [[R#loadUser|loads all saved Users]] and [[R#loadConfig boolean boolean|loads Config]] and [[R#loadLanguage boolean boolean|loads language]] and also *displays all ERRORs/WARNINGs or other important messages* with the help of [[Logger]] 
#### Module List:
- [[Logger]]