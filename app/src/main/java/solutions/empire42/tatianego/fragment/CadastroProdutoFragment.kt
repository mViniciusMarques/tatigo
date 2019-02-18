package solutions.empire42.tatianego.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker

import solutions.empire42.tatianego.R
import java.io.File


class CadastroProdutoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        ImagePicker.with(requireActivity())
            .crop(1f, 1f)                //Crop Square image(Optional)
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(620, 620)    //Final image resolution will be less than 620 x 620(Optional)
            .start()



        return inflater.inflate(R.layout.fragment_cadastro_produto, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            //Image Uri will not be null for RESULT_OK
//            val fileUri = data?.data
//            imgProfile.setImageURI(fileUri)
//
//            //You can get File object from intent
//            val file:File = ImagePicker.getFile(data)
//
//            //You can also get File Path from intent
//            val filePath:String = ImagePicker.getFilePath(data)
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
//        }

    }

    fun tnv(view: View ) {
        ImagePicker.with(requireActivity())
            .galleryOnly()       //User can only select image from Gallery
            .start()
    }

}
