package widiazine.bluexuchun.workapp.adapter

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import widiazine.bluexuchun.workapp.R

class UploadDetailAdapter(
    var context:Context,
    var ImageList:MutableList<String>
): BaseAdapter() {


    override fun getItem(position: Int): Any {
        return ImageList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        var count = 0
        if(ImageList == null){
            count = 1
        }else{
            count = ImageList.size + 1
        }

        if(count > 9){
            return ImageList.size
        }else{
            return count
        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewbox = LayoutInflater.from(context).inflate(R.layout.component_image, parent, false)
        var imageBox = viewbox.findViewById<ImageView>(R.id.camera)
        var imageCancel = viewbox.findViewById<ImageView>(R.id.camera_cancel)

        if(position < ImageList.size){
            imageCancel.visibility = View.VISIBLE
            var picUrl = ImageList.get(position)
            Glide.with(context).load(picUrl).into(imageBox)
        }else{
            imageBox.setImageResource(R.mipmap.camera)
        }
        imageCancel.setOnClickListener {
            ImageList.removeAt(position)
            notifyDataSetChanged()
        }
        return viewbox
    }

}