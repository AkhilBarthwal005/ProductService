CREATE TABLE category
(
    id         BIGINT NOT NULL,
    created_at datetime NULL,
    created_by VARCHAR(255) NULL,
    is_deleted BIT(1) NULL,
    update_at  datetime NULL,
    updated_by VARCHAR(255) NULL,
    title      VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE category_seq
(
    next_val BIGINT NULL
);

CREATE TABLE jt_instructor
(
    specialization VARCHAR(255) NULL,
    user_id        BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    avg_rating DOUBLE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE jt_ta
(
    avg_rating    INT    NOT NULL,
    no_of_session INT    NOT NULL,
    user_id       BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    user_id BIGINT NOT NULL,
    age     INT    NOT NULL,
    name    VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE msc_instructor
(
    id             BIGINT NOT NULL,
    age            INT    NOT NULL,
    name           VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE msc_mentor
(
    id   BIGINT NOT NULL,
    age  INT    NOT NULL,
    name VARCHAR(255) NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE msc_ta
(
    id            BIGINT NOT NULL,
    age           INT    NOT NULL,
    name          VARCHAR(255) NULL,
    avg_rating    INT    NOT NULL,
    no_of_session INT    NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT NOT NULL,
    created_at    datetime NULL,
    created_by    VARCHAR(255) NULL,
    is_deleted    BIT(1) NULL,
    update_at     datetime NULL,
    updated_by    VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    title         VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product_seq
(
    next_val BIGINT NULL
);

CREATE TABLE st_user
(
    dtype          VARCHAR(31) NOT NULL,
    single_user_id BIGINT      NOT NULL,
    age            INT         NOT NULL,
    name           VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    no_of_session  INT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (single_user_id)
);

CREATE TABLE tpc_instructor
(
    tpc_user_id    BIGINT NOT NULL,
    age            INT    NOT NULL,
    name           VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (tpc_user_id)
);

CREATE TABLE tpc_mentor
(
    tpc_user_id BIGINT NOT NULL,
    age         INT    NOT NULL,
    name        VARCHAR(255) NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (tpc_user_id)
);

CREATE TABLE tpc_ta
(
    tpc_user_id   BIGINT NOT NULL,
    age           INT    NOT NULL,
    name          VARCHAR(255) NULL,
    avg_rating    INT    NOT NULL,
    no_of_session INT    NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (tpc_user_id)
);

CREATE TABLE tpc_user
(
    tpc_user_id BIGINT NOT NULL,
    age         INT    NOT NULL,
    name        VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (tpc_user_id)
);

ALTER TABLE product
    ADD CONSTRAINT FK1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION;

CREATE INDEX FK1mtsbur82frn64de7balymq9s ON product (category_id);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK4od6mbg1v99qri5dtqreaayou FOREIGN KEY (user_id) REFERENCES jt_user (user_id) ON DELETE NO ACTION;

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK74kd6ct4a7jq51dr84f8m7usr FOREIGN KEY (user_id) REFERENCES jt_user (user_id) ON DELETE NO ACTION;

ALTER TABLE jt_ta
    ADD CONSTRAINT FKhq7nv0qp5o8md1xwoglkc7g7k FOREIGN KEY (user_id) REFERENCES jt_user (user_id) ON DELETE NO ACTION;