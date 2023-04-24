
[General]
Version=1

[Preferences]
Username=
Password=2277
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=WEBSITE
Count=4

[Record]
Name=URL
Type=VARCHAR2
Size=40
Data=List('www.Besheva.com', 'www.Yated_neeman.com', 'www.Israel_Hayom.com', 'www.Yediot_acharonot.com')
Master=

[Record]
Name=NUMVIEWERSDAILY
Type=NUMBER
Size=
Data=Random(300, 1000)
Master=

[Record]
Name=PLATFORMNAME
Type=VARCHAR2
Size=30
Data=List('Besheva', 'Yated_neeman', 'Israel_Hayom', 'Yediot_acharonot')
Master=

