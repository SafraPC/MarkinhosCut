USE MarkinhosCut;

DROP PROCEDURE IF EXISTS calculateTotalActiveProfessionalMarks;
   DELIMITER ||
	CREATE PROCEDURE calculateTotalActiveProfessionalMarks ()
	BEGIN
    DECLARE cpfSelected VARCHAR(11);
    DECLARE isActiveProfessional BOOLEAN;
    DECLARE total INTEGER;
    
	DECLARE done INT DEFAULT FALSE;

	DECLARE cur1 CURSOR FOR SELECT cpf FROM biggestSellerMark;

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    SET total = 0;

	OPEN cur1;
	READ_LOOP: LOOP
	FETCH cur1 INTO cpfSelected;
	IF done THEN
	LEAVE READ_LOOP;
	END IF;
	SELECT isActive INTO isActiveProfessional FROM Professional WHERE cpf = cpfSelected;
	IF isActiveProfessional THEN
    SET total = total + 1;
    END IF;
	END LOOP READ_LOOP;

    CLOSE cur1;
	SELECT total;
	END ||;
    DELIMITER ;
    
    
CALL calculateTotalActiveProfessionalMarks();