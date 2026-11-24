/* 
 * Author:  User
 * Created: Nov 24, 2026
 */
CREATE TABLE Profile (
    studentID     VARCHAR(20)   NOT NULL PRIMARY KEY,
    name          VARCHAR(100)  NOT NULL,
    programme     VARCHAR(100)  NOT NULL,
    email         VARCHAR(100)  NOT NULL,
    hobbies       VARCHAR(255),
    introduction  VARCHAR(500)
);

# Sample data for testing
INSERT INTO Profile VALUES ('2026100001', 'Muhammad Farhan Rayyan Razman', 'CDCS230 - Bachelor of Computer Science (Hons.)', 'rayyan@student.uitm.edu.my', 'Football, Gaming', 'I am a Computer Science student who likes solving problems and playing football with my friends.');
INSERT INTO Profile VALUES ('2026100002', 'Nur Farzana Rahayu Ramli', 'CDCS240 - Bachelor of Information Technology (Hons.)', 'farzana@student.uitm.edu.my', 'Hiking, Baking', 'I enjoy designing websites and learning new programming languages. In my free time I like to hiking and bake cakes.');

