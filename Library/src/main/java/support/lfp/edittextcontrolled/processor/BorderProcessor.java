package support.lfp.edittextcontrolled.processor;


import android.text.Editable;

import support.lfp.edittextcontrolled.EditTextControlView;
import support.lfp.edittextcontrolled.ValueProcessor;

/**
 * <pre>
 * Tip:
 *      边界处理器，处理最大最小值
 *
 * Function:
 *
 * Created by LiFuPing on 2018/12/5 17:14
 * </pre>
 */
public abstract class BorderProcessor<T extends Number> extends ValueProcessor {
    T mMax, mMin;
    OnOutBorderListener<T> mOnOutBorderListener;

    public BorderProcessor(T min, T max) {
        setMin(min);
        setMax(max);
    }

    /**
     * 设置最大限制值
     */
    public void setMax(T max) {
        mMax = max;
    }

    /**
     * 设置最小限制值
     */
    public void setMin(T min) {
        mMin = min;
    }

    /**
     * 获得最大限制值
     */
    public T getMax() {
        return mMax;
    }

    /**
     * 获得最小限制值
     */
    public T getMin() {
        return mMin;
    }

    protected boolean onIntercept(EditTextControlView v, CharSequence beforeText, Editable text) {
        boolean isIntercept = isIntercept(v, beforeText, text);
        if (isIntercept && mOnOutBorderListener != null) {
            return mOnOutBorderListener.onoutBorder(this, getValue(text));
        }
        return isIntercept;
    }

    protected abstract boolean isIntercept(EditTextControlView v, CharSequence beforeText, Editable text);

    /**
     * 获得内容的值
     *
     * @param text 内容
     */
    protected abstract T getValue(Editable text);

    /**
     * 范围处理器监听
     */
    public void setOnOutBorderListener(OnOutBorderListener<T> l) {
        mOnOutBorderListener = l;
    }

    /**
     * 输入值超出限制
     */
    public interface OnOutBorderListener<T> {
        /**
         * 当输入值超过限制
         *
         * @param processor 该限制触发的范围处理器
         * @param value     当前值
         * @return 是否拦截, 如果不拦截则会跳过该处理器
         */
        boolean onoutBorder(BorderProcessor processor, T value);
    }
}
