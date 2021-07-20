package comfabricethilaw.mihome.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import comfabricethilaw.mihome.R

class StaticsEntryView : FrameLayout {

    private var _value: Float = 0.0f
    private var _unit: String = ""
    private var _rate: Float = 0.0f
    private var _variation: Int = 0

    private lateinit var valueTv: TextView
    private lateinit var unitTv: TextView
    private lateinit var rateTv: TextView
    private lateinit var icon: ImageView

    private var _iconColor: Int = R.color.blue

    /**
     * The icon color. Not color resource as R.color.my_color
     */
    var iconColor: Int
        get() = _iconColor
        set(value) {
            _iconColor = value
            icon.imageTintList = ContextCompat.getColorStateList(context, _iconColor)
            invalidate()
        }

    var value: Float
        get() = _value
        set(value) {
            _value = value
            val valueText = String.format("%.2f", _value)
            valueTv.text = removeTrailingZero(valueText)
            invalidate()
        }

    var unit: String
        get() = _unit
        set(value) {
            _unit = value
            unitTv.text = unit
            invalidate()
        }

    var variation: Int
        get() = _variation
        set(value) {
            _variation = value.coerceAtMost(1)
            var rateString = String.format("%.2f", rate)
            rateString = removeTrailingZero(rateString)
            val rateText = "${getVariationLabel()} $rateString%"
            rateTv.text = rateText

            updateIconWithVariation()

            invalidate()
        }

    private fun updateIconWithVariation() {
        val angle = if (variation == 0) 0 else 180
        icon.rotation = angle.toFloat()
        iconColor = if (variation == 0) R.color.blue else R.color.rose
    }

    private fun removeTrailingZero(text: String): String {
        return text.run {
            if (this.last() == '0') take(length - 1) else this
        }
    }


    var rate: Float
        get() = _rate
        set(value) {
            _rate = value
            variation = variation
            invalidate()
        }

    private fun getVariationLabel(): String {
        return when (variation) {
            DECREASE -> context.getString(R.string.decrease)
            else -> context.getString(R.string.increase)
        }
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.StaticsEntryView, defStyle, 0
        )

        initializeViewComponents()

        value = a.getFloat(R.styleable.StaticsEntryView_value, 0f)
        unit = a.getString(R.styleable.StaticsEntryView_unit).orEmpty()
        rate = a.getFloat(R.styleable.StaticsEntryView_rate, 0f)
        variation = a.getInt(R.styleable.StaticsEntryView_variation, 0)

        a.recycle()
        invalidate()

    }


    private fun initializeViewComponents() {
        val root: View = inflate(context, R.layout.widget_statics_entry_view, null)

        val container = root.findViewById<ViewGroup>(R.id.container)
        addView(root)

        val params = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        container.layoutParams = params

        valueTv = root.findViewById(R.id.value)
        unitTv = root.findViewById(R.id.unit)
        rateTv = root.findViewById(R.id.rate)
        icon = root.findViewById(R.id.arrow)

        invalidate()
    }


    companion object {
        const val DECREASE = 0
        const val INCREASE = 1
    }

}