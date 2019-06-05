package support.lfp.edittextcontrolled;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;
import java.util.List;

import support.lfp.edittextcontrolled.processor.PasswordVisibilityProcessor;


/**
 * <pre>
 * Tip:
 *     可被控制的EditTextView，通过添加处理器来控制该EditTextView
 *
 *
 * Function:
 *      addValueProcessor()                 :添加数据处理器
 *      removeValueProcessor()              :移除数据处理器
 *      setOnEditTextValueChangeListener()  :设置数据变化监听
 *      isEmptyValue()                      :判断数据是否为空
 *      isEmptyHint()                       :判断Hint是否为空
 *      getValue()                          :获得字符串数据
 *      getDoubleValue()                    :获得Double数据
 *      getIntValue()                       :获得Int数据
 *      getFloatValue()                     :获得Float数据
 *      getLongValue()                      :获得Long数据
 *      setValue()                          :设置数据
 *      setPasswordVisibilityControlView()  :设置密码可见度控制器
 *
 * Created by LiFuPing on 2018/12/5 11:18
 * </pre>
 */
public class EditTextControlView extends AppCompatEditText {
    /*数据处理器*/
    private List<EditTextProcessor> mProcessor;
    private OnEditTextValueChangeListener mOnEditTextValueChangeListener;
    private int mInputType; //保存用户设置类型设置 - 不同类型的密码切换方式不同
    private boolean mIsTextViewStyle = false;

    public EditTextControlView(Context context) {
        super(context);
        init();
    }

    public EditTextControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        super.addTextChangedListener(mTextWatcher);

        mProcessor = new ArrayList<>();
        mInputType = getInputType();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);

    }

    /**
     * 设置为TextView样式,不允许编辑可点击
     *
     * @param isTextView 是否为TextView样式
     */
    public void setTextViewStyle(boolean isTextView) {
        if (isTextView) {
            setCursorVisible(false);
            setFocusable(false);
            setFocusableInTouchMode(false);
        } else {
            setFocusable(true);
            setCursorVisible(true);
            setFocusableInTouchMode(true);
            requestFocus();
        }
        mIsTextViewStyle = isTextView;
    }

    /**
     * 判断是否为TextView模式,可以通过setTextViewStyle方法来改变他
     *
     * @return true:是  false:否
     */
    public boolean isTextViewStyle() {
        return mIsTextViewStyle;
    }

    @Override
    public void setInputType(int type) {
        super.setInputType(type);
        mInputType = type;
    }

    /**
     * 覆盖用户设置InputType类型
     *
     * @param type
     */
    public void setCoverInputType(int type) {
        super.setInputType(type);
    }

    /**
     * 还原被覆盖的InputTYpe类型
     */
    public void setCoverRestore() {
        super.setInputType(getUseInputType());
    }

    /**
     * 获得用户设置输入类型设置
     */
    public int getUseInputType() {
        return mInputType;
    }

    /**
     * 设置数据处理器
     *
     * @param processor 数据处理器
     */
    public void addProcessor(EditTextProcessor processor) {
        if (processor == null) return;
        mProcessor.add(processor);
        processor.additionView(this);
    }

    /**
     * 移除数据处理器
     *
     * @param processor 数据处理器
     */
    public void removeProcessor(EditTextProcessor processor) {
        if (processor == null) return;
        processor.clear();
        mProcessor.remove(processor);

    }

    /**
     * 设置数据变化监听,能正确的获得最终结果
     *
     * @param l The listener
     */
    public void setOnEditTextValueChangeListener(OnEditTextValueChangeListener l) {
        mOnEditTextValueChangeListener = l;
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        CharSequence beforeText_atchange;
        CharSequence beforeText;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (beforeText_atchange == null) beforeText_atchange = s;
            beforeText = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            for (int i = 0; i < mProcessor.size(); i++) {
                final EditTextProcessor editTextProcessor = mProcessor.get(i);
                if (editTextProcessor instanceof ValueProcessor) {
                    ValueProcessor processor = (ValueProcessor) editTextProcessor;
                    if (processor.isIntercept(beforeText, s)) {
                        processor.onProcessor(beforeText, s);
                        return;
                    }
                }
            }
            if (mOnEditTextValueChangeListener != null) {
                beforeText_atchange = null;
                mOnEditTextValueChangeListener.onChange(beforeText_atchange, s);
            }

        }
    };

    /**
     * 获得输入框的内容
     */
    public String getValue() {
        final String s = getText().toString();
        if (TextUtils.isEmpty(s)) return "";
        else return s;
    }

    /**
     * 判断输入框内容是否为空
     */
    public boolean isEmptyValue() {
        return TextUtils.isEmpty(getValue());
    }

    /**
     * 判断Hint内容是否为空
     */
    public boolean isEmptyHint() {
        return TextUtils.isEmpty(getHint());
    }

    /**
     * 获得输入框内容并转为Double类型
     */
    public double getDoubleValue() throws NumberFormatException {
        if (isEmptyValue()) return 0d;
        return Double.parseDouble(getValue());
    }

    /**
     * 获得输入框内容并转为Int类型
     */
    public int getIntValue() throws NumberFormatException {
        if (isEmptyValue()) return 0;
        return Integer.parseInt(getValue());
    }

    /**
     * 获得输入框内容并转为Float类型
     */
    public float getFloatValue() throws NumberFormatException {
        if (isEmptyValue()) return 0f;
        return Float.parseFloat(getValue());
    }

    /**
     * 获得输入框内容并转为Long类型
     */
    public long getLongValue() throws NumberFormatException {
        if (isEmptyValue()) return 0l;
        return Long.parseLong(getValue());
    }

    /**
     * 设置CharSequence类型值
     *
     * @param value The value
     */
    public void setValue(CharSequence value) {
        setText(value);
    }

    /**
     * 设置Int类型值
     *
     * @param value The value
     */
    public void setValue(int value) {
        setValue(String.valueOf(value));
    }

    /**
     * 设置Long类型值
     *
     * @param value The value
     */
    public void setValue(long value) {
        setValue(String.valueOf(value));
    }

    /**
     * 设置Float类型值
     *
     * @param value The value
     */
    public void setValue(float value) {
        setValue(String.valueOf(value));
    }

    /**
     * 设置Double类型值
     *
     * @param value The value
     */
    public void setValue(double value) {
        setValue(String.valueOf(value));
    }


    /**
     * 设置密码可见度控制器,当View被触摸的时候显示密码,抬起的时候隐藏密码
     *
     * @param view 用来控制这个EditText的View
     */
    public void setPasswordVisibilityControlView(@NonNull final View view) {
        setPasswordVisibilityControlView(view, PasswordVisibilityProcessor.Style.CLICK);
    }

    /**
     * 设置密码可见度控制器,当View被触摸的时候显示密码,抬起的时候隐藏密码
     *
     * @param view  用来控制这个EditText的View
     * @param style 用来控制这个EditText的View
     */
    public void setPasswordVisibilityControlView(@NonNull final View view, PasswordVisibilityProcessor.Style style) {
        EditTextProcessor processor = new PasswordVisibilityProcessor(view, style);
        processor.additionView(this);
    }


}
