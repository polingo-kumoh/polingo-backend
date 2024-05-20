DELIMITER
//

CREATE PROCEDURE reset_auto_increment()
BEGIN
    DECLARE
tableName VARCHAR(64);
    DECLARE
done INT DEFAULT 0;
    DECLARE
cur CURSOR FOR
SELECT table_name
FROM information_schema.tables
WHERE table_schema = DATABASE()
  AND table_name IN ('detailed_situation'); -- 여기에 다른 테이블 이름도 추가할 수 있습니다.

DECLARE
CONTINUE HANDLER FOR NOT FOUND SET done = 1;

OPEN cur;

read_loop
: LOOP
        FETCH cur INTO tableName;
        IF
done THEN
            LEAVE read_loop;
END IF;

        SET
@sql = CONCAT('ALTER TABLE ', tableName, ' AUTO_INCREMENT = 1');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END LOOP;

CLOSE cur;
END
//

DELIMITER ;

CALL reset_auto_increment();

