CREATE TABLE address(
    id UUID PRIMARY KEY NOT NULL,
    country VARCHAR(2) NOT NULL,
    street VARCHAR(100) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    postal_code VARCHAR(8) NOT NULL,
    CONSTRAINT uq_address UNIQUE (country, street, house_number, postal_code)
);

CREATE TABLE customer(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    tax_id VARCHAR(14) NOT NULL,
    info VARCHAR(100),
    address_id UUID NOT NULL,
    CONSTRAINT fk_customer_address FOREIGN KEY (address_id)
        REFERENCES address (id)
        ON DELETE RESTRICT
);