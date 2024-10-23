CREATE TABLE weather_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(255),
    weather_condition VARCHAR(255),
    temperature DOUBLE,
    feels_like DOUBLE,
    date_time DATETIME
);
