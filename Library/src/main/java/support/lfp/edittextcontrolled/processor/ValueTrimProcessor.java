package support.lfp.edittextcontrolled.processor;

import android.text.Editable;
import android.text.TextUtils;

import support.lfp.edittextcontrolled.EditTextControlView;
import support.lfp.edittextcontrolled.ValueProcessor;

/**
 * <pre>
 * Tip:
 *      根据String.trim()的规则去掉首尾的空字符串和一些特殊字符串
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/5 16:29
 * </pre>
 */
public class ValueTrimProcessor extends ValueProcessor {

    @Override
    protected boolean onIntercept(EditTextControlView v, CharSequence beforeText, Editable text) {
        String str = text.toString();
        if (!TextUtils.isEmpty(str)) {
            return !str.equals(str.trim());
        }
        return false;
    }

    @Override
    protected void onProcessor(EditTextControlView v, CharSequence beforeText, Editable text) {
        v.setText(text.toString().trim());
        v.setSelection(v.length());
    }
}
