set names utf8;
set foreign_key_checks = 0;
drop database if exists ecsite2;

create database if not exists ecsite2;
use ecsite2;

drop table if exists user_info;

create table user_info (
id int not null primary key auto_increment,
login_id varchar(16) unique,
login_pass varchar(16),
user_name varchar(50),
insert_date datetime,
updated_date datetime,
admin_flg varchar(1) default '0'
);

drop table if exists item_info;

create table item_info (
id int not null primary key auto_increment,
item_name varchar(30) not null,
category_id int not null,
item_price int not null,
item_stock int not null,
image_path varchar(100) not null default './images',
image_name varchar(50) not null default 'sample.jpg',
item_detail varchar(300),
item_distributor varchar(50) not null,
insert_date datetime,
updated_date datetime,
delete_date datetime,
index(category_id),
foreign key (category_id) references m_category(category_id)
);

drop table if exists user_buy_item_transaction;

create table user_buy_item_transaction(
id int not null primary key auto_increment,
item_transaction_id int,
total_price int,
total_count int,
user_master_id varchar(16),
pay varchar(30),
insert_date datetime,
delete_date datetime
);

drop table if exists cart;

create table cart (
id int not null primary key auto_increment,
item_id int,
total_count int,
user_master_id varchar(16),
insert_date datetime,
update_date datetime
);

drop table if exists destination_info;

create table destination_info (
id int not null primary key auto_increment,
user_id varchar(16) not null,
family_name varchar(32) not null,
first_name varchar(32) not null,
phone_number varchar(13) not null,
address varchar(60) not null,
regist_date datetime not null,
update_date datetime,
foreign key (user_id) references user_info(login_id)
);

drop table if exists m_category;

create table m_category (
id int not null primary key auto_increment,
category_id int not null unique,
category_name varchar(20) not null unique,
category_description varchar(100),
regist_date datetime not null,
update_date datetime,
index(category_id)
);

INSERT INTO user_info(login_id, login_pass, user_name, admin_flg, insert_date) VALUES
("admin", "12345678", "adminUser", "1", now()),
("guest", "abcd1234", "guestUser", "0", now());
INSERT INTO m_category(category_id, category_name, regist_date) VALUES
(1, "全てのカテゴリー", now()),
(2, "本", now()),
(3, "家電・パソコン", now()),
(4, "おもちゃ・ゲーム", now()),
(5, "ＣＤ", now());
INSERT INTO item_info(item_name, category_id, item_price, item_stock, image_path, image_name, item_detail, item_distributor, insert_date) VALUES
("Java入門", 2, 1600, 20, "./images", "sample.jpg", "ベストセラー！Javaの入門書です。", "株式会社A書店", now()),
("坊ちゃん", 2, 500, 30, "./images", "sample.jpg", "主人公は東京の物理学校（現在の東京理科大学の前身）を卒業したばかりの江戸っ子気質で血気盛んで無鉄砲な新任教師。登場する人物の描写が滑稽で、わんぱく坊主のいたずらあり、悪口雑言あり、暴力沙汰あり、痴情のもつれあり、義理人情ありと、他の漱石作品と比べて大衆的であり、漱石の小説の中で最も多くの人に愛読されている作品である。", "株式会社B社", now()),
("ノートパソコン", 3, 120000, 50, "./images", "sample.jpg", null, "株式会社F", now()),
("キーボード", 3, 1200, 10, "./images", "sample.jpg", null, "C株式会社", now()),
("くるみ割り人形", 4, 800, 10, "./images", "sample.jpg", null, "株式会社D", now()),
("人生ゲーム", 4, 1200, 20, "./images", "sample.jpg", null, "E株式会社", now()),
("ベートーヴェン", 5, 1000, 15, "./images", "sample.jpg", "ベートーヴェンの名曲50曲を収録。", "株式会社G", now()),
("くるみ割り人形", 5, 1200, 20, "./images", "sample.jpg", null, "株式会社Hミュージック", now());