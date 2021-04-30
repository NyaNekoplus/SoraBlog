USE sora_blog;

select * from s_blog;
select * from s_comment;
select * from s_category;
select * from s_tag;
select * from s_user;
update s_user set user_proxy = 1 where uid>0; 
select * from s_system_config;
select * from s_file_classification;
select * from s_file;
select * from s_visit_record;

select a.*,f.url,f.js_delivr_url from (select * from s_user where enable_comment=1) a left join s_file f on a.avatar = f.uid;


update s_visit_record set ip = '32.31.30.222' where uid>36;
update s_visit_record set create_time = '2021-04-16 00:00:00' where uid<36;

        select a.*, f.url, f.jsdelivr_url from (
        (select max(update_time) update_time, level from s_blog where level>0 group by level) b
        inner join s_blog a on a.level = b.level and a.update_time = b.update_time)
        left join s_file f on a.cover_uid = f.uid;
        
select count(ip) from (select ip from s_visit_record where create_time > '2021-04-15 00:00:00' and create_time < 	 group by ip) t;

select ip from s_visit_record group by ip having count(ip)>5;
select count(ip) IP from (select distinct ip from s_visit_record) t;
select count(ip) from (select ip from s_visit_record where create_time>'2021-04-15 00:00:00' and create_time<'2021-04-19 00:00:00' group by ip) t;
SELECT DISTINCT DATE_FORMAT(create_time, '%Y-%m-%d') DATE, ip FROM s_visit_record WHERE create_time >= '2021-04-15' AND create_time <= '2021-04-19' GROUP BY DATE_FORMAT(create_time, '%Y-%m-%d'),ip;
SELECT DATE, COUNT(ip) COUNT FROM 
(SELECT DISTINCT DATE_FORMAT(create_time, '%Y-%m-%d') DATE, ip FROM s_visit_record WHERE create_time >= '2021-04-16' AND create_time <= '2021-04-19' GROUP BY DATE_FORMAT(create_time, '%Y-%m-%d'),ip) AS tmp 
GROUP BY DATE;

select a.*, f.url from ((select max(update_time) update_time, level from s_blog where level>0 group by level) b inner join s_blog a on a.level = b.level and a.update_time = b.update_time) left join s_file f on a.cover_uid = f.uid;
select * from s_blog a left join (select max(update_time) update_time, level from s_blog where level>0 group by level) b on a.level = b.level and a.update_time = b.update_time; 


select a.uid,max(a.update_time), a.level from s_blog a where a.level>0 group by a.level;

select a.*,f.url from (select * from s_blog where is_draft=0) a left join s_file f on a.cover_uid = f.uid;


select a.*,c.name from s_category c left join (select b.*,f.url from s_blog b left join s_file f on b.cover_uid = f.uid) a on c.uid = a.category_uid;


select a.*,c.name from s_category c inner join 
(select b.*,f.url from 
(select * from s_blog d where d.is_draft = 0 and d.category_uid = 1) b 
left join s_file f on b.cover_uid = f.uid) a 
on c.uid = a.category_uid order by a.create_time desc;
