delete from VIDEO;
delete from CONTENT;
 
INSERT INTO CONTENT VALUES (1, 'Content', 4); 
 
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (1,120,0,'Birinci Video', 'red', 'goal', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (2,150,120,'�kinci Video', 'orange', 'yellowCard', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (3,180,270,'���nc� Video', 'white', 'redCard', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (4,184,450,'D�rd�nc� Video', 'green', 'goal', 1);