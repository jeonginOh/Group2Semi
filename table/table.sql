drop sequence memberinfo_memid;
drop sequence loginauth_seq;
drop sequence daily_seq;
create sequence memberinfo_memid;
create sequence loginauth_seq;
create sequence daily_seq;

drop sequence bas_seq;
drop sequence buyid_seq;
drop sequence logiid_seq;
create sequence bas_seq;
create sequence buyid_seq;
create sequence logiid_seq;

drop SEQUENCE ans_seq
drop SEQUENCE ask_seq
drop SEQUENCE rev_child_seq
drop SEQUENCE revieid_seq
CREATE SEQUENCE ans_seq
CREATE SEQUENCE ask_seq
CREATE SEQUENCE rev_child_seq
CREATE SEQUENCE revieid_seq



drop table itemreview;
drop table rev_child;
drop table memberinfo;
drop table logistic;
drop table iteminfo;
drop table itemask;
drop table discount;
drop table coupon;
drop table category;
drop table buylist;
drop table basket;
drop table anstable;
drop table asktable;
drop table loginauth;
drop table itemreview;
drop table rev_child;
drop table memberinfo;
drop table logistic;
drop table iteminfo;
drop table itemask;
drop table discount;
drop table coupon;
drop table category;
drop table buylist;
drop table basket;
drop table anstable;
drop table asktable;
drop table loginauth;
drop table itemreview;
drop table rev_child;
drop table memberinfo;
drop table logistic;
drop table iteminfo;
drop table itemask;
drop table discount;
drop table coupon;
drop table category;
drop table buylist;
drop table basket;
drop table anstable;
drop table asktable;
drop table loginauth;


        

        
CREATE TABLE anstable
(
  ansid   number(10)     NOT NULL,
  askid   number(10)    ,
  context varchar2(2000),
  image   varchar2(200) ,
  ansdate date          ,
  CONSTRAINT PK_anstable PRIMARY KEY (ansid)
);

COMMENT ON TABLE anstable IS '문의게시판_답';

COMMENT ON COLUMN anstable.ansid IS '답변번호';

COMMENT ON COLUMN anstable.askid IS '문의번호';

COMMENT ON COLUMN anstable.context IS '내용';

COMMENT ON COLUMN anstable.image IS '이미지';

COMMENT ON COLUMN anstable.ansdate IS '작성일 ';

CREATE TABLE asktable
(
  askid   number(10)     NOT NULL,
  memid   number(10)    ,
  askcat  number(2)     ,
  title   varchar2(50)  ,
  context varchar2(2000),
  askdate date          ,
  image   varchar2(200) ,
  CONSTRAINT PK_asktable PRIMARY KEY (askid)
);

COMMENT ON TABLE asktable IS '문의게시판';

COMMENT ON COLUMN asktable.askid IS '문의번호';

COMMENT ON COLUMN asktable.memid IS '사용자번호';

COMMENT ON COLUMN asktable.askcat IS '문의카테고리';

COMMENT ON COLUMN asktable.title IS '제목';

COMMENT ON COLUMN asktable.context IS '내용';

COMMENT ON COLUMN asktable.askdate IS '작성일 ';

COMMENT ON COLUMN asktable.image IS '이미지';

CREATE TABLE basket
(
  basid  number(10) NOT NULL,
  memid  number(10) NOT NULL,
  itemid number(10) NOT NULL,
  count  number(10),
  CONSTRAINT PK_basket PRIMARY KEY (basid, memid, itemid)
);

COMMENT ON TABLE basket IS '장바구니n찜';

COMMENT ON COLUMN basket.basid IS '바구니번호';

COMMENT ON COLUMN basket.memid IS '사용자번호';

COMMENT ON COLUMN basket.itemid IS '물품번호';

COMMENT ON COLUMN basket.count IS '수량(0=찜)';

CREATE TABLE buylist
(
  buyid   number(10) NOT NULL,
  memid   number(10) NOT NULL,
  itemid  number(10) NOT NULL,
  count   number(10) NOT NULL,
  status  number(1)  DEFAULT 0,
  buydate date       DEFAULT sysdate,
  CONSTRAINT PK_buylist PRIMARY KEY (buyid, memid, itemid)
);

COMMENT ON TABLE buylist IS '구매한 목록';

COMMENT ON COLUMN buylist.buyid IS '구매번호';

COMMENT ON COLUMN buylist.memid IS '사용자번호';

COMMENT ON COLUMN buylist.itemid IS '물품번호';

COMMENT ON COLUMN buylist.count IS '수량';

COMMENT ON COLUMN buylist.status IS '상태';

COMMENT ON COLUMN buylist.buydate IS '구매일';



CREATE TABLE category
(
  catid   number(10)   NOT NULL,
  catname varchar2(50),
  CONSTRAINT PK_category PRIMARY KEY (catid)
);

COMMENT ON TABLE category IS '카테고리';

COMMENT ON COLUMN category.catid IS '카테고리';

COMMENT ON COLUMN category.catname IS '카테고리이름';

CREATE TABLE coupon
(
  coupid  number(10)     NOT NULL,
  expire  date          ,
  memid   number(10)    ,
  context varchar2(2000),
  avail   number(1)     ,
  CONSTRAINT PK_coupon PRIMARY KEY (coupid)
);

COMMENT ON COLUMN coupon.coupid IS '쿠폰번호 ';

COMMENT ON COLUMN coupon.expire IS '기간';

COMMENT ON COLUMN coupon.memid IS '사용자번호';

COMMENT ON COLUMN coupon.context IS '내용';

COMMENT ON COLUMN coupon.avail IS '사용여부';

CREATE TABLE discount
(
  disid     number(10) NOT NULL,
  dis_per   NUMBER(2) ,
  dis_price number(10),
  itemid    number(10),
  expire    date      ,
  CONSTRAINT PK_discount PRIMARY KEY (disid)
);

COMMENT ON TABLE discount IS '할인';

COMMENT ON COLUMN discount.disid IS '할인번호';

COMMENT ON COLUMN discount.dis_per IS '할인%';

COMMENT ON COLUMN discount.dis_price IS '할인금액';

COMMENT ON COLUMN discount.itemid IS '물품번호';

COMMENT ON COLUMN discount.expire IS '할인만료기간';

CREATE TABLE itemask
(
  iaskid     number(10)     NOT NULL,
  itemid     number(10)    ,
  memid      number(10)    ,
  context    varchar2(2000),
  iaskdate   date          ,
  anscontext varchar2(2000),
  ansdate    date          ,
  CONSTRAINT PK_itemask PRIMARY KEY (iaskid)
);

COMMENT ON TABLE itemask IS '물품문의';

COMMENT ON COLUMN itemask.iaskid IS '문의번호';

COMMENT ON COLUMN itemask.itemid IS '물품번호';

COMMENT ON COLUMN itemask.memid IS '사용자번호';

COMMENT ON COLUMN itemask.context IS '내용';

COMMENT ON COLUMN itemask.iaskdate IS '작성일 ';

COMMENT ON COLUMN itemask.anscontext IS '답변';

COMMENT ON COLUMN itemask.ansdate IS '답변일';

CREATE TABLE iteminfo
(
  itemid    number(10)    NOT NULL,
  itemname  varchar2(50)  NOT NULL,
  catid     number(10)   ,
  price     number(10)   ,
  factory   varchar2(50) ,
  origin    varchar2(50) ,
  stock     number(10)    DEFAULT 0 NOT NULL,
  expire    date          NOT NULL,
  storedate date          DEFAULT sysdate NOT NULL,
  image     varchar2(200),
  avail     number(1)    ,
  CONSTRAINT PK_iteminfo PRIMARY KEY (itemid)
);

COMMENT ON TABLE iteminfo IS '물품정보';

COMMENT ON COLUMN iteminfo.itemid IS '물품번호';

COMMENT ON COLUMN iteminfo.itemname IS '물품이름';

COMMENT ON COLUMN iteminfo.catid IS '카테고리';

COMMENT ON COLUMN iteminfo.price IS '물품가격';

COMMENT ON COLUMN iteminfo.factory IS '제조사';

COMMENT ON COLUMN iteminfo.origin IS '원산지';

COMMENT ON COLUMN iteminfo.stock IS '재고수량';

COMMENT ON COLUMN iteminfo.expire IS '유통기한';

COMMENT ON COLUMN iteminfo.storedate IS '입고일';

COMMENT ON COLUMN iteminfo.image IS '이미지';

COMMENT ON COLUMN iteminfo.avail IS '판매가능여부';

CREATE TABLE itemreview
(
  revid        number(10)     NOT NULL,
  itemid       number(10)    ,
  memid        number(10)    ,
  title        varchar2(50)  ,
  review_image varchar2(200) ,
  context      varchar2(2000),
  star         number(1)     ,
  revdate      date          ,
  CONSTRAINT PK_itemreview PRIMARY KEY (revid)
);

COMMENT ON TABLE itemreview IS '리뷰';

COMMENT ON COLUMN itemreview.revid IS '리뷰번호';

COMMENT ON COLUMN itemreview.itemid IS '물품번호';

COMMENT ON COLUMN itemreview.memid IS '사용자번호';

COMMENT ON COLUMN itemreview.title IS '제목';

COMMENT ON COLUMN itemreview.review_image IS '이미지';

COMMENT ON COLUMN itemreview.context IS '내용';

COMMENT ON COLUMN itemreview.star IS '별점';

COMMENT ON COLUMN itemreview.revdate IS '작성일 ';

CREATE TABLE loginauth
(
  id         NUMBER(10)     NOT NULL,
  token      varchar2(36)   NOT NULL,
  memid      number(10)     NOT NULL,
  identifier varchar2(1000) NOT NULL,
  per        number(1)      DEFAULT 0,
  created    date           DEFAULT sysdate,
  CONSTRAINT PK_loginauth PRIMARY KEY (id, memid)
);

COMMENT ON TABLE loginauth IS '자동로그인 관련 테이블';

COMMENT ON COLUMN loginauth.id IS '인덱스용';

COMMENT ON COLUMN loginauth.token IS 'UUID';

COMMENT ON COLUMN loginauth.memid IS '사용자번호';

COMMENT ON COLUMN loginauth.identifier IS '식별자';

COMMENT ON COLUMN loginauth.per IS '권한';

COMMENT ON COLUMN loginauth.created IS '생성일';

CREATE TABLE logistic
(
  logiid   number(10)     NOT NULL,
  memid    number(10)     NOT NULL,
  buyid    number(10)     NOT NULL,
  itemid   number(10)     NOT NULL,
  addr     varchar2(2000),
  logiinfo varchar2(200) ,
  CONSTRAINT PK_logistic PRIMARY KEY (logiid, memid, buyid, itemid)
);

COMMENT ON TABLE logistic IS '배송정보';

COMMENT ON COLUMN logistic.logiid IS '배송번호';

COMMENT ON COLUMN logistic.memid IS '사용자번호';

COMMENT ON COLUMN logistic.buyid IS '구매번호';

COMMENT ON COLUMN logistic.itemid IS '물품번호';

COMMENT ON COLUMN logistic.addr IS '배송주소';

COMMENT ON COLUMN logistic.logiinfo IS '배송정보';

CREATE TABLE memberinfo
(
  memid   number(10)     NOT NULL,
  id      VARCHAR2(15)  ,
  pwd     varchar2(100) ,
  salt    varchar2(100) ,
  age     varchar2(7)   ,
  email   varchar2(100) ,
  addr    varchar2(2000),
  regdate Date           DEFAULT sysdate,
  phone   varchar2(12)  ,
  point   number(10)     DEFAULT 0,
  status  number(1)      DEFAULT 1,
  CONSTRAINT PK_memberinfo PRIMARY KEY (memid)
);

ALTER TABLE memberinfo
  ADD CONSTRAINT UQ_id UNIQUE (id);

ALTER TABLE memberinfo
  ADD CONSTRAINT UQ_email UNIQUE (email);

ALTER TABLE memberinfo
  ADD CONSTRAINT UQ_phone UNIQUE (phone);

COMMENT ON TABLE memberinfo IS '회원정보';

COMMENT ON COLUMN memberinfo.memid IS '사용자번호';

COMMENT ON COLUMN memberinfo.id IS 'unique';

COMMENT ON COLUMN memberinfo.pwd IS 'memberpwd';

COMMENT ON COLUMN memberinfo.age IS '나이';

COMMENT ON COLUMN memberinfo.email IS '이메일';

COMMENT ON COLUMN memberinfo.addr IS '주소';

COMMENT ON COLUMN memberinfo.regdate IS '가입일';

COMMENT ON COLUMN memberinfo.phone IS '전화번호';

COMMENT ON COLUMN memberinfo.point IS '포인트';

COMMENT ON COLUMN memberinfo.status IS '사용자상태정보';

CREATE TABLE rev_child
(
  rchildid      number(10)     NOT NULL,
  revid         number(10)    ,
  context_child varchar2(2000),
  rchilddate    date          ,
  CONSTRAINT PK_rev_child PRIMARY KEY (rchildid)
);

COMMENT ON TABLE rev_child IS '리뷰답변';

COMMENT ON COLUMN rev_child.rchildid IS '리뷰리플번호';

COMMENT ON COLUMN rev_child.revid IS '리뷰번호';

COMMENT ON COLUMN rev_child.context_child IS '내용';

COMMENT ON COLUMN rev_child.rchilddate IS '작성일 ';

ALTER TABLE buylist
  ADD CONSTRAINT FK_memberinfo_TO_buylist
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);


ALTER TABLE buylist
  ADD CONSTRAINT FK_iteminfo_TO_buylist
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE basket
  ADD CONSTRAINT FK_memberinfo_TO_basket
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE basket
  ADD CONSTRAINT FK_iteminfo_TO_basket
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE discount
  ADD CONSTRAINT FK_iteminfo_TO_discount
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE iteminfo
  ADD CONSTRAINT FK_category_TO_iteminfo
    FOREIGN KEY (catid)
    REFERENCES category (catid);

ALTER TABLE itemreview
  ADD CONSTRAINT FK_iteminfo_TO_itemreview
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE itemreview
  ADD CONSTRAINT FK_memberinfo_TO_itemreview
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE rev_child
  ADD CONSTRAINT FK_itemreview_TO_rev_child
    FOREIGN KEY (revid)
    REFERENCES itemreview (revid);

ALTER TABLE itemask
  ADD CONSTRAINT FK_iteminfo_TO_itemask
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE itemask
  ADD CONSTRAINT FK_memberinfo_TO_itemask
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE anstable
  ADD CONSTRAINT FK_asktable_TO_anstable
    FOREIGN KEY (askid)
    REFERENCES asktable (askid);

ALTER TABLE coupon
  ADD CONSTRAINT FK_memberinfo_TO_coupon
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE logistic
  ADD CONSTRAINT FK_buylist_TO_logistic
    FOREIGN KEY (buyid, memid, itemid)
    REFERENCES buylist (buyid, memid, itemid);

ALTER TABLE loginauth
  ADD CONSTRAINT FK_memberinfo_TO_loginauth
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE asktable
  ADD CONSTRAINT FK_memberinfo_TO_asktable
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);
insert into category values(111000000,'콩나물/버섯류');
insert into category values(111000100,'콩나물/버섯류');
insert into category values(111000200,'시금치/부추/나물');
insert into category values(111000300,'양파/마늘/생강/파');
insert into category values(111000400,'기본 채소');
insert into category values(111000500,'간편 채소');

insert into category values(222000000,'과일');
insert into category values(222000100,'제철 과일');
insert into category values(222000200,'국산');
insert into category values(222000300,'수입');
insert into category values(222000400,'냉동');
insert into category values(222000500,'견과류');

insert into category values(333000000,'수산');
insert into category values(333000100,'생선');
insert into category values(333000200,'갑각류');
insert into category values(333000300,'어패류');
insert into category values(333000400,'가공품');
insert into category values(333000500,'기타');

insert into category values(444000000,'정육');
insert into category values(444000100,'소');
insert into category values(444000200,'돼지');
insert into category values(444000300,'조류');
insert into category values(444000400,'양념');
insert into category values(444000500,'계란류');

insert into category values(555000000,'완제품');
insert into category values(555000100,'샐러드/도시락');
insert into category values(555000200,'간편식/냉동');
insert into category values(555000300,'밥/면/즉석식품');
insert into category values(555000400,'만두/튀김');
insert into category values(555000500,'선식/씨리얼');

insert into category values(666000000,'음료');
insert into category values(666000100,'생수/음료/쥬스');
insert into category values(666000200,'커피/차');
insert into category values(666000300,'유제품');
insert into category values(666000400,'초콜릿/젤리/캔디');
insert into category values(666000500,'간식');

INSERT INTO ITEMINFO VALUES(5,'석류',222000300,4000,'캘리포니아','미국',400,SYSDATE+90,SYSDATE,'pomegranate.PNG',1);
INSERT INTO ITEMINFO VALUES(6,'자몽',222000300,1400,'애리조나','미국',320,SYSDATE+80,SYSDATE,'grapefruit.PNG',1);
INSERT INTO ITEMINFO VALUES(7,'냉동바나나',222000400,6500,'민다나오','필리핀',333,SYSDATE+60,SYSDATE,'freezebanana.PNG',1);
INSERT INTO ITEMINFO VALUES(8,'냉동아보카도',222000400,9800,'리마','페루',333,SYSDATE+76,SYSDATE,'freezeavocado.PNG',1);
INSERT INTO ITEMINFO VALUES(9,'아몬드',222000500,5900,'캘리포니아','미국',590,SYSDATE+150,SYSDATE,'almonds.PNG',1);
INSERT INTO ITEMINFO VALUES(10,'하루견과',222000500,19500,'남양주','한국',1030,SYSDATE+150,SYSDATE,'dailynut.PNG',1);
INSERT INTO ITEMINFO VALUES(11,'생연어',333000100,14000,'마린하베스트','노르웨이',360,SYSDATE+45,SYSDATE,'rowsalmon.PNG',1);
INSERT INTO ITEMINFO VALUES(12,'삼치',333000100,8900,'여수','한국',600,SYSDATE+45,SYSDATE,'cero.PNG',1);
INSERT INTO ITEMINFO VALUES(13,'손질새우',333000200,10500,'짱왓','태국',400,SYSDATE+270,SYSDATE,'shrimp.PNG',0);
INSERT INTO ITEMINFO VALUES(14,'랍스터치즈구이',333000200,19800,'노바스코샤','캐나다',190,SYSDATE+200,SYSDATE-80,'lobster.PNG',1);
INSERT INTO ITEMINFO VALUES(15,'전복',333000300,11500,'완도','한국',470,SYSDATE+40,SYSDATE-30,'abalone.PNG',1);
INSERT INTO ITEMINFO VALUES(16,'슬라이스문어',333000300,13300,'거제','한국',500,SYSDATE+70,SYSDATE,'octopus.PNG',1);
INSERT INTO ITEMINFO VALUES(17,'낙지볶음',333000400,6800,'하이즈엉','베트남',890,SYSDATE+320,SYSDATE-70,'smalloctopus.PNG',1);
INSERT INTO ITEMINFO VALUES(18,'명란젓',333000400,29500,'블라디보스토크','러시아',400,SYSDATE+250,SYSDATE,'pollackroe.PNG',1);
INSERT INTO ITEMINFO VALUES(19,'광천파래김',333000500,4250,'홍성','한국',1300,SYSDATE+150,SYSDATE,'laver.PNG',1);
INSERT INTO ITEMINFO VALUES(20,'미역',333000500,8900,'부산','한국',940,SYSDATE+170,SYSDATE-34,'seaweed.PNG',1);

insert into iteminfo values(50,'삼겹살',444000200,15000,'한돈','한국',150,sysdate+60,sysdate,'sam.png',1);
insert into iteminfo values(51,'목살',444000200,13000,'한돈','한국',180,sysdate+50,sysdate,'mok.png',1);
insert into iteminfo values(52,'꽃등심',444000100,25000,'횡성','한국',100,sysdate+30,sysdate,'flower.png',1);
insert into iteminfo values(53,'안심',444000100,23000,'횡성','한국',110,sysdate+30,sysdate,'rest.png',1);
insert into iteminfo values(54,'생닭',444000300,8000,'하림','한국',200,sysdate+30,sysdate,'dak.png',1);
insert into iteminfo values(55,'오리',444000300,12000,'하림','한국',110,sysdate+30,sysdate,'ori.png',1);
insert into iteminfo values(56,'제육',444000400,16000,'더본','한국',80,sysdate+15,sysdate,'je.png',1);
insert into iteminfo values(57,'불고기',444000400,20000,'더본','한국',110,sysdate+15,sysdate,'bull.png',1);
insert into iteminfo values(58,'계란6구',444000500,3000,'양','한국',60,sysdate+20,sysdate,'smallegg.png',1);
insert into iteminfo values(59,'계란30구',444000500,5000,'양','한국',110,sysdate+20,sysdate,'bigegg.png',1);
insert into iteminfo values(60,'닭가슴살샐러드',444000500,8000,'오빠닭','한국',110,sysdate+7,sysdate,'dakga.png',1);
insert into iteminfo values(61,'모듬샐러드',555000100,9000,'샐러드코리아','한국',110,sysdate+7,sysdate,'allsal.png',1);
insert into iteminfo values(62,'백도시락',555000200,5000,'더본','한국',110,sysdate+7,sysdate,'backdo.png',1);
insert into iteminfo values(63,'옛날도시락',555000100,3000,'도시락킹','한국',110,sysdate+7,sysdate,'olddo.png',1);
