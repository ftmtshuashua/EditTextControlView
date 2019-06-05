package support.lfp.edittextcontrolled;

/**
 * <pre>
 * Tip:
 *      当EditTextControlView内容变化的时候，保证在所有处理器都执行完毕后获得最终值。
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/5 17:01
 * </pre>
 */
@FunctionalInterface
public interface OnEditTextValueChangeListener {
    /**
     * 当编辑框内容被修改
     *
     * @param beforeText 修改之前的值
     * @param text  修改之后的值
     */
    void onChange(CharSequence beforeText, CharSequence text);
}
