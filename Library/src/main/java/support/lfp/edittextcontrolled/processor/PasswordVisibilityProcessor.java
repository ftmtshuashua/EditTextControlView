package support.lfp.edittextcontrolled.processor;

import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;

import support.lfp.edittextcontrolled.EditTextProcessor;

/**
 * <pre>
 * Tip:
 *      编辑框密码可见性控制器
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/6 13:54
 * </pre>
 */
public class PasswordVisibilityProcessor extends EditTextProcessor implements View.OnTouchListener, View.OnClickListener {

    View mView;
    boolean isPressed = false; //查看状态
    Style style;

    /**
     * @param controlview 用于控制密码显示的View
     */
    public PasswordVisibilityProcessor(View controlview) {
        this(controlview, Style.TOUCH);
    }

    /**
     * @param controlview 用于控制密码显示的View
     * @param style       控制模式
     */
    public PasswordVisibilityProcessor(View controlview, Style style) {
        this.style = style;
        mView = controlview;

    }

    @Override
    protected void onAttachedToView() {
        super.onAttachedToView();
        switch (style) {
            case CLICK:
                setViewPressed(isPressed);
                mView.setOnClickListener(this);
                break;
            case TOUCH:
                mView.setOnTouchListener(this);
                break;
        }
        reset();
    }

    @Override
    protected void onDetachedFromView() {
        super.onDetachedFromView();
        mView.setOnClickListener(null);
        mView.setOnTouchListener(null);
    }

    /*刷新隐藏状态*/
    void reset() {
        int inputType = getView().getUseInputType();
        if ((inputType & InputType.TYPE_MASK_CLASS) > 0) {
            final int variation = inputType & (InputType.TYPE_MASK_CLASS | InputType.TYPE_MASK_VARIATION);
            final boolean passwordInputType = variation == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            final boolean webPasswordInputType = variation == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
            final boolean numberPasswordInputType = variation == (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            if (passwordInputType || webPasswordInputType) {
                if (isPressed) {
                    getView().setCoverInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else getView().setCoverRestore();
                getView().setSelection(getView().length());
            } else if (numberPasswordInputType) {
                if (isPressed) {
                    getView().setCoverInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                } else getView().setCoverRestore();
                getView().setSelection(getView().length());
            } else {
                if ((inputType & InputType.TYPE_MASK_CLASS) == InputType.TYPE_CLASS_TEXT) {
                    getView().setCoverInputType(isPressed
                                    ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                                               );
                    getView().setSelection(getView().length());
                } else if ((inputType & InputType.TYPE_MASK_CLASS) == InputType.TYPE_CLASS_NUMBER) {
                    getView().setCoverInputType(isPressed
                                    ? InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL
                                    : InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD
                                               );
                    getView().setSelection(getView().length());
                }
            }
        } else {
            getView().setCoverInputType(isPressed
                            ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                                       );
            getView().setSelection(getView().length());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isPressed = true;
            reset();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            isPressed = false;
            reset();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        isPressed = !isPressed;
        setViewPressed(isPressed);
        reset();
    }

    private void setViewPressed(boolean isPressed) {
        if (mView instanceof CheckBox) {
            CheckBox box = (CheckBox) mView;
            box.setChecked(isPressed);
        } else {
            mView.setSelected(isPressed);
        }
    }


    /**
     * 控制模式
     */
    public enum Style {
        /**
         * 该模式下通过点击控制View来切换密码显示状态
         */
        CLICK,
        /**
         * 该模式下当用户手指触摸到View上的时候显示密码，松开则会还原
         */
        TOUCH
    }
}
