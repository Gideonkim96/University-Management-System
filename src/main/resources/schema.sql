CREATE TABLE IF NOT EXISTS `Courses`(
    `course_id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` varchar(100) NOT NULL,
    `description` varchar(100) NOT NULL,
    `number_of_credits` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `Students`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    date_of_birth DATE
);

CREATE TABLE IF NOT EXISTS LearnerProfilers(
    profile_id INT AUTO_INCREMENT NOT NULL,
    number_of_credits INT NOT NULL,
    gpa FLOAT NOT NULL,
    is_graduated BOOLEAN,
    start_year INT
);

CREATE TABLE IF NOT EXISTS Programs(
    program_id INT AUTO_INCREMENT PRIMARY KEY,
    required_years_to_graduation INT NOT NULL DEFAULT 4,
    required_credits_to_graduation INT NOT NULL DEFAULT 200,
    field_of_study VARCHAR(100) NOT NULL,
    degree VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS COURSE_ENROLLMENTS(
    course_id INT NOT NULL,
    enrolled_student_id INT AUTO_INCREMENT PRIMARY KEY

);
