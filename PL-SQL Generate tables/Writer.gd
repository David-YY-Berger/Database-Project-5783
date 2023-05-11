
[General]
Version=1

[Preferences]
Username=
Password=2077
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=WRITER
Count=100

[Record]
Name=WRITERID
Type=NUMBER
Size=
Data=Sequence(1, 1, 100)
Master=

[Record]
Name=WRITERNAME
Type=VARCHAR2
Size=30
Data=FirstName+' '+LastName
Master=

[Record]
Name=NUMYEARSOFEXPERIENCE
Type=NUMBER
Size=
Data=Random(3, 15)
Master=

[Record]
Name=PAYPERHOUR
Type=NUMBER
Size=
Data=Random(30, 150)
Master=

