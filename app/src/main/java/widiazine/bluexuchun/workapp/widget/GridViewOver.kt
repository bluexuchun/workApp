package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView

class GridViewOver @JvmOverloads constructor(context: Context?, attrs: AttributeSet?) : GridView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var expandSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2,MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)

    }
}