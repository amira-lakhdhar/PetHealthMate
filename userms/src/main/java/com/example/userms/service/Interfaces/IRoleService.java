package com.example.userms.service.Interfaces;


import com.example.userms.entity.Role;
import com.example.userms.generic.IGenericService;

public interface IRoleService extends IGenericService<Role,Integer> {

    public Role createNewRole(Role role);
    public void DeleteRole(Integer id);

}
