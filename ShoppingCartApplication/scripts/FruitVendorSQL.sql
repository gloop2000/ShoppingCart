--creating schema
create SCHEMA FruitVendorAppData;

--creating tables
CREATE TABLE FruitVendorAppData.Fruits (
        ID INTEGER NOT NULL,
        Name VARCHAR(20) NOT NULL,
        Stock INTEGER NOT NULL,
        Price INTEGER NOT NULL,
        PRIMARY KEY (ID)
);

CREATE TABLE FruitVendorAppData.Users (
        U_ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
        U_Name VARCHAR(30) NOT NULL,
        U_Pass VARCHAR(20) NOT NULL
);

--inserting values
insert into FruitVendorAppData.Fruits values (1,'Mango',10,100);
insert into FruitVendorAppData.Users(U_Name,U_Pass) values ('Sharath','123');

--Editing column data type
ALTER TABLE FruitVendorAppData.Fruits ADD COLUMN STOCK_NEW FLOAT;
UPDATE FruitVendorAppData.Fruits SET STOCK_NEW  = STOCK;
ALTER TABLE FruitVendorAppData.Fruits DROP COLUMN STOCK;
RENAME COLUMN FruitVendorAppData.Fruits.STOCK_NEW  TO STOCK;

ALTER TABLE FruitVendorAppData.Users ADD COLUMN Phone varchar(15);