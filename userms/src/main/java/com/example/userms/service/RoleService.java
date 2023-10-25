package com.example.userms.service;



import com.example.userms.entity.Role;
import com.example.userms.generic.IGenericServiceImp;
import com.example.userms.repo.RoleRepository;
import com.example.userms.service.Interfaces.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
 public class RoleService extends IGenericServiceImp<Role,Integer> implements IRoleService {

      private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void DeleteRole(Integer id) {
          roleRepository.deleteById(id);
    }
}
