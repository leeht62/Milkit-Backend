CREATE TABLE item (
                      item_id BIGINT NOT NULL AUTO_INCREMENT COMMENT ,
                      name VARCHAR(255) NOT NULL COMMENT ,
                      price INT NOT NULL COMMENT ,
                      stock_number INT NOT NULL COMMENT ,
                      image VARCHAR(255) COMMENT ,
                      content TEXT COMMENT ,
                      category VARCHAR(50) NOT NULL COMMENT ,
                      subcategory VARCHAR(50) NOT NULL COMMENT,
                      PRIMARY KEY (item_id)
);