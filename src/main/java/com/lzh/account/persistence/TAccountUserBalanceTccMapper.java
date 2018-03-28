package com.lzh.account.persistence;

import com.lzh.account.model.entity.generator.TAccountUserBalanceTcc;
import java.util.List;

public interface TAccountUserBalanceTccMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUserBalanceTcc record);

    TAccountUserBalanceTcc selectByPrimaryKey(Long id);

    List<TAccountUserBalanceTcc> selectAll();

    int updateByPrimaryKey(TAccountUserBalanceTcc record);
}