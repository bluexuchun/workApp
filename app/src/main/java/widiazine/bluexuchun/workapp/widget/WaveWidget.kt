package widiazine.bluexuchun.workapp.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*

class WaveWidget(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var isMeasured = false
    private var viewHeight = 0f
    private var viewWidth = 0f
    private var levelHeight = 0f
    private var waveHeight = 0f
    private var waveWidth = 0f
    private var mLeftPoints = ArrayList<Point>()
    private var mRightPoints = ArrayList<Point>()
    private var leftPath = Path()
    private var rightPath = Path()
    private var leftTotalLen = 0f
    private var rightTotalLen = 0f
    private var mLeftPaint = Paint()
    private var mRightPaint = Paint()
    var mHandler = android.os.Handler()

    var mTask:Runnable = object :Runnable{
        override fun run() {
            mHandler.postDelayed(this,1000)
        }

    }

    init {
        mLeftPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mLeftPaint.setStyle(Paint.Style.FILL_AND_STROKE)
        mLeftPaint.setColor(Color.parseColor("#fdcc30"))

        mRightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mRightPaint.setStyle(Paint.Style.FILL_AND_STROKE)
        mRightPaint.setColor(Color.parseColor("#ffffff"))

        leftPath = Path()
        rightPath = Path()

        mLeftPoints = ArrayList<Point>()
        mRightPoints = ArrayList<Point>()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if(!isMeasured){
            isMeasured = true
            viewHeight = measuredHeight.toFloat()
            viewWidth = measuredWidth.toFloat()

            levelHeight = viewHeight / 2

            //波的宽度是view的四倍  就是只能看到1/4的波
            waveWidth = viewWidth*1.0f
            //波的高度是view高度的 1/10;
            waveHeight = levelHeight / 3f

            var x = 0f
            var y = 0f
            for (i in 1..9){
                // y的计算
                if(i % 2 == 1){
                    y = levelHeight
                }else{
                    if(i % 4 == 0){
                        y = levelHeight + waveHeight * 2
                    }else{
                        y = levelHeight - waveHeight * 2
                    }
                }

                // x的计算
                x = waveWidth / 4 * (i - 1)

                mLeftPoints.add(Point(x.toInt(),y.toInt()))

            }

            for (i in mLeftPoints){
                var rightPoint = Point((i.x - waveWidth).toInt(),i.y)
                mRightPoints.add(rightPoint)
            }

            Log.d("mLeftPoints","${mLeftPoints}")
            Log.d("mRightPoints","${mRightPoints}")
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        leftPath.reset()//重置path坐标
        leftPath.moveTo(mLeftPoints.get(0).x - leftTotalLen, levelHeight + waveHeight * 3)
        leftPath.lineTo(mLeftPoints.get(0).x - leftTotalLen, levelHeight)
        for (i in 0 until 4) {
            leftPath.quadTo(mLeftPoints.get(1 + 2 * i).x - leftTotalLen, (mLeftPoints.get(1 + 2 * i).y).toFloat(), mLeftPoints.get(2 + 2 * i).x - leftTotalLen, (mLeftPoints.get(2 + 2 * i).y).toFloat())
        }
        leftPath.lineTo(mLeftPoints.get(8).x - leftTotalLen, levelHeight + waveHeight * 3)
        leftPath.close()
        canvas?.drawPath(leftPath, mLeftPaint)

        //另一个
        rightPath.reset()
        rightPath.moveTo(mRightPoints.get(0).x + rightTotalLen, levelHeight + waveHeight * 3)
        rightPath.lineTo(mRightPoints.get(0).x + rightTotalLen, levelHeight)
        for (i in 0 until 4) {
            rightPath.quadTo(mRightPoints.get(1 + 2 * i).x + rightTotalLen, (mRightPoints.get(1 + 2 * i).y).toFloat(),  mRightPoints.get(2 + 2 * i).x + rightTotalLen, (mRightPoints.get(2 + 2 * i).y).toFloat())
        }
        rightPath.lineTo(mRightPoints.get(8).x + rightTotalLen, levelHeight + waveHeight * 3)
        rightPath.close()
        canvas?.drawPath(rightPath, mRightPaint)
    }
}
