
[General]
Version=1

[Preferences]
Username=
Password=2989
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=WRITTENBY
Count=10..20

[Record]
Name=ADTYPEWRITTEN
Type=VARCHAR2
Size=30
Data=List('PictureType', 'ArticleType')
Master=

[Record]
Name=ADID
Type=NUMBER
Size=
Data=Sequence(1, 1, 600)
Master=

[Record]
Name=WRITERID
Type=NUMBER
Size=
Data=Sequence(1, 1, 500)
Master=

