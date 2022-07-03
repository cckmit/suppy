package com.zjjzfy.pojo.provider;

import java.util.List;
import java.util.Map;

/**
 * @author jackshenonly
 * @description SqlProvider
 * @date 2019-03-13 23:28
 */
public class SqlProvider {

    public String findPermissionsByRoles(Map map) {
        List<String> users = (List<String>) map.get("roles");
        StringBuilder sb = new StringBuilder();
        sb.append("select permission from tb_permission a join tb_role_permission trp on a.id = trp.permission_id ");
        sb.append("where a.available=1  and trp.role_id in (select id from tb_role where role in (");
        users.stream().forEach(s -> sb.append("'" + s + "',"));
        sb.append("''");
        sb.append(")) ");

        return sb.toString();
    }
}


