CREATE TABLE `user` (
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `mobile_number` VARCHAR(45) NOT NULL,
  `date_of_birth` VARCHAR(45) NOT NULL,
  `user_type` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `logged_in` TINYINT(1) NOT NULL,
  PRIMARY KEY (`user_name`));
  
    CREATE TABLE `courses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) DEFAULT NULL,
  `languages` VARCHAR(45) DEFAULT NULL,
  `cost` VARCHAR(45) NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `created_at` VARCHAR(45) NOT NULL,
  `updated_at` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`));
  
    CREATE TABLE `course_videos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `video_filename` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (course_id) REFERENCES courses(id));