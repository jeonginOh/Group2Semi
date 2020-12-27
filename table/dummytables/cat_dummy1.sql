-- insert into category values(111000000,'콩나물/버섯류');
-- insert into category values(111000100,'콩나물/버섯류');
-- insert into category values(111000200,'시금치/부추/나물');
-- insert into category values(111000300,'양파/마늘/생강/파');
insert into category values(111000400,'기본 채소');
insert into category values(111000500,'간편 채소');

insert into category values(222000000,'과일');
insert into category values(222000100,'제철 과일');
insert into category values(222000200,'국산');

-- itemid    number(10)    NOT NULL,
-- itemname  varchar2(50)  NOT NULL,
-- catid     number(10)   ,
-- price     number(10)   ,
-- factory   varchar2(50) ,
-- origin    varchar2(50) ,
-- stock     number(10)    DEFAULT 0 NOT NULL,
-- expire    date          NOT NULL,
-- storedate date          DEFAULT sysdate NOT NULL,
-- image     varchar2(200),
-- avail     number(1)    ,

insert into iteminfo values(1, '유기농콩나물200g', 111000100, 3000, '우리집화분', '대한민국', 200, sysdate+4, sysdate-10, 'cong1.jpg', 1);
insert into iteminfo values(2, '유기농콩나물400g', 111000100, 5000, '너희집화분', '대한민국', 100, sysdate+14, sysdate, 'cong2.jpg', 1);
insert into iteminfo values(3, '시금치1kg', 111000200, 8000, '뒷산', '대한민국', 200, sysdate+30, sysdate, 'gold.jpg', 1);
insert into iteminfo values(4, '고사리400g', 111000200, 5000, '뒷산', '대한민국', 100, sysdate+14, sysdate-14, 'gosary.jpg', 1);
insert into iteminfo values(5, '양파3개', 111000300, 2000, '', '대한민국', 100, sysdate+30, sysdate, 'onion.jpg', 1);
insert into iteminfo values(6, '깐양파 500g', 111000300, 4000, '', '대한민국', 100, sysdate+14, sysdate, 'offion.jpg', 1);
