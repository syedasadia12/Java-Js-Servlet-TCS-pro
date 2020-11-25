--Table for bank employees ONLY login info
CREATE TABLE userstore (
    username VARCHAR2(100),
    password VARCHAR2(50),
    employeeName VARCHAR2(100),
    userType VARCHAR2(50),
    lastLogin TIMESTAMP
);

--Table for CustomerStatus
CREATE TABLE CustomerStatus (
    customerID VARCHAR2(10),
    ssnID NUMBER(9),
    customerName VARCHAR2(50),
    customerAge NUMBER(3),
    customerAddress VARCHAR2(200),
    customerCity VARCHAR2(50),
    customerState VARCHAR2(50),
    message VARCHAR2(150),
    status VARCHAR2(50),
    lastUpdate TIMESTAMP
);

--Table for AccountStatus
CREATE TABLE AccountStatus (
    customerID VARCHAR2(10),
    accountID NUMBER(10),
    accountType VARCHAR2(50),
    message VARCHAR2(150),
    status VARCHAR2(50),
    lastTransaction TIMESTAMP,
    accountBalance NUMBER(10)
);


--Table for Transaction
CREATE TABLE Transaction (
    transactionID VARCHAR(10),
    accountID NUMBER(10),
    transactionType VARCHAR2(50),
    amount NUMBER(10),
    customerID VARCHAR2(10),
    targetAccountID NUMBER(10),
    transactionDate TIMESTAMP
);


--userstore table constraints
ALTER TABLE userstore ADD CONSTRAINT login_pk PRIMARY KEY (username);
ALTER TABLE userstore MODIFY (username NOT NULL);
ALTER TABLE userstore MODIFY (password NOT NULL);
ALTER TABLE userstore MODIFY (employeeName NOT NULL);
ALTER TABLE userstore MODIFY (userType NOT NULL);
ALTER TABLE userstore ADD CONSTRAINT check_userType CHECK(userType = 'Customer_Account_Executive' OR userType = 'Cashier/Teller');

--CustomerStatus table constraints

ALTER TABLE CustomerStatus ADD CONSTRAINT customerID_pk PRIMARY KEY (customerID);
ALTER TABLE CustomerStatus ADD CONSTRAINT check_age CHECK(customerAge >= 18 OR customerAge <= 110);
ALTER TABLE CustomerStatus ADD CONSTRAINT check_status CHECK(status = 'Active' or status = 'Inactive');
ALTER TABLE CustomerStatus MODIFY (customerName NOT NULL);
ALTER TABLE CustomerStatus MODIFY (customerAge NOT NULL);
ALTER TABLE CustomerStatus MODIFY (customerAddress NOT NULL);
ALTER TABLE CustomerStatus MODIFY (customerCity NOT NULL);
ALTER TABLE CustomerStatus MODIFY (customerState NOT NULL);
ALTER TABLE CustomerStatus MODIFY (ssnID UNIQUE NOT NULL);

--AccountStatus table constraints

ALTER TABLE AccountStatus ADD CONSTRAINT checkAS_status CHECK(status = 'Active' or status = 'Inactive');
ALTER TABLE AccountStatus ADD CONSTRAINT customerStatus_FK FOREIGN KEY (customerID) REFERENCES CustomerStatus(customerID);
ALTER TABLE AccountStatus ADD CONSTRAINT accountID_PK PRIMARY KEY (accountID);
ALTER TABLE AccountStatus ADD CONSTRAINT check_balance CHECK(accountBalance >= 0);

--Transaction table constraints
ALTER TABLE Transaction ADD CONSTRAINT transactionID_PK PRIMARY KEY (transactionID);
ALTER TABLE Transaction ADD CONSTRAINT customerID_FK FOREIGN KEY (customerID) REFERENCES CustomerStatus(customerID);
ALTER TABLE Transaction ADD CONSTRAINT accountsID_FK FOREIGN KEY (accountID) REFERENCES AccountStatus(accountID);
ALTER TABLE Transaction ADD CONSTRAINT checkTransactionType CHECK(transactionType = 'DEPOSIT' or transactionType = 'WITHDRAW' or transactionType = 'TRANSFER');