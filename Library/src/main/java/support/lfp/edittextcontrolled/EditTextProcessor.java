package support.lfp.edittextcontrolled;

/**
 * <pre>
 * Tip:
 *     EditText处理器,它可以为EditText动态附加能力
 *     该处理器只能使用于EditTextControlView
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/6 13:52
 * </pre>
 */
public abstract class EditTextProcessor {

    private EditTextControlView mView;

    /**
     * 获得该处理器绑定的EditTextControlView对象
     *
     * @return A EditTextControlView
     */
    public EditTextControlView getView() {
        return mView;
    }

    /**
     * 当处理器从EditTextControlView中移除的时候，做一些必要的清理工作
     */
    void clear() {
        onDetachedFromView();
        mView = null;
    }

    /**
     * 附加处理器绑定的EditTextControlView，该处理器的所有工作都围绕这个EditTextControlView
     *
     * @param v A EditTextControlView
     */
    void additionView(EditTextControlView v) {
        mView = v;
        onAttachedToView();
    }

    /**
     * 当处理器从EditTextControlView中被移除
     */
    protected void onDetachedFromView() {
    }

    /**
     * 当处理器被添加到EditTextControlView
     */
    protected void onAttachedToView() {}

}
