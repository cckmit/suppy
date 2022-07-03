package com.zjjzfy.user.service;

import com.github.pagehelper.PageInfo;
import com.zjjzfy.common.config.OrgType;
import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.pojo.TbOrgInfo;

import java.util.List;

public interface UserOrgService {

    SupplyResult getIndentorUser(String username, String nickname, String branchNo, Integer page, Integer limit);

    SupplyResult getUserOrgUserId(Integer uId);

    SupplyResult getUserOrgId(Integer uId);

    PageInfo<TbOrgInfo> selectOrgPageByType(Integer pageSize, Integer pageNo, OrgType ot);

    List<TbOrgInfo> selectOrg(OrgType ot);

    SupplyResult updateUserCheckOrg(Integer id, String orgIdStr);

    SupplyResult checkUserOrg(TbOrgInfo currentOrg, TbOrgInfo addOrModifyOrg, Integer uId);
}