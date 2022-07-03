
CREATE TABLE `tb_purchase_form` (
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `branchno`  varchar(50) DEFAULT NULL COMMENT '机构号' ,
  `purchase_billno` varchar(50) NOT NULL COMMENT '订货单号' ,
  `purchaser_id`  int(11) NOT NULL COMMENT '订货方ID' ,
  `total_price`  DECIMAL(17, 2) NULL COMMENT '订货单花费总金额' ,
  `total_storage_price`  DECIMAL(17, 2) NULL COMMENT '订货单确认到货总金额' ,
  `status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态：0待付款1未完成2已完成3强制结束' ,
  `check_status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核1已审核' ,
  `create_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录生成时间' ,
  `update_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  `check_person_id`  int(11) UNSIGNED NOT NULL COMMENT '审核人id' ,
  `check_date`  datetime NULL COMMENT '审核时间' ,
  `check_remark`  VARCHAR(500)  NULL COMMENT '审核意见' ,
  PRIMARY KEY (`id`),
  INDEX `idx_purchase_form_purchaser_id` (`purchaser_id`) USING BTREE
) COMMENT='订货单表';


CREATE TABLE `tb_purchase_detail` (
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `purchase_form_id`  int(11)  NOT NULL  COMMENT '订货单id' ,
  `purchase_billno` varchar(50) NOT NULL COMMENT '订货单号' ,
  `product_id`  varchar(20) NOT NULL COMMENT '货品编号' ,
  `purchase_quality`  smallint(6) NOT NULL DEFAULT 0 COMMENT '订货数量' ,
  `storage_quality`  smallint(6) NOT NULL DEFAULT 0 COMMENT '已确定收到发货单数量' ,
  `on_quality`  smallint(6) NOT NULL DEFAULT 0 COMMENT '未确定发货单数量' ,
  `surplus_quantity`  smallint(6) NOT NULL DEFAULT 0 COMMENT '剩余数量' ,
  `status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '0未完成1已完成',
  `purchase_price`  decimal(17,2) NOT NULL DEFAULT 0 COMMENT '订货价格' ,
  `settle_price`  decimal(17,2)  NULL DEFAULT NULL COMMENT '清算价格' ,
  `create_date`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录生成时间' ,
  `update_date`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `idx_purchase_detail_purchase_form_id` (`purchase_form_id`) USING BTREE,
  INDEX `idx_purchase_detail_product_id` (`product_id`) USING BTREE
) COMMENT='订货单明细表';



#发货单表
CREATE TABLE `tb_deliver_form` (
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `deliver_billno` varchar(50) NOT NULL COMMENT '发货单号' ,
  `purchaser_id`  int(11) NOT NULL COMMENT '订货方ID' ,
  `supplier_id`  int(11) NOT NULL COMMENT '供应商ID' ,
  `purchase_billno` varchar(50) NOT NULL COMMENT '订货单号' ,
  `purchase_form_id`  int(11)  NOT NULL  COMMENT '订货单id' ,
  `total_price`  DECIMAL(17, 2) NOT NULL COMMENT '发货单花费总金额' ,
  `status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态：0待确认1已确认' ,
  `settle_status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '清算状态：0未清算1已清算' ,
  `deliver_person_id` int(11)  NOT NULL COMMENT '发货人' ,
  `deliver_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发货时间' ,
  `confirm_person_id` int(11)  DEFAULT NULL COMMENT '确认人' ,
  `confirm_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '确认时间' ,
  `create_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录生成时间' ,
  `update_date`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
)
  COMMENT='发货单表'
;

#发货单明细表
DROP TABLE IF EXISTS `tb_deliver_detail`;
CREATE TABLE `tb_deliver_detail` (
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `product_no`  varchar(20) NOT NULL COMMENT '货品编号' ,
  `deliver_billno` varchar(50) NOT NULL COMMENT '发货单号' ,
  `deliver_form_id`  int(11)  NOT NULL  COMMENT '订货单id' ,
  `deliver_quality`  smallint(6) NOT NULL DEFAULT 0 COMMENT '发货数量' ,
  `storage_quality`  smallint(6) NOT NULL DEFAULT 0 COMMENT '入库数量' ,
  `purchase_price`  decimal(18,2) NOT NULL DEFAULT 0 COMMENT '采购价格' ,
  `create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
  `update_date`  datetime NOT NULL COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `idx_deliver_detail_deliver_form_id` (`deliver_form_id`) USING BTREE
)
  COMMENT='发货单明细表'
;

CREATE TABLE tb_product
(
  ID             INT AUTO_INCREMENT
    PRIMARY KEY,
  NAME           VARCHAR(50)     NULL
  COMMENT '货品名称',
  BARCODE        VARCHAR(50)     NULL
  COMMENT '条形码',
  BRAND          VARCHAR(20)     NULL
  COMMENT '品牌',
  UNIT           VARCHAR(20)     NULL
  COMMENT '单位',
  IMAGE          VARCHAR(400)    NULL
  COMMENT '商品图标',
  PRICE          DECIMAL(17, 2)  NULL
  COMMENT '供货价格',
  reference_price          DECIMAL(17, 2)  NULL
  COMMENT '参考价格',
  settle_price          DECIMAL(17, 2)  NULL
  COMMENT '清算价格',
  remark         VARCHAR(1000)   NULL
  COMMENT '产品介绍',
  model          VARCHAR(255)    NULL
  COMMENT '规格',
  product_status         tinyint(4) DEFAULT 2
  COMMENT '货品状态 1上架 2下架',
  category_id    INT DEFAULT  0
  COMMENT '货品分类id',
  product_label        VARCHAR(255)    NULL
  COMMENT '货品标签',
  `supplier_id`  int(11) UNSIGNED NOT NULL
  COMMENT '供应商ID' ,
  `create_date`  datetime NULL  COMMENT '创建时间' ,
  `update_date`  datetime NULL  COMMENT '更新时间'
) COMMENT = '货品表';

CREATE TABLE tb_category
(
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键id' ,
  `category_name`  varchar(50) NULL DEFAULT NULL COMMENT '分类名' ,
  `parent_id`  int(11)  DEFAULT 0 COMMENT '父级id' ,
  `category_level`  int(11) DEFAULT 0  COMMENT '层级 0层表示顶级' ,
  `is_end`  tinyint(4) DEFAULT NULL COMMENT '是否末级' ,
  `create_date`  datetime NULL  COMMENT '创建时间' ,
  `update_date`  datetime NULL  COMMENT '更新时间' ,
  `status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '0正常1删除' ,
  PRIMARY KEY (`id`)
) COMMENT = '货品分类表';
alter table tb_category add ctgr_class int null comment '0:自有商品,1用户新增的';
alter table tb_category add ctgr_rank int null comment '排序码';


CREATE TABLE tb_product_img
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  product_id  INT       NOT NULL
  COMMENT '货品id',
  img_path VARCHAR(255) NOT NULL
  COMMENT '图片路径',
  remark   VARCHAR(255) NULL
  COMMENT '备注',
  order_number    INT    NULL
  COMMENT '图片序号'
) COMMENT = '货品图片详情';


#权限表
create table if not exists tb_permission
(
  id int not null auto_increment
    primary key comment '权限记录编号',
  parent_id int not null comment '父编号',
  lvl int not null comment '层级',
  porder int null comment '同一层级下出现的顺序',
  available tinyint not null comment '权限是否可用，1：可用，0：不可用',
  permission varchar(255) null comment '权限标志，编码鉴权使用',
  name varchar(255) not null comment '权限名称，页面显示用',
  resource_type varchar(20) not null comment '资源类型[button,menu]',
  url varchar(255) null comment '访问链接',
  remark varchar(255) null comment '备注',
  constraint unique (permission)
) comment ='权限表';

#角色表
create table if not exists tb_role
(
  id int not null auto_increment
    primary key comment '角色记录编号',
  role varchar(255) not null comment '角色标识，程序中判断使用，如"admin',
  available tinyint not null comment '角色是否可用，1：可用，0：不可用',
  description varchar(255)  null comment '描述',
  constraint unique (role)
);

#角色-权限关联表
create table if not exists tb_role_permission
(
  permission_id int not null,
  role_id int not null,
  constraint foreign key (role_id) references tb_role (id),
  constraint foreign key (permission_id) references tb_permission (id)
) comment = '角色权限关联表，多对多';

#用户信息表
create table if not exists tb_user_info
(
  uid int auto_increment
    primary key comment '用户记录编号',
  username varchar(255) not null comment '账号',
  name varchar(255) not null comment '（显示）昵称，默认填充为username',
  password varchar(255) not null comment '密码',
  salt varchar(255) not null comment '加密盐',
  state tinyint not null comment '用户状态=》1:正常状态,0：用户被锁定',
  orgid int not null comment '所属组织机构',
  addtime timestamp not null comment '添加时间',
  operator int not null comment '操作员的uid',
  last_pwd_modifytime timestamp not null comment '最后一次密码修改的时间',
  last_login_time timestamp null comment '最后一次登录的时间',
  remark varchar(255) null comment '备注',
  column1 varchar(255) null ,
  column2 varchar(255) null ,
  column3 int null ,
  column4 int null ,
  constraint unique (username)
) comment = '用户信息';

#用户角色关联表
create table if not exists tb_user_role
(
  role_id int not null,
  uid int not null,
  constraint foreign key (uid) references tb_user_info (uid),
  constraint foreign key (role_id) references tb_role (id)
) comment = '用户角色关联表，多对多';


CREATE TABLE tb_org_info
(
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `org_name`  varchar(100) NOT NULL COMMENT '机构名称' ,
  `branchno`  varchar(50) NULL DEFAULT NULL COMMENT '机构号' ,
  `parent_branchno`  varchar(50) NULL DEFAULT NULL COMMENT '父级机构号' ,
  `address`  varchar(200) NULL DEFAULT NULL COMMENT '地址' ,
  `contact`  varchar(50) NULL DEFAULT NULL COMMENT '联系人' ,
  `telephone`  varchar(50) NULL DEFAULT NULL COMMENT '电话' ,
  `is_delete`  tinyint(1)  NULL DEFAULT 0 COMMENT '是否删除：1是0否' ,
  `create_date`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录生成时间' ,
  `update_date`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  `type`  tinyint(4) NOT NULL DEFAULT 1 COMMENT '机构类型 1供货方 2订货方' ,
  `supplier_type`  tinyint(4) NOT NULL DEFAULT 1 COMMENT '供货方分类' ,
  PRIMARY KEY (`id`)
) COMMENT = '机构表';





CREATE TABLE tb_repository
(
  ID        INT AUTO_INCREMENT
  COMMENT '序号'
    PRIMARY KEY,
  supplier_id INT(11) NOT  NULL
  COMMENT '供货方id',
  product_id    INT(11)  NOT NULL
  COMMENT '礼品序号',
  quantity     INT   NOT NULL DEFAULT 0
  COMMENT '数量',
  UNIQUE (supplier_id, product_id)
) COMMENT = '供货方剩余库存';


CREATE TABLE tb_repository_record
(
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `billno`  varchar(50) NOT NULL COMMENT '库存修改记录编号' ,
  `supplier_id`  int(11) NOT NULL COMMENT '供货方ID' ,
  `create_date`  datetime NULL  COMMENT '创建时间' ,
  `status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '0正常' ,
  PRIMARY KEY (`id`)
) COMMENT = '供货方库存修改记录表';

CREATE TABLE tb_repository_record_detail
(
  `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
  `record_billno`  varchar(50) NOT NULL COMMENT '库存修改记录编号' ,
  `record_id`  int(11) NOT NULL  COMMENT '库存修改记录id' ,
  `product_id`  int(11) NOT NULL  COMMENT '货品id' ,
  `product_quantity`  int(11) NOT NULL  COMMENT '货品数量' ,
  `create_date`  datetime NULL  COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '库存修改记录详情表';


CREATE TABLE `tb_org_balance` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`month` varchar(50) NOT NULL COMMENT 'yyyy-MM 年月份',
  `branchno` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `balance` decimal(17,2) DEFAULT '0.00' COMMENT '总余额',
  `used_banlance` decimal(17,2) DEFAULT '0.00' COMMENT '已使用额度',
	`last_used_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP() COMMENT '最后使用时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='单位额度使用表';



CREATE TABLE `tb_balance_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) NOT NULL COMMENT '用户主键ID',
  `occurdate` timestamp NOT NULL COMMENT '发生时间',
  `org_id` int(11) DEFAULT NULL COMMENT '发生单位',
  `money` decimal(17,2) NOT NULL COMMENT '使用金额，正数代表使用金额，负数代表充值',
  `balance` decimal(17,2) DEFAULT '0.00' COMMENT '余额',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='单位额度使用记录表';



CREATE TABLE `tb_user_org` (
  `user_id` int(11) NOT NULL COMMENT '用户主键ID',
  `org_id` int(11) DEFAULT NULL COMMENT '单位主键ID'
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户管理单位表';

ALTER TABLE tb_product ADD check_status tinyint(4) DEFAULT '1' COMMENT '货品状态 1未审核 2审核通过 3审核未通过';


-- 截止目前 由自助兑换模块（exchange）使用
CREATE TABLE `tb_ip_org` (
  `ip` VARCHAR (15) NOT NULL COMMENT '自助设备兑换IP地址',
  `org_id` int(11) NOT NULL COMMENT '单位主键ID',
  `branchno` VARCHAR(100) NOT NULL COMMENT '机构号',
  `org_name` VARCHAR(100) NOT NULL COMMENT '机构名称',
  `remark` VARCHAR (100) DEFAULT NULL COMMENT '备注',
  primary key(`ip`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='自助兑换设备ip-机构映射表';

-- 产品表 tb_product 增加一个字段 exchange_price,客户自助兑换价格。
ALTER table tb_product add exchange_price DECIMAL(17, 2) NULL COMMENT '客户自助兑换价格RMB';

-- 发票表
CREATE TABLE `tb_invoice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `application_date` datetime NOT NULL COMMENT '申请时间',
  `total` double(15,2) NOT NULL COMMENT '发票总价',
  `supplier_id` int(10) DEFAULT NULL COMMENT '供应商机构id',
  `state` int(2) DEFAULT NULL COMMENT '发票状态 0拒绝 1审核中  2通过',
  `invoice_id` varchar(35) NOT NULL COMMENT '发票编号',
  `invoice_type` varchar(25) DEFAULT NULL COMMENT '发票类型',
  `deliver_billno` varchar(18) NOT NULL COMMENT '发货单号',
  `applicater_id` int(10) DEFAULT NULL COMMENT '申请机构id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='发票表';

SET FOREIGN_KEY_CHECKS = 1;

/*增加字段  商品流量点击*/
alter table tb_product add clickrate VARCHAR(25) DEFAULT '0-0-0-0-0-0-0'


alter table tb_advertisement
	add adv_classification enum('0', '1') default '0' not null comment '0:文字广告1:图片广告2:其他广告';
alter table tb_advertisement
	add adv_position int default 0 not null comment '图片所在位置';
alter table tb_advertisement
	add href_class int default 0 not null comment '跳转类型:0:无跳转1:商品2:自定义';
alter table tb_category
	add ctgr_class int default 0 not null comment '0:普通1:特殊(新品,热卖)2:自定义';
alter table tb_category add ctgr_rank int default 0 not null comment '排序码';
alter table tb_advertisement modify end_time datetime null comment '广告展示结束时间';


create table tb_events
(
    id          int auto_increment
        primary key,
    name        varchar(50) default '活动' not null,
    status      int         default 0    null comment '0:正常1:下架2:2隐藏',
    create_time datetime                 not null comment '创建时间',
    start_time  datetime                 not null comment '开始时间',
    end_time    datetime                 null comment '结束时间,为null代表为长期活动',
    evt_rank    int         default 0    not null comment '排序码',
    evt_class   int         default 1    not null comment '0:自有的(不可删除)1:自定义的(可删除)'
)
    comment '活动表';



create table tb_events_product
(
    events_id  int not null,
    product_id int not null
)
    comment '活动物品表';


    create table tb_wtlist
(
    id          int           not null
        primary key,
    type        int default 1 not null comment '1和2',
    wt_name     varchar(50)   null,
    Remark      varchar(100)  null,
    upload_time datetime      not null,
    upload_er   int           not null,
    update_time datetime      not null,
    update_er   int           not null,
    wt_status   int default 0 not null comment '0正常1作废'
)
    comment '名单表';


    create table tb_wtlist_user
(
    wtlist_id int not null,
    client_id int not null
)
    comment '名单客户表';

/*2020-2-23 zyx*/
alter table tb_product
    add product_limit int default 1 null comment '商品限购 默认1';
/*广告*/
INSERT INTO tb_advertisement (id, name, href, img, `rank`, status, create_time, start_time, end_time, detail_img, adv_classification, adv_position, href_class) VALUES (20, '广告1', '#', '/image/bb1.jpg', 0, '1', '2019-12-13 17:13:52', '2019-09-18 00:00:00', '3333-07-23 03:10:00', null, '1', 1, 0);
INSERT INTO tb_advertisement (id, name, href, img, `rank`, status, create_time, start_time, end_time, detail_img, adv_classification, adv_position, href_class) VALUES (37, '广告2', '#', '/image/bb2.jpg', 1, '1', '2019-12-17 15:25:04', '2019-12-09 00:00:00', '3333-12-31 16:10:05', null, '1', 1, 0);
/*推荐*/
INSERT INTO tb_advertisement (id, name, href, img, `rank`, status, create_time, start_time, end_time, detail_img, adv_classification, adv_position, href_class) VALUES (5, '数据5', '#', '/images/s1.jpg', 0, '1', '2019-12-16 09:58:51', '2019-12-16 09:58:51', '3333-07-23 03:10:00', '#', '1', 3, 0);
INSERT INTO tb_advertisement (id, name, href, img, `rank`, status, create_time, start_time, end_time, detail_img, adv_classification, adv_position, href_class) VALUES (6, '数据6', '#', '/images/s2.jpg', 1, '1', '2019-12-16 09:58:51', '2019-12-16 09:58:51', '3333-07-23 03:10:00', '#', '1', 3, 0);

-- 加几个分类
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (126, '大家电', 0, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '/', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (127, '电视机', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/dsj.png', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (128, '洗衣机', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/xyj.png', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (129, '冰箱', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/bx.jpg', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (130, '燃气炉', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/rql.jpg', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (131, '油烟机', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/xyyj.jpg', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (132, '蒸烤箱', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/zkx.jpg', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (133, '洗碗机', 126, 0, 0, '2020-03-04 17:22:53', '2020-03-04 17:38:53', 0, '../classifyImage/xwj.png', 0, 0);
/*2020-3-9*/
alter table tb_advertisement add adv_position int default 0 not null;
alter table tb_advertisement add href_class int default 0 not null;
alter table tb_advertisement modify adv_classification enum('0', '1', '2') default '0' not null comment '0:文字广告1:图片广告2:其他(更多)';

INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (134, '零食', 2, 0, 0, '2020-03-08 10:22:53', '2020-03-08 10:38:53', 0, '../classifyImage/ls.jpg', 0, 0);
INSERT INTO `supply`.`tb_category`(`id`, `category_name`, `parent_id`, `category_level`, `is_end`, `create_date`, `update_date`, `status`, `img`, `ctgr_class`, `ctgr_rank`) VALUES (135, '牛奶', 2, 0, 0, '2020-03-08 10:22:53', '2020-03-08 10:38:53', 0, '../classifyImage/nn.jpg', 0, 0);

-- 是否导出
alter table tb_deliver_form add export_mark int  default 0

-- 手动作废订单
update tb_purchase_form set status = 3 where id = 207;


/*2020-3-12*/
INSERT INTO tb_permission (id, parent_id, lvl, porder, available, permission, name, resource_type, url, remark) VALUES (44, 0, 1, 0, 1, 'mbHomePage', '首页管理', 'menu', null, null);
INSERT INTO tb_permission (id, parent_id, lvl, porder, available, permission, name, resource_type, url, remark) VALUES (46, 44, 2, 0, 1, 'toIndexEditA', '首页编辑', 'menu', '/mgt/toIndexEditA', null);
/*把功能增加给指定用户*/
INSERT INTO tb_role_permission (permission_id, role_id) VALUES (44, 3);
INSERT INTO tb_role_permission (permission_id, role_id) VALUES (46, 3);
/*2020-3-18*/
alter table tb_advertisement modify status enum('0', '1', '2') default '1' not null comment '广告状态 0下架 1上架 2隐藏';

/*2020-3-30*/
CREATE TABLE `tb_networker` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `name` varchar(10) DEFAULT NULL,
        `branchno` varchar(50) DEFAULT NULL COMMENT '机构号',
        `branch_id` varchar(30) DEFAULT NULL COMMENT '员工编号',
        `phone_num` varchar(20) DEFAULT NULL COMMENT '电话号码',
        `deptment` varchar(50) DEFAULT NULL COMMENT '部门',
        `address_id` int(11) DEFAULT NULL COMMENT '地址id',
        `org_id` int(11) DEFAULT NULL COMMENT 'tb_org_info id',
        `card_no` varchar(20) DEFAULT NULL COMMENT '身份证号码',
        `create_time` datetime DEFAULT NULL,
        `update_time` datetime DEFAULT NULL,
        `operator` int(11) DEFAULT NULL COMMENT '操作人id',
        `remake` varchar(20) DEFAULT NULL COMMENT '备用1',
        `remake1` int(11) DEFAULT NULL COMMENT '备用2',
        `status` enum('0','1') NOT NULL DEFAULT '0' COMMENT '0 在职 1 离职(被删除)',
        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='网点员工信息表';

INSERT INTO `supply`.`tb_permission`(`id`, `parent_id`, `lvl`, `porder`, `available`, `permission`, `name`, `resource_type`, `url`, `remark`) VALUES (52, 1, 2, 0, 1, 'networkUserManager', '网点员工管理', 'menu', '/mgt/toNetworkUserManager', NULL);

/*2020-3-31*/
alter table tb_user_info modify column1 varchar(255) null comment '行号';
alter table jf_deliver_order modify type int default 0 null comment '类型；type=1,配送；type=0，兑换；type=2,无消费兑换';

/*2020-4-01*/
alter table tb_org_info
    add bank_no varchar(20) null comment '行号';
/*2020-4-02*/
INSERT INTO `supply`.`tb_permission`(`id`, `parent_id`, `lvl`, `porder`, `available`, `permission`, `name`, `resource_type`, `url`, `remark`) VALUES (53, 16, 2, 0, 1, 'toExchangeOrderCount', '自助兑换报表', 'menu', '/mgt/toExchangeOrderCount', NULL);

/*2020-4-8 商品新增现金支付方式 */
alter table tb_product
    add exchange_cash decimal(17,2) default 0 null comment '现金支付';
alter table tb_product
    add exchange_type enum('0', '1', '2') default '0' null comment '支付方式 0 积分支付 1 现金支付 2 混合支付';
alter table tb_product
    add exchange_remake varchar(255) null comment '兑换备注';

/*2020-4-10 枚举改int*/
alter table tb_product modify exchange_type int default 0 null comment '支付方式 0 积分支付 1 现金支付 2 混合支付';

alter table tb_product change exchange_remake exchange_remark varchar(255) null comment '兑换备注';

update tb_product set exchange_type = 0;
alter table jf_deliver_order modify type int default 0 null comment '类型；type=1,配送；type=0，积分支付兑换；type=2,无消费兑换；type=3,现金支付兑换;type=4,现金积分混合支付';




/*2020-4-17*/
delete  from tb_purchase_form  where purchase_billno     = '201911270190711049';
delete  from tb_purchase_detail  where purchase_billno     = '201911270190711049';

/*2020-4-22 商品无法入库 */
INSERT tb_repository VALUES(1145,17,261,0,0,null);

/*商品预购*/
alter table tb_product
    add purchase_ornot int default 0 null comment '0 预购 1不预购';

/*20200509商品颜色*/
alter table tb_cart
    add product_color varchar(20) null comment '物品颜色';
alter table tb_purchase_detail
    add product_color varchar(20) null comment '商品颜色';


# 20200512 商品加个已拒绝
alter table tb_product modify check_status tinyint default 1 null comment '货品状态 1未审核 2审核通过 3审核未通过 4已拒绝';

# 20200515 自助兑换单增加字段，发放时间 out_time
alter table jf_deliver_order
  add out_time datetime null comment '发放时间';

# 20200521 增加 "兑换订单额外信息表"。

create table if not exists jf_deliver_order_extra
(
  billno varchar(50) not null primary key comment '兑换订单号' ,
  cust_name varchar(100) not null comment '客户姓名/昵称' ,
  cust_tel varchar(50) not null comment '客户联系方式',
  cust_addr varchar(200) comment '客户收货地址' default ''

)comment '兑换订单额外信息表';

# 20200602 增加订单额外信息：中间过程状态
alter table jf_deliver_order_extra
  add process_status  varchar(20) not null default '' comment '中间过程状态;"OVERTIME"=打印超时等';
# 20200603 增加字段默认值
alter table jf_deliver_order_extra alter column cust_name set default '';
alter table jf_deliver_order_extra alter column cust_tel set default '';

INSERT INTO `supply`.`tb_permission`(`id`, `parent_id`, `lvl`, `porder`, `available`, `permission`, `name`, `resource_type`, `url`, `remark`) VALUES (60, 2, 2, 0, 1, 'Department', '部门管理', 'menu', NULL, NULL);
CREATE TABLE `tb_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `dept_no` int(11) DEFAULT NULL COMMENT '部门编号',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `org_id` int(11) DEFAULT NULL COMMENT '网点id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `status` int(2) unsigned zerofill DEFAULT NULL COMMENT '0 在 1不在',
  `remark` varchar(255) DEFAULT NULL,
  `remark1` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



/20200728/
alter table tb_product
	add product_prefecture int default 0 null comment '1是商品专区 内采显示兑换端不显示';

















