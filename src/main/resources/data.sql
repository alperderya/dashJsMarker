delete from VIDEO;
delete from CONTENT;
 
INSERT INTO CONTENT VALUES (1, 'Content', 4); 
 
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (1,80,0,'Goool', 'red', 'goal', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (2,100,80,'Yellow Card', 'orange', 'yellowCard', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (3,80,180,'Red Card', 'green', 'redCard', 1);
INSERT INTO VIDEO (id, duration, start_point, video_name, color, icon_name, content_id) VALUES (4,108,260,'Player Change', 'blue', 'change', 1);