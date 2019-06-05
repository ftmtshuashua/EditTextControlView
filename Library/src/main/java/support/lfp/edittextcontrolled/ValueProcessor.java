package support.lfp.edittextcontrolled;

import android.text.Editable;

/**
 * <pre>
 * Tip:
 *      当输入框内容发生改变的时候被处理
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/5 15:57
 * </pre>
 */
public abstract class ValueProcessor extends EditTextProcessor {

    /**
     * 检查该处理器是否拦截该操作,如果事件被拦截,则回调onProcessor()方法
     *
     * @param beforeText 改变之前的内容
     * @param text       改变之后的内容对象
     * @return 是否拦截事件
     */
    boolean isIntercept(CharSequence beforeText, Editable text) {
        return onIntercept(getView(), beforeText, text);
    }

    /**
     * 处理器核心方法，验证数据是否通过处理器的规则。如果未通过规则，onProcessor()方法来处理.
     * 并且在该处理器处理结束之前后续处理器无法进行处理
     *
     * @param beforeText 文本改变之前的值
     * @param text       当前文本值
     * @return 是否拦截事件
     */
    protected abstract boolean onIntercept(EditTextControlView v, CharSequence beforeText, Editable text);

    /**
     * 如果文本修改事件被拦截，则回调该方法
     *
     * @param beforeText 修改前的值
     * @param text       当前值
     */
    void onProcessor(CharSequence beforeText, Editable text) {
        onProcessor(getView(), beforeText, text);
    }

    /**
     * 如果onVerify()返回false，则表示该数据不合法，需要进行处理。
     *
     * @param beforeText 修改前的值
     * @param text       当前值
     */
    protected abstract void onProcessor(EditTextControlView v, CharSequence beforeText, Editable text);
}
