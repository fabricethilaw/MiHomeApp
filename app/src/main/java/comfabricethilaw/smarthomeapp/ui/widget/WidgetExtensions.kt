package comfabricethilaw.smarthomeapp.ui.widget

import android.graphics.PointF
import android.view.MotionEvent
import android.view.View

fun View.handleTouchEvent(
    event: MotionEvent,
    executeOnTouchDown: () -> Unit,
    executeOnTouchUp: () -> Unit
): Boolean {

    fun pointInView(point: PointF, view: View): Boolean {
        return view.left <= point.x && point.x <= view.right && view.top <= point.y && point.y <= view.bottom
    }

    return when (event.action) {
        MotionEvent.ACTION_DOWN -> {
            if (pointInView(PointF(event.x, event.y), this)) {
                executeOnTouchDown.invoke()
            }
            true
        }
        MotionEvent.ACTION_MOVE -> {
            if (pointInView(PointF(event.x, event.y), this)) {
              executeOnTouchDown.invoke()
            } else {
                executeOnTouchUp.invoke()
            }
            true
        }
        MotionEvent.ACTION_UP -> {
            executeOnTouchUp.invoke()
            performClick()
            true
        }
        MotionEvent.ACTION_CANCEL -> {
            executeOnTouchUp.invoke()
            true
        }
        else -> false
    }
}