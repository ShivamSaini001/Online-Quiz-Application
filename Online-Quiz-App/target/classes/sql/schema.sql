
CREATE TABLE IF NOT EXISTS question_categories(
	category_id INT AUTO_INCREMENT,
	category_name VARCHAR(300) NOT NULL UNIQUE,
	PRIMARY KEY(category_id)
);

CREATE TABLE IF NOT EXISTS questions(
	question_id INT AUTO_INCREMENT,
    question_text VARCHAR(2000) NOT NULL UNIQUE,
    correct_option_id int UNIQUE,
    category_id int NOT NULL,
    PRIMARY KEY(question_id),
	FOREIGN KEY (category_id) REFERENCES question_categories(category_id)
	ON DELETE cascade
	ON UPDATE cascade
);

CREATE TABLE IF NOT EXISTS options(
	option_id INT AUTO_INCREMENT,
    option_text VARCHAR(2000) NOT NULL,
    question_id int NOT NULL,
    PRIMARY KEY(option_id),
	FOREIGN KEY (question_id) REFERENCES questions(question_id)
	ON DELETE cascade
	ON UPDATE cascade
);

 ALTER TABLE questions
    ADD FOREIGN KEY (correct_option_id) REFERENCES options(option_id)
    ON DELETE cascade
	ON UPDATE cascade
    ;

CREATE TABLE IF NOT EXISTS users(
	username VARCHAR(200) NOT NULL PRIMARY KEY,
	password VARCHAR(500) NOT NULL,
	enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
	username VARCHAR(200) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	FOREIGN KEY (username) REFERENCES users(username),
	UNIQUE(username, authority)
);

CREATE TABLE IF NOT EXISTS quiz_result(
	quiz_id int primary key,
    username varchar(400) not null,
    category_id int not null,
    total_questions int not null,
    correct_answers int not null,
    wrong_answers int not null,
    not_attempt int not null,
    mark_for_each_question double not null,
    total_marks double not null,
    obtained_markes double not null,
    score_in_percentage double not null,
    created_at_date varchar(200),
    created_at_time varchar(200),
    FOREIGN KEY (username) REFERENCES users(username)
	ON DELETE cascade
	ON UPDATE cascade,
    FOREIGN KEY (category_id) REFERENCES question_categories(category_id)
	ON DELETE cascade
	ON UPDATE cascade
);


CREATE TABLE IF NOT EXISTS quiz_settings(
    number_of_questions int default 10,
    number_of_seconds_for_each_question int default 30
);    
    
CREATE TABLE IF NOT EXISTS sequence_table(
    table_name varchar(400) primary key,
    next_pk_value int default 0
);   
    
    
    