CREATE TABLE IF NOT EXISTS weatherDbDto
(
    id UUID NOT NULL,
    cityName VARCHAR(20) NOT NULL,
    date Date,
    temperature double,
    PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS cityRepository
(
    id UUID NOT NULL,
    cityName VARCHAR(20) NOT NULL,
    lat double,
    lon double,
    PRIMARY KEY (id)
);