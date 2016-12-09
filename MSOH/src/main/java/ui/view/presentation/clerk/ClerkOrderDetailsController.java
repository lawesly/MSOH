package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/30.
 */
public class ClerkOrderDetailsController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkOrderDetails.fxml";

    @FXML
    private Button backButton;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerPhoneLabel;
    @FXML
    private Label customerIDLabel;
    @FXML
    private Label orderIDLabel;
    @FXML
    private Label orderStatusLabel;
    @FXML
    private Label estimatedCheckinTimeLabel;
    @FXML
    private Label actualCheckinTimeLabel;
    @FXML
    private Label estimatedCheckoutTimeLabel;
    @FXML
    private Label actualCheckoutTimeLabel;
    @FXML
    private Label hotelNameLabel;
    @FXML
    private Label hotelIDLabel;
    @FXML
    private Label numOfCustomersLabel;
    @FXML
    private TextArea roomTypeTextArea;
    @FXML
    private Label haveChildrenLabel;
    @FXML
    private Label promotionLabel;
    @FXML
    private Label initialPriceLabel;
    @FXML
    private Label finalPriceLabel;
    @FXML
    private Button checkButton;


    //    private String clerkID;
    private String orderID;
    private OrderVO orderVO;
    private ProcessOrder processOrder;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String orderID) throws RemoteException {
//        this.clerkID = clerkID;
        //根据订单号得到订单
        this.orderID = orderID;
        processOrder = new ProcessOrderController();
        orderVO = processOrder.getSingle(orderID);

        //显示订单各项信息
        orderIDLabel.setText(orderID);
        customerIDLabel.setText(orderVO.customerID);
        customerNameLabel.setText(orderVO.customerName);
        customerPhoneLabel.setText(orderVO.phone);
        orderStatusLabel.setText(orderVO.orderType.toString());
        estimatedCheckinTimeLabel.setText(orderVO.estimatedCheckinTime == null ? "" : orderVO.estimatedCheckinTime.toString());
        estimatedCheckoutTimeLabel.setText(orderVO.estimatedCheckoutTime == null ? "" : orderVO.estimatedCheckoutTime.toString());
        actualCheckinTimeLabel.setText(orderVO.actualCheckinTime == null ? "" : orderVO.actualCheckinTime.toString());
        actualCheckoutTimeLabel.setText(orderVO.actualCheckoutTime == null ? "" : orderVO.actualCheckoutTime.toString());
        hotelNameLabel.setText(orderVO.hotelName);
        hotelIDLabel.setText(orderVO.hotelID);
        haveChildrenLabel.setText(orderVO.haveChildren ? "有" : "无");
        numOfCustomersLabel.setText(String.valueOf(orderVO.numOfCustomers));
        String roomTypes = "";
        for (String types : orderVO.rooms) {
            roomTypes += types + "; ";
        }
        roomTypeTextArea.setText(roomTypes);
        promotionLabel.setText(orderVO.promotionName);
        initialPriceLabel.setText(String.valueOf(orderVO.initialPrice));
        finalPriceLabel.setText(String.valueOf(orderVO.finalPrice));
        //根据订单状态设置按钮内容
        if (orderVO.orderType.equals(OrderStatus.UNEXECUTED)) {
            checkButton.setText("入住");
        } else if (orderVO.orderType.equals(OrderStatus.EXECUTED)) {
            checkButton.setText("退房");
        }
    }

    /**
     * 后退按钮结果，显示上级订单列表界面
     */
    @FXML
    private void showOrderList() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 入住按钮结果，显示确认入住弹窗
     */
    @FXML
    private void confirmCheck() {
        stageController = new StageController();
        stageController.loadStage("clerk/ClerkConfirmCheck.fxml", 1);
        ClerkConfirmCheckController clerkConfirmCheckController = (ClerkConfirmCheckController) stageController.getController();
        String status = checkButton.getText();
        if (status.equals("入住")) {
            clerkConfirmCheckController.initial("checkIn");
        } else if (status.equals("退房")) {
            clerkConfirmCheckController.initial("checkOut");
        }

    }


}
