/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : ORACLE.ESTONE
Source Server Version : 110200
Source Host           : localhost:1521
Source Schema         : ESTONE

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2016-11-07 16:39:50
*/


-- ----------------------------
-- Table structure for ORDERITEMS
-- ----------------------------
DROP TABLE "ESTONE"."ORDERITEMS";
CREATE TABLE "ESTONE"."ORDERITEMS" (
"ORDER_ID" NUMBER NOT NULL ,
"PRODUCT_ID" NUMBER NOT NULL ,
"BUYNUM" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Table structure for ORDRES
-- ----------------------------
DROP TABLE "ESTONE"."ORDRES";
CREATE TABLE "ESTONE"."ORDRES" (
"ID" NUMBER NOT NULL ,
"MONEY" BINARY_DOUBLE NULL ,
"RECEIVERINFO" VARCHAR2(255 BYTE) NULL ,
"PAYSTATE" NUMBER NULL ,
"ORDERTIME" DATE NULL ,
"USER_ID" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Table structure for PRODUCTS
-- ----------------------------
DROP TABLE "ESTONE"."PRODUCTS";
CREATE TABLE "ESTONE"."PRODUCTS" (
"ID" NUMBER NOT NULL ,
"NAME" VARCHAR2(40 BYTE) NULL ,
"PRICE" BINARY_DOUBLE NULL ,
"CATEGORY" VARCHAR2(40 BYTE) NULL ,
"PNUM" NUMBER NULL ,
"IMGURL" VARCHAR2(100 BYTE) NULL ,
"DESCRIPTION" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Table structure for USERS
-- ----------------------------
DROP TABLE "ESTONE"."USERS";
CREATE TABLE "ESTONE"."USERS" (
"ID" NUMBER NOT NULL ,
"USERNAME" VARCHAR2(40 BYTE) NULL ,
"PASSWORD" VARCHAR2(40 BYTE) NULL ,
"NICKNAME" VARCHAR2(40 BYTE) NULL ,
"EMAIL" VARCHAR2(100 BYTE) NULL ,
"STATE" NUMBER NULL ,
"ACTIVECODE" VARCHAR2(100 BYTE) NULL ,
"UPDATEDATE" DATE NULL ,
"ROLE" VARCHAR2(25 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Sequence structure for SEQ_ORDER
-- ----------------------------
DROP SEQUENCE "ESTONE"."SEQ_ORDER";
CREATE SEQUENCE "ESTONE"."SEQ_ORDER"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_PRODUCTS
-- ----------------------------
DROP SEQUENCE "ESTONE"."SEQ_PRODUCTS";
CREATE SEQUENCE "ESTONE"."SEQ_PRODUCTS"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 41
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_USER
-- ----------------------------
DROP SEQUENCE "ESTONE"."SEQ_USER";
CREATE SEQUENCE "ESTONE"."SEQ_USER"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 21
 CACHE 20;

-- ----------------------------
-- Indexes structure for table ORDERITEMS
-- ----------------------------

-- ----------------------------
-- Checks structure for table ORDERITEMS
-- ----------------------------
ALTER TABLE "ESTONE"."ORDERITEMS" ADD CHECK ("ORDER_ID" IS NOT NULL);
ALTER TABLE "ESTONE"."ORDERITEMS" ADD CHECK ("PRODUCT_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ORDERITEMS
-- ----------------------------
ALTER TABLE "ESTONE"."ORDERITEMS" ADD PRIMARY KEY ("ORDER_ID", "PRODUCT_ID");

-- ----------------------------
-- Indexes structure for table ORDRES
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table ORDRES
-- ----------------------------
ALTER TABLE "ESTONE"."ORDRES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table PRODUCTS
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table PRODUCTS
-- ----------------------------
ALTER TABLE "ESTONE"."PRODUCTS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table USERS
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table USERS
-- ----------------------------
ALTER TABLE "ESTONE"."USERS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Foreign Key structure for table "ESTONE"."ORDERITEMS"
-- ----------------------------
ALTER TABLE "ESTONE"."ORDERITEMS" ADD FOREIGN KEY ("ORDER_ID") REFERENCES "ESTONE"."ORDRES" ("ID");
ALTER TABLE "ESTONE"."ORDERITEMS" ADD FOREIGN KEY ("PRODUCT_ID") REFERENCES "ESTONE"."PRODUCTS" ("ID");

-- ----------------------------
-- Foreign Key structure for table "ESTONE"."ORDRES"
-- ----------------------------
ALTER TABLE "ESTONE"."ORDRES" ADD FOREIGN KEY ("USER_ID") REFERENCES "ESTONE"."USERS" ("ID");
