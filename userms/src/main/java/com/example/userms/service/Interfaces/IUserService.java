package com.example.userms.service.Interfaces;

import com.example.userms.entity.User;
import com.example.userms.generic.IGenericService;

public interface IUserService extends IGenericService<User,Long>
{

    public User createNewUser(User user,Integer id);
    public void DeleteUser(Long id);
}
