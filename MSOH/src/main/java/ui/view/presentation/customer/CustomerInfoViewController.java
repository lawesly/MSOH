package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import ui.controller.CustomerInfoChangeController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.CustomerInfoChange;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.ImageController;
import util.MemberType;
import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerInfoViewController implements ControlledStage{
    private StageController stageController;

    private String resource = "customer/CustomerInfoView.fxml";

    private String customerID;

    @FXML
    private Button saveInfoButton;

    @FXML
    private TextField nameTextField;

    private String name;

    @FXML
    private TextField emailTextField;

    private String email;

    @FXML
    private TextField phoneTextField;

    private String phone;

    @FXML
    private TextField creditTextField;

    private String credit;

    @FXML
    private TextField memberTextField;

    private String member;

    @FXML
    private TextField idTextField;

    private String ID;

    @FXML
    private ImageView iconLabel;

    private String icon;

    @FXML
    private Button backButton;

    private String newName;
    private String newEmail;
    private String newPhone;
    private String newIcon;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭客户修改信息界面
     */
    @FXML
    private void closeStage() {
        getNewInfo();
        stageController = new StageController();
        if(newName.equals(name) && newEmail.equals(email) && newPhone.equals(phone) && newIcon.equals(icon)){
            stageController.closeStage(resource);
        }
        else {
            stageController.loadStage("util/ConfirmExit.fxml", 0.75);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }
    }
    /**
     * 跳转至客户修改密码界面
     */
    @FXML
    private void modifyPassword(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerModifyPasswordView.fxml", 1);
        CustomerModifyPasswordViewController customerModifyPasswordViewController = (CustomerModifyPasswordViewController) stageController.getController();
        customerModifyPasswordViewController.init(customerID);
    }

    /**
     * 客户更改信息方法
     */
    @FXML
    private void modifyInfo(){
        getNewInfo();
        name = newName;
        email = newEmail;
        phone = newPhone;
        if(newName.equals(name) && newEmail.equals(email) && newPhone.equals(phone) && newIcon.equals(icon)) {

            CustomerInfoChange customerInfoChange = new CustomerInfoChangeController();
            try {
                ResultMessage resultMessage = customerInfoChange.changeInfo(new CustomerVO(customerID, newName, newPhone, newEmail));
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
                if (resultMessage == ResultMessage.Blank) {
                    errorBoxController.setLabel("请填写完整信息！");
                } else if (resultMessage == ResultMessage.phoneFormatWrong) {
                    errorBoxController.setLabel("手机格式错误！");
                } else if (resultMessage == ResultMessage.emailFormatWrong) {
                    errorBoxController.setLabel("邮件格式错误！");
                } else if (resultMessage == ResultMessage.ChangeInfoSuccess) {
                    errorBoxController.setLabel("成功修改信息！");
                    stageController = new StageController();
                    stageController.closeStage(resource);
                    CustomerMainViewController customerMainViewController = (CustomerMainViewController) stageController.getController("customer/CustomerMainView.fxml");
                    customerMainViewController.setNameLabel(name);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("未修改信息！");
        }

    }

    /**
     * 客户修改信息界面初始化方法
     * @param customerID
     */
    public void init(String customerID){
        this.customerID = customerID;
        setInfo();
    }

    /**
     * 显示客户原信息
     */
    private void setInfo(){
        try {
            UserAdmin userAdmin = new UserAdminController();
            CustomerVO customerVO = userAdmin.findCustomerByID(customerID);
            name = customerVO.name;
            email = customerVO.email;
            phone = customerVO.phone;
            icon = customerVO.picUrl;
            newIcon = icon;
            if(!customerVO.picUrl.equals("")) {
                ImageController imageController = new ImageController();
                WritableImage wr = imageController.loadImage(customerVO.picUrl, 145, 145);
                iconLabel.setImage(wr);
            }
            credit = customerVO.credit + "";
            if(customerVO.memberType == MemberType.ENTREPRISE) {
                member = "企业会员";
            }else if(customerVO.memberType == MemberType.NORMAL){
                member = "普通会员";
            }else if(customerVO.memberType == MemberType.NONMEMBER){
                member = "非会员";
            }
            ID = customerVO.ID;

            nameTextField.setText(name);
            emailTextField.setText(email);
            phoneTextField.setText(phone);
            creditTextField.setText(credit);
            memberTextField.setText(member);
            idTextField.setText(ID);
            getNewInfo();
        }catch (RemoteException e){
            e.printStackTrace();
        }
            getNewInfo();
    }

    private void getNewInfo(){
        newName = nameTextField.getText();
        newEmail = emailTextField.getText();
        newPhone = phoneTextField.getText();
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}

