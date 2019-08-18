CREATE TABLE `shiro_user`
(
    `id`       bigint(20)  NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(64) NOT NULL COMMENT '密码，加密存储',
    `created`  datetime    NOT NULL,
    `updated`  datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

/*Data for the table `tb_user` */

insert into `shiro_user`(`id`, `username`, `password`, `created`, `updated`)
values (1, 'admin', '$2a$10$9ZhDOBp.sRKat4l14ygu/.LscxrMUcDAfeVOEPiYwbcRkoB09gCmi', '2019-04-04 23:21:27',
        '2019-04-04 23:21:29');

/*Table structure for table `tb_user_role` */
