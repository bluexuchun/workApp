package widiazine.bluexuchun.workapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.header.*
import widiazine.bluexuchun.workapp.R
import android.graphics.BitmapFactory
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.model.ResponseCommon
import widiazine.bluexuchun.workapp.model.ResponseModel
import widiazine.bluexuchun.workapp.model.db.DbControl
import widiazine.bluexuchun.workapp.model.db.UserTables
import widiazine.bluexuchun.workapp.utils.Urls
import java.io.File


class InfoActivity:BaseActivity(){

    val RESULT_LOAD_IMAGE = 1
    val RESULT_TAKE_PHOTO = 2

    override fun getLayoutResId(): Int {
        return R.layout.activity_info
    }

    override fun init() {
        super.init()
        setImgsBar()
        header_title.text = "信息填写"
        header_back.visibility = View.VISIBLE

        var viewAry = listOf<View>(username,school)
        infoView.setOnTouchListener { v, event ->
            isTouchView(event,viewAry)
            true
        }
        userava.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,RESULT_LOAD_IMAGE)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_LOAD_IMAGE -> {
                var selectedImage = data!!.data
                var filePathColumns = arrayOf(MediaStore.Images.Media.DATA)
                var cursor = contentResolver.query(selectedImage,filePathColumns,null,null,null)
                cursor.moveToFirst()
                var columnIndex = cursor.getColumnIndex(filePathColumns[0])
                var picturePath = cursor.getString(columnIndex)
                cursor.close()
                setImageBitmap(picturePath)
                Log.v("picturePath","${File(picturePath)}")

                var url = "${Urls().SERVER}api.php?entry=app&c=upload&a=upload&do=upload"
                OkGo.post<String>(url)
                    .tag(this)
                    .params("filename", File(picturePath))
                    .execute(object : StringCallback(){
                        override fun onSuccess(response: Response<String>?) {
                            var gson = Gson()
                            var responseData = gson.fromJson(response?.body(),ResponseCommon::class.java)
                            if(responseData.status == 1){
                                longToast("东波臭臭${responseData.data}")
                            }else{
                                toast("东波臭臭")
                            }
                        }

                        override fun onError(response: Response<String>?) {
                            super.onError(response)
                        }
                    })
            }
        }
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    private fun setImageBitmap(photoPath:String) {
        //获取imageview的宽和高
        val targetWidth = userava.getWidth()
        val targetHeight = userava.getHeight()

        //根据图片路径，获取bitmap的宽和高
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(photoPath, options)
        val photoWidth = options.outWidth
        val photoHeight = options.outHeight

        //获取缩放比例
        var inSampleSize = 1
        if (photoWidth > targetWidth || photoHeight > targetHeight) {
            val widthRatio = Math.round(photoWidth.toFloat() / targetWidth)
            val heightRatio = Math.round(photoHeight.toFloat() / targetHeight)
            inSampleSize = Math.min(widthRatio, heightRatio)
        }

        //使用现在的options获取Bitmap
        options.inSampleSize = inSampleSize
        options.inJustDecodeBounds = false
        val bitmap = BitmapFactory.decodeFile(photoPath, options)
        userava.setImageBitmap(bitmap)
    }

}