package ui.view.presentation.Clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/11/28.
 */
public class ClerkHotelInfoController implements ControlledStage {

    StageController stageController;

    @FXML
    private Button checkCommentsButton;
    @FXML
    private Button addRoomButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 查看详细评价按钮结果，显示酒店详细评价页面
     */
    public void showHotelComments() {
        stageController = new StageController();
        stageController.loadStage("Clerk/ClerkHotelComments.fxml", 1);
    }

    /**
     * 录入客房按钮结果，显示录入可用客房界面
     */
    public void showModifyAvailableRooms() {
        stageController = new StageController();
        stageController.loadStage("Clerk/ClerkModifyAvailableRooms.fxml", 1);
    }

    /**
     * 修改信息按钮结果，显示修改酒店信息界面
     */
    public void showModifyHotelInfo(){

    }

}