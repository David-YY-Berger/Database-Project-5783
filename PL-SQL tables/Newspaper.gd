
[General]
Version=1

[Preferences]
Username=
Password=2459
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=NEWSPAPER
Count=10..20

[Record]
Name=NUMCOPIESPRINTED
Type=NUMBER
Size=
Data=Random(1000, 5000)
Master=

[Record]
Name=PLATFORMNAME
Type=VARCHAR2
Size=30
Data=List('Besheva', 'Yated_neeman', 'Israel_Hayom', 'Mishpacha', 'Yediot_acharonot')
Master=

