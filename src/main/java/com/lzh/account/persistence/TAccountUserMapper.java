package com.lzh.account.persistence;

import com.lzh.account.model.entity.generator.TAccountUser;
import java.util.List;

public interface TAccountUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUser record);

    TAccountUser selectByPrimaryKey(Long id);

    List<TAccountUser> selectAll();

    int updateByPrimaryKey(TAccountUser record);
}