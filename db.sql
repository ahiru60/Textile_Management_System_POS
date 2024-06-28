
-- Create Databases if they do not exist
CREATE DATABASE IF NOT EXISTS clothmgt_userLogin;
CREATE DATABASE IF NOT EXISTS clothmgt_brands;
CREATE DATABASE IF NOT EXISTS clothmgt_reports;

-- Use the clothmgt_userLogin database and create the passmgt table if it does not exist
USE clothmgt_userLogin;
CREATE TABLE IF NOT EXISTS passmgt (
    user_name VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

-- Use the clothmgt_brands database and create an example table for a brand if it does not exist
-- Replace [brandName] with the actual brand name when creating the table
USE clothmgt_brands;
-- Example table for a brand (replace [brandName] with the actual brand name)
CREATE TABLE IF NOT EXISTS [brandName] (
    itemId INT NOT NULL AUTO_INCREMENT,
    item_name VARCHAR(30),
    category VARCHAR(20),
    price VARCHAR(20),
    no_of_items VARCHAR(20),
    PRIMARY KEY(itemId)
);

-- Use the clothmgt_reports database and create the purchase_report table if it does not exist
USE clothmgt_reports;
CREATE TABLE IF NOT EXISTS purchase_report (
    purchase_id INT NOT NULL AUTO_INCREMENT,
    item_id VARCHAR(50),
    no_of_items VARCHAR(20),
    price VARCHAR(20),
    brand_name VARCHAR(50),
    item_name VARCHAR(50),
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(purchase_id)
);
