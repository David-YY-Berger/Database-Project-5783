
--ariel, i began writing this, to test the tables:

-- must first create the parents, and only then you create the children. 

insert into Advertisement
values (1, 24, 'name of article..');

insert into picturead(adid, length, width)
values (1, 10, 10);

-- you can make an article w the same adid as the pitcure one...(is this bad?) 
-- it means that there will be the same author for them... 
insert into article(adid, numwords)
values (1, 1000);

INSERT INTO Writer (writerId, name, NumYearsOfExperience, PayPerHour)
VALUES (613, 'Rashi', 45, 100);
INSERT INTO Writer(writerId, name, NumYearsOfExperience, PayPerHour)
VALUES (100, 'Tosafos', 40, 50);
INSERT INTO Writer (writerId, name, NumYearsOfExperience, PayPerHour)
VALUES (1000, 'R Ovadia Yosef', 5, 10);



