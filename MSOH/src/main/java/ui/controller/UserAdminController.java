package ui.controller;

import ui.view.controllerservice.UserAdmin;
import util.ResultMessage;
import vo.ClerkVO;
import vo.CustomerVO;
import vo.MarketerVO;
import vo.UserVO;

import java.util.List;

/**
 * Created by zqh on 2016/11/12.
 */
public class UserAdminController implements UserAdmin{
    public UserVO findAllByID(String ID) {
        return null;
    }

    public UserVO findAllByName(String name) {
        return null;
    }

    public List<ClerkVO> findAllClerk() {
        return null;
    }

    public List<ClerkVO> findClerkByName(String name) {
        return null;
    }

    public ClerkVO findClerkByID(String ID) {
        return null;
    }

    public List<MarketerVO> findAllMarketer() {
        return null;
    }

    public List<MarketerVO> findMarketerByName(String name) {
        return null;
    }

    public MarketerVO findMarketerByID(String ID) {
        return null;
    }

    public List<CustomerVO> findAllCustomer() {
        return null;
    }

    public List<CustomerVO> findCustomerByName(String name) {
        return null;
    }

    public CustomerVO findCustomerByID(String ID) {
        return null;
    }

    public ResultMessage addClerk(ClerkVO vo) {
        return null;
    }

    public ResultMessage addMarketer(MarketerVO vo) {
        return null;
    }

    public ResultMessage deleteClerk(ClerkVO vo) {
        return null;
    }

    public ResultMessage deleteMarketer(MarketerVO vo) {
        return null;
    }

    public ResultMessage updateCustomerInfo(CustomerVO vo) {
        return null;
    }

    public ResultMessage updateClerkInfo(ClerkVO vo) {
        return null;
    }

    public ResultMessage updateMarketerInfo(MarketerVO vo) {
        return null;
    }
}