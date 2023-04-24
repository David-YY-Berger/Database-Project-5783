CREATE TABLE Advertisement
(
  adId INT NOT NULL,
  hoursToWrite INT NOT NULL,
  adName VARCHAR(40) NOT NULL,
  PRIMARY KEY (adId)
);

CREATE TABLE PictureAd
(
  length INT NOT NULL,
  width INT NOT NULL,
  adId INT NOT NULL,
  PRIMARY KEY (adId),
  FOREIGN KEY (adId) REFERENCES Advertisement(adId)
);

CREATE TABLE Article
(
  numWords INT NOT NULL,
  adId INT NOT NULL,
  PRIMARY KEY (adId),
  FOREIGN KEY (adId) REFERENCES Advertisement(adId)
);

CREATE TABLE Platform
(
  platformName VARCHAR(30) NOT NULL,
  PRIMARY KEY (platformName)
);

CREATE TABLE Website
(
  url VARCHAR(100) NOT NULL,
  numViewersDaily INT NOT NULL,
  platformName VARCHAR(30) NOT NULL,
  PRIMARY KEY (platformName),
  FOREIGN KEY (platformName) REFERENCES Platform(platformName)
);

CREATE TABLE Newspaper
(
  numCopiesPrinted INT NOT NULL,
  platformName VARCHAR(30) NOT NULL,
  PRIMARY KEY (platformName),
  FOREIGN KEY (platformName) REFERENCES Platform(platformName)
);

CREATE TABLE Writer
(
  writerId INT NOT NULL,
  writerName VARCHAR(30) NOT NULL,
  numYearsOfExperience INT NOT NULL,
  payPerHour INT NOT NULL,
  PRIMARY KEY (writerId)
);

CREATE TABLE AppearsOn
(
  datePublished DATE NOT NULL,
  priceToPayPlatform INT NOT NULL,
  platformType VARCHAR(20) NOT NULL,
  adTypeAppears VARCHAR(20) NOT NULL,
  adId INT NOT NULL,
  platformName VARCHAR(30) NOT NULL,
  PRIMARY KEY (datePublished, platformType, adTypeAppears, adId, platformName),
  FOREIGN KEY (adId) REFERENCES Advertisement(adId),
  FOREIGN KEY (platformName) REFERENCES Platform(platformName)
);

CREATE TABLE WrittenBy
(
  adTypeWritten VARCHAR(20) NOT NULL,
  adId INT NOT NULL,
  writerId INT NOT NULL,
  PRIMARY KEY (adTypeWritten, adId, writerId),
  FOREIGN KEY (adId) REFERENCES Advertisement(adId),
  FOREIGN KEY (writerId) REFERENCES Writer(writerId)
);
