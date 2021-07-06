package comfabricethilaw.smarthomeapp.ui.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import comfabricethilaw.smarthomeapp.R

class TouchableConstraintLayout : ConstraintLayout {

    private var parentView: ViewGroup? = null
    private var rect: Rect? = null
    private val iconView by lazy { findViewById<ImageView>(R.id.icon) }
    private val title by lazy { findViewById<TextView>(R.id.title) }


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun provideParentView(parent: ViewGroup) {
        this.parentView = parent
    }

    override fun performClick(): Boolean {
        Toast.makeText(context, "CLicked", Toast.LENGTH_SHORT).show()
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return handleTouchEvent(
            event, executeOnTouchDown = { toggleDownColours() },
            executeOnTouchUp = { toggleLiftColours() }
        )
    }

    private fun toggleDownColours() {
        title.setTextColor(ContextCompat.getColor(title.context, R.color.orange))
        iconView.backgroundTintList =
            ContextCompat.getColorStateList(iconView.context, R.color.orange)
        iconView.imageTintList = ContextCompat.getColorStateList(iconView.context, R.color.white)

    }

    private fun toggleLiftColours() {
        title.setTextColor(ContextCompat.getColor(title.context, R.color.charade))
        iconView.backgroundTintList =
            ContextCompat.getColorStateList(iconView.context, R.color.gray_94)
        iconView.imageTintList =
            ContextCompat.getColorStateList(iconView.context, R.color.silver_chalice)
    }

    fun setIcon(icon: Int) {
        iconView.setImageDrawable(ContextCompat.getDrawable(context, icon))
    }

    fun setText(name: String) {
        title.text = name
    }

}
