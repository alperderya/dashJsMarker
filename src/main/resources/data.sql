delete from VIEW_STATS;
delete from VIDEO;
delete from CONTENT;
 
INSERT INTO CONTENT VALUES (1, 'Content', 4); 
 
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (1,120,0,'Birinci Video', 'red', 'goal', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (2,150,120,'Ýkinci Video', 'orange', 'yellowCard', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (3,180,270,'Üçüncü Video', 'white', 'redCard', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (4,184,450,'Dördüncü Video', 'green', 'goal', 1);

--INSERT INTO VIEW_STATS VALUES (1, null, 1, 1, 'initial');
--INSERT INTO VIEW_STATS VALUES (2, null, 1, 2, 'initial');
--INSERT INTO VIEW_STATS VALUES (3, null, 1, 3, 'initial');
--INSERT INTO VIEW_STATS VALUES (4, null, 1, 4, 'initial');