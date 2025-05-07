CREATE TABLE item (
                      item_id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      price INT NOT NULL,
                      stock INT NOT NULL,
                      image VARCHAR(255),
                      content TEXT,
                      category VARCHAR(50) NOT NULL,
                      subcategory VARCHAR(50) NOT NULL,
                      PRIMARY KEY (item_id)
);