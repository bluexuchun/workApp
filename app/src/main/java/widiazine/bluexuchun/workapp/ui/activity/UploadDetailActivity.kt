package widiazine.bluexuchun.workapp.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_uploaddetail.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import widiazine.bluexuchun.workapp.R
import widiazine.bluexuchun.workapp.adapter.UploadDetailAdapter

class UploadDetailActivity: BaseActivity(){

    var rxPermissions = RxPermissions(this)

    var uploadadapter:UploadDetailAdapter? = null

    var ImgLists = mutableListOf<String>()

    override fun getLayoutResId(): Int {
        return R.layout.activity_uploaddetail
    }

    override fun specialSit(): Boolean = false

    override fun isBaseOnWidth(): Boolean = false

    override fun getSizeInDp(): Float = 667F

    override fun init() {
        super.init()
        setStatusBar(window,1, Color.WHITE)
        setFragmentBar(Color.WHITE, Color.BLACK,"上传")
        header_black_back.visibility = View.VISIBLE
        header_black_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out)
        }
        uploadadapter = UploadDetailAdapter(this,ImgLists)
        udgirdview.adapter = uploadadapter
        udgirdview.setOnItemClickListener(object:AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == parent!!.count - 1){
                    requestPermission()
                    rxPermissions
                        .request(Manifest.permission.CAMERA)
                        .subscribe{
                            if(it){
                                // 权限通过
                                PictureSelector.create(this@UploadDetailActivity)
                                    .openGallery(PictureMimeType.ofImage())
                                    .forResult(PictureConfig.CHOOSE_REQUEST)
                            }else{
                                // 权限拒绝并再也不显示
                                toast("Camera Permission Denied")
                            }
                        }

                }
            }

        })
    }

    // 处理选择的照片的地址
    private fun refreshAdapter(imgList:MutableList<LocalMedia>){
        for (i in 0..imgList.size-1){
            var compressPath = imgList[i].path
            if(ImgLists.size > 8){
                toast("图片不能超过10条")
            }else{
                ImgLists.add(compressPath)
            }
            uploadadapter?.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                PictureConfig.CHOOSE_REQUEST -> {
                    var imgList = PictureSelector.obtainMultipleResult(data)
                    refreshAdapter(imgList)
                }
            }
        }
    }

    fun requestPermission() {
        val checkSelfPermission = ContextCompat.checkSelfPermission(this@UploadDetailActivity,
            Manifest.permission.CAMERA)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //todo :has ready get permission write your code here
//            toast("allow")
        } else {
            //requset permission
            ActivityCompat.requestPermissions(this@UploadDetailActivity,
                arrayOf(Manifest.permission.CAMERA), 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && permissions[0] == Manifest.permission.CAMERA) {
                //todo:permission granted
                Toast.makeText(this@UploadDetailActivity,"permission granted",Toast.LENGTH_SHORT).show()
            } else{
                //todo:permission denied
                Toast.makeText(this@UploadDetailActivity,"permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }


}
