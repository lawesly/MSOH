package ui.view.presentation.Marketer;

import ui.view.presentation.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerWebPromotionController implements ControlledStage {

    StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 制定按钮结果，显示制定网站促销策略界面
     */
    public void showCreatePromotion() {
        stageController = new StageController();
        stageController.loadStage("Marketer/MarketerCreateWebPromotion.fxml", 1);
    }
}