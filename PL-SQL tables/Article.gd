
[General]
Version=1

[Preferences]
Username=
Password=2303
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=ARTICLE
Count=10..20

[Record]
Name=NUMWORDS
Type=NUMBER
Size=
Data=Random(500, 100)
Master=

[Record]
Name=ADID
Type=NUMBER
Size=
Data=Sequence(200, 1, 600)
Master=

