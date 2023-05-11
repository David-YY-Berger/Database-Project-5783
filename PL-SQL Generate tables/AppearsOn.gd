
[General]
Version=1

[Preferences]
Username=
Password=2927
Database=
DateFormat=
CommitCount=0
CommitDelay=0
InitScript=

[Table]
Owner=SYSTEM
Name=APPEARSON
Count=100

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
Data=List('Website')
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
Data=Random(51, 150)
Master=

[Record]
Name=PLATFORMNAME
Type=VARCHAR2
Size=30
Data=List('Besheva', 'Yated_neeman', 'Yediot_acharonot', 'Yisrael_hayom')
Master=

