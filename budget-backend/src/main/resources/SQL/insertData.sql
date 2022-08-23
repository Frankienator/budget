-- Insert Test Data into Database

DELETE * FROM entry WHERE id < 0;

INSERT INTO entry (id, description, amount, created, type)
VALUES  (-1, "test1", 10.2, "2022-01-15", "freizeit"),
        (-2, "test2", -5.3, "2021-12-31", "sparen");