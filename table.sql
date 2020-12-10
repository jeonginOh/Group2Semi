
        
CREATE TABLE anstable
(
  ansid   number(10)     NOT NULL,
  askid   number(10)    ,
  memid   number(10)    ,
  context varchar2(2000),
  image   varchar2(200) ,
  ansdate date          ,
  CONSTRAINT PK_anstable PRIMARY KEY (ansid)
);

COMMENT ON TABLE anstable IS '문의게시판_답';

COMMENT ON COLUMN anstable.ansid IS '답변번호';

COMMENT ON COLUMN anstable.askid IS '문의번호';

COMMENT ON COLUMN anstable.memid IS '사용자번호';

COMMENT ON COLUMN anstable.context IS '내용';

COMMENT ON COLUMN anstable.image IS '이미지';

COMMENT ON COLUMN anstable.ansdate IS '작성일 ';

CREATE TABLE asktable
(
  askid   number(10)     NOT NULL,
  askcat  number(2)     ,
  title   varchar2(50)  ,
  context varchar2(2000),
  askdate date          ,
  image   varchar2(200) ,
  CONSTRAINT PK_asktable PRIMARY KEY (askid)
);

COMMENT ON TABLE asktable IS '문의게시판';

COMMENT ON COLUMN asktable.askid IS '문의번호';

COMMENT ON COLUMN asktable.askcat IS '문의카테고리';

COMMENT ON COLUMN asktable.title IS '제목';

COMMENT ON COLUMN asktable.context IS '내용';

COMMENT ON COLUMN asktable.askdate IS '작성일 ';

COMMENT ON COLUMN asktable.image IS '이미지';

CREATE TABLE basket
(
  basid  number(10)  NOT NULL,
  memid  number(10)  NOT NULL,
  itemid number(10)  NOT NULL,
  count  number(100),
  CONSTRAINT PK_basket PRIMARY KEY (basid, memid, itemid)
);

COMMENT ON TABLE basket IS '장바구니&찜';

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
  coupid  number(10),
  CONSTRAINT PK_buylist PRIMARY KEY (buyid, memid, itemid)
);

COMMENT ON TABLE buylist IS '구매한 목록';

COMMENT ON COLUMN buylist.buyid IS '구매번호';

COMMENT ON COLUMN buylist.memid IS '사용자번호';

COMMENT ON COLUMN buylist.itemid IS '물품번호';

COMMENT ON COLUMN buylist.count IS '수량';

COMMENT ON COLUMN buylist.status IS '상태';

COMMENT ON COLUMN buylist.buydate IS '구매일';

COMMENT ON COLUMN buylist.coupid IS '쿠폰번호 ';

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
  avail   boolean       ,
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
  avail     boolean       DEFAULT false,
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

CREATE TABLE logistic
(
  logiid   number(10)     NOT NULL,
  buyid    number(10)     NOT NULL,
  addr     varchar2(2000),
  logiinfo varchar2(200) ,
  CONSTRAINT PK_logistic PRIMARY KEY (logiid, buyid)
);

COMMENT ON TABLE logistic IS '배송정보';

COMMENT ON COLUMN logistic.logiid IS '배송번호';

COMMENT ON COLUMN logistic.buyid IS '구매번호';

COMMENT ON COLUMN logistic.addr IS '배송주소';

COMMENT ON COLUMN logistic.logiinfo IS '배송정보';

CREATE TABLE memberinfo
(
  memid   number(10)     NOT NULL,
  id      VARCHAR2(15)   NOT NULL,
  pwd     varchar2(15)   NOT NULL,
  age     number(7)     ,
  email   varchar2(100) ,
  addr    varchar2(2000) NOT NULL,
  regdate Date           DEFAULT sysdate NOT NULL,
  phone   varchar2(12)  ,
  point   number(10)     DEFAULT 0,
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

CREATE TABLE rev_child
(
  rchildid   number(10)     NOT NULL,
  revid      number(10)    ,
  context    varchar2(2000),
  rchilddate date          ,
  CONSTRAINT PK_rev_child PRIMARY KEY (rchildid)
);

COMMENT ON TABLE rev_child IS '리뷰답변';

COMMENT ON COLUMN rev_child.rchildid IS '리뷰리플번호';

COMMENT ON COLUMN rev_child.revid IS '리뷰번호';

COMMENT ON COLUMN rev_child.context IS '내용';

COMMENT ON COLUMN rev_child.rchilddate IS '작성일 ';

CREATE TABLE review
(
  revid   number(10)     NOT NULL,
  itemid  number(10)    ,
  memid   number(10)    ,
  title   varchar2(50)  ,
  image   varchar2(200) ,
  context varchar2(2000),
  star    number(1)     ,
  revdate date          ,
  CONSTRAINT PK_review PRIMARY KEY (revid)
);

COMMENT ON TABLE review IS '리뷰';

COMMENT ON COLUMN review.revid IS '리뷰번호';

COMMENT ON COLUMN review.itemid IS '물품번호';

COMMENT ON COLUMN review.memid IS '사용자번호';

COMMENT ON COLUMN review.title IS '제목';

COMMENT ON COLUMN review.image IS '이미지';

COMMENT ON COLUMN review.context IS '내용';

COMMENT ON COLUMN review.star IS '별점';

COMMENT ON COLUMN review.revdate IS '작성일 ';

ALTER TABLE buylist
  ADD CONSTRAINT FK_memberinfo_TO_buylist
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE buylist
  ADD CONSTRAINT FK_coupon_TO_buylist
    FOREIGN KEY (coupid)
    REFERENCES coupon (coupid);

ALTER TABLE buylist
  ADD CONSTRAINT FK_iteminfo_TO_buylist
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE logistic
  ADD CONSTRAINT FK_buylist_TO_logistic
    FOREIGN KEY (buyid)
    REFERENCES buylist (buyid);

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

ALTER TABLE review
  ADD CONSTRAINT FK_iteminfo_TO_review
    FOREIGN KEY (itemid)
    REFERENCES iteminfo (itemid);

ALTER TABLE review
  ADD CONSTRAINT FK_memberinfo_TO_review
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE rev_child
  ADD CONSTRAINT FK_review_TO_rev_child
    FOREIGN KEY (revid)
    REFERENCES review (revid);

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

ALTER TABLE anstable
  ADD CONSTRAINT FK_memberinfo_TO_anstable
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

ALTER TABLE coupon
  ADD CONSTRAINT FK_memberinfo_TO_coupon
    FOREIGN KEY (memid)
    REFERENCES memberinfo (memid);

      