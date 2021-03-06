package comfabricethilaw.mihome.ui.widget

import android.content.Context
import android.graphics.Rect
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getColorStateList
import com.google.android.material.card.MaterialCardView
import comfabricethilaw.mihome.R


class TouchableCardView : MaterialCardView {

    private var parentView: ViewGroup? = null
    private var rect: Rect? = null

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
        return super.performClick()
        // Toast.makeText(context, "CLicked", Toast.LENGTH_SHORT).show()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return handleTouchEvent(
            event, executeOnTouchDown = { toggleDownColours() },
            executeOnTouchUp = { toggleLiftColours() }
        )
    }


    private fun toggleDownColours() {
        parentView?.let {
            TransitionManager.beginDelayedTransition(it)
        }

        setCardBackgroundColor(getColor(context, R.color.orange))

        findViewById<ImageView>(R.id.icon).imageTintList =
            getColorStateList(context, R.color.white)

        findViewById<TextView>(R.id.title).setTextColor(
            getColor(context, R.color.white)
        )
        findViewById<TextView>(R.id.subtitle).setTextColor(
            getColor(context, R.color.white_a60)
        )
    }

    private fun toggleLiftColours() {
        setCardBackgroundColor(getColor(context, R.color.white))
        findViewById<ImageView>(R.id.icon).imageTintList =
            getColorStateList(context, R.color.orange)
        findViewById<TextView>(R.id.title).setTextColor(
            getColor(
                context,
                R.color.charade
            )
        )

        findViewById<TextView>(R.id.subtitle).setTextColor(
            getColor(context, R.color.charade_20)
        )
    }

}

