package com.example.customlistview3

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityTwo : AppCompatActivity(), Removable, Updateble {

    val GALERRY_REQUEST = 1
    var product:Product? = null
    var item: Int? = null
    var photoUri: Uri? = null
    var ListAdapter : ListAdapter?=null
    val products: MutableList<Product> = mutableListOf()
    var check = true

    private lateinit var toolbar: Toolbar
    private lateinit var listViewLW: ListView
    private lateinit var saveBTN: Button
    private lateinit var productPriceET: EditText
    private lateinit var productNameET: EditText
    private lateinit var editImageIV: ImageView
    private lateinit var productDescriptionET: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        init()
        title = "Корзина с продуктами"
        setSupportActionBar(toolbar)


        editImageIV.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALERRY_REQUEST)
        }
        saveBTN.setOnClickListener {
            createProduct()
            ListAdapter = ListAdapter(this@ActivityTwo, products)
            listViewLW.adapter = ListAdapter
            ListAdapter?.notifyDataSetChanged()
            clearEditField()
            ListAdapter?.notifyDataSetChanged()
        }

        listViewLW.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val product = ListAdapter!!.getItem(position)
                item = position
                var dialog = MyAlertDialog()
                var args = Bundle()
                args.putSerializable("product", product)
                dialog.arguments = args
                dialog.show(supportFragmentManager, "custom")

            }
    }

    private fun init() {
        listViewLW = findViewById(R.id.listViewLW)
        saveBTN = findViewById(R.id.saveBTN)
        productNameET = findViewById(R.id.productNameET)
        productPriceET = findViewById(R.id.productPriceET)
        editImageIV = findViewById(R.id.editImageIV)
        productDescriptionET = findViewById(R.id.productDescriptionET)

        toolbar = findViewById(R.id.toolbar)
    }


    private fun createProduct() {
        val productName = productNameET.text.toString()
        val productPrice = productPriceET.text.toString()
        val productimage = photoUri.toString()
        val descriptionimage = productDescriptionET.text.toString()
        product = Product(productName, productPrice,  descriptionimage, productimage,)
        products.add(product!!)
        clearEditField()
        photoUri=null
    }

    private fun clearEditField() {
        productNameET.text.clear()
        productPriceET.text.clear()
        productDescriptionET.text.clear()
        editImageIV.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALERRY_REQUEST -> if (resultCode == RESULT_OK) {
                photoUri = data?.data
                editImageIV.setImageURI(photoUri)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_exit -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun remove(product: Product) {
        ListAdapter?.remove(product)
    }

    override fun update(product: Product) {

        val intent = Intent(this@ActivityTwo, ActivityThree::class.java)
        intent.putExtra("product", product)
        intent.putExtra("products", this.products as ArrayList<Product>)
        intent.putExtra("position", item)
        intent.putExtra("check", check)
        startActivity(intent)
    }

}