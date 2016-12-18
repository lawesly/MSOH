package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import ui.controller.EditPromotionController;
import ui.view.controllerservice.EditPromotion;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.PromotionVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/15.
 */
public class MarketerConfirmDeletePromotionController implements ControlledStage {

    private StageController stageController;

    private String resource = "marketer/MarketerConfirmDeletePromotion.fxml";

    private PromotionVO promotionVO;
    private EditPromotion editPromotion;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        this.promotionVO = promotionVO;
    }

    /**
     * 确认按钮结果，删除该促销策略
     */
    @FXML
    private void confirmDelete() throws RemoteException {
        editPromotion = new EditPromotionController();
        ResultMessage resultMessage = editPromotion.deletePromotion(promotionVO.promotionID);
        if (resultMessage.equals(ResultMessage.Promotion_DeletePromotionSuccess)) {
            stageController = this.returnMessage("删除成功！");
            stageController.closeStage(resource);
        } else {
            this.returnMessage("未知错误！");
        }
    }

    /**
     * 取消按钮结果，关闭弹窗
     */
    @FXML
    private void cancelDelete() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 显示提示信息
     *
     * @param error
     * @return
     */
    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }
}
