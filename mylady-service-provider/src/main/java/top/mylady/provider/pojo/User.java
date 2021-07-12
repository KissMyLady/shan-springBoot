package top.mylady.provider.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String phone;
    private Date created;
    private String salt;

    /*
    *
    * DROP TABLE IF EXISTS `tb_user`;
    CREATE TABLE `tb_user` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `username` varchar(32) NOT NULL COMMENT '用户名',
      `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
      `phone` varchar(11) DEFAULT NULL COMMENT '注册手机号',
      `created` datetime NOT NULL COMMENT '创建时间',
      `salt` varchar(32) NOT NULL COMMENT '密码加密的salt值',
      PRIMARY KEY (`id`),
      UNIQUE KEY `username` (`username`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='用户表';
    * */
}
