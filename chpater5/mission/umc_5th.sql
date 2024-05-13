select mission.name, description, mi.point, mm.status, s.name
	from member as m
    join member_mission as mm on m.id = mm.member_id
    join mission as mi on mm.mission_id = mi.id
    join store as s on mi.store_id = s.id
    order by clear_time is null asc, clear_time,
    mi.created_at desc
    limit 15 offset (n-1) * 15;
    
----------------

CREATE TABLE sequences (review VARCHAR(32), currval BIGINT UNSIGNED) 
	ENGINE = InnoDB;
    
DELIMITER $$
CREATE PROCEDURE `create_sequence` (IN input_name TEXT)
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
	DELETE FROM sequences WHERE name = input_name;
	INSERT INTO sequences VALUES (input_name, 0);
END $$

DELIMITER $$
CREATE FUNCTION `nextval` (input_name VARCHAR(32))
RETURNS BIGINT UNSIGNED 
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
	DECLARE ret BIGINT UNSIGNED;
    UPDATE sequences SET currval = currval + 1 WHERE name = input_name;
    SELECT currval INTO ret FROM sequences WHERE name = input_name LIMIT 1;
    RETURN ret;
END $$

create_sequence(review)

insert into review(nextval(review), member_id, store_id, star_point,
	evaluation)
    values((select nextval('review') as id from dual), 3, 4, 5)
    
--------------------    
    
select m.name, description, point
	from mission m
    join store as s on m.store_id = s.id
    where m.id not in
		(select id from member_mission
        where member_id = 1
        and mission_id = m.id)
	and m.created_at > last_select
    and s.region = 
		(select region
		from member
        where id = $(user_id))
    order by created_at desc;
    
------------------

select name,phone_num,point
	from member
    where id = $(user_id)

select s.name, r.start_point, r.evaluation
	from member m, store s, review r
    where m.id = r.member_id
    and r.store_id = s.sid
    and r.created at > last_created_at
    order by created_at desc;

