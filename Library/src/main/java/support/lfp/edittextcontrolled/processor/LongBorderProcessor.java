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
public class LongBorderProcessor extends BorderProcessor<Long> {

    public LongBorderProcessor(Long min, Long max) {
        super(min, max);
    }

    @Override
    protected boolean isIntercept(EditTextControlView v, CharSequence beforeText, Editable text) {
        try {
            long value = getValue(text);
            return !(value >= getMin() && value <= getMax());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected Long getValue(Editable text) {
        return getView().getLongValue();
    }

    @Override
    protected void onProcessor(EditTextControlView v, CharSequence beforeText, Editable text) {
        double value = v.getLongValue();
        if (value <= getMin()) {
            v.setValue(getMin());
        } else if (value >= getMax()) {
            v.setValue(getMax());
        }
        v.setSelection(v.length());
    }
}
