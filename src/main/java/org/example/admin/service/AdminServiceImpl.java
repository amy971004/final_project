package org.example.admin.service;


import org.example.admin.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDAO dao;

    @Autowired
    public AdminServiceImpl(AdminDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean deleteUser(String userId) {
        return dao.deleteUser(userId) > 0;
    }
}
