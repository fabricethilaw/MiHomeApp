package comfabricethilaw.mihome.ui.widget

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat


class CheckableFrameLayout : FrameLayout, Checkable {
    private var mChecked = false
    private var mOnCheckedChangeListener: OnCheckedChangeListener? = null

    private val iconView by lazy { findViewById<ImageView>(comfabricethilaw.mihome.R.id.icon) }
    private val title by lazy { findViewById<TextView>(comfabricethilaw.mihome.R.id.title) }


    constructor(context: Context?) : super(context!!) {
        isClickable = true
        isLongClickable = true
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        isClickable = true
        isLongClickable = true
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        isClickable = true
        isLongClickable = true
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(checkableView: View?, isChecked: Boolean)
    }

    override fun isChecked(): Boolean {
        return mChecked
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    override fun setChecked(b: Boolean) {
        if (b != mChecked) {
            mChecked = b
            refreshDrawableState()
            if (mChecked) {
                toggleDownColours()
            } else {
                toggleLiftColours()
            }
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener!!.onCheckedChanged(this, mChecked)
            }
        }
    }

    override fun toggle() {
        isChecked = !mChecked
    }

    public override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }


    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        mOnCheckedChangeListener = listener
    }

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }

    private fun toggleDownColours() {
        title.setTextColor(
            ContextCompat.getColor(
                title.context,
                comfabricethilaw.mihome.R.color.orange
            )
        )
        iconView.backgroundTintList =
            ContextCompat.getColorStateList(
                iconView.context,
                comfabricethilaw.mihome.R.color.orange
            )
        iconView.imageTintList =
            ContextCompat.getColorStateList(iconView.context, comfabricethilaw.mihome.R.color.white)

    }

    private fun toggleLiftColours() {
        title.setTextColor(
            ContextCompat.getColor(
                title.context,
                comfabricethilaw.mihome.R.color.charade
            )
        )
        iconView.backgroundTintList =
            ContextCompat.getColorStateList(
                iconView.context,
                comfabricethilaw.mihome.R.color.gray_94
            )
        iconView.imageTintList =
            ContextCompat.getColorStateList(
                iconView.context,
                comfabricethilaw.mihome.R.color.silver_chalice
            )
    }

    fun setIcon(icon: Int) {
        iconView.setImageDrawable(ContextCompat.getDrawable(context, icon))
    }

    fun setText(name: String) {
        title.text = name
    }
}