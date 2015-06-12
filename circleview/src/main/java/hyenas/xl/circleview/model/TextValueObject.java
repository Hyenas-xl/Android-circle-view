package hyenas.xl.circleview.model;

/**
 * Created by xl on 2015/6/12.
 */
public class TextValueObject {

    private String textValue;
    private boolean isHighlight;

    public TextValueObject(String textValue,boolean isHighlight){
        this.textValue = textValue;
        this.isHighlight = isHighlight;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public boolean isHighlight() {
        return isHighlight;
    }

    public void setIsHighlight(boolean isHighlight) {
        this.isHighlight = isHighlight;
    }
}
