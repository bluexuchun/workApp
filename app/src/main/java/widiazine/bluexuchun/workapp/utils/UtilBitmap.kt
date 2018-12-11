package widiazine.bluexuchun.workapp.utils

import android.content.Context
import android.renderscript.Allocation
import android.renderscript.ScriptIntrinsicBlur
import android.renderscript.RenderScript
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.renderscript.Element
import android.util.Log
import android.widget.ImageView
import android.app.Activity
import android.os.Build
import android.view.View
import java.lang.reflect.Field
import android.view.ViewGroup




class UtilBitmap {
    /**
     * 图片缩放比例
     */
    private val BITMAP_SCALE = 0.2f

    /**
     * 将Drawable对象转化为Bitmap对象
     *
     * @param drawable Drawable对象
     * @return 对应的Bitmap对象
     */
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap: Bitmap

        //如果本身就是BitmapDrawable类型 直接转换即可
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        //取得Drawable固有宽高
        if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            //创建一个1x1像素的单位色图
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        } else {
            //直接设置一下宽高和ARGB
            bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }

        //重新绘制Bitmap
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }


    /**
     * 模糊ImageView
     *
     * @param context
     * @param img     ImageView
     * @param level   模糊等级【0 ~ 25之间】
     */
    fun blurImageView(context: Context, img: ImageView, level: Float) {
        // 将图片处理成模糊
        val bitmap = blurBitmap(context, drawableToBitmap(img.getDrawable()), level)
        if (bitmap != null) {
            img.setImageBitmap(bitmap)
        }
    }

    /**
     * 模糊ImageView
     *
     * @param context
     * @param img     ImageView
     * @param level   模糊等级【0 ~ 25之间】
     * @param color   为ImageView蒙上一层颜色
     */
    fun blurImageView(context: Context, img: ImageView, level: Float, color: Int) {
        // 将图片处理成模糊
        val bitmap = blurBitmap(context, drawableToBitmap(img.getDrawable()), level)
        if (bitmap != null) {
            val drawable = coverColor(context, bitmap, color)
            img.setScaleType(ImageView.ScaleType.FIT_XY)
            img.setImageDrawable(drawable)
        } else {
            img.setImageBitmap(null)
            img.setBackgroundColor(color)
        }
    }

    /**
     * 将bitmap转成蒙上颜色的Drawable
     *
     * @param context
     * @param bitmap
     * @param color   要蒙上的颜色
     * @return Drawable
     */
    fun coverColor(context: Context, bitmap: Bitmap, color: Int): Drawable {
        val paint = Paint()
        paint.setColor(color)
        val rect = RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat())
        Canvas(bitmap).drawRoundRect(rect, 0f, 0f, paint)
        return BitmapDrawable(context.getResources(), bitmap)
    }


    /**
     * 模糊图片的具体方法
     *
     * @param context 上下文对象
     * @param bitmap  需要模糊的图片
     * @return 模糊处理后的图片
     */
    fun blurBitmap(context: Context, bitmap: Bitmap, blurRadius: Float): Bitmap? {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0f
        }
        if (blurRadius > 25) {
            blurRadius = 25f
        }
        var outputBitmap: Bitmap? = null
        try {

            Class.forName("android.renderscript.ScriptIntrinsicBlur")
            // 计算图片缩小后的长宽
            val width = Math.round(bitmap.width * BITMAP_SCALE)
            val height = Math.round(bitmap.height * BITMAP_SCALE)
            if (width < 2 || height < 2) {
                return null
            }

            // 将缩小后的图片做为预渲染的图片。
            val inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
            // 创建一张渲染后的输出图片。
            outputBitmap = Bitmap.createBitmap(inputBitmap)

            // 创建RenderScript内核对象
            val rs = RenderScript.create(context)
            // 创建一个模糊效果的RenderScript的工具对象
            val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

            // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
            // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)

            // 设置渲染的模糊程度, 25f是最大模糊度
            blurScript.setRadius(blurRadius)
            // 设置blurScript对象的输入内存
            blurScript.setInput(tmpIn)
            // 将输出数据保存到输出内存中
            blurScript.forEach(tmpOut)

            // 将数据填充到Allocation中
            tmpOut.copyTo(outputBitmap)

            return outputBitmap
        } catch (e: Exception) {
            Log.e("Bemboy_Error", "Android版本过低")
            return null
        }

    }

    private var barHeight = -1

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return 状态栏高度
     */
    fun getBarHeight(context: Context): Int {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            barHeight = 0
        }

        if (barHeight === -1) {
            var c: Class<*>? = null
            var obj: Any? = null
            var field: Field? = null
            var x = 0

            try {
                c = Class.forName("com.android.internal.R\$dimen")
                obj = c!!.newInstance()
                field = c.getField("status_bar_height")
                x = Integer.parseInt(field!!.get(obj).toString())
                barHeight = context.resources.getDimensionPixelSize(x)

            } catch (e1: Exception) {
                e1.printStackTrace()
                return 0
            }

        }
        return barHeight
    }

    /**
     * 获取屏幕截屏 【不包含状态栏】
     *
     * @param activity
     * @param containTopBar 是否包含状态栏
     * @return
     */
    fun getScreenshot(activity: Activity, containTopBar: Boolean): Bitmap? {
        try {
            val window = activity.window
            val view = window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache(true)
            val bmp1 = view.drawingCache
            /**
             * 除去状态栏和标题栏
             */
            val height = if (containTopBar) 0 else getBarHeight(activity)
            return Bitmap.createBitmap(bmp1, 0, height, bmp1.width, bmp1.height - height)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 获取Activity截图
     *
     * @param activity
     * @return bitmap 截图
     */
    fun getDrawing(activity: Activity): Bitmap? {
        val view = (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
        return getDrawing(view)
    }

    fun getDrawing(view: View): Bitmap? {
        try {
            view.isDrawingCacheEnabled = true
            var tBitmap = view.drawingCache
            // 拷贝图片，否则在setDrawingCacheEnabled(false)以后该图片会被释放掉
            tBitmap = Bitmap.createBitmap(tBitmap)
            view.isDrawingCacheEnabled = false
            return tBitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }


}