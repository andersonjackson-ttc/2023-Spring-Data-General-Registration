SELECT * FROM cpt275_db.tbl_student_transcript
where student_id = 30
ORDER BY CASE WHEN term_id = '2023 Spring Full' THEN 1
	WHEN term_id = '2023 Spring 1' THEN 2
    WHEN term_id = '2023 Spring 2' THEN 3
    WHEN term_id = '2023 Summer Full' THEN 4
    WHEN term_id = '2023 Summer 1' THEN 5
    WHEN term_id = '2023 Summer 2' THEN 6
    WHEN term_id = '2023 Fall Full' THEN 7
    WHEN term_id = '2023 Fall 1' THEN 8
    WHEN term_id = '2023 Fall 2' THEN 9
    END;