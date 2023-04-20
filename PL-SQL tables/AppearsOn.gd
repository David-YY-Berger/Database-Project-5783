
[General]
Version=1

[Preferences]
Username=
Password=2206
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=APPEARSON
Count=10..20

[Record]
Name=DATEPUBLISHED
Type=DATE
Size=
Data=Random(1.1.2000, 1.1.2023)
Master=

[Record]
Name=PRICETOPAYPLATFORM
Type=NUMBER
Size=
Data=Random(500, 2000)
Master=

[Record]
Name=PLATFORMTYPE
Type=VARCHAR2
Size=30
Data=List('WebsiteType', 'NewspaperType')
Master=

[Record]
Name=ADTYPEAPPEARS
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
Name=PLATFORMNAME
Type=VARCHAR2
Size=30
Data=List('Besheva', 'Yated_neeman', 'Israel_Hayom', 'Mishpacha', 'Yediot_acharonot')
Master=

