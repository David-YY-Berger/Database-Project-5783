
[General]
Version=1

[Preferences]
Username=
Password=2578
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=ADVERTISEMENT
Count=200

[Record]
Name=ADID
Type=NUMBER
Size=
Data=Sequence(1, 1, 600)
Master=

[Record]
Name=HOURSTOWRITE
Type=NUMBER
Size=
Data=Random(5, 40)
Master=

[Record]
Name=ADNAME
Type=VARCHAR2
Size=30
Data=List('Coca_cola1', 'Fanta1', 'Shweps1', 'Sprite1', 'Kinley1', 'Coca_cola2', 'Fanta2', 'Shweps2', 'Sprite2', 'Kinley2')
Master=

