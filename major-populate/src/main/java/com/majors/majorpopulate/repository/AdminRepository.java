package com.majors.majorpopulate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.majors.majorpopulate.POJO.Admin;

public interface AdminRepository extends CrudRepository<Admin,Integer>{
    
    @Autowired
    Admin admin;

    public Admin getAdmin(int id){
        return  admin.get(id);
    }
}
