package support.lfp.edittextcontrolled.processor;

import android.text.Editable;

import support.lfp.edittextcontrolled.EditTextControlView;

/**
 * <pre>
 * Tip:
 *      编辑内容范围处理,给定一个范围值，使输入内容不超过该范围
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/5 17:31
 * </pre>
 */
public class IntegerBorderProcessor extends BorderProcessor<Integer> {

    public IntegerBorderProcessor(Integer min, Integer max) {
        super(min, max);
    }

    @Override
    protected boolean isIntercept(EditTextControlView v, CharSequence beforeText, Editable text) {
        try {
            int value = getValue(text);
            return !(value >= getMin() && value <= getMax());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected Integer getValue(Editable text) {
        return getView().getIntValue();
    }

    @Override
    protected void onProcessor(EditTextControlView v, CharSequence beforeText, Editable text) {
        double value = v.getIntValue();
        if (value <= getMin()) {
            v.setValue(getMin());
        } else if (value >= getMax()) {
            v.setValue(getMax());
        }
        v.setSelection(v.length());
    }
}
