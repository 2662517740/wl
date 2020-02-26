package com.service.impl;

import com.entity.Administrators;
import com.mapper.AdministratorsMapper;
import com.service.IAdministratorsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-02-26
 */
@Service
public class AdministratorsServiceImpl extends ServiceImpl<AdministratorsMapper, Administrators> implements IAdministratorsService {

}
