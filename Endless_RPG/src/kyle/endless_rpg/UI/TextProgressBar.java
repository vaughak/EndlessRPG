package kyle.endless_rpg.UI;
import kyle.endless_rpg.Assets;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TextProgressBar extends ProgressBar{
   private Label labelName;
   private Label labelValue;
   private LabelStyle labelStyle;
   private boolean valueVisible = true;

   public TextProgressBar(String text, float min, float max, float stepSize, boolean vertical, ProgressBarStyle style, LabelStyle labelStyle, float w, float h) {
      super(min, max, stepSize, vertical, style, w, h);
      
      
     // this.labelStyle = new LabelStyle(labelStyle);
     // this.labelStyle.fontColor.a = 0.7f;
      

      Skin skin = Assets.manager.get(Assets.uiskinJSON);
      
      labelName = new Label(text, skin);
     // labelName = new Label(text, this.labelStyle);
      add(labelName).expandX().left();
      
      labelValue = new Label((int) min + "/" + (int) max, skin);
      add(labelValue).right();
   }
   
   public Label getLabelName() {
      return labelName;
   }

   public void setLabelName(Label labelName) {
      this.labelName = labelName;
   }

   public Label getLabelValue() {
      return labelValue;
   }

   public void setLabelValue(Label labelValue) {
      this.labelValue = labelValue;
   }

   public boolean isValueVisible() {
      return valueVisible;
   }

   public void setValueVisible(boolean valueVisible) {
      this.valueVisible = valueVisible;
      labelValue.setVisible(valueVisible);
   }
   
   @Override
   public boolean setValue(float value){
      if(super.setValue(value) == false)
         return false;

      refreshValue();
      return true;
   }
   
   @Override
   public void setRange(float min, float max){
      super.setRange(min, max);
      refreshValue();
   }
   
   public void refreshValue(){
      labelValue.setText((int) value+"/"+ (int) max);
   }
   
   public LabelStyle getLabelStyle() {
      return labelStyle;
   }

   public void setLabelStyle(LabelStyle labelStyle) {
      this.labelStyle = labelStyle;
   }

   public float getLabelAlpha() {
      return this.labelStyle.fontColor.a;
   }

   public void setLabelAlpha(float labelAlpha) {
      this.labelStyle.fontColor.a = labelAlpha;
   }
   
}