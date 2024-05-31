CREATE TABLE productservices.`role`
(
    id         BIGINT NOT NULL,
    created_at datetime NULL,
    created_by VARCHAR(255) NULL,
    update_at  datetime NULL,
    updated_by VARCHAR(255) NULL,
    is_deleted BIT(1) NULL,
    role_name  VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

ALTER TABLE productservices.product
    ADD quantity INT NULL;

ALTER TABLE productservices.product
    MODIFY quantity INT NOT NULL;

DROP TABLE productservices.category_seq;

DROP TABLE productservices.product_seq;

ALTER TABLE productservices.st_user
DROP
COLUMN avg_rating;

ALTER TABLE productservices.st_user
    ADD avg_rating INT NOT NULL;

ALTER TABLE productservices.st_user
    MODIFY avg_rating INT NOT NULL;

ALTER TABLE productservices.st_user
    MODIFY dtype VARCHAR (31) NULL;

ALTER TABLE productservices.st_user
    MODIFY no_of_session INT NOT NULL;